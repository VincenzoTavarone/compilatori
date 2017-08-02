/* JFlex example: part of Java language lexer specification */
import java_cup.runtime.*;
/**
* This class is a simple example lexer.
*/
%%
%class Lexer
%unicode
%line
%column
%{
// Sezione personalizzata
StringBuffer string = new StringBuffer();
private Yytoken symbol(int type) {
return new Yytoken(type, yyline, yycolumn);
}
private Yytoken symbol(int type, Object value) {
return new Yytoken(type, yyline, yycolumn, value);
}
%}
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]
/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent = ( [^*] | \*+ [^/*] )*
Identifier = [:jletter:] [:jletterdigit:]*
DecIntegerLiteral = 0 | [1-9][0-9]*
%state STRING
%state Comment
%%
/* keywords */
<YYINITIAL> "abstract" { return symbol(Symbols.ABSTRACT); }
<YYINITIAL> "final" { return symbol(Symbols.FINAL); }
<YYINITIAL> "boolean" { return symbol(Symbols.BOOLEAN); }
<YYINITIAL> "int" { return symbol(Symbols.INTEGER); }
<YYINITIAL> "break" { return symbol(Symbols.BREAK); }
<YYINITIAL> "if" { return symbol(Symbols.IF); }
<YYINITIAL> "then" { return symbol(Symbols.THEN); }
<YYINITIAL> "else" { return symbol(Symbols.ELSE); }
<YYINITIAL> {
/* identifiers */
{Identifier} { return symbol(Symbols.IDENTIFIER); }
/* literals */
{DecIntegerLiteral} { return symbol(Symbols.INTEGER_LITERAL); }
\" { string.setLength(0); yybegin(STRING); }
/* operators */
"=" { return symbol(Symbols.EQ); }
"==" { return symbol(Symbols.EQEQ); }
"+" { return symbol(Symbols.PLUS); }
"<" { return symbol(Symbols.REOP,"LT"); }
">" { return symbol(Symbols.REOP,"GT"); }
"<=" { return symbol(Symbols.REOP,"LE"); }
">=" { return symbol(Symbols.REOP,"GE"); }
"<>" { return symbol(Symbols.REOP,"NE"); }
/* comments */
{Comment} { /* ignore */ }
/* whitespace */
{WhiteSpace} { /* ignore */ }
}
<STRING> {
\" { yybegin(YYINITIAL);
return symbol(Symbols.STRING_LITERAL,
string.toString()); }
[^\n\r\"\\]+ { string.append( yytext() ); }
\\t { string.append('\t'); }
\\n { string.append('\n'); }
\\r { string.append('\r'); }
\\\" { string.append('\"'); }
\\ { string.append('\\'); }
}
<STRING,Comment> <<EOF>>  {throw new Error("Rilevato EOF: "+string.toString());}
/* error fallback */
[^] { throw new Error("Illegal character <"+
yytext()+">"); }
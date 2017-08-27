/* JFlex example: partial Java language lexer specification */
import java_cup.runtime.*;

/**
* This class is a simple example lexer.
*/
%%

%class Lexer
%unicode
%cup
%line
%column

%{
StringBuffer string = new StringBuffer();
TableOfSymbols table = new TableOfSymbols();

private Symbol symbol(int type) {
return new Symbol(type, yyline, yycolumn);
}
private Symbol symbol(int type, Object value) {
return new Symbol(type, yyline, yycolumn, value);
}
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*

%state STRING
%state Comment
%%

/* keywords */
<YYINITIAL> "abstract"           { return symbol(sym.ABSTRACT); }
<YYINITIAL> "boolean"            { return symbol(sym.BOOLEAN); }
<YYINITIAL> "break"              { return symbol(sym.BREAK); }
<YYINITIAL> "if" { return symbol(sym.IF); }
<YYINITIAL> "then" { return symbol(sym.THEN); }
<YYINITIAL> "else" { return symbol(sym.ELSE); }

<YYINITIAL> {
/* identifiers */ 
{Identifier}                   {
								 table.add(symbol(sym.IDENTIFIER, yytext()));
								return symbol(sym.IDENTIFIER); 
								}

/* literals */
{DecIntegerLiteral}            { table.add(symbol(sym.INTEGER_LITERAL, yytext())); 
									return symbol(sym.INTEGER_LITERAL); }
\"                             { string.setLength(0); yybegin(STRING); }

/* operators */
"="                            { return symbol(sym.EQ); }
"=="                           { return symbol(sym.EQEQ); }
"+"                            { return symbol(sym.PLUS); }
"<" 							{ return symbol(sym.REOP,"LT"); }
">" 							{ return symbol(sym.REOP,"GT"); }
"<=" 							{ return symbol(sym.REOP,"LE"); }
">=" 							{ return symbol(sym.REOP,"GE"); }
"<>"							{ return symbol(sym.REOP,"NE"); }

/* comments */
{Comment}                      { /* ignore */ }

/* whitespace */
{WhiteSpace}                   { /* ignore */ }
}

<STRING> {
\"                             { yybegin(YYINITIAL); 
								table.add(symbol(sym.STRING_LITERAL, yytext())); 
                               return symbol(sym.STRING_LITERAL, 
                               string.toString()); }
[^\n\r\"\\]+                   { string.append( yytext() ); }
\\t                            { string.append('\t'); }
\\n                            { string.append('\n'); }

\\r                            { string.append('\r'); }
\\\"                           { string.append('\"'); }
\\                             { string.append('\\'); }
}
<STRING,Comment>	<<EOF>>		{throw new Error("Rilevato EOF: "+string.toString());}
/* error fallback */
[^]				{ throw new Error("Illegal character <"+yytext()+">"); }

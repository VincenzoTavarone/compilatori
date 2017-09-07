package esercizio4;
import java_cup.runtime.*;

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

public TableOfSymbols getTable(){
return table;
}

%}

LineTerminator = \r|\n|\r\n

WhiteSpace     = {LineTerminator} | [ \t\f]

digit = [0-9]

letter = [a-zA-Z]

letterOrDigit = {letter} | {digit}

identifier = {letter} {letterOrDigit}*

integer_constant = 0 | [1-9] {digit}*

character_constant = '[^>]'

instruction_separator =	";"

end_program = "."

separator = ","

lpar = "("

rpar = ")"

assign = ":="

unary_minus = ":-"

//multiplying_operator = "*" | "/" | "&&"

//adding_operator = "+" | "-" | "||"

//relational_operator = "==" | "!=" | "<" | "<=" | ">=" | ">"

not = "!"

read = "<-"

write = "->"

%state STRING

%%

<YYINITIAL> "integer"	{ return symbol(sym.INTEGER); }
<YYINITIAL> "boolean"	{ return symbol(sym.BOOLEAN); }
<YYINITIAL> "if"		{ return symbol(sym.IF); }
<YYINITIAL> "then"		{ return symbol(sym.THEN); }
<YYINITIAL> "else"		{ return symbol(sym.ELSE); }
<YYINITIAL> "while"		{ return symbol(sym.WHILE); }
<YYINITIAL> "do"		{ return symbol(sym.DO); }
<YYINITIAL> "begin"		{ return symbol(sym.BEGIN); }
<YYINITIAL> "var"		{ return symbol(sym.VAR); }
<YYINITIAL> "procedure"	{ return symbol(sym.PROCEDURE); }
<YYINITIAL> "program"	{ return symbol(sym.PROGRAM); }
<YYINITIAL> "true"		{ return symbol(sym.TRUE); }
<YYINITIAL> "false"		{ return symbol(sym.FALSE); }
<YYINITIAL> "end"		{ return symbol(sym.END); }

<YYINITIAL> {

	{identifier}	{ table.add(symbol(sym.IDENTIFIER, yytext())); return symbol(sym.IDENTIFIER); }
	
	{integer_constant}	{ table.add(symbol(sym.INTEGER_CONSTANT, yytext())); return symbol(sym.INTEGER_CONSTANT); }
	
	\"	{string.setLength(0); yybegin(STRING); }
	
	/*operatori*/
	{assign}		{return symbol(sym.ASSIGN); }
	{unary_minus}	{return symbol(sym.UNARY_MINUS); }
	{not}			{return symbol(sym.NOT); }
	{read}			{return symbol(sym.READ); }	
	{write}			{return symbol(sym.WRITE); }
	
	/*adding_operator*/
	"+"		{return symbol(sym.ADDING_OPERATOR, "PLUS"); }
	"-"		{return symbol(sym.ADDING_OPERATOR, "MINUS"); }
	"||"	{return symbol(sym.ADDING_OPERATOR, "OR"); }
	
	/*relational_operator*/
	"=="	{return symbol(sym.RELATIONAL_OPERATOR, "EQEQ"); }
	"!="	{return symbol(sym.RELATIONAL_OPERATOR, "NE"); }
	"<"		{return symbol(sym.RELATIONAL_OPERATOR, "LT"); }
	"<="	{return symbol(sym.RELATIONAL_OPERATOR, "LE"); }
	">"		{return symbol(sym.RELATIONAL_OPERATOR, "GT"); }
	">="	{return symbol(sym.RELATIONAL_OPERATOR, "GE"); }
	
	/*multiplying_operator*/
	"*"		{return symbol(sym.MULTIPLYING_OPERATOR, "MUL"); }
	"/"		{return symbol(sym.MULTIPLYING_OPERATOR, "DIV"); }
	"&&"	{return symbol(sym.MULTIPLYING_OPERATOR, "AND"); }
	
	/*separatori*/
	{instruction_separator}		{return symbol(sym.INSTRUCTION_SEPARATOR); }
	{end_program}				{return symbol(sym.END_PROGRAM); }
	{separator}					{return symbol(sym.SEPARATOR); }
	
	/*parentesi*/
	{lpar}		{return symbol(sym.LPAR); }
	{rpar}		{return symbol(sym.RPAR); }
	
	/*white space*/
	{WhiteSpace}	{/*ignore*/}
	
	{character_constant}	{ table.add(symbol(sym.CHARACTER_CONSTANT, yytext())); return symbol(sym.CHARACTER_CONSTANT); }
}

<STRING> {
	\"						{ table.add(symbol(sym.STRING_CONSTANT, yytext())); yybegin(YYINITIAL); return symbol(sym.STRING_CONSTANT, string.toString()); }
	([^\n>])				{ string.append( yytext() ); }
}
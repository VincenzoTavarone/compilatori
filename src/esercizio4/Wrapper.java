package esercizio4;

public class Wrapper {
	
	private static String[] sym_name = {
			"",
			"WRITE",
			"RELATIONAL_OPERATOR",
			"END_PROGRAM",
			"STRING",
			"MULTIPLYING_OPERATOR",
			"ADDING_OPERATOR",
			"LPAR",
			"INSTRUCTION_SEPARATOR",
			"RPAR",
			"BEGIN",
			"NOT",
			"ASSIGN",
			"CHARACTER_CONSTANT",
			"TRUE",
			"DO",
			"SEPARATOR",
			"ELSE",
			"IDENTIFIER",
			"THEN",
			"VAR",
			"WHILE",
			"PROCEDURE",
			"IF",
			"INTEGER",
			"INTEGER_CONSTANT",
			"UNARY_MINUS",
			"BOOLEAN",
			"READ",
			"FALSE",
			"PROGRAM",
			"EOF",
			"END"
	};
	
	public static String getClassName(int sym){
		return sym_name[sym];
	}

}

package esercizio5;

public class Wrapper {
	
	private static String[] sym_name = {
			"EOF",
			"error",
			"LPAR",
			"RPAR",
			"MINUS",
			"PROGRAM",
			"IDENTIFIER",
			"INSTRUCTION_SEPARATOR",
			"END_PROGRAM",
			"VAR",
			"PROCEDURE",
			"INTEGER",
			"BOOLEAN",
			"BEGIN",
			"WRITE",
			"READ",
			"IF",
			"THEN",
			"WHILE",
			"DO",
			"END",
			"ASSIGN",
			"RELATIONAL_OPERATOR",
			"ADDING_OPERATOR",
			"MULTIPLYING_OPERATOR",
			"NOT",
			"TRUE",
			"FALSE",
			"SEPARATOR",
			"ELSE",
			"INTEGER_CONSTANT",
			"STRING_CONSTANT",
			"CHARACTER_CONSTANT",
			"VARIN"
	};
	
	public static String getClassName(int sym){
		return sym_name[sym];
	}

}

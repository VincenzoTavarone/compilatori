package esercizio2_1;

public class Wrapper {
	
	private static String[] sym_name = {
			"",
			"IDENTIFIER",
			"INTEGER_LITERAL",
			"EQ",
			"PLUS",
			"STRING_LITERAL",
			"EQEQ",
			"BREAK",
			"BOOLEAN",
			"ABSTRACT",
			"REOP",
			"THEN",
			"ELSE",
			"IF",
			"EOF"
	};
	
	public static String getClassName(int sym){
		return sym_name[sym];
	}

}

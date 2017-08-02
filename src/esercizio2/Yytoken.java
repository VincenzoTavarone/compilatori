package esercizio2;

public class Yytoken {
	
	private int type;
	private Object value;
	private int yyline;
	private int yycolumn;
	private static String[] typeName = {
		"",
		"REOP",
		"IDENTIFIER",
		"INTEGER_LITERAL",
		"EQ",
		"PLUS",
		"IF",
		"EQEQ",
		"INTEGER",
		"THEN",
		"ELSE",
		"BREAK",
		"FINAL",
		"BOOLEAN",
		"ABSTRACT",
		"STRING_LITERAL"
	};

	public Yytoken(int type, int yyline, int yycolumn) {
		this.type = type;
		this.yycolumn = yycolumn;
		this.yyline = yyline;
	}

	public Yytoken(int type, int yyline, int yycolumn, Object value) {
		this.type = type;
		this.yycolumn = yycolumn;
		this.yyline = yyline;
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getYyline() {
		return yyline;
	}

	public void setYyline(int yyline) {
		this.yyline = yyline;
	}

	public int getYycolumn() {
		return yycolumn;
	}

	public void setYycolumn(int yycolumn) {
		this.yycolumn = yycolumn;
	}
	
	public String toString() {
		return (this.value != null) ? "Yytoken : { type : "+typeName[type]+", value : "+value+"}": "Yytoken : { type : "+typeName[type]+"}";
	}
	
}

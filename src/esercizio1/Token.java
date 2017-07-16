package esercizio1;

public class Token {
	
	private String name;
	private String value;
	
	public Token(){
		
	}
	
	public Token(String name) {
		this.name = name;
	}
	
	public Token(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		if(this.value==null)
			return name;
		return "("+name+","+value+")";
	}

}

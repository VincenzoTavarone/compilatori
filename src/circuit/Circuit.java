package circuit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


// Circuit.java
//
// Code to join lexer and parser for circuit description language.
//
// Ian Stark


public class Circuit {
	
	public static void main(String[] args) throws Exception {
		
	   	File file = new File("src/circuit/input.txt");
		FileReader input = null;
		try {
			input = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		parser p = new parser(new Yylex(input));
		System.out.println("Resistance is "+ p.parse().value);
		
	}
}

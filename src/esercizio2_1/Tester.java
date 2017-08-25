package esercizio2_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java_cup.runtime.Symbol;

public class Tester {
	
	public static void main(String[] args) {
		
		File file = new File("src/esercizio2_1/input.txt");
		FileReader input = null;
		try {
			input = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Lexer lexer = new Lexer(input);
		
		Symbol[] tokens = lexer.getLexTable();
		
		int i = 0;
		
		while(tokens[i]!=null){
			System.out.println(tokens[i].toString());
			i++;
		}
		
	}

}
package esercizio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

	public static void main(String[] args) {
		
		File file = new File("src/esercizio2/input.txt");
		FileReader input = null;
		try {
			input = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Lexer lexer = new Lexer(input);
		
		Yytoken[] tokens = lexer.getLexTable();
		
		int i = 0;
		
		while(tokens[i]!=null){
			System.out.println(tokens[i]);
			i++;
		}
				
	}
	
}

package esercizio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		
		Yytoken token = null;
		
		try {
			while((token = lexer.yylex()) != null){
				System.out.println(token.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}

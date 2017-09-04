package esercizio4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java_cup.runtime.Symbol;


public class Tester {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("esercizio 4\n");
		
		File file = new File("src/esercizio4/input.txt");
		FileReader input = null;
		try {
			input = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Lexer lexer = new Lexer(input);
		
		Symbol symbol = null;
		
		try {
			while((symbol = lexer.next_token()) != null){
				System.out.println(Wrapper.getClassName(symbol.sym));
				if(symbol.sym == sym.EOF)
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n");
		
		GrammaticaCup parser = new GrammaticaCup(new Lexer(new FileReader(file)));
		
		System.out.println(parser.parse());
		
	}

}

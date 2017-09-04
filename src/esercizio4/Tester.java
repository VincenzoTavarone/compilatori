package esercizio4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java_cup.runtime.Symbol;


public class Tester {
	
	public static void main(String[] args) {
		
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
				System.out.println(symbol);
				if(symbol.sym == sym.EOF)
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nTabella dei simboli\n");
		
		System.out.println(lexer.getTable().getTable().toString());
		
		GrammaticaCup cup = null;
		
		try {
			cup = new GrammaticaCup(new Lexer(new FileReader(file)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.out.println(cup.parse().value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

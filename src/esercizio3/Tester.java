package esercizio3;

import common.Token;

public class Tester {
	
	public static void main(String[] args) {
		
		System.out.println("Lexer esercizio 3\n");
		Token[] tokens = Lexer.lexer("src/esercizio3/esempio.txt");
		int i=0;
		while(tokens[i]!=null){
			System.out.println(tokens[i]);
			i++;
		}
		System.out.println("\nparser\n");
		Parser parser = new Parser(tokens);
		System.out.println(parser.parse());
	}

}

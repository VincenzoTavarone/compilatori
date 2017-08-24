package esercizio3;

import common.Token;

import esercizio1.Lexer;

public class Tester {
	
	public static void main(String[] args) {
		
		Token[] tokens = Lexer.lexer("src/esercizio1/esempio.txt");
		int i=0;
		while(tokens[i]!=null){
			System.out.println(tokens[i]);
			i++;
		}
	}

}

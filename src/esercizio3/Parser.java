package esercizio3;

import common.Token;

public class Parser {
	
	/*
	Grammatica:
	    Program -> Stat Program1
	    Program1 -> SEMI Stat Program1 | epsilon
	    Stat -> matched_stat | open_stat
	    matched_stat -> IF Id_num Relaz Id_num THEN matched_stat ELSE matched_stat | ID ASSING ID_num
	    open_stat -> IF Id_num Relaz Id_num THEN open_stat1
	    opens_stat1 -> stat | matched_stat ELSE open_stat
	    Id_num -> ID | NUM
	    Relaz -> LE | NE | LT | GE | GT
	 */
	
	private int pointer;
	private Token[] tokens;
	
	public Parser(Token[] tokens){
		pointer = 0;
		this.tokens = tokens;
	}
	
	public boolean parser(){
		/*
		 * Servono almeno 3 token per formare 1 statement e almeno uno statement per formare un programma
		 */
		if(tokens[2]==null)
			return false;
		return true;
	}

	private boolean program(){
		return true;
	}
	
	private boolean stat(){
		return true;
	}
	
	private boolean condition(){
		return true;
	}
	
}

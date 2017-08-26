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
	    opens_stat1 -> Stat | matched_stat ELSE open_stat
	    Id_num -> ID | NUM
	    Relaz -> LE | NE | LT | GE | GT
	 */
	
	private int pointer;
	private Token[] tokens;
	
	public Parser(Token[] tokens){
		pointer = 0;
		this.tokens = tokens;
	}
	
	public boolean parse(){
		/*
		 * Servono almeno 3 token per formare 1 statement e almeno uno statement per formare un programma
		 */
		if(tokens[2]==null)
			return false;
		
		while(tokens[pointer]!=null){
			if(!program())
				return false;
		}
		return true;
	}
	
	/*
	 * I metodi vanno passati tutti a private
	 */
	public boolean program(){
		
		if(!stat())
			return false;
		
		pointer++;
		
		if(!program1())
			return false;
		
		return true;
	}
	
	public boolean program1(){
		
		if(!tokens[pointer].getName().equals("SEMI") && tokens[pointer+1]!=null)
			return false;
		
		pointer++;
		return true;
	}
	
	public boolean stat(){
		return true;
	}
	
	public boolean matched_stat(){
		return true;
	}
	
	public boolean open_stat(){
		return true;
	}
	
	public boolean open_stat_1(){
		return true;
	}
	
	public boolean id_num(){
		if(!tokens[pointer].getName().equals("ID") || !tokens[pointer].getName().equals("NUM"))
			return false;
		return true;
	}
	
	public boolean relaz(){
		if(!tokens[pointer].getName().equals("RELOP"))
			return false;
		return true;
	}
	
}

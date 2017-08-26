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
	
	private boolean isFinished(){
		return tokens[pointer]==null;
	}
	
	public boolean parse(){
		/*
		 * Servono almeno 3 token per formare 1 statement e almeno uno statement per formare un programma
		 */
		if(tokens[2]==null)
			return false;
		
		if(!program()){
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
		
		if(!tokens[pointer].getName().equals("SEMI"))
			return isFinished();
		
		pointer++;
		if(!stat())
			return false;
		
		pointer++;
		if(!program1())
			return false;
		
		return true;
	}
	
	public boolean stat(){
		
		int backtrack = pointer;
		
		if(!matched_stat()){
			pointer = backtrack;
			return open_stat();
		}
		
		return true;
	}
	
	public boolean matched_stat(){
		
		if(tokens[pointer].getName().equals("IF")){
			
			pointer++;
			if(!id_num())
				return false;
			
			pointer++;
			if(!relaz())
				return false;
			
			pointer++;
			if(!id_num())
				return false;
			
			pointer++;
			if(!tokens[pointer].getName().equals("THEN"))
				return false;
			
			pointer++;
			if(!matched_stat())
				return false;
			
			pointer++;
			if(!tokens[pointer].getName().equals("ELSE"))
				return false;
			
			pointer++;
			if(!matched_stat())
				return false;
				
		}else{
			if(!tokens[pointer].getName().equals("ID"))
				return false;
			
			pointer++;
			if(!tokens[pointer].getName().equals("ASSIGN"))
				return false;
			
			pointer++;
			if(!id_num())
				return false;
		}
		
		return true;
	}
	
	public boolean open_stat(){
		
		if(!tokens[pointer].getName().equals("IF"))
			return false;
		
		pointer++;
		if(!id_num())
			return false;
		
		pointer++;
		if(!relaz())
			return false;
		
		pointer++;
		if(!id_num())
			return false;
		
		pointer++;
		if(!tokens[pointer].getName().equals("THEN"))
			return false;
		
		pointer++;
		if(!open_stat())
			return false;
		
		return true;
	}
	
	public boolean open_stat_1(){
		
		int backtrack = pointer;
		
		if(!stat()){
			pointer = backtrack;
			if(!matched_stat())
				return false;
			
			pointer++;
			if(!tokens[pointer].getName().equals("ELSE"))
				return false;
			
			pointer++;
			if(!open_stat())
				return false;
		}
		
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

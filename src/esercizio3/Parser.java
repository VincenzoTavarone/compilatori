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
	private boolean eof;
	
	public Parser(Token[] tokens){
		pointer = 0;
		eof = false;
		this.tokens = tokens;
	}
	
	private boolean isFinished(){
		return eof;
	}
	
	private void increment(){
		if(tokens[pointer+1]!=null)
			pointer++;
		else
			eof = true;
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
	private boolean program(){
		
		if(!stat())
			return false;
		
		increment();
		
		if(!program1())
			return false;
		
		return true;
	}
	
	private boolean program1(){
		
		System.out.println("in program 1");
				
		if(!tokens[pointer].getName().equals("SEMI"))
			return isFinished();
		
		increment();
		if(!stat())
			return false;
		
		increment();
		if(!program1())
			return false;
		
		return true;
	}
	
	private boolean stat(){
		
		System.out.println("in stat");
		
		int backtrack = pointer;
		
		if(!matched_stat()){
			pointer = backtrack;
			return open_stat();
		}
		
		return true;
	}
	
	private boolean matched_stat(){
		
		System.out.println("in matched stat");
				
		if(tokens[pointer].getName().equals("IF")){
			
			increment();
			if(!id_num())
				return false;
			
			increment();
			if(!relaz())
				return false;
			
			increment();
			if(!id_num())
				return false;
			
			increment();
			if(!tokens[pointer].getName().equals("THEN"))
				return false;
			
			increment();
			if(!matched_stat())
				return false;
			
			increment();
			if(!tokens[pointer].getName().equals("ELSE"))
				return false;
			
			increment();
			if(!matched_stat())
				return false;
				
		}else{
			System.out.println("in else matched stat");
			if(!tokens[pointer].getName().equals("ID"))
				return false;
			
			increment();
			if(!tokens[pointer].getName().equals("ASSIGN"))
				return false;
			
			increment();
			if(!id_num())
				return false;
		}
		
		return true;
	}
	
	private boolean open_stat(){
				
		if(!tokens[pointer].getName().equals("IF"))
			return false;
		
		increment();
		if(!id_num())
			return false;
		
		increment();
		if(!relaz())
			return false;
		
		increment();
		if(!id_num())
			return false;
		
		increment();
		if(!tokens[pointer].getName().equals("THEN"))
			return false;
		
		increment();
		if(!open_stat_1())
			return false;
		
		return true;
	}
	
	private boolean open_stat_1(){
		
		System.out.println("in open stat 1");
		System.out.println(tokens[pointer]);
		
		int backtrack = pointer;
		
		if(!stat()){
			pointer = backtrack;
			if(!matched_stat())
				return false;
			
			increment();
			if(!tokens[pointer].getName().equals("ELSE"))
				return false;
			
			increment();
			if(!open_stat())
				return false;
		}
		
		return true;
	}
	
	private boolean id_num(){
		if(!tokens[pointer].getName().equals("ID") && !tokens[pointer].getName().equals("NUM"))
			return false;
		return true;
	}
	
	private boolean relaz(){
		if(!tokens[pointer].getName().equals("RELOP"))
			return false;
		return true;
	}
	
}

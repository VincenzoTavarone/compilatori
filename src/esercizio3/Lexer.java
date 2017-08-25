package esercizio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.MalformedTokenException;
import common.Token;


public class Lexer {
	
	private static final String MalformedStringException = "C'è un errore nella posizione :";
	private static final String RELOP = "RELOP";
	private static final String ID = "ID";
	private static final String NUMBER = "NUM";
	private static final String SEMI = "SEMI";
	private static final String ASSIGN = "ASSIGN";
	private static final char EOF = '\u001a';
	
	public static Token[] lexer(String path){
		
		Token[] tokens = new Token[24];
		int index = 0; //indice array tokens
		Map tabella_simboli = new HashMap();
		/*
		 * Preinstallo le parole chiave
		 */
		tabella_simboli.put("if", new Token("IF"));
		tabella_simboli.put("then", new Token("THEN"));
		tabella_simboli.put("else", new Token("ELSE"));
		char[] buffer = new char[1024]; //buffer di destinazione
		File file = new File(path);
		Pattern digit = Pattern.compile("[0-9]");
		Pattern letter = Pattern.compile("[A-Za-z]");
		Pattern relop = Pattern.compile("<|>|=");
		try{
			FileReader file_reader = new FileReader(file);
			BufferedReader buffered_reader = new BufferedReader(file_reader);
			int size = buffered_reader.read(buffer);
			buffer[size] = EOF; 
			int state = 0;
			int lexemeBegin = 0;
			for(int i = 0; i < size; i++){
				Matcher matcher = null;
				Token token = null;
				boolean white_space = (buffer[i]==' ' || buffer[i]=='\n' || buffer[i]=='\t');
				switch(state){
				case 0:
					if(buffer[i]!='<' && buffer[i]!='>' && buffer[i]!='='){
						state = 4;
						if(!white_space){
							i--;
						}
						break;
					}
					if(buffer[i]=='<') state = 1;
					if(buffer[i]=='=') state = 2;
					if(buffer[i]=='>') state = 3;
					break;
				
				case 1:
					if(buffer[i]=='='){
						token = new Token(RELOP, "LE");
					}else if(buffer[i]=='>'){
						token = new Token(RELOP, "NE");
					}else{
						matcher = relop.matcher(""+buffer[i]);
						if(matcher.matches())
							throw new MalformedTokenException(MalformedStringException+i);
						else{
							token = new Token(RELOP, "LT");
							if(!white_space)
								i--;
						}
					}
					tokens[index] = token;
					index++;
					lexemeBegin = i;
					state = 0;
					break;
					
				case 2:
					matcher = relop.matcher(""+buffer[i]);
					if(matcher.matches())
						throw new MalformedTokenException(MalformedStringException+i);
					else{
						token = new Token(ASSIGN);
						tokens[index] = token;
						index++;
						lexemeBegin = i;
						state = 0;
						if(!white_space)
							i--;
					}
					break;
					
				case 3:
					if(buffer[i]=='='){
						token = new Token(RELOP, "GE");
					}else{
						matcher = relop.matcher(""+buffer[i]);
						if(matcher.matches())
							throw new MalformedTokenException(MalformedStringException+i);
						else{
							token = new Token(RELOP, "GT");
							if(!white_space)
								i--;
						}
					}
					tokens[index] = token;
					index++;
					lexemeBegin = i;
					state = 0;
					break;
				
				/*
				 * Da qui gestisco stringhe
				 */
					
				case 4:
					matcher = letter.matcher(""+buffer[i]);
					if(matcher.matches()){
						state = 5;
						lexemeBegin = i;
						i--;
					}else{
						state = 6;
						if(!white_space){
							i--;
						}
					}
					break;
					
				case 5:
					matcher = letter.matcher(""+buffer[i]);
					Matcher digit_matcher = digit.matcher(""+buffer[i]);
					if(matcher.matches() || digit_matcher.matches()){
						if(buffer[i+1]==EOF){ 
							String value = String.copyValueOf(buffer,lexemeBegin, i-lexemeBegin+1); //il codice è uguale, bisogna vedere se si può fare una funzione esterna
							String key = ""+value.hashCode();
							if(tabella_simboli.get(value) instanceof Token){
								Token cast = (Token)tabella_simboli.get(value);
								token = new Token(cast.getName());
							}else{
								tabella_simboli.put(key, value);
								token = new Token(ID, key);
							}
							tokens[index] = token;
							index++;
							lexemeBegin = i;
							state = 0;
						}
						break;
					}else{
						String value = String.copyValueOf(buffer,lexemeBegin, i-lexemeBegin);
						String key = ""+value.hashCode();
						if(tabella_simboli.get(value) instanceof Token){
							Token cast = (Token)tabella_simboli.get(value);
							token = new Token(cast.getName());
						}else{
							tabella_simboli.put(key, value);
							token = new Token(ID, key);
						}
						tokens[index] = token;
						index++;
						lexemeBegin = i;
						state = 0;
						if(!white_space)
							i--;
					}
					break;
					
				/*
				 * Gestione numeri
				 */
									
				case 6:
					matcher = digit.matcher(""+buffer[i]);
					if(matcher.matches()){
						state = 7;
						lexemeBegin = i;
						i--;
					}else{
						state = 12;
						if(!white_space){
							i--;
						}
					}
					break;
				
				case 7:
					matcher = digit.matcher(""+buffer[i]);
					if(matcher.matches()){
						if(buffer[i+1]==EOF){
							token = new Token(NUMBER, String.copyValueOf(buffer, lexemeBegin, i-lexemeBegin+1));
							tokens[index] = token;
							index++;
							lexemeBegin = i;
							state = 0;
						}
						break;
					}else if(buffer[i]=='.'){
						state = 8;
					}else if(buffer[i]=='E'){
						state = 9;
					}else{
						token = new Token(NUMBER, String.copyValueOf(buffer, lexemeBegin, i-lexemeBegin));
						tokens[index] = token;
						index++;
						lexemeBegin = i;
						state = 0;
						if(!white_space)
							i--;
					}
					break;
				
				case 8:
					matcher = digit.matcher(""+buffer[i]);
					if(buffer[i]=='.')
						throw new MalformedTokenException(MalformedStringException+i);
					else if(matcher.matches()){
						if(buffer[i+1]==EOF){
							token = new Token(NUMBER, String.copyValueOf(buffer, lexemeBegin, i-lexemeBegin+1));
							tokens[index] = token;
							index++;
							lexemeBegin = i;
							state = 0;
						}
						break;
					}else if(buffer[i]=='E'){
						state = 9;
					}else{
						token = new Token(NUMBER, String.copyValueOf(buffer, lexemeBegin, i-lexemeBegin));
						tokens[index] = token;
						index++;
						lexemeBegin = i;
						state = 0;
						if(!white_space)
							i--;
					}
					break;
					
				case 9 :
					matcher = digit.matcher(""+buffer[i]);
					if(buffer[i]=='+' || buffer[i]=='-'){
						state = 10;
					}else if(matcher.matches()){
						state = 11;
					}else{
						throw new MalformedTokenException(MalformedStringException+i);
					}
					break;
				
				case 10 : 
					matcher = digit.matcher(""+buffer[i]);
					if(buffer[i]=='+' || buffer[i]=='-'){
						throw new MalformedTokenException(MalformedStringException+i);
					}else if(matcher.matches()){
						if(buffer[i+1]==EOF){
							token = new Token(NUMBER, String.copyValueOf(buffer, lexemeBegin, i-lexemeBegin+1));
							tokens[index] = token;
							index++;
							lexemeBegin = i;
							state = 0;
						}
					}else{
						token = new Token(NUMBER, String.copyValueOf(buffer, lexemeBegin, i-lexemeBegin));
						tokens[index] = token;
						index++;
						lexemeBegin = i;
						state = 0;
						if(!white_space)
							i--;
					}
					break;
					
				case 11 : 
					matcher = digit.matcher(""+buffer[i]);
					if(matcher.matches()){
						if(buffer[i+1]==EOF){
							token = new Token(NUMBER, String.copyValueOf(buffer, lexemeBegin, i-lexemeBegin+1));
							tokens[index] = token;
							index++;
							lexemeBegin = i;
							state = 0;
						}
					}else{
						token = new Token(NUMBER, String.copyValueOf(buffer, lexemeBegin, i-lexemeBegin));
						tokens[index] = token;
						index++;
						lexemeBegin = i;
						state = 0;
						if(!white_space)
							i--;
					}
					break;
				
				/*
				 * Gestione token SEMI
				 */
					
				case 12:
					if(buffer[i]==';'){
						token = new Token(SEMI);
						tokens[index] = token;
						index++;
						state=0;
					}else{
						state = 20;
						if(!white_space)
							i--;
					}
					break;
					
				/*
				 * Gestione spazi, tab
				 */

				case 20:
					if(white_space)
						break;
					else{
						i--;
						state = 0;
					}
					break;
										
				}
			}//fine del ciclo			
			buffered_reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return tokens;
	}
}


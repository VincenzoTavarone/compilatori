package esercizio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexer {
	
	private static final String MalformedErrorString = "C'è un errore nella posizione :";
	private static final String RELOP = "RELOP";
	private static final String ID = "ID";
	
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
			buffer[size] = '\u001a';//EOF
			int state = 0;
			int lexemeBegin = 0;
			for(int i = 0; i < size; i++){
				Matcher matcher = null;
				Token token = null;
				switch(state){
				case 0:
					if(buffer[i]!='<' && buffer[i]!='>' && buffer[i]!='='){
						state = 4;
						if(buffer[i]!=' ' && buffer[i]!='\n' && buffer[i]!='\t'){
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
							throw new MalformedTokenException(MalformedErrorString+i);
						else{
							token = new Token(RELOP, "LT");
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
						throw new MalformedTokenException(MalformedErrorString+i);
					else{
						token = new Token(RELOP, "EQ");
						tokens[index] = token;
						index++;
						lexemeBegin = i;
						state = 0;
					}
					break;
					
				case 3:
					if(buffer[i]=='='){
						token = new Token(RELOP, "GE");
					}else{
						matcher = relop.matcher(""+buffer[i]);
						if(matcher.matches())
							throw new MalformedTokenException(MalformedErrorString+i);
						else{
							token = new Token(RELOP, "GT");
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
						if(buffer[i]!=' ' && buffer[i]!='\n' && buffer[i]!='\t'){
							i--;
						}
					}
					break;
					
				case 5:
					matcher = letter.matcher(""+buffer[i]);
					Matcher digit_matcher = digit.matcher(""+buffer[i]);
					if(matcher.matches() || digit_matcher.matches()){
						if(buffer[i+1]=='\u001a'){ //EOF
							token = new Token(ID, String.copyValueOf(buffer,lexemeBegin, i-lexemeBegin+1));
							tokens[index] = token;
							index++;
							lexemeBegin = i;
							state = 0;
						}
						break;
					}else{
						token = new Token(ID, String.copyValueOf(buffer,lexemeBegin, i-lexemeBegin));
						tokens[index] = token;
						index++;
						lexemeBegin = i;
						state = 0;
					}
					break;
				
				/*
				 * Gestione numeri
				 */
				
				case 6:
					i--;
					state = 0;
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


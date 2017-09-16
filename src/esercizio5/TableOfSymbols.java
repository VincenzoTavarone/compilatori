package esercizio5;

import java.util.HashMap;

import java_cup.runtime.Symbol;

public class TableOfSymbols {
	
	private HashMap<String, String> table;
	
	public TableOfSymbols(){
		this.table = new HashMap<String, String>();
	}
	
	public void add(Symbol symbol){
		table.put((String)symbol.value, Wrapper.getClassName(symbol.sym));
	}
	
	public String get(String key){
		return (String) table.get(key);
	}
	
	public HashMap<String, String> getTable(){
		return table;
	}
	

}

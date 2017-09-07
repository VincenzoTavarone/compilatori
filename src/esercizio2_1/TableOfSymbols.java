package esercizio2_1;

import java.util.HashMap;

import java_cup.runtime.Symbol;

public class TableOfSymbols {
	
	private HashMap<Object, String> table;
	
	public TableOfSymbols(){
		this.table = new HashMap<Object, String>();
	}
	
	public void add(Symbol symbol){
		table.put(symbol.value, Wrapper.getClassName(symbol.sym));
	}
	
	public String get(Symbol symbol){
		return (String) table.get(symbol.value);
	}
	
	public HashMap<Object, String> getTable(){
		return table;
	}
	

}

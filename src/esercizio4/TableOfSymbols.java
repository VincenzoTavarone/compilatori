package esercizio4;

import java.util.HashMap;

import java_cup.runtime.Symbol;

public class TableOfSymbols {
	
	private HashMap table;
	
	public TableOfSymbols(){
		this.table = new HashMap();
	}
	
	public void add(Symbol symbol){
		table.put(symbol.value, Wrapper.getClassName(symbol.sym));
	}
	
	public String get(Symbol symbol){
		return (String) table.get(symbol.value);
	}
	
	public HashMap getTable(){
		return table;
	}
	

}

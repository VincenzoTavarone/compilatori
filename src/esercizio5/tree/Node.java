package esercizio5.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node<T> {
	
	private Node<T> parent;
	private T value;
	private List<Node<T>> children;
	private HashMap<String, Node<String>> table;
	
	public Node(){
		children = new ArrayList<Node<T>>();
		table = new HashMap<String, Node<String>>();
	}
	
	public Node(T value) {
		this.setValue(value);
		children = new ArrayList<Node<T>>();
		table = new HashMap<String, Node<String>>();
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public List<Node<T>> getChildren() {
		return children;
	}
	
	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public void setChildren(List<Node<T>> children) {
		this.children = children;
	}
	
	public void addChild(Node<T> node){
		node.setParent(this);
		children.add(node);
	}
	
	public Node<T> removeChildAt(int index){
		return children.remove(index);
	}
	
	public boolean isRoor(){
		return parent==null;
	}
	
	public boolean isExternal(){
		return children.isEmpty();
	}
	
	public boolean isInternal(){
		return !isExternal();
	}
	
	/*
	 * Gestione tabella simboli per scope
	 */
	public HashMap<String, Node<String>> getTable() {
		return table;
	}

	public void setTable(HashMap<String, Node<String>> table) {
		this.table = table;
	}
	
	public Node<String> getElement(String key){
		return table.get(key);
	}
	
	public void putElement(String key, Node<String> node){
		table.put(key, node);
	}
}

package esercizio4.tree;

import java.util.List;

public class Node<T> {
	
	private Node<T> parent;
	private T value;
	private List<Node<T>> children;
	
	public Node(){
		
	}
	
	public Node(T value) {
		this.setValue(value);
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
}

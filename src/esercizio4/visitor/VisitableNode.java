package esercizio4.visitor;

import esercizio4.tree.Node;

public class VisitableNode<T> extends Node<T> implements Visitable{
	
	private String type;

	public VisitableNode(T value) {
		super(value);
	}
	
	public VisitableNode(String type, T value){
		super(value);
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void accept(Visitor visitor){
		visitor.visit(this);
	}

}

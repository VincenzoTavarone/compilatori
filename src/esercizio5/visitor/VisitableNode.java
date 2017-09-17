package esercizio5.visitor;

import esercizio5.tree.Node;

public class VisitableNode<T> extends Node<T> implements Visitable{
	
	private String name;

	public VisitableNode(T value) {
		super(value);
	}
	
	public VisitableNode(String name, T value){
		super(value);
		this.setName(name);
	}
	
	public String accept(Visitor visitor){
		return visitor.visit(this);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		if(this.getValue()!=null)
			return this.getName()+" attr=\""+this.getValue()+"\"";
		return this.getName();
	}

}

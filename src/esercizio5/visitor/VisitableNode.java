package esercizio5.visitor;

import esercizio5.tree.Node;

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
	
	public String accept(Visitor visitor){
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		if(this.getValue()!=null)
			return this.getType()+" attr=\""+this.getValue()+"\"";
		return this.getType();
	}

}

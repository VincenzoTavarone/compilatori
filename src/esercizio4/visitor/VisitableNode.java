package esercizio4.visitor;

import esercizio4.tree.Node;

public class VisitableNode<T> extends Node<T> implements Visitable{

	public VisitableNode(T value) {
		super(value);
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}

}

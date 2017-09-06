package esercizio4.visitor;

import esercizio4.tree.Node;
import esercizio4.tree.Tree;

public class SyntaxTree<T> extends Tree<T> implements Visitor{

	public SyntaxTree(Node<T> root) {
		super(root);
	}

	public void visit(Visitable visitable) {
		
	}

}

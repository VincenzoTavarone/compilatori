package esercizio5.visitor;

import java.util.Stack;

import esercizio5.tree.Node;
import esercizio5.tree.Tree;

public class SemanticAnalyzer<T> extends Tree<T> implements Visitor {
	
	private Stack<Node<String>> stack;
	
	public SemanticAnalyzer(){
		this.stack = new Stack<Node<String>>();
	}

	public String visit(Visitable visitable) {
		@SuppressWarnings("unchecked")
		VisitableNode<String> node = (VisitableNode<String>) visitable;
		//regola A
		if(node.getType().equals("ProgramOp") || node.getType().equals("ProcDeclPartOp"))
			stack.push(node);
		for (Node<String> c : node.getChildren()) {
			VisitableNode<String> current = (VisitableNode<String>) c;
			if(current.isInternal())
				current.accept(this);
			else{
				
			}
		}
		//regola A
		if(node.getType().equals("ProgramOp") || node.getType().equals("ProcDeclPartOp"))
			stack.pop();
		return null;
	}

}

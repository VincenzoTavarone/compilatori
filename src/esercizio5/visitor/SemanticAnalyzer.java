package esercizio5.visitor;

import java.util.HashMap;
import java.util.Stack;

import esercizio5.exception.MultiDeclarationsException;
import esercizio5.exception.NotDeclaredIdException;
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
		if(node.getName().equals("ProgramOp") || node.getName().equals("ProcDeclPartOp"))
			stack.push(node);
		for (Node<String> c : node.getChildren()) {
			VisitableNode<String> current = (VisitableNode<String>) c;
			if(current.isInternal()){
				//regola B
				if(current.getName().equals("VarDeclOp") || current.getName().equals("ProcDeclOp")){
					try{
						checkRuleB(current);
					}catch(MultiDeclarationsException e){
						e.printStackTrace();
					}
				}
				//regola C
				if(current.getName().equals("AssignOp") || current.getName().equals("ReadOp") || current.getName().equals("CallOp") || current.getName().equals("VarOp")){
					try{
						checkRuleC(current);
					}catch(NotDeclaredIdException e){
						e.printStackTrace();
					}
				}
				//regola D
				if(current.getName().equals("ConstOp")){
					current.setType(((VisitableNode<String>) current.getChildren().get(0)).getType());
				}
				//regola E
			}else{
				
			}
		}
		//regola A
		if(node.getName().equals("ProgramOp") || node.getName().equals("ProcDeclPartOp"))
			stack.pop();
		return null;
	}
	
	//regola B
	private void checkRuleB(VisitableNode<String> current) throws MultiDeclarationsException{
		HashMap<String, Node<String>> TableOnTop = stack.peek().getTable();
		if(current.getName().equals("ProcDeclOp")){
			VisitableNode<String> id = (VisitableNode<String>) current.getChildren().get(0);
			if(TableOnTop.containsKey(id.getValue()))
				throw new MultiDeclarationsException("Errore dichiarazione multipla");
			else
				TableOnTop.put(id.getValue(), id);
		}else{
			for(int i=1; i < current.getChildren().size(); i++){
				VisitableNode<String> child = (VisitableNode<String>) current.getChildren().get(i);
				if(TableOnTop.containsKey(child.getValue()))
					throw new MultiDeclarationsException("Errore dichiarazione multipla");
				else{
					child.setType(((VisitableNode<String>) current.getChildren().get(0)).getType());
					TableOnTop.put(child.getValue(), child);
				}					
			}
		}
	}
	
	//regola C
	private void checkRuleC(VisitableNode<String> current) throws NotDeclaredIdException{
		
		VisitableNode<String> child = (VisitableNode<String>) current.getChildren().get(0);
		boolean find = false;

		for(int i = stack.size()-1; i >=0; i--){
			if(!find){
				HashMap<String, Node<String>> current_table = stack.get(i).getTable();
				if(current_table.containsKey(child.getValue())){
					find = true;
				}
			}
		}
		if(!find)
			throw new NotDeclaredIdException("Identificatore non dichiarato");
	}

}

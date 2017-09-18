package esercizio5.visitor;

import java.util.HashMap;
import java.util.Stack;

import esercizio5.exception.MultiDeclarationsException;
import esercizio5.exception.NotDeclaredIdException;
import esercizio5.exception.TypeMismatchException;
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
						System.err.println(e.getMessage());
					}
				}
				//regola C
				if(current.getName().equals("AssignOp") || current.getName().equals("ReadOp") || current.getName().equals("CallOp") || current.getName().equals("VarOp")){
					try{
						checkRuleC(current);
					}catch(NotDeclaredIdException e){
						System.err.println(e.getMessage());
					}
				}
				//regola D
				if(current.getName().equals("ConstOp")){
					checkRuleD(current);
				}
				//regole E
				//WhileOp
				if(current.getName().equals("WhileOp")){
					try{
						checkWhileOp(current);
					}catch(TypeMismatchException e){
						System.err.println(e.getMessage());
					}
				}
				//AssingOp
				if(current.getName().equals("AssignOp")){
					try{
						checkAssingOp(current);
					}catch(TypeMismatchException e){
						System.err.println(e.getMessage());
					}
				}
				//AddOp && MulOp
				if(current.getName().equals("AddOp")||current.getName().equals("MulOp")){
					try{
						checkAddOpOrMultOp(current);
					}catch(TypeMismatchException e){
						System.err.println(e.getMessage());
					}
				}
				//NotOp
				if(current.getName().equals("NotOp")){
					try{
						checkNotOp(current);
					}catch(TypeMismatchException e){
						System.err.println(e.getMessage());
					}
				}
				//Relop
				if(current.getName().equals("RelationalOp")){
					try{
						checkRelop(current);
					}catch(TypeMismatchException e){
						System.err.println(e.getMessage());
					}
				}
				//unaryMinus
				if(current.getName().equals("UnaryMinusOp")){
					try{
						checkUnaryMinus(current);
					}catch(TypeMismatchException e){
						System.err.println(e.getMessage());
					}
				}
				//checkIfElse
				if(current.getName().equals("IfThenElseOp")||current.getName().equals("IfThenOp")){
					try{
						checkIfThenElseOrIfThen(current);
					}catch(TypeMismatchException e){
						System.err.println(e.getMessage());
					}
				}
				
				//altre regole
				if(current.getName().equals("VarOp")){
					current.setType(((VisitableNode<String>) current.getChildren().get(0)).getType());
				}
				if(current.getName().equals("ExprOp")||current.getName().equals("SimpleExprOp"))
					checkExpression(current);
				
				//chiama il metodo ricorsivamente
				current.accept(this);				
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
					throw new MultiDeclarationsException("Errore dichiarazione multipla : "+child.getValue());
				else{
					child.setType(((VisitableNode<String>) current.getChildren().get(0)).getName());
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
					child.setType(((VisitableNode<String>) current_table.get(child.getValue())).getType());
					find = true;
				}
			}
		}
		if(!find)
			throw new NotDeclaredIdException("Identificatore non dichiarato : "+child.getValue());
	}
	
	//regola D
	private void checkRuleD(VisitableNode<String> current){
		VisitableNode<String> child = (VisitableNode<String>)current.getChildren().get(0);
		switch (child.getName()) {
		case "INTEGER_CONSTANT":
			current.setType("INTEGER");
			break;
		case "STRING_CONSTANT":
			current.setType("STRING");
			break;
		case "CHARACTER_CONSTANT":
			current.setType("CHARACTER");
			break;
		case "BOOLEAN_CONSTANT":
			current.setType("BOOLEAN");
		}
	}
	
	
	//TYPE SYSTEM
	//costrutto while
	//while_statement ::= WHILE expression DO statement
	private void checkWhileOp(VisitableNode<String> current) throws TypeMismatchException{
		
		VisitableNode<String> left = (VisitableNode<String>)current.getChildren().get(0);
		//Controllo se il nodo è già stato valutato
		if(left.getType()==null)
			current.accept(this);
		if(left.getType().equals("BOOLEAN")||left.getType().equals("INTEGER"))
			current.setType("VOID");
		else
			throw new TypeMismatchException("");
	}
	
	//costrutto assign
	private void checkAssingOp(VisitableNode<String> current) throws TypeMismatchException{
		
		VisitableNode<String> left = (VisitableNode<String>)current.getChildren().get(0);
		VisitableNode<String> right = (VisitableNode<String>)current.getChildren().get(1);
		
		if(right.getType()==null)
			current.accept(this);
		
		if(left.getType().equals(right.getType()))
			current.setType("VOID");
		else
			throw new TypeMismatchException("Errore in assingOp, nodo 1 tipo :"+left.getType()+" nodo 2 :"+right.getType());
	}
	
	//costrutti AddOp e MultOp
	private void checkAddOpOrMultOp(VisitableNode<String> current) throws TypeMismatchException{
		//Il nodo in posizione 0 è il tipo di operazione (es. plus, minus, or...)
		VisitableNode<String> left = (VisitableNode<String>)current.getChildren().get(1);
		VisitableNode<String> right = (VisitableNode<String>)current.getChildren().get(2);
				
		if(left.getType()==null || right.getType()==null)
			current.accept(this);
		
		if(left.getType().equals(right.getType()) && (left.getType().equals("INTEGER")||left.getType().equals("BOOLEAN")))
			current.setType("INTEGER");
		else
			throw new TypeMismatchException("Errore in add o multop, i nodi devono essere dello stesso tipo. Nodo 1 : "+
											left.getType() + " nodo 2 : "+right.getType());
	}
	
	//costrutto notOp
	private void checkNotOp(VisitableNode<String> current) throws TypeMismatchException{
		
		VisitableNode<String> child = (VisitableNode<String>)current.getChildren().get(0);
		
		if(child.getType()==null)
			current.accept(this);
		
		if(child.getType().equals("BOOLEAN")||child.getType().equals("INTEGER"))
			current.setType("BOOLEAN");
		else
			throw new TypeMismatchException("L'operatore Not accetta solo tipi booleani o interi");
			
	}
	
	//costrutto Relop
	private void checkRelop(VisitableNode<String> current) throws TypeMismatchException{
		
		VisitableNode<String> left = (VisitableNode<String>)current.getChildren().get(1);
		VisitableNode<String> right = (VisitableNode<String>)current.getChildren().get(2);
		
		if(left.getType()==null|| right.getType()==null)
			current.accept(this);
		
		if(left.getType().equals(right.getType()) && (left.getType().equals("INTEGER")||left.getType().equals("BOOLEAN")))
			current.setType("BOOLEAN");
		else
			throw new TypeMismatchException("Errore in relop, i nodi devono essere dello stesso tipo. Nodo 1 : "+
											left.getType() + " nodo 2 : "+right.getType());
		
	}
	
	//costrutto UnaryMinus
	private void checkUnaryMinus(VisitableNode<String> current) throws TypeMismatchException{
		
		VisitableNode<String> child = (VisitableNode<String>) current.getChildren().get(0);
		
		if(child.getType()==null)
			current.accept(this);
		
		if(child.getType().equals("INTEGER") || child.getType().equals("BOOLEAN"))
			current.setType("INTEGER");
		else
			throw new TypeMismatchException("L'operatore unary minus accetta solo tipi booleani o interi");
	}
	
	//costrutto ifThenElse o IfThen
	private void checkIfThenElseOrIfThen(VisitableNode<String> current) throws TypeMismatchException{
		
		VisitableNode<String> right = (VisitableNode<String>) current.getChildren().get(0);
		
		if(right.getType()==null)
			current.accept(this);
		
		if(right.getType().equals("INTEGER")|| right.getType().equals("BOOLEAN"))
			current.setType("VOID");
		else
			throw new TypeMismatchException("Errore nel corpo della parola chiave THEN : "+right+" type : "+right.getType());
	}
	
	//costrutto expression
	private void checkExpression(VisitableNode<String> current){
		
		VisitableNode<String> child = (VisitableNode<String>) current.getChildren().get(0);
		
		if(child.getType()==null)
			current.accept(this);
		
		current.setType(child.getType());
		
	}

}

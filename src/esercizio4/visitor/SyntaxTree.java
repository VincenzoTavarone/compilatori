package esercizio4.visitor;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import esercizio4.tree.Node;
import esercizio4.tree.Tree;

public class SyntaxTree<T> extends Tree<T> implements Visitor{
	
	private String result;

	public SyntaxTree() {
		super();
	}

	public String visit(Visitable visitable) {
		@SuppressWarnings("unchecked")
		VisitableNode<String> node = (VisitableNode<String>) visitable;
		result = "<"+node.toString()+">";
		for (Node<String> c:node.getChildren()) {
			VisitableNode<String> current = (VisitableNode<String>)c;
			if(c.isInternal())
				result+=current.accept(this);
			else
				result+="<"+current.toString()+"/>";
		}
		result+="</"+node.toString()+">";
		return result;
	}
	
	public void write(){
		System.out.println("provo a scrivere");
		Writer w = null;
		try{
			w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/esercizio4/output.xml"), "utf-8"));
			w.write(this.result);
			w.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}

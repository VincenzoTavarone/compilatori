package esercizio5.visitor;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class CTranslator implements Visitor {
	
	private String code;
	
	public CTranslator(){
		
	}

	@Override
	public String visit(Visitable visitable) {
		code = "";
		@SuppressWarnings("unchecked")
		VisitableNode<String> node = (VisitableNode<String>)visitable;
		switch (node.getName()) {
		
		case "ProgramOp":
			code += "#include <stdio.h>\n"+
					"typedef int bool;\n"+
					"#define TRUE 1\n"+
					"#define FALSE 0\n";
			code += ((VisitableNode<String>)node.getChildren().get(1)).accept(this);
			break;
			
		case "BlockOp":
			for(int i = 0; i < node.getChildren().size() - 1; i++){
				VisitableNode<String> child = (VisitableNode<String>)node.getChildren().get(i);
				code += child.accept(this);
			}
			
			code += "\nint main(){\n";
			
			code += ((VisitableNode<String>) node.getChildren().get(2)).accept(this);
			
			code += "\n}";
			break;
		
		case "VarDeclOp":
			VisitableNode<String> type = (VisitableNode<String>)node.getChildren().get(0);
			if(type.getName().equals("INTEGER"))
				code += "int ";
			else
				code += "bool ";
			for(int i = 1; i < node.getChildren().size(); i++ ){
				code += ((VisitableNode<String>)node.getChildren().get(i)).accept(this);
				if(i!=node.getChildren().size() - 1)
					code += ", ";
			}
			code += ";\n";
			break;
			
		case "ID":
			code += node.getValue();
			break;
		
		case "ProcDeclOp":
			VisitableNode<String> identifier = (VisitableNode<String>)node.getChildren().get(0);
			code += "void "+identifier.getValue()+" {\n";
			code += ((VisitableNode<String>)node.getChildren().get(1)).accept(this);
			code += "}\n";
			break;
			
		case "RelationalOp":
			code += ((VisitableNode<String>)node.getChildren().get(1)).accept(this);
			code += node.getChildren().get(0).getValue();
			code += ((VisitableNode<String>)node.getChildren().get(2)).accept(this);
			break;
			
		case "AddOp":
			code += ((VisitableNode<String>)node.getChildren().get(1)).accept(this);
			code += node.getChildren().get(0).getValue();
			code += ((VisitableNode<String>)node.getChildren().get(2)).accept(this);
			break;
			
		case "MulOp":
			code += ((VisitableNode<String>)node.getChildren().get(1)).accept(this);
			code += node.getChildren().get(0).getValue();
			code += ((VisitableNode<String>)node.getChildren().get(2)).accept(this);
			break;
			
		case "AssignOp":
			code += ((VisitableNode<String>)node.getChildren().get(0)).accept(this);
			code += " = ";
			code += ((VisitableNode<String>)node.getChildren().get(1)).accept(this);
			code += ";\n";
			break;
			
		case "ReadOp":
			code += "scanf(\"";
			for(int i = 0; i < node.getChildren().size(); i++){
				code += "%d";
			}
			code += "\"";
			for(int i = 0; i < node.getChildren().size(); i++){
				code += ", &"+((VisitableNode<String>)node.getChildren().get(i)).accept(this);
			}
			code += ");\n";
			break;
			
		case "WriteOp":
			code += "printf(\"";
			for(int i = 0; i < node.getChildren().size(); i++){
				switch (((VisitableNode<String>) node.getChildren().get(i)).getType()) {
				case "CHARACTER":
					code += "%c";
					break;
				case "STRING":
					code += "%s";
				default:
					code += "%d";
					break;
				}
			}
			code += "\\n\"";
			for(int i=0; i < node.getChildren().size(); i++){
				code += ", " +((VisitableNode<String>) node.getChildren().get(i)).accept(this);
			}
			code +=");\n";
			break;
			
		case "CallOp":
			code += ((VisitableNode<String>)node.getChildren().get(0)).accept(this) + "();\n";
			break;
			
		case "IfThenOp":
			code += "if("+((VisitableNode<String>)node.getChildren().get(0)).accept(this)+"){\n";
			code += ((VisitableNode<String>)node.getChildren().get(1)).accept(this)+"}\n";
			break;
			
		case "IfThenElseOp":
			code += "if("+((VisitableNode<String>)node.getChildren().get(0)).accept(this)+"){\n";
			code += ((VisitableNode<String>)node.getChildren().get(1)).accept(this)+"}\n";
			code += "else{\n"+((VisitableNode<String>)node.getChildren().get(2)).accept(this)+"}\n";
			break;
		
		case "NotOp":
			code += "!("+((VisitableNode<String>)node.getChildren().get(0)).accept(this)+")";
			break;
			
		case "ConstOp":
			code += node.getChildren().get(0).getValue();
			break;
			
		case "WhileOp":
			code += "while("+((VisitableNode<String>)node.getChildren().get(0)).accept(this)+"){\n";
			code += ((VisitableNode<String>)node.getChildren().get(1)).accept(this)+"}\n";
			break;

		default:
			for(int i = 0; i < node.getChildren().size(); i++){
				code += ((VisitableNode<String>)node.getChildren().get(i)).accept(this);
			}
			break;
		}
		return code;
	}
	
	public void write(String filename){
		Writer w = null;
		try{
			w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
			w.write(this.code);
			w.close();
		}catch(IOException e){
			e.printStackTrace();
		}

	}

}

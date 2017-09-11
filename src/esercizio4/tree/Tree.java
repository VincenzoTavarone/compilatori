package esercizio4.tree;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
	
	private Node<T> root;
	
	public Tree(Node<T> root){
		this.setRoot(root);
	}

	public Tree() {
		
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	public List<Node<T>> getListOfNodes(){
		List<Node<T>> list = new ArrayList<Node<T>>();
		preorder(root, list);
		return list;
	}
	
	private void preorder(Node<T> e, List<Node<T>> list){
		list.add(e);
		for (Node<T> node : e.getChildren()) {
			preorder(node, list);
		}
	}

}

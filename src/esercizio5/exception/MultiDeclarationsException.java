package esercizio5.exception;

public class MultiDeclarationsException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public MultiDeclarationsException(){
		super();
	}
	
	public MultiDeclarationsException(String msg){
		super(msg);
	}
	
}

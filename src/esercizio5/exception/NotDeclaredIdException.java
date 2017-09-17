package esercizio5.exception;

public class NotDeclaredIdException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NotDeclaredIdException(){
		super();
	}
	
	public NotDeclaredIdException(String msg){
		super(msg);
	}

}

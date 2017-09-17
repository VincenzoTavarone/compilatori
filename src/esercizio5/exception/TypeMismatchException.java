package esercizio5.exception;

public class TypeMismatchException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public TypeMismatchException(){
		super();
	}
	
	public TypeMismatchException(String msg){
		super(msg);
	}

}

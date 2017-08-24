package common;

public class MalformedTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MalformedTokenException(String msg){
		super(msg);
	}	

}

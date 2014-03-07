package exception;

public class UnknownShapeException extends Exception {
	
	public String getMessage(){
		return "trying to construct unknown shape...";
	}
}

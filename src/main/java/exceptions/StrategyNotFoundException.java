package exceptions;

public class StrategyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public  StrategyNotFoundException(String message) {
    	super(message);
    }
}

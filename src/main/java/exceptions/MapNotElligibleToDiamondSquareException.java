package exceptions;

public class MapNotElligibleToDiamondSquareException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public  MapNotElligibleToDiamondSquareException(String message) {
    	super(message);
    }

}

package local.jniGenerator.exceptions;

public class BadTypeException extends RuntimeException {

	public BadTypeException(Class<?> clazz) {
		super("Illegal use of type " + clazz.getName() + ".");
		cl = clazz;
	}

	public BadTypeException(Class<?> clazz, String message) {
		super("Illegal use of type " + clazz.getName() + ": " + message);
		cl = clazz;
		// TODO Auto-generated constructor stub
	}
	
	public Class<?> getType(){
		return cl;
	}
	
	private final Class<?> cl;

}
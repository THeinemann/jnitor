package local.jnitor.exceptions;

public class BadTypeException extends IllegalArgumentException {

	public BadTypeException(Class<?> clazz) {
		super("Illegal use of type " + clazz.getName() + ".");
		cl = clazz;
	}

	public BadTypeException(Class<?> clazz, String message) {
		super("Illegal use of type " + clazz.getName() + ": " + message);
		cl = clazz;
	}
	
	public Class<?> getType(){
		return cl;
	}
	
	private final Class<?> cl;

}

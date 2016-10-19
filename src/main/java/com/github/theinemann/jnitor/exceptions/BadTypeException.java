package com.github.theinemann.jnitor.exceptions;

/**
 * Exception class to be thrown when a type does not fulfill some requirements.
 * 
 * This may be e.g. when a method that expects an object type receives a primitive type. The types
 * typically are represented either by reflection objects, or objects like TypeWrapper from the Jnitor
 * code base.
 * 
 * @author Thomas Heinemann
 *
 */
public class BadTypeException extends IllegalArgumentException {

	/**
	 * UID for serialization format
	 */
	private static final long serialVersionUID = 7800848228719140204L;

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

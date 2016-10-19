package com.github.theinemann.jnitor.wrappers;

/**
 * Abstract base class to provide functionality for all kinds of methods ("Normal" methods and constructors)
 * 
 * @author Thomas Heinemann
 *
 */
public abstract class AbstractMethodWrapper {

	public AbstractMethodWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Creates the signature of a method, by analyzing the parameter and return types 
	 * 
	 * This signature can be used e.g. in an JNI callback to find the correct Java method.
	 * 
	 * @param parameters The parameters of the method
	 * @param returnType The return type of the method
	 * @return A string containing the JNI signature of the method.
	 */
	static protected String getSignatureFromTypes(Class<?>[] parameters, Class<?> returnType)
	{
		String result = "(";
		
		for (Class<?> paramClass : parameters)
		{
			result += TypeWrapper.getTypeSignature(paramClass);
		}
		
		result += ")" + TypeWrapper.getTypeSignature(returnType);
		
		return result;
	}

}

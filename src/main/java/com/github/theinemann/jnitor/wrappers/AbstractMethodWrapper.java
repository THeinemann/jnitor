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

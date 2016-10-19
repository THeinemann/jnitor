package com.github.theinemann.jnitor.wrappers;

import java.lang.reflect.Constructor;

/**
 * Wrapper class around a reflection constructor object.
 * 
 * @author Thomas Heinemann
 *
 */
public class ConstructorWrapper extends AbstractMethodWrapper {

	public ConstructorWrapper(Constructor<?> constructor) {
		this.constructor = constructor;
	}
	
	public static String getConstructorSignature(Constructor<?> constructor)
	{
		return AbstractMethodWrapper.getSignatureFromTypes(constructor.getParameterTypes(), void.class);
	}
	
	public String getConstructorSignature()
	{
		return getConstructorSignature(constructor);
	}
	
	public Constructor<?> getWrappedConstructor()
	{
		return constructor;
	}
	
	private final Constructor<?> constructor;
}

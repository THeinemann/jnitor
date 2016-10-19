package com.github.theinemann.jnitor.model;

import com.github.theinemann.jnitor.wrappers.ConstructorWrapper;

/**
 * Model class to provide information about constructors to the templating engine. 
 * 
 * @author Thomas Heinemann
 *
 */
public class Constructor {
	public Constructor(ConstructorWrapper constructor) {
		this.constructor = constructor;
	}
	
	public String getSignature()
	{
		return constructor.getConstructorSignature();
	}
	
	public Parameter[] getParameters()
	{
		return Parameter.fromTypeArray(constructor.getWrappedConstructor().getParameterTypes());
	}

	
	private final ConstructorWrapper constructor;
}

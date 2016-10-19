package com.github.theinemann.jnitor.model;

import com.github.theinemann.jnitor.wrappers.MethodWrapper;
import com.github.theinemann.jnitor.wrappers.TypeWrapper;

public class Method {
	public Method(MethodWrapper method) {
		this.method = method;
	}
	
	public String getName()
	{
		return method.getName();
	}
	
	public boolean isStatic()
	{
		return method.isStatic();
	}
	
	public boolean isVoid()
	{
		return method.getWrappedMethod().getReturnType() == void.class;
	}
	
	public String getReturnType()
	{
		Class<?> returnType = method.getWrappedMethod().getReturnType();
		return TypeWrapper.getJniType(returnType);
	}
	
	public String getSignature()
	{
		return method.getMethodSignature();
	}
	
	public String getJniFunction()
	{
		return method.getJniFunction();
	}
	
	public Parameter[] getParameters()
	{
		return Parameter.fromTypeArray(method.getWrappedMethod().getParameterTypes());
	}

	private final MethodWrapper method;
}

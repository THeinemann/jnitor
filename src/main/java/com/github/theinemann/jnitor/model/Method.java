/*
 *    Copyright 2016 Thomas Heinemann
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */


package com.github.theinemann.jnitor.model;

import com.github.theinemann.jnitor.wrappers.MethodWrapper;
import com.github.theinemann.jnitor.wrappers.TypeWrapper;

/**
 * Model class to provide information about methods to the engine.
 * 
 * @author Thomas Heinemann
 *
 */
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

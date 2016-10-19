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


package com.github.theinemann.jnitor.wrappers;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Wrapper class around a reflection method object.
 * 
 * @author Thomas Heinemann
 *
 */
public class MethodWrapper extends AbstractMethodWrapper {
	public MethodWrapper(Method method)
	{
		this.method = method;
	}
	
	public String getName() {
		return method.getName();
	}
	
	public static String getMethodSignature(Method method)
	{	
		return AbstractMethodWrapper.getSignatureFromTypes(method.getParameterTypes(), method.getReturnType());
	}
	
	public String getMethodSignature()
	{
		return getMethodSignature(method);
	}
	
	public boolean isStatic()
	{
		return Modifier.isStatic(method.getModifiers());
	}
	
	public String getJniFunction()
	{
		String result = "";
		Class<?> cl = method.getReturnType();
		
		String prefix = null;
		if (isStatic()) {
			prefix = "CallStatic";
		} else {
			prefix = "Call";
		}
		
		if (cl.isPrimitive())
		{
			String methodName = cl.getName();
			methodName = methodName.substring(0, 1).toUpperCase()
					   + methodName.substring(1);
			result = prefix + methodName + "Method";
		}
		else
		{
			result = prefix + "ObjectMethod";
		}
		
		return result;
	}
	
	public Method getWrappedMethod()
	{
		return method;
	}
	
	private final Method method;
}

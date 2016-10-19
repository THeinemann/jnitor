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


import java.util.Arrays;

import com.github.theinemann.jnitor.wrappers.ConstructorWrapper;
import com.github.theinemann.jnitor.wrappers.MethodWrapper;
import com.github.theinemann.jnitor.wrappers.TypeWrapper;

/**
 * Model class to provide information about types (classes and primitive types) to the engine. 
 * 
 * @author Thomas Heinemann
 *
 */
public class Type {

	public Type(TypeWrapper type) {
		this.type = type;
		
		this.macro = type.getWrappedClass().getName().toUpperCase().replace(".", "_");
	}
	
	public Type(Class<?> clazz) {
		this.type = new TypeWrapper(clazz);
		
		this.macro = type.getWrappedClass().getName().toUpperCase().replace(".", "_");
	}

	public String getClassname()
	{
		return type.getWrappedClass().getSimpleName();
	}
	
	public String getHeaderFileName()
	{
		return getQualifiedName() + ".h";
	}
	
	public String getQualifiedName()
	{
		return type.getWrappedClass().getName();
	}
	
	public String getJniQualifiedName()
	{
		return type.getJniQualifiedName();
	}
	
	public String[] getPackages() {
		String[] result = type.getWrappedClass().getName().split("\\.");
		
		return Arrays.copyOfRange(result, 0, result.length - 1);
	}
	
	public String getMacro()
	{
		return macro;
	}
	
	public Method[] getMethods()
	{
		MethodWrapper[] methods = type.getMethodWrappers();
		Method[] result = new Method[methods.length];
		for (int i = 0; i < methods.length; ++i)
		{
			result[i] = new Method(methods[i]);
		}
		
		return result;
	}
	
	public Constructor[] getConstructors()
	{
		ConstructorWrapper[] constructors = type.getConstructorWrappers();
		Constructor[] result = new Constructor[constructors.length];
		for (int i = 0; i < constructors.length; ++i)
		{
			result[i] = new Constructor(constructors[i]);
		}
		
		return result;
	}
	
	public boolean isAbstract()
	{
		return type.isAbstract();
	}
	
	private final TypeWrapper type;
	
	private final String macro;
}

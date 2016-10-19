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

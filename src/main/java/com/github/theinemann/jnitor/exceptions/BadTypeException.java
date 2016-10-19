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

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

package com.github.theinemann.jnitor.exampleClasses;

/**
 * Basic implementation of the ExampleInterface.
 * 
 * This class should not be inclued in the generation, because its objects should be accessed only through the interface
 * from C++.
 * 
 * @author Thomas Heinemann
 */
public class ExampleInterfaceImpl implements ExampleInterface {

	public ExampleInterfaceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Squares an integer.
	 * 
	 * @param x The integer to be squared.
	 * @return The squared value of x.
	 */
	@Override
	public int doSomething(int x) {
		return x*x;
	}
	
	/**
	 * Returns a new example class object, whose magic number is set to the hash code of the string parameter.
	 * 
	 * @param stringToHash The string whose has code will be the magic number of the returned example class object
	 * @return An ExampleClass object.
	 */
	public ExampleClass getExampleClass(String stringToHash) {
		return new ExampleClass(stringToHash.hashCode());
	}

}

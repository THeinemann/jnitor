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
 * Example interface class to ensure that C++ wrapping of interfaces works as intended.
 * 
 * @author Thomas Heinemann
 */
public interface ExampleInterface {
	/**
	 * Transforms an int in some way.
	 * 
	 * Details must be specified in implementing classes.
	 * 
	 * @param x The integer to transform.
	 * @return The transformed integer.
	 */
	int doSomething(int x);
}

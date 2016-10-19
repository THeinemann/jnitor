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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.theinemann.jnitor.exampleClasses.ExampleClass;
import com.github.theinemann.jnitor.wrappers.MethodWrapper;

public class MethodWrapperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetJniFunction() throws NoSuchMethodException, SecurityException {
		Class<ExampleClass> exampleClass = ExampleClass.class;
		
		MethodWrapper setMagicNumber = new MethodWrapper(exampleClass.getMethod("setMagicNumber", int.class));
		MethodWrapper isDefaultConstructed = new MethodWrapper(exampleClass.getMethod("isDefaultConstructed"));
		MethodWrapper negate = new MethodWrapper(exampleClass.getMethod("negate"));
		MethodWrapper getSquareRoot = new MethodWrapper(exampleClass.getMethod("getSquareRoot", double.class));
		
		assertEquals("CallVoidMethod", setMagicNumber.getJniFunction());
		assertEquals("CallBooleanMethod", isDefaultConstructed.getJniFunction());
		assertEquals("CallObjectMethod", negate.getJniFunction());
		assertEquals("CallStaticDoubleMethod", getSquareRoot.getJniFunction());
	}

}

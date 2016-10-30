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

import java.lang.reflect.Method;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.theinemann.jnitor.exampleClasses.ExampleClass;
import com.github.theinemann.jnitor.exampleClasses.ExampleInterface;
import com.github.theinemann.jnitor.exampleClasses.ExampleInterfaceImpl;
import com.github.theinemann.jnitor.exceptions.BadTypeException;
import com.github.theinemann.jnitor.wrappers.TypeWrapper;

public class TypeWrapperTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetTypeSignaturePrimitive() {
		assertEquals("Z", TypeWrapper.getTypeSignature(boolean.class));
		assertEquals("B", TypeWrapper.getTypeSignature(byte.class));
		assertEquals("C", TypeWrapper.getTypeSignature(char.class));
		assertEquals("S", TypeWrapper.getTypeSignature(short.class));
		assertEquals("I", TypeWrapper.getTypeSignature(int.class));
		assertEquals("J", TypeWrapper.getTypeSignature(long.class));
		assertEquals("F", TypeWrapper.getTypeSignature(float.class));
		assertEquals("D", TypeWrapper.getTypeSignature(double.class));
		assertEquals("V", TypeWrapper.getTypeSignature(void.class));
	}
	
	@Test
	public void testGetTypeSignature() {
		assertEquals("Ljava/lang/String;", TypeWrapper.getTypeSignature(String.class));
		assertEquals("Ljava/lang/Integer;", TypeWrapper.getTypeSignature(Integer.class));
	}
	
	@Test
	public void testGetTypeSignatureArray() {
		assertEquals("[Ljava/lang/String;", TypeWrapper.getTypeSignature(String[].class));
		assertEquals("[Ljava/lang/Integer;", TypeWrapper.getTypeSignature(Integer[].class));
		assertEquals("[Lcom/github/theinemann/jnitor/wrappers/TypeWrapperTest;", TypeWrapper.getTypeSignature(TypeWrapperTest[].class));
		assertEquals("[J", TypeWrapper.getTypeSignature(long[].class));
		assertEquals("[Z", TypeWrapper.getTypeSignature(boolean[].class));
	}
	
	@Test
	public void testGetJniQualifiedName() {
		assertEquals("java/lang/String", TypeWrapper.getJniQualifiedName(String.class));
		assertEquals("java/lang/Integer", TypeWrapper.getJniQualifiedName(Integer.class));
		
		assertEquals("[Ljava/lang/String;", TypeWrapper.getJniQualifiedName(String[].class));
		assertEquals("[Ljava/lang/Integer;", TypeWrapper.getJniQualifiedName(Integer[].class));
		assertEquals("[Lcom/github/theinemann/jnitor/wrappers/TypeWrapperTest;", TypeWrapper.getJniQualifiedName(TypeWrapperTest[].class));
		assertEquals("[J", TypeWrapper.getJniQualifiedName(long[].class));
		assertEquals("[Z", TypeWrapper.getJniQualifiedName(boolean[].class));
		
		// getJniQualifiedName must call an exception if called with a primitive type.
		exception.expect(BadTypeException.class);
		TypeWrapper.getJniQualifiedName(int.class);
	}
	
	@Test
	public void testGetJniType() {
		assertEquals("jboolean", TypeWrapper.getJniType(boolean.class));
		assertEquals("jbyte", TypeWrapper.getJniType(byte.class));
		assertEquals("jchar", TypeWrapper.getJniType(char.class));
		assertEquals("jshort", TypeWrapper.getJniType(short.class));
		assertEquals("jint", TypeWrapper.getJniType(int.class));
		assertEquals("jlong", TypeWrapper.getJniType(long.class));
		assertEquals("jfloat", TypeWrapper.getJniType(float.class));
		assertEquals("jdouble", TypeWrapper.getJniType(double.class));
		assertEquals("void", TypeWrapper.getJniType(void.class));
		
		assertEquals("jstring", TypeWrapper.getJniType(String.class));
		assertEquals("jclass", TypeWrapper.getJniType(Class.class));
		
		assertEquals("jobject", TypeWrapper.getJniType(Object.class));
		assertEquals("jobject", TypeWrapper.getJniType(Integer.class));
		assertEquals("jobject", TypeWrapper.getJniType(Method.class));
		
		assertEquals("jarray", TypeWrapper.getJniType(Integer[].class));
		assertEquals("jarray", TypeWrapper.getJniType(long[].class));
		assertEquals("jarray", TypeWrapper.getJniType(boolean[].class));
	}
	
	@Test
	public void testEquals() {
		final TypeWrapper typeWrapper1 = new TypeWrapper(Object.class);
		final TypeWrapper typeWrapper2 = new TypeWrapper(Object.class);
		
		assertTrue(typeWrapper1.equals(typeWrapper1));
		assertTrue(typeWrapper1.equals(typeWrapper2));
		assertTrue(typeWrapper2.equals(typeWrapper1));
		assertTrue(typeWrapper2.equals(typeWrapper2));
		
		assertEquals(typeWrapper1, typeWrapper2);
	}
	
	@Test
	public void testHashCode() {
		final TypeWrapper typeWrapper1 = new TypeWrapper(Object.class);
		final TypeWrapper typeWrapper2 = new TypeWrapper(Object.class);
		
		final TypeWrapper typeWrapper3;
		if (TypeWrapperTest.class.hashCode() != Object.class.hashCode()) {
			typeWrapper3 = new TypeWrapper(TypeWrapperTest.class);
		} else if (TypeWrapper.class.hashCode() != Object.class.hashCode()) {
			typeWrapper3 = new TypeWrapper(TypeWrapper.class);
		} else {
			System.err.println("Warning: TypeWrapperTest and TypeWrapper have the same hash code as Object!");
			typeWrapper3 = null;
		}
		
		assertEquals(typeWrapper1.hashCode(), typeWrapper2.hashCode());
		
		if (typeWrapper3 != null) {
			assertNotEquals(typeWrapper1.hashCode(), typeWrapper3.hashCode());
		}
		
	}
	
	/**
	 * 
	 * Dependent types are:
	 * - java.lang.Object (super class)
	 * - ExampleInterface (implemented interface)
	 * - String (parameter type of method getExampleClass)
	 * - ExampleClass (return type of method getExampleClass)
	 * - Class (return type of method getClass, inherited from Object)
	 * 
	 */
	@Test
	public void testGetDependentTypes() {
		final TypeWrapper typeWrapper = new TypeWrapper(ExampleInterfaceImpl.class);
		
		Set<TypeWrapper> dependentTypes = typeWrapper.getDependentTypes();
		
		assertEquals(5, dependentTypes.size());
		
		assertTrue(dependentTypes.contains(new TypeWrapper(Object.class)));
		assertTrue(dependentTypes.contains(new TypeWrapper(ExampleInterface.class)));
		
		assertTrue(dependentTypes.contains(new TypeWrapper(String.class)));
		assertTrue(dependentTypes.contains(new TypeWrapper(ExampleClass.class)));
		assertTrue(dependentTypes.contains(new TypeWrapper(Class.class)));
		
		assertFalse(dependentTypes.contains(new TypeWrapper(ExampleInterfaceImpl.class)));
		assertFalse(dependentTypes.contains(new TypeWrapper(int.class)));
		assertFalse(dependentTypes.contains(new TypeWrapper(void.class)));
	}

}

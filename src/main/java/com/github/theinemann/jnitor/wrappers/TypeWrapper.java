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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.github.theinemann.jnitor.exceptions.BadTypeException;

/**
 * Wrapper class around a reflection class object.
 * 
 * Also covers primitive types.
 * 
 * @author Thomas Heinemann
 *
 */
public class TypeWrapper {
	/**
	 * Creates a new type wrapper object for a given type
	 * 
	 * @param clazz A class object representing a Java type
	 */
	public TypeWrapper(Class<?> clazz)
	{
		cl = clazz;
		
		dependentTypes = new HashSet<>();
	}
	
	/**
	 * Returns the type signature (as used e.g. in JNI method descriptors) for a type
	 * 
	 * See the JNI documentation for the specification of the type signature.
	 * 
	 * @param cl The class object to be wrapped.
	 * @return The type signature for cl.
	 * @throws BadTypeException If cl is a primitive type not present in Java 8
	 */
	public static String getTypeSignature(Class<?> cl)
	{
		String result = "";
		if (cl.isPrimitive())
		{
			if (cl == boolean.class)
			{
				result =  "Z";
			} 
			else if (cl == byte.class)
			{
				result =  "B";
			}
			else if (cl == char.class)
			{
				result =  "C";
			}
			else if (cl == short.class)
			{
				result =  "S";
			}
			else if (cl == int.class)
			{
				result =  "I";
			}
			else if (cl == long.class)
			{
				result =  "J";
			}
			else if (cl == float.class)
			{
				result =  "F";
			}
			else if (cl == double.class)
			{
				result =  "D";
			}
			else if (cl == void.class)
			{
				result =  "V";
			}
			else {
				// Should never happen, unless new primitive types are added to Java in a future version.
				throw new BadTypeException(cl, "Unexpected primitive type");
			}
		}
		else if (cl.isArray())
		{
			result =  getJniQualifiedName(cl);
		}
		else
		{
			result =  "L" + getJniQualifiedName(cl) + ";";
		}
		return result;
	}
	
	/**
	 * Returns the type signature (as used e.g. in JNI method descriptors) for the wrapped type. 
	 * 
	 * See the JNI documentation for the specification of the type signature.
	 * 
	 * @return The type signature for cl.
	 */
	public String getTypeSignature()
	{
		return getTypeSignature(cl);
	}
	
	/**
	 * Returns the qualified name of a class as used in JNI.
	 * 
	 * The qualified name in JNI uses a slash '/' instead of a dot '.' to seperate package names etc.
	 *  
	 * The result can be used e.g. in the JNI function findClass, to find the respective class.
	 * 
	 * As a qualified name is only relevant for classes, this function may never be called for a
	 * primitive type.
	 * 
	 * @param cl A class object. Must not represent a primitive type
	 * @return The qualified name 
	 * @throws BadTypeException If called with a primitive type
	 */
	public static String getJniQualifiedName(Class<?> cl)
	{
		if (cl.isPrimitive())
		{
			throw new BadTypeException(cl, "Cannot get the qualified name of a primitive type.");
		}
			
		
		return cl.getName().replace(".", "/");
	}
	
	/**
	 * Returns the qualified name of the wrapped class as used in JNI.
	 * 
	 * The qualified name in JNI uses a slash '/' instead of a dot '.' to seperate package names etc.
	 *  
	 * The result can be used e.g. in the JNI function findClass, to find the respective class.
	 * 
	 * As a qualified name is only relevant for classes, this function may never be called on a
	 * wrapper of a primitive type.
	 * 
	 * @return The qualified name 
	 * @throws BadTypeException If called with a primitive type
	 */
	public String getJniQualifiedName()
	{
		return getJniQualifiedName(cl);
	}
	
	/**
	 * Returns the C type provided by JNI which represents a Java type.
	 * 
	 * For primitives, special typedefs are present in the JNI, as well as for arrays and
	 * the built-in types java.lang.String, and java.lang.Class. For all other types, jobject
	 * is used.
	 * 
	 * @param cl A Java class object
	 * @return A string containing the name of the respective JNI C type for cl.
	 */
	public static String getJniType(Class<?> cl) {
		String result = "";
		
		if (cl.isPrimitive())
		{
			if (cl == void.class)
			{
				result = "void";
			}
			else {
				result = "j" + cl.getName();
			}
		}
		else if (cl == String.class)
		{
			result = "jstring";
		}
		else if (cl == Class.class)
		{
			result = "jclass";
		}
		else if (cl.isArray())
		{
			result = "jarray";
		}
		else
		{
			result = "jobject";
		}
		
		return result;
	}
	
	/**
	 * Returns the C type provided by JNI which represents the wrapped Java type. 
	 * 
	 * For primitives, special typedefs are present in the JNI, as well as for arrays and
	 * the built-in types java.lang.String, and java.lang.Class. For all other types, jobject
	 * is used.
	 * 
	 * @return A string containing the name of the respective JNI C type for the wrapped type.
	 */
	public String getJniType()
	{
		return getJniType(cl);
	}
	
	public Class<?> getWrappedClass()
	{
		return cl;
	}
	
	public boolean isAbstract()
	{
		return cl.isInterface() || Modifier.isAbstract(cl.getModifiers());
	}
	
	/**
	 * Returns an array of MethodWrapper objects, which contain a wrapper for each public method of
	 * the wrapped type.
	 * 
	 * @return An array of MethodWrapper objects, which contain a wrapper for each public method of
	 * the wrapped type. 
	 */
	public MethodWrapper[] getMethodWrappers()
	{
		Method[] methods = cl.getMethods();
		MethodWrapper[] result = new MethodWrapper[methods.length];
		
		for (int i = 0; i < methods.length; ++i) {
			result[i] = new MethodWrapper(methods[i]);
		}
		
		return result;
	}
	
	/**
	 * Returns an array of ConstructorWrapper objects, which contain a wrapper for each public constructor of
	 * the wrapped type.
	 * 
	 * @return An array of ConstructorWrapper objects, which contain a wrapper for each public constructor of
	 * the wrapped type. 
	 */
	public ConstructorWrapper[] getConstructorWrappers()
	{
		Constructor<?>[] constructors = cl.getConstructors();
		ConstructorWrapper[] result = new ConstructorWrapper[constructors.length];
		
		for (int i = 0; i < constructors.length; ++i)
		{
			result[i] = new ConstructorWrapper(constructors[i]);
		}
		
		return result;
	}
	
	public Set<TypeWrapper> getDependentTypes() {
		collectDependentTypes();
		return new HashSet<>(dependentTypes);
	}
	
	private void collectDependentTypes() {
		if (cl.isPrimitive()) {
			return;
		}
		
		if (!dependentTypes.isEmpty()) {
			return;
		}
		
		Class<?> superclass = cl.getSuperclass();
		if (superclass != null) {
			dependentTypes.add(new TypeWrapper(superclass));
		}
		
		Arrays.stream(cl.getInterfaces()).forEach(clazz -> dependentTypes.add(new TypeWrapper(clazz)));
		
		for (Method method : cl.getMethods()) {
			Class<?> returnType = method.getReturnType();
			if (!returnType.isPrimitive()) {
				dependentTypes.add(new TypeWrapper(method.getReturnType()));
			}
			
			for (Class<?> paramType : method.getParameterTypes()) {
				if (!paramType.isPrimitive()) {
					dependentTypes.add(new TypeWrapper(paramType));
				}
			}
		}
		
		for (Constructor<?> method : cl.getConstructors()) {
			for (Class<?> paramType : method.getParameterTypes()) {
				if (!paramType.isPrimitive()) {
					dependentTypes.add(new TypeWrapper(paramType));
				}
			}
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		
		return ((other instanceof TypeWrapper) && (((TypeWrapper)other).getWrappedClass().equals(this.cl)));
	}
	
	@Override
	public int hashCode() {
		return cl.hashCode();
	}

	private final Set<TypeWrapper> dependentTypes;
	
	private final Class<?> cl;
}

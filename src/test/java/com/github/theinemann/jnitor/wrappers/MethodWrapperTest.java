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

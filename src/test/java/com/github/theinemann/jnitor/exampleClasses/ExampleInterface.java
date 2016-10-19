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

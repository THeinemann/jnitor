package com.github.theinemann.jnitor;

import org.junit.Test;

import com.github.theinemann.jnitor.Jnitor;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class JnitorTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public JnitorTest( String testName )
    {
        super( testName );
    }

    /**
     * Rigourous Test :-)
     */
    @Test
    public void testMain()
    {
    	String[] parameters = {"-outputDirectory", "./build/generatedTestSources",
    			"com.github.theinemann.jnitor.exampleClasses.ExampleClass",
    			"com.github.theinemann.jnitor.exampleClasses.ExampleInterface"};
        Jnitor.main(parameters);
        
    }
}

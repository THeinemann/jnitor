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

package com.github.theinemann.jnitor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;

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
     * Test to generate C++ code for some classes.
     * 
     * @throws ClassNotFoundException Leads to failing test
     */
    @Test
    public void testMain() throws ClassNotFoundException
    {
    	String[] parameters = {"-outputDirectory", "./build/generatedTestSources",
    			"com.github.theinemann.jnitor.exampleClasses.ExampleClass",
    			"com.github.theinemann.jnitor.exampleClasses.ExampleInterface"};
		try {
			Jnitor.main(parameters);
		} catch (MalformedURLException e) {
			// Because we do not provide an URL here, the exception is very unlikely, hence we do not declare it.
			throw new RuntimeException(e);
		}
    }
    
    /**
     * Tests that generation works as desired when a user-defined class path is given
     * 
     * @throws ClassNotFoundException Leads to failing test. 
     * @throws MalformedURLException Leads to failing test.
     */
    @Test
    public void testMainSeparateClassPath() throws ClassNotFoundException, MalformedURLException {
    	String[] parameters = {"-outputDirectory", "./build/generatedTestSources",
    			"-classpath", System.getProperty("user.dir") + "/exampleClasses/build/classes/main/",
    			"com.github.theinemann.jnitor.exampleClasses.SeparateExampleClass",
    			"com.github.theinemann.jnitor.JnitorController"};
		Jnitor.main(parameters);
    }
    
    /**
     * Checks that no exception is thrown when the -k switch is provided.
     * 
     * @throws ClassNotFoundException
     * @throws MalformedURLException
     */
    @Test
    public void testMainKeepGoing() throws ClassNotFoundException, MalformedURLException {
    	String[] parameters = {"-outputDirectory", "./build/tmp",
    			"-k",
    			"non.existing.Class"};
		Jnitor.main(parameters);
    }
    
    /**
     * Checks that no exception is thrown when the -k switch is provided.
     * 
     * @throws ClassNotFoundException
     * @throws MalformedURLException
     */
    @Test
    public void testMainNoKeepGoing() {
    	String[] parameters = {"-outputDirectory", "./build/tmp",
    			"non.existing.Class"};
		try {
			Jnitor.main(parameters);
		} catch (MalformedURLException e) {
			StringWriter exceptionMessageStringWriter = new StringWriter();
			PrintWriter exceptionMessagePrintWriter = new PrintWriter(exceptionMessageStringWriter);
			e.printStackTrace(exceptionMessagePrintWriter);
			
			fail("Wrong exception type MalformedUrlException:\n" + exceptionMessageStringWriter.toString());
		} catch (ClassNotFoundException e) {
			// This exception must be thrown, so the test succeeds if we are here.
			return;
		}
		
		fail("No exception was thrown.");
    }
}

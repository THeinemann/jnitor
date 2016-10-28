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
     * Rigourous Test :-)
     */
    @Test
    public void testMain()
    {
    	String[] parameters = {"-outputDirectory", "./build/generatedTestSources",
    			"com.github.theinemann.jnitor.exampleClasses.ExampleClass",
    			"com.github.theinemann.jnitor.exampleClasses.ExampleInterface",
    			"com.github.theinemann.jnitor.JnitorController"};
		try {
			Jnitor.main(parameters);
		} catch (MalformedURLException e) {
			// Because we do not provide an URL here, the exception is very unlikely, hence we do not declare it.
			throw new RuntimeException(e);
		}
        
    }
}

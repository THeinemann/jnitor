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

import java.util.List;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import com.github.theinemann.jnitor.exceptions.InitializationException;
import com.github.theinemann.jnitor.exceptions.JnitorException;
import com.github.theinemann.jnitor.exceptions.JnitorRuntimeException;

/**
 * Main class for Jnitor program.
 *
 * Contains the command line interface.
 * 
 * @author Thomas Heinemann
 * 
 */
public class Jnitor 
{
    public static void main( String[] args )
    {
    	Jnitor jnitor = new Jnitor();
    	
    	CmdLineParser parser = new CmdLineParser(jnitor);
    	
    	try {
    		parser.parseArgument(args);
    	}
    	catch (CmdLineException e) {
    		System.err.println(e.getMessage());
    		System.err.println(jnitor.getClass().getName() + " -outputDirectory DIRECTORY [CLASS...]\n");
    		
    		parser.printUsage(System.err);
    		System.exit(-1);;
    	}
    	
    	try {
			jnitor.initialize();
		} catch (InitializationException e) {
			System.err.println("Error during initialization: " + e.getMessage());
			
			System.exit(-1);
		}
    	
    	for (String className : jnitor.classNames ) {
    		jnitor.writeSingleClass(className);
    	}
    }
    
    private void initializeClassLoader() throws IllegalArgumentException {
    	if (classPath != null) {
    		
    		String[] urlStrings = classPath.split(":");
    		
    		List<URL> urls = Arrays.stream(urlStrings).map(x -> {
				try {
					return (new File(x).toURI().toURL());
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException("Illegal class path entry: " + x, e);
				}
			}).collect(Collectors.toList());
    		
    		
    		classLoader = new URLClassLoader(urls.toArray(new URL[0]), this.getClass().getClassLoader());
    		
    	}
    }

	private void initialize() throws InitializationException {
		if (outputDirectory == null) {
			throw new InitializationException("Output directory was not set.");
		}
		
		if (outputDirectory.exists() && !outputDirectory.isDirectory()) {
			throw new InitializationException(outputDirectory.getPath() + " is not a directory.");
		}
		
		if (!outputDirectory.exists()) {
			outputDirectory.mkdir();
		}
		
		try {
			initializeClassLoader();
		} catch (IllegalArgumentException e) {
			throw new InitializationException("Invalid classpath: " + this.classPath, e);
		}
	}
	
	public void writeSingleClass(String className) {
		try {
			Class<?> cl = classLoader.loadClass(className);
			writeSingleClass(cl);
		} catch (ClassNotFoundException e) {
			if (keepGoing) {
				System.err.println("Class " + className + " was not found. Will not generate sources for this class.");
			} else {
				throw new JnitorRuntimeException(e); 
			}
		}		
	}

	public void writeSingleClass(Class<?> clazz) {
		JnitorController jnitor;
    	try {
			jnitor = new JnitorController();
		} catch (InitializationException e2) {
			e2.printStackTrace();
			return;
		}
    	
    	final String fileNameWithoutExtension = outputDirectory.getAbsolutePath() + "/" + clazz.getName();
    	
    	Writer headerWriter;
    	Writer sourceWriter;
		try {
			headerWriter = new OutputStreamWriter(new FileOutputStream(fileNameWithoutExtension + ".h"));
			sourceWriter = new OutputStreamWriter(new FileOutputStream(fileNameWithoutExtension + ".cpp"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
    	
		
		try {
			jnitor.writeWrapperSources(clazz, headerWriter, sourceWriter);
		} catch (JnitorException e1) {
			e1.printStackTrace();
			return;
		}
    	
    	try {
			headerWriter.close();
			sourceWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	@Option(name="-k",
			usage="Do not abort when a given class is not found.",
			required = false)
	private boolean keepGoing = false;
    
    @Option(name="-outputDirectory",
    		usage="The directory where files are written",
    		required=true)
    private File outputDirectory = new File("./src");
    
    @Option(name="-classpath",
    		usage="Specify a custom class path which is searched for the specified classes.",
    		required = false)
    private String classPath = null;
    
    private ClassLoader classLoader = this.getClass().getClassLoader();
    
    @Argument
    private List<String> classNames = new ArrayList<String>();
}

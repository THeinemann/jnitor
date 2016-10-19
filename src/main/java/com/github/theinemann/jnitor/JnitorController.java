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

import java.io.IOException;
import java.io.Writer;

import com.github.theinemann.jnitor.exceptions.InitializationException;
import com.github.theinemann.jnitor.exceptions.JnitorException;
import com.github.theinemann.jnitor.model.Type;
import com.github.theinemann.jnitor.wrappers.TypeWrapper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Class to control the source code generation for given classes.
 * 
 * @author Thomas Heinemann
 * 
 */
public class JnitorController {

	public JnitorController() throws InitializationException {
    	cfg = new Configuration(Configuration.VERSION_2_3_23);

		cfg.setClassLoaderForTemplateLoading(cfg.getClass().getClassLoader(), "/templates");
    	
    	cfg.setDefaultEncoding("UTF-8");
    	
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    	
    	
    	try {
			this.header = cfg.getTemplate("template.h");
		} catch (IOException e) {
			throw new InitializationException(e);
		}
    	if (header == null)
    	{
    		throw new InitializationException("Template library did not return template object for header generation.");
    	}
    	
    	try {
			source = cfg.getTemplate("template.cpp");
		} catch (IOException e) {
			throw new InitializationException(e);
		}
    	if (source == null)
    	{
    		throw new InitializationException("Template library did not return template object for header generation.");
    	}
    	
	}
	
	public void writeWrapperSources(Class<?> clazz, Writer headerWriter, Writer sourceWriter) throws JnitorException {
		Type type = new Type(new TypeWrapper(clazz));
    	
    	try {
    		header.process(type, headerWriter);
			source.process(type, sourceWriter);
		} catch (TemplateException | IOException e) {
			throw new JnitorException(e);
		}
	}
	
    
    private final Configuration cfg; 
    
    private final Template header;
    private final Template source;

}

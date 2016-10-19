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

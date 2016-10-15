package local.jnitor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import local.jnitor.exceptions.InitializationException;
import local.jnitor.exceptions.JnitorException;
import local.jnitor.model.Parameter;
import local.jnitor.model.Type;
import local.jnitor.wrappers.TypeWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Hello world!
 *
 */
public class Jnitor 
{
	public Jnitor() throws InitializationException {
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
	
    public static void main( String[] args )
    {
    	Jnitor jnitor;
    	try {
			jnitor = new Jnitor();
		} catch (InitializationException e2) {
			e2.printStackTrace();
			return;
		}
    	
    	Writer headerWriter;
    	Writer sourceWriter;
		try {
			headerWriter = new OutputStreamWriter(new FileOutputStream("test.h"));
			sourceWriter = new OutputStreamWriter(new FileOutputStream("test.cpp"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return;
		}
    	
		
		try {
			jnitor.writeWrapperSources(Parameter.class, headerWriter, sourceWriter);
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
    
    private final Configuration cfg; 
    
    private final Template header;
    private final Template source;
}

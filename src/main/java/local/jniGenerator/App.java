package local.jniGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;

import local.jniGenerator.model.Parameter;
import local.jniGenerator.model.Type;
import local.jniGenerator.wrappers.TypeWrapper;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

		cfg.setClassLoaderForTemplateLoading(cfg.getClass().getClassLoader(), "/templates");
    	
    	cfg.setDefaultEncoding("UTF-8");
    	
    	cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    	
    	Template header = null;
    	try {
			header = cfg.getTemplate("template.h");
		} catch (TemplateNotFoundException e) {
			System.out.print("Could not open template for header generation: ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			System.out.print("Header template name is illegal: ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.print("Could not parse template for header generation: ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("IO error while reading template for header generation: ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	if (header == null)
    	{
    		return;
    	}
    	
    	Template source = null;
    	try {
			source = cfg.getTemplate("template.cpp");
		} catch (TemplateNotFoundException e) {
			System.out.print("Could not open template for source (cpp) file generation: ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			System.out.print("Source (cpp) file template name is illegal: ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.print("Could not parse template for source (cpp) file generation: ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.print("IO error while reading template for source (cpp) file generation: ");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	if (source == null)
    	{
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
    	
    	Type type = new Type(new TypeWrapper(Parameter.class));
    	
    	try {
    		header.process(type, headerWriter);
			source.process(type, sourceWriter);
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			headerWriter.close();
			sourceWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}

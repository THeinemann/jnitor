package local.jnitor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import local.jnitor.exceptions.InitializationException;
import local.jnitor.exceptions.JnitorException;
import local.jnitor.model.Parameter;

/**
 * Main class for Jnitor program.
 *
 * Contains the command line interface.
 */
public class Jnitor 
{
    public static void main( String[] args )
    {
    	JnitorController jnitor;
    	try {
			jnitor = new JnitorController();
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
}

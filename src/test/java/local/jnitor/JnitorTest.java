package local.jnitor;

import org.junit.Test;

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
    	String[] parameters = {"-outputDirectory", "./build/generatedTestSources", "local.jnitor.ExampleClass"};
        Jnitor.main(parameters);
    }
}

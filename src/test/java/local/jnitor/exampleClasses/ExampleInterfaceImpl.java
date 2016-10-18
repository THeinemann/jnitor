package local.jnitor.exampleClasses;

/**
 * Basic implementation of the ExampleInterface.
 * 
 * This class should not be inclued in the generation, because its objects should be accessed only through the interface
 * from C++.
 * 
 * @author Thomas Heinemann
 */
public class ExampleInterfaceImpl implements ExampleInterface {

	public ExampleInterfaceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Squares an integer.
	 * 
	 * @param x The integer to be squared.
	 * @return The squared value of x.
	 */
	@Override
	public int doSomething(int x) {
		return x*x;
	}

}

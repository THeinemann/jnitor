package local.jnitor.exampleClasses;

/**
 * Basic example class to generate source code on.
 *
 */
public class ExampleClass {

	public ExampleClass() {
		magicNumber = 42;
		defaultConstructed = true;
	}
	
	public ExampleClass(int magicNumber) {
		this.magicNumber = magicNumber;
		defaultConstructed = false;
	}
	
	public int getMagicNumber() {
		return magicNumber;
	}
	
	public void setMagicNumber(int magicNumber) {
		this.magicNumber = magicNumber;
	}
	
	public void resetMagicNumber() {
		this.magicNumber = 42;
	}
	
	public boolean isDefaultConstructed() {
		return defaultConstructed;
	}
	
	public ExampleClass negate() {
		return new ExampleClass(-magicNumber);
	}
	
	public ExampleInterface getExampleInterface() {
		return new ExampleInterfaceImpl();
	}
	
	private int magicNumber;
	private final boolean defaultConstructed;
}

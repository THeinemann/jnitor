package local.jnitor.model;

import local.jnitor.wrappers.ConstructorWrapper;

public class Constructor {
	public Constructor(ConstructorWrapper constructor) {
		this.constructor = constructor;
	}
	
	public String getSignature()
	{
		return constructor.getConstructorSignature();
	}
	
	public Parameter[] getParameters()
	{
		return Parameter.fromTypeArray(constructor.getWrappedConstructor().getParameterTypes());
	}

	
	private final ConstructorWrapper constructor;
}

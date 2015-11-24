package local.jniGenerator.wrappers;

import java.lang.reflect.Constructor;

public class ConstructorWrapper extends AbstractMethodWrapper {

	public ConstructorWrapper(Constructor<?> constructor) {
		this.constructor = constructor;
	}
	
	public static String getConstructorSignature(Constructor<?> constructor)
	{
		return AbstractMethodWrapper.getSignatureFromTypes(constructor.getParameterTypes(), void.class);
	}
	
	public String getConstructorSignature()
	{
		return getConstructorSignature(constructor);
	}
	
	public Constructor<?> getWrappedConstructor()
	{
		return constructor;
	}
	
	private final Constructor<?> constructor;
}

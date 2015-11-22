package local.jniGenerator.model;

import local.jniGenerator.MethodWrapper;
import local.jniGenerator.TypeWrapper;

public class Type {

	public Type(TypeWrapper type) {
		this.type = type;
		
		this.macro = type.getWrappedClass().getName().toUpperCase().replace(".", "_");
	}
	
	public Type(Class<?> clazz) {
		this.type = new TypeWrapper(clazz);
		
		this.macro = type.getWrappedClass().getName().toUpperCase().replace(".", "_");
	}

	public String getClassname()
	{
		return type.getWrappedClass().getSimpleName();
	}
	
	public String getHeaderFileName()
	{
		return getClassname().toLowerCase() + ".h";
	}
	
	public String getQualifiedName()
	{
		return type.getJniQualifiedName();
	}
	
	public String getMacro()
	{
		return macro;
	}
	
	public Method[] getMethods()
	{
		MethodWrapper[] methods = type.getMethodWrappers();
		Method[] result = new Method[methods.length];
		for (int i = 0; i < methods.length; ++i)
		{
			result[i] = new Method(methods[i]);
		}
		
		return result;
	}
	
	
	private final TypeWrapper type;
	
	private final String macro;
}

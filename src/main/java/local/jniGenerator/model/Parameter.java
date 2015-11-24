package local.jniGenerator.model;

import local.jniGenerator.wrappers.TypeWrapper;

public class Parameter {
	public Parameter(Class<?> type, String name)
	{
		this.jniType = TypeWrapper.getJniType(type);
		this.javaType = type.getName();
		this.name = name;
	}
	
	public static Parameter[] fromTypeArray(Class<?>[] parameters)
	{
		Parameter[] result = new Parameter[parameters.length];
		
		for (int i = 0; i < parameters.length; ++i)
		{
			result[i] = new Parameter(parameters[i], "param" + Integer.toString(i));
		}
		
		return result;
	}
	
	public String getType()
	{
		return jniType;
	}
	
	public String getJavaType()
	{
		return javaType;
	}
	
	public String getName()
	{
		return name;
	}
	
	private String jniType;
	private String javaType;
	private String name;

}
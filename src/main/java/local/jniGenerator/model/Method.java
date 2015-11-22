package local.jniGenerator.model;

import local.jniGenerator.MethodWrapper;
import local.jniGenerator.TypeWrapper;

public class Method {
	public class Parameter
	{
		private Parameter(Class<?> type, String name)
		{
			this.jniType = TypeWrapper.getJniType(type);
			this.javaType = type.getName();
			this.name = name;
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
	
	public Method(MethodWrapper method) {
		this.method = method;
	}
	
	public String getName()
	{
		return method.getName();
	}
	
	public boolean isStatic()
	{
		return method.isStatic();
	}
	
	public boolean isVoid()
	{
		return method.getWrappedMethod().getReturnType() == void.class;
	}
	
	public String getReturnType()
	{
		Class<?> returnType = method.getWrappedMethod().getReturnType();
		return TypeWrapper.getJniType(returnType);
	}
	
	public String getSignature()
	{
		return method.getMethodSignature();
	}
	
	public String getJniFunction()
	{
		return method.getJniFunction();
	}
	
	public Parameter[] getParameters()
	{
		Class<?>[] parameters = method.getWrappedMethod().getParameterTypes();
		Parameter[] result = new Parameter[parameters.length];
		
		for (int i = 0; i < parameters.length; ++i)
		{
			result[i] = new Parameter(parameters[i], "param" + Integer.toString(i));
		}
		
		return result;
	}

	MethodWrapper method;
}

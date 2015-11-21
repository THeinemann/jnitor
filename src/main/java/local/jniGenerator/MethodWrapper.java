package local.jniGenerator;

import java.lang.reflect.Method;

public class MethodWrapper {
	public MethodWrapper(Method method)
	{
		this.method = method;
	}
	
	public String getName() {
		return method.getName();
	}
	
	public static String getMethodSignature(Method method)
	{
		String result = "(";
		
		for (Class<?> paramClass : method.getParameterTypes())
		{
			result += TypeWrapper.getTypeSignature(paramClass);
		}
		
		result += ")" + TypeWrapper.getTypeSignature(method.getReturnType());
		
		return result;
	}
	
	public String getMethodSignature()
	{
		return getMethodSignature(method);
	}
	
	private Method method;
}

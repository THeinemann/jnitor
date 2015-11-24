package local.jniGenerator.wrappers;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodWrapper extends AbstractMethodWrapper {
	public MethodWrapper(Method method)
	{
		this.method = method;
	}
	
	public String getName() {
		return method.getName();
	}
	
	public static String getMethodSignature(Method method)
	{	
		return AbstractMethodWrapper.getSignatureFromTypes(method.getParameterTypes(), method.getReturnType());
	}
	
	public String getMethodSignature()
	{
		return getMethodSignature(method);
	}
	
	public boolean isStatic()
	{
		return Modifier.isStatic(method.getModifiers());
	}
	
	public String getJniFunction()
	{
		String result = "";
		Class<?> cl = method.getReturnType();
		
		if (cl.isPrimitive())
		{
			String methodName = cl.getName();
			methodName = methodName.substring(0, 1).toUpperCase()
					   + methodName.substring(1);
			result = "Call" + methodName + "Method";
		}
		else
		{
			result = "CallObjectMethod";
		}
		
		return result;
	}
	
	public Method getWrappedMethod()
	{
		return method;
	}
	
	private final Method method;
}

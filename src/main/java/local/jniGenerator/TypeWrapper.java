package local.jniGenerator;

import java.lang.reflect.Method;

public class TypeWrapper {
	public TypeWrapper(Class<?> clazz)
	{
		cl = clazz;
	}
	
	public static String getTypeSignature(Class<?> cl)
	{
		String result = "";
		if (cl.isPrimitive())
		{
			if (cl == boolean.class)
			{
				result =  "Z";
			} 
			else if (cl == byte.class)
			{
				result =  "B";
			}
			else if (cl == char.class)
			{
				result =  "C";
			}
			else if (cl == short.class)
			{
				result =  "S";
			}
			else if (cl == int.class)
			{
				result =  "I";
			}
			else if (cl == long.class)
			{
				result =  "J";
			}
			else if (cl == float.class)
			{
				result =  "F";
			}
			else if (cl == double.class)
			{
				result =  "D";
			}
			else {
				// TODO: throw
				return null;
			}
		}
		else if (cl.isArray())
		{
			result =  cl.getName();
		}
		else
		{
			result =  "L" + cl.getName() + ";";
		}
		result = result.replace(".", "/");
		return result;
	}
	
	public String getTypeSignature()
	{
		return getTypeSignature(cl);
	}
	
	public MethodWrapper[] getMethodWrappers()
	{
		Method[] methods = cl.getMethods();
		MethodWrapper[] result = new MethodWrapper[methods.length];
		
		for (int i = 0; i < methods.length; ++i) {
			result[i] = new MethodWrapper(methods[i]);
		}
		
		return result;
	}
	
	private Class<?> cl;
}

package local.jnitor.wrappers;

public abstract class AbstractMethodWrapper {

	public AbstractMethodWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	static protected String getSignatureFromTypes(Class<?>[] parameters, Class<?> returnType)
	{
		String result = "(";
		
		for (Class<?> paramClass : parameters)
		{
			result += TypeWrapper.getTypeSignature(paramClass);
		}
		
		result += ")" + TypeWrapper.getTypeSignature(returnType);
		
		return result;
	}

}

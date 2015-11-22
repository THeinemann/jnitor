package local.jniGenerator.model;

import local.jniGenerator.MethodWrapper;

public class Method {

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

	MethodWrapper method;
}

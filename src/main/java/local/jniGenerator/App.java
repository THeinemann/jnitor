package local.jniGenerator;

import java.lang.reflect.Method;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Class<?> cl = Method.class;
        
        TypeWrapper wrapper = new TypeWrapper(cl);
        
        System.out.println(wrapper.getTypeSignature());
        
        for (MethodWrapper methodWrapper : wrapper.getMethodWrappers())
        {
        	System.out.println(methodWrapper.getName() + ": " + methodWrapper.getMethodSignature());
        }
    }
}

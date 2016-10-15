package local.jnitor;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import local.jnitor.exceptions.BadTypeException;
import local.jnitor.wrappers.TypeWrapper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TypeWrapperTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetTypeSignaturePrimitive() {
		assertEquals("Z", TypeWrapper.getTypeSignature(boolean.class));
		assertEquals("B", TypeWrapper.getTypeSignature(byte.class));
		assertEquals("C", TypeWrapper.getTypeSignature(char.class));
		assertEquals("S", TypeWrapper.getTypeSignature(short.class));
		assertEquals("I", TypeWrapper.getTypeSignature(int.class));
		assertEquals("J", TypeWrapper.getTypeSignature(long.class));
		assertEquals("F", TypeWrapper.getTypeSignature(float.class));
		assertEquals("D", TypeWrapper.getTypeSignature(double.class));
		assertEquals("V", TypeWrapper.getTypeSignature(void.class));
	}
	
	@Test
	public void testGetTypeSignature() {
		assertEquals("Ljava/lang/String;", TypeWrapper.getTypeSignature(String.class));
		assertEquals("Ljava/lang/Integer;", TypeWrapper.getTypeSignature(Integer.class));
	}
	
	@Test
	public void testGetTypeSignatureArray() {
		assertEquals("[Ljava/lang/String;", TypeWrapper.getTypeSignature(String[].class));
		assertEquals("[Ljava/lang/Integer;", TypeWrapper.getTypeSignature(Integer[].class));
		assertEquals("[Llocal/jnitor/TypeWrapperTest;", TypeWrapper.getTypeSignature(TypeWrapperTest[].class));
		assertEquals("[J", TypeWrapper.getTypeSignature(long[].class));
		assertEquals("[Z", TypeWrapper.getTypeSignature(boolean[].class));
	}
	
	@Test
	public void testGetJniQualifiedName() throws BadTypeException {
		assertEquals("java/lang/String", TypeWrapper.getJniQualifiedName(String.class));
		assertEquals("java/lang/Integer", TypeWrapper.getJniQualifiedName(Integer.class));
		
		assertEquals("[Ljava/lang/String;", TypeWrapper.getJniQualifiedName(String[].class));
		assertEquals("[Ljava/lang/Integer;", TypeWrapper.getJniQualifiedName(Integer[].class));
		assertEquals("[Llocal/jnitor/TypeWrapperTest;", TypeWrapper.getJniQualifiedName(TypeWrapperTest[].class));
		assertEquals("[J", TypeWrapper.getJniQualifiedName(long[].class));
		assertEquals("[Z", TypeWrapper.getJniQualifiedName(boolean[].class));
		
		// getJniQualifiedName must call an exception if called with a primitive type.
		exception.expect(BadTypeException.class);
		TypeWrapper.getJniQualifiedName(int.class);
	}
	
	@Test
	public void testGetJniType() {
		assertEquals("jboolean", TypeWrapper.getJniType(boolean.class));
		assertEquals("jbyte", TypeWrapper.getJniType(byte.class));
		assertEquals("jchar", TypeWrapper.getJniType(char.class));
		assertEquals("jshort", TypeWrapper.getJniType(short.class));
		assertEquals("jint", TypeWrapper.getJniType(int.class));
		assertEquals("jlong", TypeWrapper.getJniType(long.class));
		assertEquals("jfloat", TypeWrapper.getJniType(float.class));
		assertEquals("jdouble", TypeWrapper.getJniType(double.class));
		assertEquals("void", TypeWrapper.getJniType(void.class));
		
		assertEquals("jstring", TypeWrapper.getJniType(String.class));
		assertEquals("jclass", TypeWrapper.getJniType(Class.class));
		
		assertEquals("jobject", TypeWrapper.getJniType(Object.class));
		assertEquals("jobject", TypeWrapper.getJniType(Integer.class));
		assertEquals("jobject", TypeWrapper.getJniType(Method.class));
		
		assertEquals("jarray", TypeWrapper.getJniType(Integer[].class));
		assertEquals("jarray", TypeWrapper.getJniType(long[].class));
		assertEquals("jarray", TypeWrapper.getJniType(boolean[].class));
	}

}

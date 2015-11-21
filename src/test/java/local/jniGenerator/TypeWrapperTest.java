package local.jniGenerator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TypeWrapperTest {

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
		assertEquals("[Llocal/jniGenerator/TypeWrapperTest;", TypeWrapper.getTypeSignature(TypeWrapperTest[].class));
		assertEquals("[J", TypeWrapper.getTypeSignature(long[].class));
	}

}

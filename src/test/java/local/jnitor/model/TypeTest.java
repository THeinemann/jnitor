package local.jnitor.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import local.jnitor.wrappers.TypeWrapper;

public class TypeTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testGetPackages() {
		final Type typeType = new Type(Type.class);
		
		String[] reference = {"local", "jnitor", "model"};
		
		assertArrayEquals(reference, typeType.getPackages());
		
	}

}

package org.codereligion.beast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import org.codereligion.beast.object.AbstractClass;
import org.codereligion.beast.object.AnotherComplexClass;
import org.codereligion.beast.object.ComplexClass;
import org.codereligion.beast.object.EmptyEnum;
import org.codereligion.beast.object.FinalClass;
import org.codereligion.beast.object.OneElementEnum;
import org.junit.Test;

/**
 * Tests helper methods of the {@link ObjectFactory}.
 * 
 * TODO extend test
 *
 * @author sgroebler
 * @since 18.08.2012
 */
public class ObjectFactoryTest {

	private static ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider<?>>emptySet());
	
	@Test
	public void testNewBeanObject() {
		final ComplexClass defaultObject = objectFactory.getDefaultObject(ComplexClass.class);
		assertNotNull(defaultObject);
		assertNotNull(defaultObject.toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNewDirtyPropertyWithNullClass() {
		objectFactory.getDirtyObject(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNewDirtyPropertyWithFinalClass() {
		objectFactory.getDirtyObject(FinalClass.class);
	}
	
	@Test
	public void testNewDirtyPropertyWithAbstractClass() {
		objectFactory.getDirtyObject(AbstractClass.class);
	}
	
	@Test
	public void testNewDirtyPropertyWithInterface() {
		objectFactory.getDirtyObject(List.class);
	}
	
	@Test
	public void testNewDirtyPropertyWithEmptyEnum() {
		final EmptyEnum emptyEnum = objectFactory.getDirtyObject(EmptyEnum.class);
		assertNull(emptyEnum);
	}
	
	@Test
	public void testNewDirtyPropertyWithOneElementEnum() {
		final OneElementEnum oneElementEnum = objectFactory.getDirtyObject(OneElementEnum.class);
		assertNull(oneElementEnum);
	}
	
	@Test
	public void testNewDirtyPropertyWithString() {
		final String string = objectFactory.getDirtyObject(String.class);

		assertNotNull(string);
		assertEquals("1", string);
	}
	
	@Test
	public void testNewDirtyPropertyWithArray() {
		final Integer[] intArray = objectFactory.getDirtyObject(Integer[].class);

		assertNotNull(intArray);
		assertEquals(1, intArray.length);
		assertEquals(Integer.valueOf(1), intArray[0]);
	}
	
	@Test
	public void testNewDirtyPropertyWithEnum() {
		final ComplexClass.Enumeration enumeration = objectFactory.getDirtyObject(ComplexClass.Enumeration.class);
		
		assertNotNull(enumeration);
		assertEquals(1, enumeration.ordinal());
	}
	
	@Test
	public void testNewDirtyPropertyWithComplexClass() {
		final ComplexClass dirtyComplexObject1 = objectFactory.getDirtyObject(ComplexClass.class);
		
		assertNotNull(dirtyComplexObject1);
		assertEquals(1, dirtyComplexObject1.hashCode());
		assertEquals("1", dirtyComplexObject1.toString());

		final ComplexClass dirtyComplexObject2 = objectFactory.getDirtyObject(ComplexClass.class);
		final AnotherComplexClass anotherDirtyComplexObject = objectFactory.getDirtyObject(AnotherComplexClass.class);

		// test equals
		assertFalse(dirtyComplexObject1.equals(null));
		assertFalse(dirtyComplexObject1.equals("foo"));
		
		assertTrue(dirtyComplexObject1.equals(dirtyComplexObject1));
		assertTrue(dirtyComplexObject1.equals(dirtyComplexObject2));
		assertTrue(dirtyComplexObject2.equals(dirtyComplexObject1));
		
		assertFalse(dirtyComplexObject1.equals(anotherDirtyComplexObject));
		assertFalse(anotherDirtyComplexObject.equals(dirtyComplexObject1));
		
		final ComplexClass defaultObject = objectFactory.getDefaultObject(ComplexClass.class);
		assertFalse(defaultObject.equals(dirtyComplexObject1));
		assertFalse(dirtyComplexObject1.equals(defaultObject));
	}
}

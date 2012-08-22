package com.codereligion.beast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;

import com.google.common.collect.Sets;

import java.util.Set;

import com.codereligion.beast.object.AbstractClass;
import com.codereligion.beast.object.AnotherComplexClass;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.EmptyEnum;
import com.codereligion.beast.object.FinalClass;
import com.codereligion.beast.object.OneElementEnum;

import com.codereligion.beast.InstanceProvider;
import com.codereligion.beast.ObjectFactory;

import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * Tests helper methods of the {@link ObjectFactory}.
 * 
 * TODO extend test with custom intance providers
 * * overriding: object, string, boxed and unboxed primitive types
 * * adding: additional providers custom providers
 * * test with enums
 * * test with arrays
 *
 * @author Sebastian Gröbler
 * @since 18.08.2012
 */
public class ObjectFactoryTest {

	private static ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider<?>>emptySet());
	
	@Test(expected = NullPointerException.class)
	public void testGetDefaultObjectWithNullClass() {
		objectFactory.getDefaultObject(null);
	}
	
	@Test
	public void testGetDefaultObject() {
		final ComplexClass defaultObject = objectFactory.getDefaultObject(ComplexClass.class);
		assertNotNull(defaultObject);
		assertNotNull(defaultObject.toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetDirtyObjectWithNullClass() {
		objectFactory.getDirtyObject(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDirtyObjectWithFinalClass() {
		objectFactory.getDirtyObject(FinalClass.class);
	}
	
	@Test
	public void testGetDirtyObjectWithAbstractClass() {
		objectFactory.getDirtyObject(AbstractClass.class);
	}
	
	@Test
	public void testGetDirtyObjectWithInterface() {
		objectFactory.getDirtyObject(List.class);
	}
	
	@Test
	public void testGetDirtyObjectWithEmptyEnum() {
		final EmptyEnum emptyEnum = objectFactory.getDirtyObject(EmptyEnum.class);
		assertNull(emptyEnum);
	}
	
	@Test
	public void testGetDirtyObjectWithOneElementEnum() {
		final OneElementEnum oneElementEnum = objectFactory.getDirtyObject(OneElementEnum.class);
		assertNull(oneElementEnum);
	}
	
	@Test
	public void testGetDirtyObjectWithString() {
		final String string = objectFactory.getDirtyObject(String.class);

		assertNotNull(string);
		assertEquals("1", string);
	}
	
	@Test
	public void testGetDirtyObjectWithArray() {
		final Integer[] intArray = objectFactory.getDirtyObject(Integer[].class);

		assertNotNull(intArray);
		assertEquals(1, intArray.length);
		assertEquals(Integer.valueOf(1), intArray[0]);
	}
	
	@Test
	public void testGetDirtyObjectWithEnum() {
		final ComplexClass.Enumeration enumeration = objectFactory.getDirtyObject(ComplexClass.Enumeration.class);
		
		assertNotNull(enumeration);
		assertEquals(1, enumeration.ordinal());
	}
	
	@Test
	public void testGetDirtyObjectWithComplexClass() {
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
	
	@Test
	@Ignore("Currently custom arrays are not featured")
	public void testCustomInstanceProviderWithBoxedPrimitiveArray() {
		final Integer[] defaultObject = new Integer[]{Integer.valueOf(21)};
		final Integer[] dirtyObject = new Integer[]{Integer.valueOf(42)};
		final InstanceProvider<Integer[]> provider = new CustomInstanceProvider<Integer[]>(defaultObject, dirtyObject);
		final Set<InstanceProvider<?>> providers = Sets.<InstanceProvider<?>>newHashSet(provider);
		final ObjectFactory factory = new ObjectFactory(providers);
		
		final int[] primitiveDefaultResult = factory.getDefaultObject(int[].class);
		assertEquals(1, primitiveDefaultResult.length);
		assertEquals(21, primitiveDefaultResult[0]);
		
		final Integer[] boxdedPrimitiveDefaultResult = factory.getDefaultObject(Integer[].class);
		assertEquals(1, boxdedPrimitiveDefaultResult.length);
		assertEquals(Integer.valueOf(21), boxdedPrimitiveDefaultResult[0]);
		
		final int[] primitiveDirtyResult = factory.getDirtyObject(int[].class);
		assertEquals(1, primitiveDirtyResult.length);
		assertEquals(21, primitiveDirtyResult[0]);
		
		final Integer[] boxedPrimitiveDirtyResult = factory.getDirtyObject(Integer[].class);
		assertEquals(1, boxedPrimitiveDirtyResult.length);
		assertEquals(Integer.valueOf(21), boxedPrimitiveDirtyResult[0]);
		
		// check that primitives and boxed primitive arrays are symmetrically
		assertEquals(Integer.valueOf(primitiveDefaultResult[0]), boxdedPrimitiveDefaultResult[0]);
		assertEquals(Integer.valueOf(primitiveDirtyResult[0]), boxedPrimitiveDirtyResult[0]);
	}
}

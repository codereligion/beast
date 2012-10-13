/*
 * Copyright 2012 The Beast Authors (www.codereligion.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codereligion.beast.internal.creation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.codereligion.beast.InstanceProvider;

import com.codereligion.beast.internal.creation.ObjectFactory;


import com.codereligion.beast.object.AbstractClass;
import com.codereligion.beast.object.AnotherComplexClass;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.EmptyEnum;
import com.codereligion.beast.object.FinalClass;
import com.codereligion.beast.object.OneElementEnum;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 * Tests helper methods of the {@link ObjectFactory}.
 * 
 * TODO extend test with custom intance providers
 * - overriding: object, string, boxed and unboxed primitive types
 * - adding: additional providers custom providers
 * - test with enums
 * - test with arrays
 * - test with propertyNames
 *
 * @author Sebastian Gröbler
 * @since 18.08.2012
 */
public class ObjectFactoryWithCustomInstanceProvidersTest {

	private static ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider>emptySet());
	
	@Test(expected = NullPointerException.class)
	public void testGetDefaultObjectWithNullClass() {
		objectFactory.getDefaultObject(null, null);
	}
	
	@Test
	public void testGetDefaultObject() {
		final Object defaultObject = objectFactory.getDefaultObject(ComplexClass.class, null);
		assertNotNull(defaultObject);
		assertTrue(defaultObject instanceof ComplexClass);
		assertNotNull(defaultObject.toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetDirtyObjectWithNullClass() {
		objectFactory.getDirtyObject(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDirtyObjectWithFinalClass() {
		objectFactory.getDirtyObject(FinalClass.class, null);
	}
	
	@Test
	public void testGetDirtyObjectWithAbstractClass() {
		objectFactory.getDirtyObject(AbstractClass.class, null);
	}
	
	@Test
	public void testGetDirtyObjectWithInterface() {
		objectFactory.getDirtyObject(List.class, null);
	}
	
	@Test
	public void testGetDirtyObjectWithEmptyEnum() {
		final Object emptyEnum = objectFactory.getDirtyObject(EmptyEnum.class, null);
		assertNull(emptyEnum);
	}
	
	@Test
	public void testGetDirtyObjectWithOneElementEnum() {
		final Object oneElementEnum = objectFactory.getDirtyObject(OneElementEnum.class, null);
		assertNull(oneElementEnum);
	}
	
	@Test
	public void testGetDirtyObjectWithString() {
		final Object string = objectFactory.getDirtyObject(String.class, null);

		assertNotNull(string);
		assertTrue(string instanceof String);
		assertEquals("1", string);
	}
	
	@Test
	public void testGetDirtyObjectWithArray() {
		final Object intArray = objectFactory.getDirtyObject(Integer[].class, null);

		assertNotNull(intArray);
		assertTrue(intArray instanceof Integer[]);
		assertEquals(1, ((Integer[])intArray).length);
		assertEquals(Integer.valueOf(1), ((Integer[])intArray)[0]);
	}
	
	@Test
	public void testGetDirtyObjectWithEnum() {
		final Object enumeration = objectFactory.getDirtyObject(ComplexClass.Enumeration.class, null);
		
		assertNotNull(enumeration);
		assertTrue(enumeration instanceof ComplexClass.Enumeration);
		assertEquals(1, ((Enum<?>)enumeration).ordinal());
	}
	
	@Test
	public void testGetDirtyObjectWithComplexClass() {
		final Object dirtyComplexObject1 = objectFactory.getDirtyObject(ComplexClass.class, null);
		
		assertNotNull(dirtyComplexObject1);
		assertTrue(dirtyComplexObject1 instanceof ComplexClass);
		assertEquals(1, dirtyComplexObject1.hashCode());
		assertEquals("1", dirtyComplexObject1.toString());

		final Object dirtyComplexObject2 = objectFactory.getDirtyObject(ComplexClass.class, null);
		final Object anotherDirtyComplexObject = objectFactory.getDirtyObject(AnotherComplexClass.class, null);

		assertTrue(dirtyComplexObject2 instanceof ComplexClass);
		assertTrue(anotherDirtyComplexObject instanceof AnotherComplexClass);
		
		// test equals
		assertFalse(dirtyComplexObject1.equals(null));
		assertFalse(dirtyComplexObject1.equals("foo"));
		
		assertTrue(dirtyComplexObject1.equals(dirtyComplexObject1));
		assertTrue(dirtyComplexObject1.equals(dirtyComplexObject2));
		assertTrue(dirtyComplexObject2.equals(dirtyComplexObject1));
		
		assertFalse(dirtyComplexObject1.equals(anotherDirtyComplexObject));
		assertFalse(anotherDirtyComplexObject.equals(dirtyComplexObject1));
		
		final Object defaultObject = objectFactory.getDefaultObject(ComplexClass.class, null);
		assertTrue(defaultObject instanceof ComplexClass);
		assertFalse(defaultObject.equals(dirtyComplexObject1));
		assertFalse(dirtyComplexObject1.equals(defaultObject));
	}
}

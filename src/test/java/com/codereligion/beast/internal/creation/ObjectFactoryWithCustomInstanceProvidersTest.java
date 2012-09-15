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

	private static ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider<?>>emptySet());
	
	@Test(expected = NullPointerException.class)
	public void testGetDefaultObjectWithNullClass() {
		objectFactory.getDefaultObject(null, null);
	}
	
	@Test
	public void testGetDefaultObject() {
		final ComplexClass defaultObject = objectFactory.getDefaultObject(ComplexClass.class, null);
		assertNotNull(defaultObject);
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
		final EmptyEnum emptyEnum = objectFactory.getDirtyObject(EmptyEnum.class, null);
		assertNull(emptyEnum);
	}
	
	@Test
	public void testGetDirtyObjectWithOneElementEnum() {
		final OneElementEnum oneElementEnum = objectFactory.getDirtyObject(OneElementEnum.class, null);
		assertNull(oneElementEnum);
	}
	
	@Test
	public void testGetDirtyObjectWithString() {
		final String string = objectFactory.getDirtyObject(String.class, null);

		assertNotNull(string);
		assertEquals("1", string);
	}
	
	@Test
	public void testGetDirtyObjectWithArray() {
		final Integer[] intArray = objectFactory.getDirtyObject(Integer[].class, null);

		assertNotNull(intArray);
		assertEquals(1, intArray.length);
		assertEquals(Integer.valueOf(1), intArray[0]);
	}
	
	@Test
	public void testGetDirtyObjectWithEnum() {
		final ComplexClass.Enumeration enumeration = objectFactory.getDirtyObject(ComplexClass.Enumeration.class, null);
		
		assertNotNull(enumeration);
		assertEquals(1, enumeration.ordinal());
	}
	
	@Test
	public void testGetDirtyObjectWithComplexClass() {
		final ComplexClass dirtyComplexObject1 = objectFactory.getDirtyObject(ComplexClass.class, null);
		
		assertNotNull(dirtyComplexObject1);
		assertEquals(1, dirtyComplexObject1.hashCode());
		assertEquals("1", dirtyComplexObject1.toString());

		final ComplexClass dirtyComplexObject2 = objectFactory.getDirtyObject(ComplexClass.class, null);
		final AnotherComplexClass anotherDirtyComplexObject = objectFactory.getDirtyObject(AnotherComplexClass.class, null);

		// test equals
		assertFalse(dirtyComplexObject1.equals(null));
		assertFalse(dirtyComplexObject1.equals("foo"));
		
		assertTrue(dirtyComplexObject1.equals(dirtyComplexObject1));
		assertTrue(dirtyComplexObject1.equals(dirtyComplexObject2));
		assertTrue(dirtyComplexObject2.equals(dirtyComplexObject1));
		
		assertFalse(dirtyComplexObject1.equals(anotherDirtyComplexObject));
		assertFalse(anotherDirtyComplexObject.equals(dirtyComplexObject1));
		
		final ComplexClass defaultObject = objectFactory.getDefaultObject(ComplexClass.class, null);
		assertFalse(defaultObject.equals(dirtyComplexObject1));
		assertFalse(dirtyComplexObject1.equals(defaultObject));
	}
}

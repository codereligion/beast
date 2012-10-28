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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.codereligion.beast.InstanceProvider;
import com.codereligion.beast.object.AbstractClass;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.EmptyEnum;
import com.codereligion.beast.object.FinalClass;
import com.codereligion.beast.object.MissingDefaultConstructor;
import java.util.List;
import java.util.Set;
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
public abstract class AbstractObjectFactoryTest {
	
	/**
	 * TODO
	 *
	 * @param beanClass
	 * @param propertyName
	 * @return
	 */
	public abstract Object getObject(Class<?> beanClass, String propertyName);
	
	/**
	 * TODO
	 *
	 * @param providers
	 * @param beanClass
	 * @param propertyName
	 * @return
	 */
	public abstract Object getObject(Set<InstanceProvider> instanceProviders, Class<?> beanClass, String propertyName);

	@Test(expected = NullPointerException.class)
	public void givenNullClassShouldThrowNpe() {
		getObject(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void giveClassWithOutDefaultConstructorShouldThrowIae() {
		getObject(MissingDefaultConstructor.class, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenFinalClassShouldThrowIae() {
		getObject(FinalClass.class, null);
	}
	
	@Test
	public void givenValidClassShouldReturnValidDefaultInstanceOfThatClass() {
		final Object object = getObject(ComplexClass.class, null);
		assertNotNull(object);
		assertTrue(object instanceof ComplexClass);
		assertNotNull(object.toString());
	}
	
	@Test
	public void givenAbstractClassShouldReturnProxy() {
		final Object object = getObject(AbstractClass.class, null);
		assertNotNull(object);
		assertTrue(object instanceof AbstractClass);
		assertNotNull(object.toString());
	}
	
	@Test
	public void givenInterfaceShouldReturnProxy() {
		final Object object = getObject(List.class, null);
		assertNotNull(object);
		assertTrue(object instanceof List);
		assertNotNull(object.toString());
	}
	
	@Test
	public void givenEmptyEnumShouldReturnNull() {
		final Object emptyEnum = getObject(EmptyEnum.class, null);
		assertNull(emptyEnum);
	}
}

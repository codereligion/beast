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

package com.codereligion.beast.internal.util;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.codereligion.beast.object.MissingDefaultConstructor;

import com.codereligion.beast.internal.util.ReflectUtil;




import com.codereligion.beast.object.ApiUser;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.RestApi;
import com.codereligion.beast.object.TypeMissmatchBetweenReadAndWriteMethods;
import com.codereligion.beast.object.User;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.Set;
import org.junit.Test;

/**
 * Tests {@link ReflectUtil}.
 * 
 * @author Sebastian Gröbler
 * @since 12.08.2012
 */
public class ReflectUtilTest {
	
	@Test(expected = NullPointerException.class)
	public void givenNullClassShouldThrowNpeWhenCallingGetSettableProperties() {
		ReflectUtil.getSettableProperties(null);
	}

	@Test
	public void givenValidClassShouldReturnValidPropertieDescriptorsWhenCallingGetSettableProperties() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSettableProperties(ComplexClass.class);
		
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		
		for (final PropertyDescriptor property : properties) {
			assertNotNull(property.getWriteMethod());
			assertNotNull(property.getReadMethod());
		}
	}
	
	@Test
	public void givenUnboundGenericsClassShouldNotCauseIntrospectionBugWhenCallingGetSettableProperties() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSettableProperties(User.class);
		
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		assertEquals(1, properties.size());
		
		final PropertyDescriptor property = properties.iterator().next();
		assertEquals(Integer.class, property.getPropertyType());
		assertNotNull(property.getWriteMethod());
		assertNotNull(property.getReadMethod());
	}
	
	@Test
	public void givenBoundGenericsClassShouldNotCauseIntrospectionBugWhenCallingGetSettableProperties() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSettableProperties(RestApi.class);
		
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		assertEquals(1, properties.size());
		
		final PropertyDescriptor property = properties.iterator().next();
		assertEquals(ApiUser.class, property.getPropertyType());
		assertNotNull(property.getWriteMethod());
		assertNotNull(property.getReadMethod());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenClassWithTypeMissmatchBetweenGetterAndSetterShouldThrowIaeWhenCallingGetSettableProperties() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSettableProperties(TypeMissmatchBetweenReadAndWriteMethods.class);
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		assertEquals(1, properties.size());
		
		final PropertyDescriptor property = properties.iterator().next();
		assertEquals(Date.class, property.getPropertyType());
		assertNotNull(property.getWriteMethod());
		assertNotNull(property.getReadMethod());
	}
	
	@Test
	public void givenClassWithoutDefaultConstructorShouldReturnFalseWhenCallingHasDefaultConstructor() {
		assertFalse(ReflectUtil.hasDefaultConstructor(MissingDefaultConstructor.class));
	}
	
	@Test
	public void givenClassWithDefaultConstructorShouldReturnWhenCallingHasDefaultConstructor() {
		assertTrue(ReflectUtil.hasDefaultConstructor(ComplexClass.class));
	}
}

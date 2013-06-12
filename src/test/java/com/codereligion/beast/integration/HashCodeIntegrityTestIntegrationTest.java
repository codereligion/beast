/**
 * Copyright 2013 www.codereligion.com
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
package com.codereligion.beast.integration;

import com.codereligion.beast.object.ExceptionThrowingSetter;

import com.codereligion.beast.internal.test.HashCodeIntegrityTest;

import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.MissingHashCodeImplementation;
import com.codereligion.beast.object.MissingPropertyInEquals;
import com.codereligion.beast.object.MissingPropertyInHashCode;

import com.codereligion.beast.HashCodeIntegrityTestBuilder;



import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests {@link HashCodeIntegrityTest}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class HashCodeIntegrityTestIntegrationTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new HashCodeIntegrityTestBuilder(null)
			.create()
			.run();
	}
	
	@Test
	public void testValidClass() {
		new HashCodeIntegrityTestBuilder(ComplexClass.class)
			.create()
			.run();
	}

	@Test(expected = AssertionError.class)
	public void testWithMissingImplemention() {
		new HashCodeIntegrityTestBuilder(MissingHashCodeImplementation.class)
			.create()
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithUnsupportedClass() {
		new HashCodeIntegrityTestBuilder(String.class)
			.create()
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInEquals() {
		new HashCodeIntegrityTestBuilder(MissingPropertyInEquals.class)
			.create()
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInHashCode() {
		new HashCodeIntegrityTestBuilder(MissingPropertyInHashCode.class)
			.create()
			.run();
	}

	@Test(expected = AssertionError.class)
	public void testValidClassWithUnnecessaryExclude() {
		new HashCodeIntegrityTestBuilder(ComplexClass.class)
			.addExcludedPropertyNames(Sets.newHashSet("anotherComplexObject"))
			.create()
			.run();
	}

	@Test
	public void testWithExceptionThrowingSetterForExcludedProperty() {
		new HashCodeIntegrityTestBuilder(ExceptionThrowingSetter.class)
			.addExcludedPropertyNames(Sets.newHashSet("foo"))
			.create()
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithExceptionThrowingSetterForIncludedProperty() {
		new HashCodeIntegrityTestBuilder(ExceptionThrowingSetter.class)
			.addIncludedPropertyNames(Sets.newHashSet("foo"))
			.create()
			.run();
	}
	
	@Test
	public void testWithExceptionThrowingSetterForNonIncludedProperty() {
		new HashCodeIntegrityTestBuilder(ExceptionThrowingSetter.class)
			.addIncludedPropertyNames(Sets.newHashSet("bar"))
			.create()
			.run();
	}
}

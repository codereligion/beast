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

package com.codereligion.beast;

import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.MissingHashCodeImplementation;
import com.codereligion.beast.object.MissingPropertyInEquals;
import com.codereligion.beast.object.MissingPropertyInHashCode;

import com.codereligion.beast.HashCodeIntegrityTest;
import com.codereligion.beast.HashCodeIntegrityTestBuilder;



import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests {@link HashCodeIntegrityTest}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class HashCodeIntegrityTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new HashCodeIntegrityTestBuilder()
			.create(null)
			.run();
	}
	
	@Test
	public void testValidClass() {
		new HashCodeIntegrityTestBuilder()
			.create(ComplexClass.class)
			.run();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new HashCodeIntegrityTestBuilder()
			.create(MissingHashCodeImplementation.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithUnsupportedClass() {
		new HashCodeIntegrityTestBuilder()
			.create(String.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInEquals() {
		new HashCodeIntegrityTestBuilder()
			.create(MissingPropertyInEquals.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInHashCode() {
		new HashCodeIntegrityTestBuilder()
			.create(MissingPropertyInHashCode.class)
			.run();
	}

	@Test(expected = AssertionError.class)
	public void testValidClassWithUnnecessaryExclude() {
		new HashCodeIntegrityTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("anotherComplexObject"))
			.create(ComplexClass.class)
			.run();
	}
	
	@Test
	public void testWithMissingPropertyInHashCodeWithExcludes() {
		new HashCodeIntegrityTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("bar"))
			.create(MissingPropertyInHashCode.class)
			.run();
	}
}

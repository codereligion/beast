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
import com.codereligion.beast.object.MissingPropertyInToString;
import com.codereligion.beast.object.MissingToStringImplementation;

import com.codereligion.beast.ToStringIntegrityTest;
import com.codereligion.beast.ToStringIntegrityTestBuilder;



import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests {@link ToStringIntegrityTest}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class ToStringIntegrityTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new ToStringIntegrityTestBuilder()
			.create(null)
			.run();
	}
	
	@Test
	public void testValidClass() {
		new ToStringIntegrityTestBuilder()
			.create(ComplexClass.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new ToStringIntegrityTestBuilder()
			.create(MissingToStringImplementation.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithUnsupportedClass() {
		new ToStringIntegrityTestBuilder()
			.create(String.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInToString() {
		new ToStringIntegrityTestBuilder()
			.create(MissingPropertyInToString.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testValidClassWithUnnecessaryExclude() {
		new ToStringIntegrityTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("anotherComplexObject"))
			.create(ComplexClass.class)
			.run();
	}
	
	@Test
	public void testWithMissingPropertyInToStringWithExcludes() {
		new ToStringIntegrityTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("bar"))
			.create(MissingPropertyInToString.class)
			.run();
	}
}

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
import com.codereligion.beast.object.ExceptionThrowingSetter;
import com.codereligion.beast.object.MissingHashCodeImplementation;
import com.codereligion.beast.object.NullPointerInHashCode;

import com.codereligion.beast.HashCodeNullSafetyTestBuilder;



import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import org.junit.Test;

/**
 * Tests {@link HashCode}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class HashCodeNullSafetyTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new HashCodeNullSafetyTestBuilder()
			.create(null)
			.run();
	}

	@Test
	public void testValidClass() {
		new HashCodeNullSafetyTestBuilder()
			.create(ComplexClass.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new HashCodeNullSafetyTestBuilder()
			.create(MissingHashCodeImplementation.class)
			.run();
	}

	@Test(expected = AssertionError.class)
	public void testWithNullPointerInHashCode() {
		new HashCodeNullSafetyTestBuilder()
			.create(NullPointerInHashCode.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithExceptionThrowingSetter() {
		new HashCodeNullSafetyTestBuilder()
			.create(ExceptionThrowingSetter.class)
			.run();
	}
	
	
	@Test
	public void testWithNullPointerInHashCodeWithExcludes() {
		new HashCodeNullSafetyTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("complexObject"))
			.create(NullPointerInHashCode.class)
			.run();
	}
}

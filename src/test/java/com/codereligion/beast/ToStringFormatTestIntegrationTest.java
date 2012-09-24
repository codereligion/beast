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

import com.codereligion.beast.object.ExceptionThrowingSetter;
import com.google.common.collect.Sets;

import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.MissingToStringImplementation;
import com.codereligion.beast.object.WrongFormatInToString;

import com.codereligion.beast.ToStringFormatTestBuilder;



import java.util.regex.Pattern;
import org.junit.Test;

/**
 * Tests {@link ToStringFormatTestBuilder}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class ToStringFormatTestIntegrationTest {

	private static final Pattern ECLIPSE_TO_STRING_PATTERN = Pattern.compile(".+ \\[(.+=.+, )*(.+=.+)?\\]");
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new ToStringFormatTestBuilder()
			.create(null, Pattern.compile(".*"))
			.run();
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithNullPattern() {
		new ToStringFormatTestBuilder()
			.create(WrongFormatInToString.class, null)
			.run();	
	}
	
	@Test
	public void testValidClass() {
		new ToStringFormatTestBuilder()
			.create(ComplexClass.class, ECLIPSE_TO_STRING_PATTERN)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new ToStringFormatTestBuilder()
			.create(MissingToStringImplementation.class, ECLIPSE_TO_STRING_PATTERN)
			.run();
	}

	@Test(expected = AssertionError.class)
	public void testWithWrongFormat() {
		new ToStringFormatTestBuilder()
			.create(WrongFormatInToString.class, ECLIPSE_TO_STRING_PATTERN)
			.run();
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testWithExceptionThrowingSetter() {
		new EqualsNullSafetyTestBuilder()
			.create(ExceptionThrowingSetter.class)
			.run();
	}
	
	@Test
	public void testWithExceptionThrowingSetterForExcludedProperty() {
		new EqualsNullSafetyTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("foo"))
			.create(ExceptionThrowingSetter.class)
			.run();
	}
}

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
package com.codereligion.beast.internal.util;

import static com.codereligion.beast.internal.util.Assert.assertFalse;
import static com.codereligion.beast.internal.util.Assert.assertTrue;
import static com.codereligion.beast.internal.util.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Tests {@link Assert}.
 *
 * @author Sebastian Gröbler
 * @since 11.09.2012
 */
public class AssertTest {
	
	@Test(expected = AssertionError.class)
	public void givenTrueShouldThrowAnAssertionErrorWhenCallingAssertFalse() {
		assertFalse(true, "foo");
	}
	
	@Test
	public void givenFalseShouldHaveNoEffectWhenCallingAssertFalse() {
		assertFalse(false, "foo");
	}
	
	@Test
	public void givenTrueShouldHaveNoEffectWhenCallingAssertTrue() {
		assertTrue(true, "foo");
	}
	
	@Test(expected = AssertionError.class)
	public void givenFalseShouldThrowAnAssertionErrorWhenCallingAssertTrue() {
		assertTrue(false, "foo");
	}
	
	@Test(expected = AssertionError.class)
	public void givenStringShouldAlwaysThrowAnAssertionError() {
		fail("foo");
	}
	
	@Test
	public void givenStringAndMessageArgumentShouldAlwaysThrowAnFormattedAssertionError() {
		try {
			fail("foo %s", "bar");
			fail();
		} catch (final AssertionError ae) {
			assertEquals("foo bar", ae.getMessage());
		}
	}
}

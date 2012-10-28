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

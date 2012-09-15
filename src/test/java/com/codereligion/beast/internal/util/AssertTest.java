package com.codereligion.beast.internal.util;

import static com.codereligion.beast.internal.util.Assert.assertFalse;
import static com.codereligion.beast.internal.util.Assert.assertTrue;
import static com.codereligion.beast.internal.util.Assert.fail;
import org.junit.Test;

/**
 * Tests {@link Assert}.
 *
 * @author Sebastian Gröbler
 * @since 11.09.2012
 */
public class AssertTest {
	
	@Test(expected = AssertionError.class)
	public void testAssertFalseWithTrue() {
		assertFalse(true, "foo");
	}
	
	@Test
	public void testAssertFalseWithFalse() {
		assertFalse(false, "foo");
	}
	
	@Test
	public void testAssertTrueWithTrue() {
		assertTrue(true, "foo");
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertTrueWithFalse() {
		assertTrue(false, "foo");
	}

	@Test(expected = AssertionError.class)
	public void testFail() {
		fail("foo");
	}
}

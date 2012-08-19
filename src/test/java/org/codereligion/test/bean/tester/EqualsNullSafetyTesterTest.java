package org.codereligion.test.bean.tester;

import com.google.common.collect.Sets;
import java.util.Collections;
import org.codereligion.test.bean.object.ComplexClass;
import org.codereligion.test.bean.object.ExceptionThrowingSetter;
import org.codereligion.test.bean.object.MissingEqualsImplementation;
import org.codereligion.test.bean.object.NullPointerInEquals;
import org.junit.Test;

/**
 * Tests {@link Equals}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class EqualsNullSafetyTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		Equals.testNullSafety(null);
	}
	
	@Test
	public void testValidClass() {
		Equals.testNullSafety(ComplexClass.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		Equals.testNullSafety(MissingEqualsImplementation.class);
	}

	@Test(expected = AssertionError.class)
	public void testNullPointerInEquals() {
		Equals.testNullSafety(NullPointerInEquals.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithExceptionThrowingSetter() {
		Equals.testNullSafety(ExceptionThrowingSetter.class);
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullClass() {
		Equals.testNullSafety(null, Collections.<String>emptySet());
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullExcludes() {
		Equals.testNullSafety(ComplexClass.class, null);
	}

	@Test
	public void testValidClassWithEmptyExcludes() {
		Equals.testNullSafety(ComplexClass.class, Collections.<String>emptySet());
	}
	
	@Test
	public void testNullPointerInEqualsWithExcludes() {
		Equals.testNullSafety(NullPointerInEquals.class, Sets.newHashSet("complexObject"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithExcludesWithExceptionThrowingSetter() {
		Equals.testNullSafety(ExceptionThrowingSetter.class, Collections.<String>emptySet());
	}
}
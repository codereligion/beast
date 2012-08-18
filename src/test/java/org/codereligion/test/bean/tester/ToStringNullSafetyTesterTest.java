package org.codereligion.test.bean.tester;

import org.codereligion.test.bean.tester.ToStringNullSafetyTester;

import org.codereligion.test.bean.object.User;

import com.google.common.collect.Sets;
import org.codereligion.test.bean.object.NullPointerInHashCode;

import org.codereligion.test.bean.object.ComplexClass;

import java.util.Collections;

import org.codereligion.test.bean.object.ExceptionThrowingSetter;

import org.codereligion.test.bean.object.NullPointerInToString;
import org.junit.Test;

/**
 * Tests {@link ToStringNullSafetyTester}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class ToStringNullSafetyTesterTest {

	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		ToString.testNullSafety(null);
	}
	
	@Test
	public void testValidClass() {
		ToString.testNullSafety(ComplexClass.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonImplementingClass() {
		ToString.testNullSafety(User.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testWithNullPointerInToString() {
		ToString.testNullSafety(NullPointerInToString.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithExceptionThrowingSetter() {
		ToString.testNullSafety(ExceptionThrowingSetter.class);
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullClass() {
		ToString.testNullSafety(null, Collections.<String>emptySet());
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullExcludes() {
		ToString.testNullSafety(ComplexClass.class, null);
	}

	@Test
	public void testValidClassWithEmptyExcludes() {
		ToString.testNullSafety(ComplexClass.class, Collections.<String>emptySet());
	}

	@Test
	public void testWithNullPointerInHashCodeWithExcludes() {
		ToString.testNullSafety(NullPointerInHashCode.class, Sets.newHashSet("complexObject"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithExcludesWithExceptionThrowingSetter() {
		ToString.testNullSafety(ExceptionThrowingSetter.class, Collections.<String>emptySet());
	}
}

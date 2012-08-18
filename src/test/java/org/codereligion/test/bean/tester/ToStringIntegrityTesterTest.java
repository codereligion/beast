package org.codereligion.test.bean.tester;

import org.codereligion.test.bean.tester.ToStringIntegrityTester;

import org.codereligion.test.bean.object.User;

import java.util.Collections;
import org.codereligion.test.bean.object.ComplexClass;

import com.google.common.collect.Sets;
import java.util.HashSet;
import org.codereligion.test.bean.object.MissingPropertyInToString;
import org.junit.Test;

/**
 * Tests {@link ToStringIntegrityTester}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class ToStringIntegrityTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		ToString.testIntegrity(null);
	}
	
	@Test
	public void testValidClass() {
		ToString.testIntegrity(ComplexClass.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonImplementingClass() {
		ToString.testIntegrity(User.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithUnsupportedClass() {
		ToString.testIntegrity(String.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInToString() {
		ToString.testIntegrity(MissingPropertyInToString.class);
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullClass() {
		ToString.testIntegrity(null, new HashSet<String>());
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullExcludes() {
		ToString.testIntegrity(MissingPropertyInToString.class, null);
	}

	@Test
	public void testValidClassWithEmptyExcludes() {
		ToString.testIntegrity(ComplexClass.class, Collections.<String>emptySet());
	}
	
	@Test(expected = AssertionError.class)
	public void testValidClassWithUnnecessaryExclude() {
		ToString.testIntegrity(ComplexClass.class, Sets.newHashSet("anotherComplexObject"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithExcludesWithUnsupportedClass() {
		ToString.testIntegrity(String.class, new HashSet<String>());
	}
	
	@Test
	public void testWithMissingPropertyInToStringWithExcludes() {
		ToString.testIntegrity(MissingPropertyInToString.class, Sets.newHashSet("bar"));
	}
}

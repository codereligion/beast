package org.codereligion.test.bean;

import java.util.HashSet;
import java.util.Set;

import org.codereligion.test.bean.object.MissingPropertyInEquals;
import org.codereligion.test.bean.object.MissingPropertyInHashCode;
import org.junit.Test;

/**
 * Tests {@link HashCodeTester}.
 * TODO test null safety
 * 
 * @author sgroebler
 * @siince 14.08.2012
 */
public class HashCodeTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testTestIntegrityWithNullClass() {
		HashCodeTester.testIntegrity(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestIntegrityWithExcludesWithNullClass() {
		HashCodeTester.testIntegrity(null, new HashSet<String>());
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestIntegrityWithExcludesWithNullExcludes() {
		HashCodeTester.testIntegrity(MissingPropertyInEquals.class, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTestIntegrityWithUnsupportedClass() {
		HashCodeTester.testIntegrity(String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTestIntegrityWithExcludesWithUnsupportedClass() {
		HashCodeTester.testIntegrity(String.class, new HashSet<String>());
	}
	
	@Test(expected = AssertionError.class)
	public void testTestIntegrityWithMissingPropertyInEquals() {
		HashCodeTester.testIntegrity(MissingPropertyInEquals.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testTestIntegrityWithMissingPropertyInHashCode() {
		HashCodeTester.testIntegrity(MissingPropertyInHashCode.class);
	}
	
	@Test
	public void testTestIntegrityWithMissingPropertyInHashCodeWithExcludes() {
		final Set<String> excludes = new HashSet<String>();
		excludes.add("bar");

		HashCodeTester.testIntegrity(MissingPropertyInHashCode.class, excludes);
	}
}

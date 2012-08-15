package org.codereligion.test.bean;

import java.util.HashSet;
import java.util.Set;

import org.codereligion.test.bean.object.MissingPropertyInToString;
import org.junit.Test;

/**
 * Tests {@link ToStringTester}.
 * 
 * TODO test null safety
 * TODO test format
 * 
 * @author sgroebler
 * @siince 14.08.2012
 */
public class ToStringTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testTestIntegrityWithNullClass() {
		ToStringTester.testIntegrity(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestIntegrityWithExcludesWithNullClass() {
		ToStringTester.testIntegrity(null, new HashSet<String>());
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestIntegrityWithExcludesWithNullExcludes() {
		ToStringTester.testIntegrity(MissingPropertyInToString.class, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTestIntegrityWithUnsupportedClass() {
		ToStringTester.testIntegrity(String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTestIntegrityWithExcludesWithUnsupportedClass() {
		ToStringTester.testIntegrity(String.class, new HashSet<String>());
	}
	
	@Test(expected = AssertionError.class)
	public void testTestIntegrityWithMissingPropertyInToString() {
		ToStringTester.testIntegrity(MissingPropertyInToString.class);
	}
	
	@Test
	public void testTestIntegrityWithMissingPropertyInToStringWithExcludes() {
		final Set<String> excludes = new HashSet<String>();
		excludes.add("bar");
		
		ToStringTester.testIntegrity(MissingPropertyInToString.class, excludes);
	}
}

package org.codereligion.test.bean;

import java.util.HashSet;
import java.util.Set;

import org.codereligion.test.bean.object.MissingPropertyInEquals;
import org.codereligion.test.bean.object.NullPointerInEquals;
import org.junit.Test;

/**
 * Tests {@link EqualsTester}.
 * 
 * TODO test null safety
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class EqualsTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testTestIntegrityWithNullClass() {
		EqualsTester.testIntegrity(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestIntegrityWithExcludesWithNullClass() {
		EqualsTester.testIntegrity(null, new HashSet<String>());
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestIntegrityWithExcludesWithNullExcludes() {
		EqualsTester.testIntegrity(MissingPropertyInEquals.class, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTestIntegrityWithUnsupportedClass() {
		EqualsTester.testIntegrity(String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTestIntegrityWithExcludesWithUnsupportedClass() {
		EqualsTester.testIntegrity(String.class, new HashSet<String>());
	}

	@Test(expected = AssertionError.class)
	public void testTestIntegrityWithMissingPropertyInEquals() {
		EqualsTester.testIntegrity(MissingPropertyInEquals.class);
	}
	
	@Test
	public void testTestIntegrityWithMissingPropertyInEqualsWithExcludes() {
		final Set<String> excludes = new HashSet<String>();
		excludes.add("complexObject");
		
		EqualsTester.testIntegrity(MissingPropertyInEquals.class, excludes);
	}

	@Test(expected = NullPointerException.class)
	public void testTestNullSafetyWithNullClass() {
		EqualsTester.testNullSafety(null);
	}
	
	@Test(expected = AssertionError.class)
	public void testTestNullSafety() {
		EqualsTester.testNullSafety(NullPointerInEquals.class);
	}
}

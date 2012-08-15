package org.codereligion.test.bean;

import java.util.HashSet;
import java.util.Set;

import org.codereligion.test.bean.object.MissingPropertyInEquals;
import org.codereligion.test.bean.object.MissingPropertyInHashCode;
import org.codereligion.test.bean.object.MissingPropertyInToString;
import org.junit.Test;

/**
 * Tests {@link BeanTester}.
 * 
 * @author sgroebler
 * @siince 14.08.2012
 */
public class BeanTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testTestEqualsWithNullClass() {
		BeanTester.testEquals(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestEqualsWithExcludesWithNullClass() {
		BeanTester.testEquals(null, new HashSet<String>());
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestEqualsWithExcludesWithNullExcludes() {
		BeanTester.testEquals(MissingPropertyInEquals.class, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTestEqualsWithUnsupportedClass() {
		BeanTester.testEquals(String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTestEqualsWithExcludesWithUnsupportedClass() {
		BeanTester.testEquals(String.class, new HashSet<String>());
	}

	@Test(expected = AssertionError.class)
	public void testTestEqualsWithMissingPropertyInEquals() {
		BeanTester.testEquals(MissingPropertyInEquals.class);
	}
	
	@Test
	public void testTestEqualsWithMissingPropertyInEqualsWithExcludes() {
		final Set<String> excludes = new HashSet<String>();
		excludes.add("complexObject");
		
		BeanTester.testEquals(MissingPropertyInEquals.class, excludes);
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestHashCodeWithNullClass() {
		BeanTester.testHashCode(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestHashCodeWithExcludesWithNullClass() {
		BeanTester.testHashCode(null, new HashSet<String>());
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestHashCodeWithExcludesWithNullExcludes() {
		BeanTester.testHashCode(MissingPropertyInEquals.class, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTestHashCodeWithUnsupportedClass() {
		BeanTester.testHashCode(String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTestHashCodeWithExcludesWithUnsupportedClass() {
		BeanTester.testHashCode(String.class, new HashSet<String>());
	}
	
	@Test(expected = AssertionError.class)
	public void testTestHashCodeWithMissingPropertyInEquals() {
		BeanTester.testHashCode(MissingPropertyInEquals.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testTestHashCodeWithMissingPropertyInHashCode() {
		BeanTester.testHashCode(MissingPropertyInHashCode.class);
	}
	
	@Test
	public void testTestHashCodeWithMissingPropertyInHashCodeWithExcludes() {
		final Set<String> excludes = new HashSet<String>();
		excludes.add("bar");

		BeanTester.testHashCode(MissingPropertyInHashCode.class, excludes);
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestToStringWithNullClass() {
		BeanTester.testToString(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestToStringWithExcludesWithNullClass() {
		BeanTester.testToString(null, new HashSet<String>());
	}
	
	@Test(expected = NullPointerException.class)
	public void testTestToStringWithExcludesWithNullExcludes() {
		BeanTester.testToString(MissingPropertyInToString.class, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTestToStringWithUnsupportedClass() {
		BeanTester.testToString(String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTestToStringWithExcludesWithUnsupportedClass() {
		BeanTester.testToString(String.class, new HashSet<String>());
	}
	
	@Test(expected = AssertionError.class)
	public void testTestToStringWithMissingPropertyInToString() {
		BeanTester.testToString(MissingPropertyInToString.class);
	}
	
	@Test
	public void testTestToStringWithMissingPropertyInToStringWithExcludes() {
		final Set<String> excludes = new HashSet<String>();
		excludes.add("bar");
		
		BeanTester.testToString(MissingPropertyInToString.class, excludes);
	}
	
	@Test
	public void testTestToStringWithWrongFormat() {
		// TODO
	}
}

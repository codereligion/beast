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

	@Test(expected = AssertionError.class)
	public void testTestEqualsWithMissingPropertyInEquals() {
		BeanTester.testEquals(MissingPropertyInEquals.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testTestHashCodeWithMissingPropertyInEquals() {
		BeanTester.testHashCode(MissingPropertyInEquals.class);
	}
	
	public void testTestEqualsWithMissingPropertyInEqualsWithExcludes() {
		final Set<String> excludes = new HashSet<String>();
		excludes.add("bar");
		
		BeanTester.testEquals(MissingPropertyInEquals.class, excludes);
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

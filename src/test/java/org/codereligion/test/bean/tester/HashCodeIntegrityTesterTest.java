package org.codereligion.test.bean.tester;

import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.HashSet;
import org.codereligion.test.bean.object.ComplexClass;
import org.codereligion.test.bean.object.MissingPropertyInEquals;
import org.codereligion.test.bean.object.MissingPropertyInHashCode;
import org.codereligion.test.bean.object.User;
import org.junit.Test;

/**
 * Tests {@link HashCode}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class HashCodeIntegrityTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		HashCode.testIntegrity(null);
	}
	
	@Test
	public void testValidClass() {
		HashCode.testIntegrity(ComplexClass.class);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNonImplementingClass() {
		HashCode.testIntegrity(User.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithUnsupportedClass() {
		HashCode.testIntegrity(String.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInEquals() {
		HashCode.testIntegrity(MissingPropertyInEquals.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInHashCode() {
		HashCode.testIntegrity(MissingPropertyInHashCode.class);
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullClass() {
		HashCode.testIntegrity(null, new HashSet<String>());
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullExcludes() {
		HashCode.testIntegrity(MissingPropertyInEquals.class, null);
	}
	
	@Test
	public void testValidClassWithEmptyExcludes() {
		HashCode.testIntegrity(ComplexClass.class, Collections.<String>emptySet());
	}
	
	@Test(expected = AssertionError.class)
	public void testValidClassWithUnnecessaryExclude() {
		HashCode.testIntegrity(ComplexClass.class, Sets.newHashSet("anotherComplexObject"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithExcludesWithUnsupportedClass() {
		HashCode.testIntegrity(String.class, new HashSet<String>());
	}
	
	@Test
	public void testWithMissingPropertyInHashCodeWithExcludes() {
		HashCode.testIntegrity(MissingPropertyInHashCode.class, Sets.newHashSet("bar"));
	}
}

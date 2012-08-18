package org.codereligion.test.bean.tester;

import com.google.common.collect.Sets;
import java.util.Collections;
import org.codereligion.test.bean.object.ComplexClass;
import org.codereligion.test.bean.object.ExceptionThrowingSetter;
import org.codereligion.test.bean.object.NullPointerInHashCode;
import org.codereligion.test.bean.object.User;
import org.junit.Test;

/**
 * Tests {@link HashCode}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class HashCodeNullSafetyTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		HashCode.testNullSafety(null);
	}

	@Test
	public void testValidClass() {
		HashCode.testNullSafety(ComplexClass.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonImplementingClass() {
		HashCode.testNullSafety(User.class);
	}

	@Test(expected = AssertionError.class)
	public void testWithNullPointerInHashCode() {
		HashCode.testNullSafety(NullPointerInHashCode.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithExceptionThrowingSetter() {
		HashCode.testNullSafety(ExceptionThrowingSetter.class);
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullClass() {
		HashCode.testNullSafety(null, Collections.<String>emptySet());
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullExcludes() {
		HashCode.testNullSafety(ComplexClass.class, null);
	}
	
	@Test
	public void testValidClassWithEmptyExcludes() {
		HashCode.testNullSafety(ComplexClass.class, Collections.<String>emptySet());
	}
	
	@Test
	public void testWithNullPointerInHashCodeWithExcludes() {
		HashCode.testNullSafety(NullPointerInHashCode.class, Sets.newHashSet("complexObject"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithExcludesWithExceptionThrowingSetter() {
		HashCode.testNullSafety(ExceptionThrowingSetter.class, Collections.<String>emptySet());
	}
}

package com.codereligion.beast;

import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.ExceptionThrowingSetter;
import com.codereligion.beast.object.MissingHashCodeImplementation;
import com.codereligion.beast.object.NullPointerInHashCode;

import com.codereligion.beast.HashCodeNullSafetyTestBuilder;



import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import org.junit.Test;

/**
 * Tests {@link HashCode}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class HashCodeNullSafetyTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new HashCodeNullSafetyTestBuilder()
			.create(null)
			.run();
	}

	@Test
	public void testValidClass() {
		new HashCodeNullSafetyTestBuilder()
			.create(ComplexClass.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new HashCodeNullSafetyTestBuilder()
			.create(MissingHashCodeImplementation.class)
			.run();
	}

	@Test(expected = AssertionError.class)
	public void testWithNullPointerInHashCode() {
		new HashCodeNullSafetyTestBuilder()
			.create(NullPointerInHashCode.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithExceptionThrowingSetter() {
		new HashCodeNullSafetyTestBuilder()
			.create(ExceptionThrowingSetter.class)
			.run();
	}
	
	
	@Test
	public void testWithNullPointerInHashCodeWithExcludes() {
		new HashCodeNullSafetyTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("complexObject"))
			.create(NullPointerInHashCode.class)
			.run();
	}
}

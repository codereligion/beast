package org.codereligion.beast;

import org.codereligion.beast.object.ComplexClass;
import org.codereligion.beast.object.ExceptionThrowingSetter;
import org.codereligion.beast.object.MissingToStringImplementation;
import org.codereligion.beast.object.NullPointerInToString;

import org.codereligion.beast.ToStringNullSafetyTest;
import org.codereligion.beast.ToStringNullSafetyTestBuilder;

import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests {@link ToStringNullSafetyTest}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class ToStringNullSafetyTesterTest {

	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new ToStringNullSafetyTestBuilder()
			.create(null)
			.run();
	}
	
	@Test
	public void testValidClass() {
		new ToStringNullSafetyTestBuilder()
			.create(ComplexClass.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new ToStringNullSafetyTestBuilder()
			.create(MissingToStringImplementation.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithNullPointerInToString() {
		new ToStringNullSafetyTestBuilder()
			.create(NullPointerInToString.class)
			.run();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithExceptionThrowingSetter() {
		new ToStringNullSafetyTestBuilder()
			.create(ExceptionThrowingSetter.class)
			.run();
	}
	
	@Test
	public void testWithNullPointerToStringCodeWithExcludes() {
		new ToStringNullSafetyTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("complexObject"))
			.create(NullPointerInToString.class)
			.run();
	}
}

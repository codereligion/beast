package org.codereligion.beast;

import org.codereligion.beast.object.ComplexClass;
import org.codereligion.beast.object.ExceptionThrowingSetter;
import org.codereligion.beast.object.MissingEqualsImplementation;
import org.codereligion.beast.object.NullPointerInEquals;

import org.codereligion.beast.EqualsNullSafetyTest;
import org.codereligion.beast.EqualsNullSafetyTestBuilder;

import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests {@link EqualsNullSafetyTest}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class EqualsNullSafetyTestTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new EqualsNullSafetyTestBuilder()
			.create(null)
			.run();
	}
	
	@Test
	public void testValidClass() {
		new EqualsNullSafetyTestBuilder()
			.create(ComplexClass.class)
			.run();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new EqualsNullSafetyTestBuilder()
			.create(MissingEqualsImplementation.class)
			.run();
	}

	@Test(expected = AssertionError.class)
	public void testNullPointerInEquals() {
		new EqualsNullSafetyTestBuilder()
			.create(NullPointerInEquals.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithExceptionThrowingSetter() {
		new EqualsNullSafetyTestBuilder()
			.create(ExceptionThrowingSetter.class)
			.run();
	}
	
	@Test
	public void testNullPointerInEqualsWithExcludes() {
		new EqualsNullSafetyTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("complexObject"))
			.create(NullPointerInEquals.class)
			.run();
	}
}
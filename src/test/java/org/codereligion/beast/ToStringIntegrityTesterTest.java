package org.codereligion.beast;

import org.codereligion.beast.object.ComplexClass;
import org.codereligion.beast.object.MissingPropertyInToString;
import org.codereligion.beast.object.MissingToStringImplementation;

import org.codereligion.beast.ToStringIntegrityTest;
import org.codereligion.beast.ToStringIntegrityTestBuilder;

import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests {@link ToStringIntegrityTest}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class ToStringIntegrityTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new ToStringIntegrityTestBuilder()
			.create(null)
			.run();
	}
	
	@Test
	public void testValidClass() {
		new ToStringIntegrityTestBuilder()
			.create(ComplexClass.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new ToStringIntegrityTestBuilder()
			.create(MissingToStringImplementation.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithUnsupportedClass() {
		new ToStringIntegrityTestBuilder()
			.create(String.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInToString() {
		new ToStringIntegrityTestBuilder()
			.create(MissingPropertyInToString.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testValidClassWithUnnecessaryExclude() {
		new ToStringIntegrityTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("anotherComplexObject"))
			.create(ComplexClass.class)
			.run();
	}
	
	@Test
	public void testWithMissingPropertyInToStringWithExcludes() {
		new ToStringIntegrityTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("bar"))
			.create(MissingPropertyInToString.class)
			.run();
	}
}

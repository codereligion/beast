package org.codereligion.beast;

import org.codereligion.beast.object.ComplexClass;
import org.codereligion.beast.object.MissingHashCodeImplementation;
import org.codereligion.beast.object.MissingPropertyInEquals;
import org.codereligion.beast.object.MissingPropertyInHashCode;

import org.codereligion.beast.HashCodeIntegrityTest;
import org.codereligion.beast.HashCodeIntegrityTestBuilder;

import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests {@link HashCodeIntegrityTest}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class HashCodeIntegrityTesterTest {
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new HashCodeIntegrityTestBuilder()
			.create(null)
			.run();
	}
	
	@Test
	public void testValidClass() {
		new HashCodeIntegrityTestBuilder()
			.create(ComplexClass.class)
			.run();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new HashCodeIntegrityTestBuilder()
			.create(MissingHashCodeImplementation.class)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithUnsupportedClass() {
		new HashCodeIntegrityTestBuilder()
			.create(String.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInEquals() {
		new HashCodeIntegrityTestBuilder()
			.create(MissingPropertyInEquals.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInHashCode() {
		new HashCodeIntegrityTestBuilder()
			.create(MissingPropertyInHashCode.class)
			.run();
	}

	@Test(expected = AssertionError.class)
	public void testValidClassWithUnnecessaryExclude() {
		new HashCodeIntegrityTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("anotherComplexObject"))
			.create(ComplexClass.class)
			.run();
	}
	
	@Test
	public void testWithMissingPropertyInHashCodeWithExcludes() {
		new HashCodeIntegrityTestBuilder()
			.addExcludedPropertyNames(Sets.newHashSet("bar"))
			.create(MissingPropertyInHashCode.class)
			.run();
	}
}

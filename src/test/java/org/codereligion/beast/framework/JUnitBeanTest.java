package org.codereligion.beast.framework;

import org.junit.Test;

import java.util.regex.Pattern;

/**
 * JUnit specific implementation of the {@link AbstractBeanTest}.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public abstract class JUnitBeanTest <T> extends AbstractBeanTest<T> {

	@Test
	@Override
	public void testEqualsIntegrity() {
		super.testEqualsIntegrity();
	}
	
	@Test
	@Override
	public void testEqualsNullSafety() {
		super.testEqualsNullSafety();
	}
	
	@Test
	@Override
	public void testHashCodeIntegrity() {
		super.testHashCodeIntegrity();
	}
	
	@Test
	@Override
	public void testHashCodeNullSafety() {
		super.testHashCodeNullSafety();
	}
	
	@Test
	@Override
	public void testToStringIntegrity() {
		super.testToStringIntegrity();
	}
	
	@Test
	@Override
	public void testToStringNullSafety() {
		super.testToStringNullSafety();
	}
	
	@Test
	@Override
	public void testToStringFormat() {
		super.testToStringFormat();
	}
	
	@Override
	protected Pattern getToStringPattern() {
		return Pattern.compile(".+ \\[(.+=.+, )*(.+=.+)?\\]");
	}
}

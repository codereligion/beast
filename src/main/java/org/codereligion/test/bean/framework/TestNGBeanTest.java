package org.codereligion.test.bean.framework;

import org.testng.annotations.Test;


/**
 * TODO document
 * TODO test
 * TODO usuage
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public abstract class TestNGBeanTest <T> extends AbsractBeanTest<T> {
	
	@Test
	@Override
	public void testEquals() {
		super.testEquals();
	}
	
	@Test
	@Override
	public void testHashCode() {
		super.testHashCode();
	}
	
	@Test
	@Override
	public void testToString() {
		super.testToString();
	}
}

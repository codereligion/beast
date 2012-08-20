package org.codereligion.beast.framework;

import org.codereligion.beast.object.ComplexClass;

/**
 * Tests {@link ComplexClass} with the TestNG implementation.
 * 
 * @author sgroebler
 * @since 15.08.2012
 */
public class ComplexClassTestNGTest extends TestNGBeanTest<ComplexClass>{

	@Override
	protected Class<ComplexClass> getClazz() {
		return ComplexClass.class;
	}
}
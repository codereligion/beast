package org.codereligion.test.bean.framework;

import org.codereligion.test.bean.object.ComplexClass;

/**
 * Tests {@link ComplexClass} with the JUnit implementation.
 * 
 * @author sgroebler
 * @since 15.08.2012
 */
public class ComplexClassJUnitTest extends JUnitBeanTest<ComplexClass>{

	@Override
	protected Class<ComplexClass> getClazz() {
		return ComplexClass.class;
	}
}
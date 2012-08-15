package org.codereligion.test.bean;

import org.codereligion.test.bean.object.ComplexClass;

/**
 * Tests {@link ComplexClass}.
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

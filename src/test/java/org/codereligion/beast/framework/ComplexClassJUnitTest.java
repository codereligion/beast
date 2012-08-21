package org.codereligion.beast.framework;

import org.codereligion.beast.object.ComplexClass;

/**
 * Tests {@link ComplexClass} with the JUnit implementation.
 * 
 * @author Sebastian Gröbler
 * @since 15.08.2012
 */
public class ComplexClassJUnitTest extends JUnitBeast<ComplexClass>{

	@Override
	protected Class<ComplexClass> getClazz() {
		return ComplexClass.class;
	}
}
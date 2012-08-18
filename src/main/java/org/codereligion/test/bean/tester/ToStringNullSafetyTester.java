package org.codereligion.test.bean.tester;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Set;
import org.codereligion.test.bean.creation.ObjectMethodNames;


/**
 * TODO update documentation
 * Tests the toString implementation of a java bean.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
final class ToStringNullSafetyTester <T> extends AbstractTester<T> {
	
	/**
	 * TODO
	 * Constructs an instance.
	 *
	 * @param beanClass
	 * @param excludedPropertyNames
	 */
	ToStringNullSafetyTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		super(beanClass, excludedPropertyNames);
		
        if (!isMethodImplemented(ObjectMethodNames.TO_STRING)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement toString.");
        }
	}
	
	@Override
	protected void test() {
		for (final PropertyDescriptor property : this.setableProperties) {
			
			final Class<?> propertyType = property.getPropertyType();
			
			if (propertyType.isPrimitive()) {
				continue;
			}
				
			final String propertyName = property.getName();
			   
            if (this.excludedPropertyNames.contains(propertyName)) {
            	continue;
            }
			
			final T dirtyObject = newBeanObject();
			final Method setter = property.getWriteMethod();
			setValue(dirtyObject, setter, null);
			
			try {
				dirtyObject.toString();
			} catch (final NullPointerException e) {
				fail("If the property '%s' is null, calling the toString method throws a NullPointerException. " +
            		 "If the property can never be null add it to the excludedPropertyNames.", propertyName);
			}
				
			// TODO should this not better be in another test
//			final boolean areEqualWithNulls = defaultToStringResult.equals(dirtyToString);
//			assertFalse(areEqualWithNulls,
//					"If the property '%s' is null the toString result should differ from " +
//					"a toString result of an instance where this property is not null.",
//					propertyName);
		}
	}
}
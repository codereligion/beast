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
final class ToStringIntegrityTester <T> extends AbstractTester<T> {

	/**
	 * TODO
	 * Constructs an instance.
	 *
	 * @param beanClass
	 * @param excludedPropertyNames
	 */
	ToStringIntegrityTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		super(beanClass, excludedPropertyNames);

        if (!isMethodImplemented(ObjectMethodNames.TO_STRING)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement toString.");
        }
	}

	@Override
	protected void test() {
		final T defaultObject = newBeanObject();
		final String defaultToStringResult  = defaultObject.toString();
		
		for (final PropertyDescriptor property : this.setableProperties) {
			
			final String propertyName = property.getName();
			final T dirtyObject = newBeanObject();
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
			final Object dirtyProperty = newDirtyProperty(propertyType);
			
			setValue(dirtyObject, setter, dirtyProperty);
			
			final boolean areEqual = defaultToStringResult.equals(dirtyObject.toString());
			final boolean isExcluded = this.excludedPropertyNames.contains(propertyName);
			final boolean isUnnecessarilyExcluded = !areEqual && isExcluded;
			
			assertFalse(isUnnecessarilyExcluded,
						"The property '%s' is contained the excludedPropertyNames, but is actually " +
						"supported by the toString implementation. Either remove it from the " +
						"excludedPropertyNames or the toString implementation.",
						propertyName);
			
			final boolean isUnintentionallyMissing = areEqual && !isExcluded;
			
			assertFalse(isUnintentionallyMissing,
						"The property '%s' is not included in the toString method. If this is " +
						"intentional add it to the excludedPropertyNames.",
						propertyName);
		}
	}
}
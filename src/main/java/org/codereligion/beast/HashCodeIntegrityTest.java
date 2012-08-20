package org.codereligion.beast;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Set;


/**
 * TODO update documentation
 * Tests the hashCode implementation of a java bean.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public final class HashCodeIntegrityTest <T> extends AbstractTest<T> {
	
	/**
	 * TODO
	 * Constructs an instance.
	 *
	 * @param beanClass
	 * @param excludedPropertyNames
	 */
	HashCodeIntegrityTest(
			final Class<T> beanClass,
			final Set<String> excludedPropertyNames,
			final ObjectFactory objectFactory) {
		
		super(beanClass, excludedPropertyNames, objectFactory);

        if (!isMethodImplemented(ObjectMethodNames.HASH_CODE)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement hashCode.");
        }
	}

	@Override
	public void run() {
		final T defaultObject = newBeanObject();
		
		for (final PropertyDescriptor property : this.setableProperties) {
			
			final String propertyName = property.getName();
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
			final T dirtyObject = newBeanObject();
			final Object dirtyProperty = this.objectFactory.getDirtyObject(propertyType);
			
			setValue(dirtyObject, setter, dirtyProperty);
			
			final boolean areEqual = defaultObject.equals(dirtyObject);
			final boolean hashCodesAreEqual = defaultObject.hashCode() == dirtyObject.hashCode();
			final boolean isEqualsHashCodeContractViolated = areEqual == true && hashCodesAreEqual == false;
			
			// hashCode and equals contract must not be violated, disregarding excludes
			assertFalse(isEqualsHashCodeContractViolated,
						"If the property '%s' is different in two instances, these two " +
						"instances are equal according to the equals method, but their hashCodes " +
						"are different. This is a violation of the equals/hashCode contract.",
						propertyName);
			
			final boolean isExcluded = this.excludedPropertyNames.contains(propertyName);
			final boolean isUnnecessarilyExcluded = !hashCodesAreEqual && isExcluded;
			
			assertFalse(isUnnecessarilyExcluded,
						"The property '%s' is contained the excludedPropertyNames, but is actually " +
						"supported by the hashCode implementation. Either remove it from the " +
						"excludedPropertyNames or the hashCode implementation.",
						propertyName);
			
			final boolean isUnintentionallyMissing = hashCodesAreEqual && !isExcluded;
			
			assertFalse(isUnintentionallyMissing,
						"The property '%s' may not be included in the hashCode implementation or the generated hashCode " +
						"may be to weak to reflect changes in this property. If the property is not included and this is " +
						"intentional add it to the excludedPropertyNames.",
						propertyName);
		}
	}
}
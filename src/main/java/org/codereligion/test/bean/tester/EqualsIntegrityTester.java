package org.codereligion.test.bean.tester;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Set;
import org.codereligion.test.bean.creation.ObjectMethodNames;


/**
 * TODO update documentation
 * Tests the equals implementation of a java bean.
 *
 * @author sgroebler
 * @since 11.08.2012
 */
final class EqualsIntegrityTester<T> extends AbstractTester<T> {

    /**
     * TODO
     * Constructs an instance.
     *
     * @param beanClass
     * @param excludedPropertyNames
     */
    EqualsIntegrityTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
        super(beanClass, excludedPropertyNames);
        
        if (!isMethodImplemented(ObjectMethodNames.EQUALS)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement equals.");
        }
    }

    @Override
    protected void test() {
        final T defaultObject = newBeanObject();
        
        assertFalse(defaultObject.equals(null),
				"Equals method for instances of %s is equals to null.",
				this.beanClassCanonicalName);

        assertTrue(defaultObject.equals(defaultObject),
        		"Equals method for instances of %s is not reflexive.",
        		this.beanClassCanonicalName);

        for (final PropertyDescriptor property : this.setableProperties) {

            final String propertyName = property.getName();
            final T dirtyObject = newBeanObject();
            final Class<?> propertyType = property.getPropertyType();
            final Method setter = property.getWriteMethod();
            final Object dirtyProperty = newDirtyProperty(propertyType);

            setValue(dirtyObject, setter, dirtyProperty);
            
            final boolean isExcluded = this.excludedPropertyNames.contains(propertyName);
            final boolean defaultObjectEqualsDirtyObject = defaultObject.equals(dirtyObject);
            final boolean dirtyObjectEqualsDefaultObject = dirtyObject.equals(defaultObject);
            final boolean isNotSymmetric = defaultObjectEqualsDirtyObject != dirtyObjectEqualsDefaultObject;
            
            assertFalse(isNotSymmetric,
            			"Equals method for instances of %s is not symmetric for property '%s'.",
            			this.beanClassCanonicalName,
            			propertyName);
            
            final boolean isUnecessarilyExcluded = !defaultObjectEqualsDirtyObject && isExcluded;
            
        	assertFalse(isUnecessarilyExcluded,
        				"The property '%s' is contained the excludedPropertyNames, but is actually " +
        				"supported by the equals implementation. Either remove it from the " +
        				"excludedPropertyNames or the equals implementation.",
        				propertyName);

            final boolean isUnintentionallyMissing = defaultObjectEqualsDirtyObject && !isExcluded;
            
        	assertFalse(isUnintentionallyMissing, 
        				"The property '%s' is not included in the equals method. If this is " +
        				"intentional add it to the excludedPropertyNames.",
        				propertyName);
        }
    }
}
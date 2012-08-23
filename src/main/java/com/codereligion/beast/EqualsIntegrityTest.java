package com.codereligion.beast;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Set;


/**
 * TODO update documentation
 * Tests the equals implementation of a java bean.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class EqualsIntegrityTest<T> extends AbstractTest<T> {

    /**
     * TODO
     * Constructs an instance.
     *
     * @param beanClass
     * @param excludedPropertyNames
     */
    EqualsIntegrityTest(
    		final Class<T> beanClass,
    		final Set<String> excludedPropertyNames,
    		final ObjectFactory objectFactory) {
    	
        super(beanClass, excludedPropertyNames, objectFactory);
        
        if (!isMethodImplemented(ObjectMethodNames.EQUALS)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement equals.");
        }
    }

    @Override
    public void run() {
        final T defaultObject = newBeanObject();
        
        assertFalse(defaultObject.equals(null),
				"Equals method for instances of %s is equals to null.",
				this.beanClassCanonicalName);

        assertTrue(defaultObject.equals(defaultObject),
        		"Equals method for instances of %s is not reflexive.",
        		this.beanClassCanonicalName);

        for (final PropertyDescriptor property : this.settableProperties) {

            final String propertyName = property.getName();
            final T dirtyObject = newBeanObject();
            final Class<?> propertyType = property.getPropertyType();
            final Method setter = property.getWriteMethod();
            final Object dirtyProperty = this.objectFactory.getDirtyObject(propertyType, propertyName);

            setValue(dirtyObject, setter, dirtyProperty);
            
            final boolean defaultObjectEqualsDirtyObject = defaultObject.equals(dirtyObject);
            final boolean dirtyObjectEqualsDefaultObject = dirtyObject.equals(defaultObject);
            final boolean isNotSymmetric = defaultObjectEqualsDirtyObject != dirtyObjectEqualsDefaultObject;
            
            assertFalse(isNotSymmetric,
            			"Equals method for instances of %s is not symmetric for property '%s'.",
            			this.beanClassCanonicalName,
            			propertyName);
            
            final boolean isExcluded = this.excludedPropertyNames.contains(propertyName);
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
package com.codereligion.beast;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Set;


/**
 * TODO update documentation
 * Tests the hashCode implementation of a java bean.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class HashCodeNullSafetyTest <T> extends AbstractTest<T> {

	/**
	 * TODO
	 * Constructs an instance.
	 *
	 * @param beanClass
	 * @param excludedPropertyNames
	 */
	HashCodeNullSafetyTest(
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
		for (final PropertyDescriptor property : this.settableProperties) {
			
			final Class<?> propertyType = property.getPropertyType();
			
			if (propertyType.isPrimitive()) {
				continue;
			}
			
			final String propertyName = property.getName();
	            
            if (this.excludedPropertyNames.contains(propertyName)) {
            	continue;
            }
				
			final Method setter = property.getWriteMethod();
			final T dirtyObject = newBeanObject();
			
			setValue(dirtyObject, setter, null);
			
			try {
				dirtyObject.hashCode();
			} catch (final NullPointerException e) {
				fail("If the property '%s' is null, calling the hashCode method throws a NullPointerException. " +
            		 "If the property can never be null add it to the excludedPropertyNames.", propertyName);
			}
		}
	}
}
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
final class EqualsNullSafetyTester<T> extends AbstractTester<T> {

    /**
     * TODO
     * Constructs an instance.
     *
     * @param beanClass
     * @param excludedPropertyNames
     */
    EqualsNullSafetyTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
        super(beanClass, excludedPropertyNames);
        
        if (!isMethodImplemented(ObjectMethodNames.EQUALS)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement equals.");
        }
    }

    @Override
    protected void test() {
        final T defaultObject = newBeanObject();
        final boolean objectIsEqualToItSelf = defaultObject.equals(defaultObject);

        assertTrue(objectIsEqualToItSelf, "Equals method for instance of '%s' is not reflexive.", this.beanClassCanonicalName);

        for (final PropertyDescriptor property : this.setableProperties) {

            final Class<?> propertyType = property.getPropertyType();
            final String propertyName = property.getName();

            if (propertyType.isPrimitive()) {
            	continue;
            }
            
            if (this.excludedPropertyNames.contains(propertyName)) {
            	continue;
            }

            final T dirtyObject = newBeanObject();
            final Method setter = property.getWriteMethod();

            setValue(dirtyObject, setter, null);

            try {
            	defaultObject.equals(dirtyObject);
            	dirtyObject.equals(defaultObject);
            	// TODO should this not better be in another test?
//                assertFalse(defaultObject.equals(dirtyObject),
//                		"If the property '%s' is null on one instance and not null on another " +
//                		"instance the equals method should return false when applied to those instances.",
//                		propertyName);
//                
//                assertFalse(dirtyObject.equals(defaultObject),
//                		"If the property '%s' is null on one instance and not null on another " +
//				    	"instance the equals method should return false when applied to those instances.",
//				    	propertyName);
                
            } catch (final NullPointerException e) {
            	fail("If the property '%s' is null, calling the equals method throws a NullPointerException. " +
            		 "If the property can never be null add it to the excludedPropertyNames.", propertyName);
            }
        }
    }
}
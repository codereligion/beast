/*
 * Copyright 2012 The Beast Authors (www.codereligion.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codereligion.beast.internal.test;

import static com.codereligion.beast.internal.util.Assert.fail;

import java.lang.reflect.InvocationTargetException;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.creation.ObjectMethodNames;
import java.beans.PropertyDescriptor;
import java.util.Set;


/**
 * TODO update documentation
 * Tests the equals implementation of a java bean.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class EqualsNullSafetyTest extends AbstractNullSafetyTest {
	
    /**
     * TODO
	 * Constructs a new instance.
	 *
	 * @param beanClass
	 * @param objectFactory
	 * @param excludedPropertyNames
	 */
	public EqualsNullSafetyTest(
    		final Class<?> beanClass,
    		final ObjectFactory objectFactory,
    		final Set<String> excludedPropertyNames) {
    	
        super(beanClass, objectFactory, excludedPropertyNames);
        
        if (!isMethodImplemented(ObjectMethodNames.EQUALS)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement equals.");
        }
    }

    @Override
    public void run() {
        final Object defaultObject = newBeanObject();
        for (final PropertyDescriptor property : this.settableProperties) {

            final Class<?> propertyType = property.getPropertyType();
            final String propertyName = property.getName();

            if (propertyType.isPrimitive()) {
            	continue;
            }

            final Object dirtyObject = newBeanObject();

            try {
	            setValue(dirtyObject, property, null);
	            boolean equalsThrowsNullPointerException = false;
	            
	            try {
	            	defaultObject.equals(dirtyObject);
	            	dirtyObject.equals(defaultObject);
	            } catch (final NullPointerException e) {
	            	equalsThrowsNullPointerException = true;
	            }
	            
	            final boolean isExcluded = this.excludedPropertyNames.contains(propertyName);
	            final boolean equalsThrowsUnexpectedNullPointerException = !isExcluded && equalsThrowsNullPointerException;
	            if (equalsThrowsUnexpectedNullPointerException) {
	            	fail("If the property '%s' is null, calling the equals method throws a NullPointerException. " +
	            			"Add the property name to the excludedPropertyNames, if it can never be null.", propertyName);
	            }
	            
	            final boolean unnecessarilyExcluded = isExcluded && !equalsThrowsNullPointerException;
	            if (unnecessarilyExcluded) {
	            	fail("The property '%s' is contained the excludedPropertyNames, but is actually handled null-safe. " +
	            			"Remove the property from the excludedPropertyNames.", propertyName);
	            }
            } catch (final InvocationTargetException e) {
            	handlePropertySetterExcetion(property, e);
            }
            
        }
    }
    

	@Override
    public boolean equals(final Object obj) {
		if (this == obj) {
		    return true;
	    }
	    if (obj == null) {
		    return false;
	    }
	    if (getClass() != obj.getClass()) {
		    return false;
	    }
	    
        final EqualsNullSafetyTest other = (EqualsNullSafetyTest) obj;
	    
	    if (!this.beanClass.equals(other.beanClass)) {
		    return false;
	    } 
	    if (!this.excludedPropertyNames.equals(other.excludedPropertyNames)) {
		    return false;
	    } 
	    if (!this.beanClassCanonicalName.equals(other.beanClassCanonicalName)) {
	    	return false;
	    } 
	    if (!this.objectFactory.equals(other.objectFactory)) {
		    return false;
	    } 
	    if (!this.settableProperties.equals(other.settableProperties)) {
		    return false;
	    }
    	return true;
    }

	@Override
    public String toString() {
	    final StringBuilder builder = new StringBuilder();
	    builder.append("EqualsNullSafetyTest [beanClass=");
	    builder.append(this.beanClass);
	    builder.append(", excludedPropertyNames=");
	    builder.append(this.excludedPropertyNames);
	    builder.append(", beanClassCanonicalName=");
	    builder.append(this.beanClassCanonicalName);
	    builder.append(", settableProperties=");
	    builder.append(this.settableProperties);
	    builder.append(", objectFactory=");
	    builder.append(this.objectFactory);
	    builder.append("]");
	    return builder.toString();
    }
}
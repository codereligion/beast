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
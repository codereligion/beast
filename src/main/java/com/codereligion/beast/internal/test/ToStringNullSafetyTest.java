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

import java.util.Collections;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.creation.ObjectMethodNames;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Set;


/**
 * TODO update documentation
 * Tests the toString implementation of a java bean.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringNullSafetyTest <T> extends AbstractTest<T> {
	
	/**
	 * TODO
	 */
	private final Set<String> excludedPropertyNames;
	
	/**
	 * TODO
	 * Constructs a new instance.
	 *
	 * @param beanClass
	 * @param objectFactory
	 * @param excludedPropertyNames
	 */
	public ToStringNullSafetyTest(
			final Class<T> beanClass,
			final ObjectFactory objectFactory,
			final Set<String> excludedPropertyNames) {
		
		super(beanClass, objectFactory);
		
        if (!isMethodImplemented(ObjectMethodNames.TO_STRING)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement toString.");
        }
        

        if (excludedPropertyNames == null) {
    		throw new NullPointerException("excludedPropertyNames must not be null.");
    	}
    	
    	this.excludedPropertyNames = Collections.unmodifiableSet(excludedPropertyNames);
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
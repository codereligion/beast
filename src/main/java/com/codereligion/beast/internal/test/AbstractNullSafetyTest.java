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

import java.beans.PropertyDescriptor;

import com.codereligion.beast.internal.creation.ObjectFactory;
import java.util.Collections;
import java.util.Set;


/**
 * TODO update documentation
 * Tests the equals implementation of a java bean.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class AbstractNullSafetyTest<T> extends AbstractTest<T> {
	
	/**
	 * TODO
	 */
	protected final Set<String> excludedPropertyNames;

    /**
     * TODO
	 * Constructs a new instance.
	 *
	 * @param beanClass
	 * @param objectFactory
	 * @param excludedPropertyNames
	 */
	public AbstractNullSafetyTest(
    		final Class<T> beanClass,
    		final ObjectFactory objectFactory,
    		final Set<String> excludedPropertyNames) {
    	
        super(beanClass, objectFactory);
        
        if (excludedPropertyNames == null) {
    		throw new NullPointerException("excludedPropertyNames must not be null.");
    	}
    	
    	this.excludedPropertyNames = Collections.unmodifiableSet(excludedPropertyNames);
    }
	

	@Override
    protected void handlePropertySetterExcetion(final PropertyDescriptor property, final Throwable e) {

		final String propertyName = property.getName();
	    if (!this.excludedPropertyNames.contains(propertyName)) {
	    	final String message = String.format("Calling the setter of the property '%s' threw an exception. " +
												 "The setter call can be avoided by excluding the property from the test.",
												 propertyName);
			throw new IllegalArgumentException(message, e);
	    }
    }
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + this.beanClass.hashCode();
	    result = prime * result + this.excludedPropertyNames.hashCode();
	    result = prime * result + this.beanClassCanonicalName.hashCode();
	    result = prime * result + this.objectFactory.hashCode();
	    result = prime * result + this.settableProperties.hashCode();
	    return result;
    }
}
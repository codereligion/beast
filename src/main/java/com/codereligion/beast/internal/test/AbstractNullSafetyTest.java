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

import com.codereligion.beast.internal.creation.ObjectFactory;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;


/**
 * Abstract test which provides the basic functionality for a null-safety test.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class AbstractNullSafetyTest extends AbstractTest {
	
	/**
	 * The names of the properties excluded from the test.
	 */
	protected final Set<String> excludedPropertyNames;

    /**
	 * Constructs a new instance of this test for the given {@code beanClass}
	 * using the given {@code objectFactory} and {@code excludedPropertyNames}.
	 *
	 * @param beanClass the {@link Class} to test
	 * @param objectFactory the {@link ObjectFactory} to use
	 * @param excludedPropertyNames the names of the properties to exclude from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 */
	public AbstractNullSafetyTest(
    		final Class<?> beanClass,
    		final ObjectFactory objectFactory,
    		final Set<String> excludedPropertyNames) {
    	
        super(beanClass, objectFactory);
        
        if (excludedPropertyNames == null) {
    		throw new NullPointerException("excludedPropertyNames must not be null.");
    	}
    	
    	this.excludedPropertyNames = Collections.unmodifiableSet(excludedPropertyNames);
    }
	

	@Override
    public void handleInvocationTargetException(final PropertyDescriptor property, final InvocationTargetException e) {

		final String propertyName = property.getName();
	    if (!this.excludedPropertyNames.contains(propertyName)) {
	    	final String message = String.format("Calling the setter of the property '%s' threw an exception. " +
												 "The setter call can be avoided by excluding the property from the test.",
												 propertyName);
			throw new IllegalArgumentException(message, e);
	    }
    }
	
	/**
	 * Compares the given object by verifying that:
	 * 
	 * <ul>
	 * <li> it is not {@code null}
	 * <li> it is an instance of {@link AbstractNullSafetyTest}
	 * <li> the given object has the same {@code beanClass} as this object
	 * <li> the given object has the same {@code objectFactory} as this object
	 * <li> the given object has the same {@code excludedPropertyNames} as this object
	 * </ul>
	 * 
	 * This method should be used by sub-classes to avoid duplication.
	 */
	@Override
    public boolean equals(final Object obj) {
		if (this == obj) {
		    return true;
	    }
	    if (obj == null) {
		    return false;
	    }
	    if (!(obj instanceof AbstractNullSafetyTest)) {
		    return false;
	    }
	    
        final AbstractNullSafetyTest other = (AbstractNullSafetyTest) obj;
        
        if (!super.equals(other)) {
        	return false;
        }
	    
	    if (!this.excludedPropertyNames.equals(other.excludedPropertyNames)) {
	    	return false;
	    } 
    	return true;
    }

	/**
	 * Generates a hash code based on the unique and visible members of this class.
	 * This method should be used by sub-classes to avoid duplication.
	 */
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = super.hashCode();
	    result = prime * result + this.excludedPropertyNames.hashCode();
	    return result;
    }
	
	/**
	 * Generates a concatenated string which contains key/value pairs of every unique
	 * and visible member of this class with its according value. This method should be
	 * used by sub-classes to avoid duplication.
	 */
	@Override
    public String toString() {
	    final StringBuilder builder = new StringBuilder();
	    builder.append(super.toString());
	    builder.append(", excludedPropertyNames=");
	    builder.append(this.excludedPropertyNames);
	    return builder.toString();
    }
}
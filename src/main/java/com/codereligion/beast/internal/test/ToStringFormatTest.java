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

import static com.codereligion.beast.internal.util.Assert.assertTrue;

import java.util.Collections;

import java.util.Set;

import java.beans.PropertyDescriptor;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.creation.ObjectMethodNames;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * TODO update documentation
 * Tests the toString implementation of a java bean.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringFormatTest <T> extends AbstractTest<T> {
	
	/**
	 * TODO
	 */
	private final Set<String> excludedPropertyNames;
	
	/**
	 * The compiled regular expression the toString result should match.
	 */
	private Pattern toStringPattern;

	/**
	 * TODO
	 * Constructs a new instance.
	 *
	 * @param beanClass
	 * @param objectFactory
	 * @param pattern
	 */
	public ToStringFormatTest(
			final Class<T> beanClass,
			final ObjectFactory objectFactory,
			final Pattern pattern,
			final Set<String> excludedPropertyNames) {
		
		super(beanClass, objectFactory);
		
		if (pattern == null) {
			throw new NullPointerException("pattern must not be null.");
		}

        if (excludedPropertyNames == null) {
    		throw new NullPointerException("excludedPropertyNames must not be null.");
    	}
    	
    	this.excludedPropertyNames = Collections.unmodifiableSet(excludedPropertyNames);

        if (!isMethodImplemented(ObjectMethodNames.TO_STRING)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement toString.");
        }
		
		this.toStringPattern = pattern;
	}
	
	@Override
	public void run() {
		final T defaultObject = newBeanObject();
		final String defaultToStringResult  = defaultObject.toString();

		final Matcher matcher = this.toStringPattern.matcher(defaultToStringResult);
		final boolean toStringMatchesPattern = matcher.matches();
		
		assertTrue(toStringMatchesPattern, 
				"The required pattern '%s' was not matched by the toString result: '%s'.",
				this.toStringPattern.pattern(),
				defaultToStringResult);
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
	    result = prime * result + this.toStringPattern.hashCode();
	    result = prime * result + this.beanClassCanonicalName.hashCode();
	    result = prime * result + this.objectFactory.hashCode();
	    result = prime * result + this.settableProperties.hashCode();
	    return result;
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
	    
	    @SuppressWarnings("rawtypes")
        final ToStringFormatTest other = (ToStringFormatTest) obj;
	    
	    if (!this.beanClass.equals(other.beanClass)) {
		    return false;
	    } 
	    if (!this.toStringPattern.equals(other.toStringPattern)) {
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
	    builder.append("ToStringFormatTest [beanClass=");
	    builder.append(this.beanClass);
	    builder.append(", toStringPattern=");
	    builder.append(this.toStringPattern);
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
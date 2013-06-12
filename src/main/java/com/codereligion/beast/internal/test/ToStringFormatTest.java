/**
 * Copyright 2013 www.codereligion.com
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
import static com.codereligion.beast.internal.util.Assert.fail;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.creation.ObjectMethodNames;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Tests the toString implementation of the class under test for the following criteria:
 * 
 * <ul>
 * <li> the toString method must be implemented
 * <li> the result must comply to the specified {@link Pattern}
 * </ul>
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringFormatTest extends AbstractTest {
	
	/**
	 * The names of the properties excluded from the test.
	 */
	private final Set<String> excludedPropertyNames;
	
	/**
	 * The compiled regular expression the toString result should match.
	 */
	private Pattern toStringPattern;

	/**
	 * Constructs a new instance of this test for the given {@code beanClass}
	 * using the given {@code objectFactory}, {@code pattern} and {@code excludedPropertyNames}.
	 *
	 * @param beanClass the {@link Class} to test
	 * @param objectFactory the {@link ObjectFactory} to use
	 * @param pattern the pattern to which to toString result must comply
	 * @param excludedPropertyNames the names of the properties to exclude from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 */
	public ToStringFormatTest(
			final Class<?> beanClass,
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
		
		if (!isMethodImplemented(ObjectMethodNames.TO_STRING)) {
        	fail("The given class %s does not implement toString.", this.beanClassCanonicalName);
        }
		
		final Object defaultObject = newBeanObject();
		final String defaultToStringResult  = defaultObject.toString();

		final Matcher matcher = this.toStringPattern.matcher(defaultToStringResult);
		final boolean toStringMatchesPattern = matcher.matches();
		
		assertTrue(toStringMatchesPattern, 
				"The required pattern '%s' was not matched by the toString result: '%s'.",
				this.toStringPattern.pattern(),
				defaultToStringResult);
	}

	@Override
    public void handleInvocationTargetException(
    		final PropertyDescriptor property,
    		final InvocationTargetException exception) {
		
		final String propertyName = property.getName();
	    if (!this.excludedPropertyNames.contains(propertyName)) {
	    	final String message = String.format("Calling the setter of the property '%s' threw an exception. " +
												 "The setter call can be avoided by excluding the property from the test.",
												 propertyName);
			throw new IllegalArgumentException(message, exception);
	    }
    }

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = super.hashCode();
	    result = prime * result + this.toStringPattern.hashCode();
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
	    
    	return super.equals(obj);
    }

	@Override
    public String toString() {
	    final StringBuilder builder = new StringBuilder();
	    builder.append("ToStringFormatTest [");
	    builder.append(super.toString());
	    builder.append(", toStringPattern=");
	    builder.append(this.toStringPattern);
	    builder.append("]");
	    return builder.toString();
    }
}
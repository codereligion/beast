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


import java.util.Set;
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
	 * The compiled regular expression the toString result should match.
	 */
	private Pattern toStringPattern;

	/**
	 * TODO 
	 * Constructs an instance.
	 *
	 * @param beanClass
	 * @param pattern
	 */
	ToStringFormatTest(
			final Class<T> beanClass,
			final Set<String> excludedPropertyNames,
			final ObjectFactory objectFactory,
			final Pattern pattern) {
		
		super(beanClass, excludedPropertyNames, objectFactory);
		
		if (pattern == null) {
			throw new NullPointerException("pattern must not be null.");
		}

        if (!isMethodImplemented(ObjectMethodNames.TO_STRING)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement toString.");
        }
		
		this.toStringPattern = pattern;
	}
	
	@Override
	public void run() {
		// TODO check if not excluded propertyNames appear in the toString result?
		final T defaultObject = newBeanObject();
		final String defaultToStringResult  = defaultObject.toString();

		final Matcher matcher = this.toStringPattern.matcher(defaultToStringResult);
		final boolean toStringMatchesPattern = matcher.matches();
		
		assertTrue(toStringMatchesPattern, 
				"The required pattern '%s' was not matched by the toString result: '%s'.",
				this.toStringPattern.pattern(),
				defaultToStringResult);
	}
}
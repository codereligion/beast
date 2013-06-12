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
package com.codereligion.beast.internal.test.strategy;


import java.lang.reflect.InvocationTargetException;

import java.beans.PropertyDescriptor;

import java.util.Set;


/**
 * Abstract implementation which provides basic functionalities for an exclude based integrity test.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class AbstractIntegrityExcludeStrategy extends AbstractIntegrityStrategy {

    public AbstractIntegrityExcludeStrategy(Set<String> propertyNames) {
	    super(propertyNames);
    }

    @Override
    public void handleInvocationTargetException(final PropertyDescriptor property, final InvocationTargetException exception) {

    	if (property == null) {
	        throw new NullPointerException("property must not be null.");
        }
    	
    	if (exception == null) {
	        throw new NullPointerException("exception must not be null.");
        }
    	
    	final String propertyName = property.getName();
    	if (!this.propertyNames.contains(propertyName)) {
    		final String message = String.format("Calling the setter of the property '%s' threw an exception. " +
					 							 "The setter call can be avoided by excluding the property from the test.",
												 propertyName);
    		throw new IllegalArgumentException(message, exception);
    	}
    }
}
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

package com.codereligion.beast.internal.test.strategy;


import java.beans.PropertyDescriptor;

import java.util.Set;


/**
 * Abstract implementation which provides basic functionalities for an include based integrity test.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class AbstractIntegrityIncludeStrategy extends AbstractIntegrityStrategy {

    public AbstractIntegrityIncludeStrategy(Set<String> propertyNames) {
	    super(propertyNames);
    }

    @Override
    public void handlePropertySetterException(final PropertyDescriptor property, final Throwable e) {
    	final String propertyName = property.getName();
    	if (this.propertyNames.contains(propertyName)) {
    		final String message = String.format("Calling the setter of the property '%s' threw an exception. " +
												 "The setter call can be avoided by removing the property from the includes.",
												 propertyName);
    		throw new IllegalArgumentException(message, e);
    	}
    }
}
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


import java.util.Collections;

import java.util.Set;


/**
 * TODO update documentation
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class IntegrityStrategy {
	
	protected final Set<String> propertyNames;

    /**
     * TODO
     * Constructs an instance.
     *
     * @param propertyNames
     */
    public IntegrityStrategy(final Set<String> propertyNames) {
    	
    	if (propertyNames == null) {
    		throw new NullPointerException("propertyNames must not be null.");
    	}
    	
    	this.propertyNames = Collections.unmodifiableSet(propertyNames);
    }

    /**
     * TODO
     *
     * @param defaultObject
     * @param dirtyObject
     * @param propertyName
     */
    public abstract void apply(Object defaultObject, Object dirtyObject, String propertyName);
}
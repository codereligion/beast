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

package com.codereligion.beast.internal.test.builder;

import java.util.HashSet;
import java.util.Set;



/**
 * Abstract implementation which provides basic functionalities for an integration test builder.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class AbstractIntegrityTestBuilder extends AbstractTestBuilder {
	
	/**
	 * The names of the properties which should be included in the test.
	 */
	protected Set<String> includedPropertyNames = new HashSet<String>();
	
	/**
	 * Creates the concrete {@link AbstractIntegrityTestBuilder} for the given {@code beanClass}.
	 *
	 * @param beanClass the {@link Class} to generate the test for
	 * @return the instance of the created test
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	public AbstractIntegrityTestBuilder(final Class<?> beanClass) {
	    super(beanClass);
    }
    
	/**
     * Adds a name of a property which should be included in the test.
     *
     * @param propertyName the name of the property
     * @return a reference of this instance
     * @throws NullPointerException when the given parameter is {@code null}
     * @throws Ill
     */
    public AbstractIntegrityTestBuilder addIncludedPropertyName(final String propertyName) {
    	
    	if (propertyName == null) {
    		throw new NullPointerException("propertyName must not be null.");
    	}

    	if (!this.excludedPropertyNames.isEmpty()) {
    		throw new IllegalStateException("Adding an includedPropertyName is not allowed, when excludedPropertyNames where already provided.");
    	}
    	
    	this.includedPropertyNames.add(propertyName);
    	return this;
    }
    
    /**
     * Adds names of properties which should be included in the test.
     *
     * @param propertyNames the names of the properties
     * @return a reference of this instance
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public AbstractIntegrityTestBuilder addIncludedPropertyNames(final Set<String> propertyNames) {
    	
    	if (propertyNames == null) {
    		throw new NullPointerException("propertyNames must not be null.");
    	}
    	
    	if (!this.excludedPropertyNames.isEmpty()) {
    		throw new IllegalStateException("Adding includedPropertyNames is not allowed, when excludedPropertyNames where already provided.");
    	}
    	
    	this.includedPropertyNames.addAll(propertyNames);
    	return this;
    }
    
    @Override
    public AbstractIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {
    	
    	if (!this.includedPropertyNames.isEmpty()) {
    		throw new IllegalStateException("Adding an excludedPropertyName is not allowed, when includedPropertyNames where already provided.");
    	}
    	
    	return (AbstractIntegrityTestBuilder) super.addExcludedPropertyName(propertyName);
    }
    
    @Override
    public AbstractIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {

    	if (!this.includedPropertyNames.isEmpty()) {
    		throw new IllegalStateException("Adding excludedPropertyNames is not allowed, when includedPropertyNames where already provided.");
    	}
    	
    	return (AbstractIntegrityTestBuilder) super.addExcludedPropertyNames(propertyNames);
    }
}
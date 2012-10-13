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

package com.codereligion.beast.internal.builder;

import com.codereligion.beast.internal.test.Test;

import com.codereligion.beast.InstanceProvider;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract implementation which provides basic functionalities for a test builder.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class AbstractTestBuilder {
	
	/**
	 * The class to be tested
	 */
	protected final Class<?> beanClass;
	
	/**
	 * The custom instance providers.
	 */
	protected Set<InstanceProvider> instanceProviders = new HashSet<InstanceProvider>();
	
	/**
	 * The names of the properties which should be excluded from the test.
	 */
	protected Set<String> excludedPropertyNames = new HashSet<String>();

	/**
	 * Creates the concrete {@link AbstractTestBuilder} for the given {@code beanClass}.
	 *
	 * @param beanClass the {@link Class} to generate the test for
	 * @return the instance of the created test
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	public AbstractTestBuilder(final Class<?> beanClass) {
		
		if (beanClass == null) {
            throw new NullPointerException("beanClass must not be null.");
        }
		
		this.beanClass = beanClass;
	}
	
	/**
	 * Creates the concrete {@link Test} for the specified {@code beanClass} applying the
	 * specified configurations.
	 *
	 * @return the instance of the created test
	 */
	public abstract Test create();
	
	/**
	 * Adds an {@link InstanceProvider}.
	 *
	 * @param instanceProvider the {@link InstanceProvider} to add
	 * @return a reference of this instance
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	protected AbstractTestBuilder addInstanceProvider(final InstanceProvider instanceProvider) {
		
		if (instanceProvider == null) {
			throw new NullPointerException("instanceProvider must not be null.");
		}
		
		this.instanceProviders.add(instanceProvider);
		return this;
	}
	
	/**
	 * Adds a {@link Set} of {@link InstanceProvider}s.
	 *
	 * @param instanceProviders a {@link Set} of {@link InstanceProvider}s
	 * @return a reference of this instance
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	protected AbstractTestBuilder addInstanceProviders(final Set<InstanceProvider> instanceProviders) {

		if (instanceProviders == null) {
			throw new NullPointerException("instanceProviders must not be null.");
		}
		
		this.instanceProviders.addAll(instanceProviders);
		return this;
	}

	/**
     * Adds a name of a property which should be excluded from the test.
     *
     * @param propertyName the name of the property
     * @return a reference of this instance
     * @throws NullPointerException when the given parameter is {@code null}
     */
	public AbstractTestBuilder addExcludedPropertyName(final String propertyName) {
    	
    	if (propertyName == null) {
    		throw new NullPointerException("propertyName must not be null.");
    	}
    	
    	this.excludedPropertyNames.add(propertyName);
    	return this;
    }

	/**
     * Adds names of properties which should be excluded from the test.
     *
     * @param propertyNames the names of the properties
     * @return a reference of this instance
     * @throws NullPointerException when the given parameter is {@code null}
     */
	public AbstractTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
    	
    	if (propertyNames == null) {
    		throw new NullPointerException("propertyNames must not be null.");
    	}
    	
    	this.excludedPropertyNames.addAll(propertyNames);
    	return this;
    }
}
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

import com.codereligion.beast.internal.test.AbstractTestBuilder;

import com.codereligion.beast.internal.creation.ObjectFactory;

import com.codereligion.beast.internal.test.Test;

import com.codereligion.beast.internal.test.ToStringNullSafetyTest;


import java.util.HashSet;

import java.util.Set;

/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringNullSafetyTestBuilder extends AbstractTestBuilder {

	private Set<String> excludedPropertyNames = new HashSet<String>();
	
	@Override
	public <T> Test create(final Class<T> beanClass) {
		return new ToStringNullSafetyTest<T>(beanClass, new ObjectFactory(this.instanceProviders), this.excludedPropertyNames);
	}
	
	@Override
	public ToStringNullSafetyTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (ToStringNullSafetyTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public ToStringNullSafetyTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (ToStringNullSafetyTestBuilder) super.addInstanceProviders(instanceProviders);
	}

	/**
     * TODO
     *
     * @param propertyName
     * @return
     */
	public ToStringNullSafetyTestBuilder addExcludedPropertyName(final String propertyName) {
    	
    	if (propertyName == null) {
    		throw new NullPointerException("propertyName must not be null.");
    	}
    	
    	this.excludedPropertyNames.add(propertyName);
    	return this;
    }

	/**
     * TODO
     *
     * @param propertyNames
     * @return
     */
	public ToStringNullSafetyTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
    	
    	if (propertyNames == null) {
    		throw new NullPointerException("propertyNames must not be null.");
    	}
    	
    	this.excludedPropertyNames.addAll(propertyNames);
    	return this;
    }
}
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

import com.codereligion.beast.internal.test.EqualsNullSafetyTest;


import java.util.HashSet;

import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class EqualsNullSafetyTestBuilder extends AbstractTestBuilder {

	private Set<String> excludedPropertyNames = new HashSet<String>();
	
	/**
	 * TODO
	 * Constructs a new instance.
	 *
	 * @param beanClass
	 */
	public EqualsNullSafetyTestBuilder(final Class<?> beanClass) {
		super(beanClass);
	}
	
	@Override
	public Test create() {
		return new EqualsNullSafetyTest(this.beanClass, new ObjectFactory(this.instanceProviders), this.excludedPropertyNames);
	}

	@Override
	public EqualsNullSafetyTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (EqualsNullSafetyTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public EqualsNullSafetyTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (EqualsNullSafetyTestBuilder) super.addInstanceProviders(instanceProviders);
	}

	/**
     * TODO
     *
     * @param propertyName
     * @return
     */
	public EqualsNullSafetyTestBuilder addExcludedPropertyName(final String propertyName) {
    	
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
	public EqualsNullSafetyTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
    	
    	if (propertyNames == null) {
    		throw new NullPointerException("propertyNames must not be null.");
    	}
    	
    	this.excludedPropertyNames.addAll(propertyNames);
    	return this;
    }
}
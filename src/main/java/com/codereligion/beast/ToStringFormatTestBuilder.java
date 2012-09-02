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

import java.util.HashSet;

import java.util.Set;
import java.util.regex.Pattern;


/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringFormatTestBuilder {
	
	protected Set<String> excludedPropertyNames = new HashSet<String>();
	protected Set<InstanceProvider<?>> instanceProviders = new HashSet<InstanceProvider<?>>();
	
	/**
	 * TODO
	 *
	 * @param propertyName
	 * @return
	 */
	public ToStringFormatTestBuilder addExcludedPropertyName(final String propertyName) {
		
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
	public ToStringFormatTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		
		if (propertyNames == null) {
			throw new NullPointerException("propertyNames must not be null.");
		}
		
		this.excludedPropertyNames.addAll(propertyNames);
		return this;
	}
	
	/**
	 * TODO
	 *
	 * @param instanceProvider
	 * @return
	 */
	public ToStringFormatTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		
		if (instanceProvider == null) {
			throw new NullPointerException("instanceProvider must not be null.");
		}
		
		this.instanceProviders.add(instanceProvider);
		return this;
	}
	
	/**
	 * TODO
	 *
	 * @param instanceProviders
	 * @return
	 */
	public ToStringFormatTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {

		if (instanceProviders == null) {
			throw new NullPointerException("instanceProviders must not be null.");
		}
		
		this.instanceProviders.addAll(instanceProviders);
		return this;
	}
	
	/**
	 * TODO
	 *
	 * @param beanClass
	 * @param pattern
	 * @return
	 */
	public <T> Test create(final Class<T> beanClass, final Pattern pattern) {
		return new ToStringFormatTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.instanceProviders), pattern);
	}
	
	/**
	 * TODO
	 *
	 * @param beanClass
	 * @param pattern
	 */
	public <T> void createAndRun(final Class<T> beanClass, final Pattern pattern) {
		create(beanClass, pattern).run();
	}
}
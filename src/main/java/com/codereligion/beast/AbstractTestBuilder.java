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



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
abstract class AbstractTestBuilder {
	
	protected Set<String> excludedPropertyNames = new HashSet<String>();
	protected Set<InstanceProvider<?>> instanceProviders = new HashSet<InstanceProvider<?>>();
	
	/**
	 * TODO
	 *
	 * @param propertyName
	 * @return
	 */
	protected AbstractTestBuilder addExcludedPropertyName(final String propertyName) {
		
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
	protected AbstractTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		
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
	protected AbstractTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		
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
	protected AbstractTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {

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
	 * @return
	 */
	public abstract <T> Test create(Class<T> beanClass);
	
	/**
	 * TODO
	 *
	 * @param beanClass
	 */
	public <T> void createAndRun(Class<T> beanClass) {
		create(beanClass).run();
	}
}
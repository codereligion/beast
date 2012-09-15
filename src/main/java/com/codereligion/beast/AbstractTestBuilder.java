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

import com.codereligion.beast.internal.test.Test;

import java.util.HashSet;
import java.util.Set;



/**
 * TODO document
 * TODO test null check
 * TODO pull up exclude methods and make format test also use this class as super class
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
abstract class AbstractTestBuilder {
	
	protected Set<InstanceProvider<?>> instanceProviders = new HashSet<InstanceProvider<?>>();
	
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
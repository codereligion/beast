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

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.Test;
import com.codereligion.beast.internal.test.ToStringIntegrityTest;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import com.codereligion.beast.internal.test.strategy.ToStringIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.ToStringIntegrityIncludeStrategy;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringIntegrityTestBuilder extends AbstractTestBuilder {

	/**
	 * TODO
	 */
	private Set<String> excludedPropertyNames = new HashSet<String>();
	
	/**
	 * TODO
	 */
	private Set<String> includedPropertyNames = new HashSet<String>();
	
	@Override
	public <T> Test create(final Class<T> beanClass) {

		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		final IntegrityStrategy integrityStrategy; 
		
		if (!this.includedPropertyNames.isEmpty()) {
			integrityStrategy = new ToStringIntegrityIncludeStrategy(this.includedPropertyNames);
		} else {
			// default strategy
			integrityStrategy = new ToStringIntegrityExcludeStrategy(this.excludedPropertyNames);
		}
		
		return new ToStringIntegrityTest<T>(beanClass, new ObjectFactory(this.instanceProviders), integrityStrategy);
	}

	@Override
	public ToStringIntegrityTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (ToStringIntegrityTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public ToStringIntegrityTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (ToStringIntegrityTestBuilder) super.addInstanceProviders(instanceProviders);
	}

	/**
     * TODO
     *
     * @param propertyName
     * @return
     */
    public ToStringIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {
    	
    	if (propertyName == null) {
    		throw new NullPointerException("propertyName must not be null.");
    	}

    	if (!this.excludedPropertyNames.isEmpty()) {
    		throw new IllegalStateException("Adding an excludedPropertyName is not allowed, when includedPropertyNames where already provided.");
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
    public ToStringIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
    	
    	if (propertyNames == null) {
    		throw new NullPointerException("propertyNames must not be null.");
    	}
    	
    	if (!this.includedPropertyNames.isEmpty()) {
    		throw new IllegalStateException("Adding excludedPropertyNames is not allowed, when includedPropertyNames where already provided.");
    	}
    	
    	this.excludedPropertyNames.addAll(propertyNames);
    	return this;
    }
    
    /**
     * TODO
     *
     * @param propertyName
     * @return
     */
    public ToStringIntegrityTestBuilder addIncludedPropertyName(final String propertyName) {
    	
    	if (propertyName == null) {
    		throw new NullPointerException("propertyName must not be null.");
    	}

    	if (!this.includedPropertyNames.isEmpty()) {
    		throw new IllegalStateException("Adding an includedPropertyName is not allowed, when excludedPropertyNames where already provided.");
    	}
    	
    	this.includedPropertyNames.add(propertyName);
    	return this;
    }
    
    /**
     * TODO
     *
     * @param propertyNames
     * @return
     */
    public ToStringIntegrityTestBuilder addIncludedPropertyNames(final Set<String> propertyNames) {
    	
    	if (propertyNames == null) {
    		throw new NullPointerException("propertyNames must not be null.");
    	}
    	
    	if (!this.excludedPropertyNames.isEmpty()) {
    		throw new IllegalStateException("Adding includedPropertyNames is not allowed, when excludedPropertyNames where already provided.");
    	}
    	
    	this.includedPropertyNames.addAll(propertyNames);
    	return this;
    }
}
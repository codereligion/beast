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
import com.codereligion.beast.internal.test.EqualsIntegrityTest;
import com.codereligion.beast.internal.test.Test;
import com.codereligion.beast.internal.test.strategy.EqualsIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.EqualsIntegrityIncludeStrategy;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import java.util.HashSet;
import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class EqualsIntegrityTestBuilder extends AbstractTestBuilder {
	
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
			integrityStrategy = new EqualsIntegrityIncludeStrategy(this.includedPropertyNames);
		} else {
			// default strategy
			integrityStrategy = new EqualsIntegrityExcludeStrategy(this.excludedPropertyNames);
		}
		
		return new EqualsIntegrityTest<T>(beanClass, new ObjectFactory(this.instanceProviders), integrityStrategy);
	}

	@Override
	public EqualsIntegrityTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (EqualsIntegrityTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public EqualsIntegrityTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (EqualsIntegrityTestBuilder) super.addInstanceProviders(instanceProviders);
	}

	/**
     * TODO
     *
     * @param propertyName
     * @return
     */
    public EqualsIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {
    	
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
    public EqualsIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
    	
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
    public EqualsIntegrityTestBuilder addIncludedPropertyName(final String propertyName) {
    	
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
    public EqualsIntegrityTestBuilder addIncludedPropertyNames(final Set<String> propertyNames) {
    	
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
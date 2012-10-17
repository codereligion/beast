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

import com.codereligion.beast.internal.test.builder.AbstractIntegrityTestBuilder;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.HashCodeIntegrityTest;
import com.codereligion.beast.internal.test.Test;
import com.codereligion.beast.internal.test.strategy.HashCodeIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.HashCodeIntegrityIncludeStrategy;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import java.util.Set;



/**
 * Builder for the hashCode integrity test. The resulting test will apply the following criteria
 * to the class under test:
 * 
 * <ul>
 * <li> the hashCode method must be implemented
 * <li> the result of hashCode method of two instances must be equal, if the instances are equal according to their equals implementation
 * <li> if included properties have been provided
 * 	<ul>
 * 		<li> the included properties must be included in the implementation
 * 		<li> all not included properties must not be included in the implementation
 * 	</ul>
 * <li> if excluded properties have been provided
 * 	<ul>
 * 		<li> the excluded properties must be excluded from the implementation
 * 		<li> all not excluded properties must be included in the implementation
 * 	</ul>
 * </ul>
 * 
 * If neither includes nor excludes have been specified, all public settable properties
 * are expected to be included in the implementation.
 * 
 * <p>
 * <b>Caution:</b> You can either provide included or excluded properties, not both.
 * 
 * <p>
 * <b>When to use excludes:</b> Use excludes to make sure that no additional properties
 * can be added to the class under test without either altering the test or extending
 * the hashCode implementation. This strategy aims for an accurate and complete hashCode
 * implementation in order to cover as much of the class' state variations as possible.
 * 
 * <p>
 * <b>When to use includes:</b> Use includes to make sure that no additional properties
 * can be added to the hashCode implementation without altering the test. This strategy
 * aims for high-performance of the hashCode implementation, rather than for correctness.
 * This strategy should only be used, when performance is prioritized to correctness.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class HashCodeIntegrityTestBuilder extends AbstractIntegrityTestBuilder {
	
	/**
	 * Creates a new builder which will create a test for the given {@code beanClass}.
	 *
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	public HashCodeIntegrityTestBuilder(final Class<?> beanClass) {
		super(beanClass);
	}
	
	@Override
	public Test create() {
		
		final IntegrityStrategy integrityStrategy; 
		
		if (!this.includedPropertyNames.isEmpty()) {
			integrityStrategy = new HashCodeIntegrityIncludeStrategy(this.includedPropertyNames);
		} else {
			// default strategy
			integrityStrategy = new HashCodeIntegrityExcludeStrategy(this.excludedPropertyNames);
		}
		
		return new HashCodeIntegrityTest(this.beanClass, new ObjectFactory(this.instanceProviders), integrityStrategy);
	}

	@Override
	public HashCodeIntegrityTestBuilder addInstanceProvider(final InstanceProvider instanceProvider) {
		return (HashCodeIntegrityTestBuilder) super.addInstanceProvider(instanceProvider);
	}

	@Override
	public HashCodeIntegrityTestBuilder addInstanceProviders(final Set<InstanceProvider> instanceProviders) {
		return (HashCodeIntegrityTestBuilder) super.addInstanceProviders(instanceProviders);
	}

	@Override
	public HashCodeIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {
		return (HashCodeIntegrityTestBuilder) super.addExcludedPropertyName(propertyName);
	}

	@Override
	public HashCodeIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (HashCodeIntegrityTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}

	@Override
	public HashCodeIntegrityTestBuilder addIncludedPropertyName(final String propertyName) {
		return (HashCodeIntegrityTestBuilder) super.addIncludedPropertyName(propertyName);
	}

	@Override
	public HashCodeIntegrityTestBuilder addIncludedPropertyNames(final Set<String> propertyNames) {
		return (HashCodeIntegrityTestBuilder) super.addIncludedPropertyNames(propertyNames);
	}
}
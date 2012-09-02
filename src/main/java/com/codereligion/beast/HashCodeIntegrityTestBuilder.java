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

import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class HashCodeIntegrityTestBuilder extends AbstractTestBuilder {

	@Override
	public HashCodeIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {
		return (HashCodeIntegrityTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public HashCodeIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (HashCodeIntegrityTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public HashCodeIntegrityTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (HashCodeIntegrityTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public HashCodeIntegrityTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (HashCodeIntegrityTestBuilder) super.addInstanceProviders(instanceProviders);
	}
	
	public <T> Runnable create(final Class<T> beanClass) {
		return new HashCodeIntegrityTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.instanceProviders));
	}
}
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
public final class HashCodeNullSafetyTestBuilder extends AbstractTestBuilder {

	@Override
	public HashCodeNullSafetyTestBuilder addExcludedPropertyName(final String propertyName) {
		return (HashCodeNullSafetyTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public HashCodeNullSafetyTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (HashCodeNullSafetyTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public HashCodeNullSafetyTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (HashCodeNullSafetyTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public HashCodeNullSafetyTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (HashCodeNullSafetyTestBuilder) super.addInstanceProviders(instanceProviders);
	}
	
	public <T> Runnable create(final Class<T> beanClass) {
		return new HashCodeNullSafetyTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.instanceProviders));
	}
}
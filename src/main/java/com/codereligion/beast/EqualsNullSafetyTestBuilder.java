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
public final class EqualsNullSafetyTestBuilder extends AbstractTestBuilder {
	
	@Override
	public EqualsNullSafetyTestBuilder addExcludedPropertyName(final String propertyName) {
		return (EqualsNullSafetyTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public EqualsNullSafetyTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (EqualsNullSafetyTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public EqualsNullSafetyTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (EqualsNullSafetyTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public EqualsNullSafetyTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (EqualsNullSafetyTestBuilder) super.addInstanceProviders(instanceProviders);
	}
	
	public <T> EqualsNullSafetyTest<T> create(final Class<T> beanClass) {
		return new EqualsNullSafetyTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.instanceProviders));
	}
}
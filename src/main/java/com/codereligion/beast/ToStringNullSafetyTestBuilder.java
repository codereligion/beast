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

import com.codereligion.beast.internal.builder.AbstractTestBuilder;
import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.Test;
import com.codereligion.beast.internal.test.ToStringNullSafetyTest;
import java.util.Set;

/**
 * Builder for the toString null-safety test. The resulting test will apply the following criteria
 * to the class under test:
 * 
 * <ul>
 * <li> the hashCode method must be implemented
 * <li> calling toString for properties with {@code null} values, 
 * 		which have not been excluded must not throw a {@link NullPointerException}
 * <li> calling toString for properties with {@code null} values,
 * 		which have been excluded must throw a {@link NullPointerException}
 * </ul>
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringNullSafetyTestBuilder extends AbstractTestBuilder {

	/**
	 * Creates a new builder which will create a test for the given {@code beanClass}.
	 *
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	public ToStringNullSafetyTestBuilder(final Class<?> beanClass) {
	    super(beanClass);
    }
	
	@Override
	public Test create() {
		return new ToStringNullSafetyTest(this.beanClass, new ObjectFactory(this.instanceProviders), this.excludedPropertyNames);
	}

	@Override
	public ToStringNullSafetyTestBuilder addInstanceProvider(final InstanceProvider instanceProvider) {
		return (ToStringNullSafetyTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public ToStringNullSafetyTestBuilder addInstanceProviders(final Set<InstanceProvider> instanceProviders) {
		return (ToStringNullSafetyTestBuilder) super.addInstanceProviders(instanceProviders);
	}

	@Override
	public ToStringNullSafetyTestBuilder addExcludedPropertyName(final String propertyName) {
    	return (ToStringNullSafetyTestBuilder) super.addExcludedPropertyName(propertyName);
    }

	@Override
	public ToStringNullSafetyTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
    	return (ToStringNullSafetyTestBuilder) super.addExcludedPropertyNames(propertyNames);
    }
}
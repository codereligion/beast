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

package com.codereligion.beast.internal.test.builder;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.AbstractNullSafetyTest;
import com.codereligion.beast.internal.test.Test;
import java.util.Set;



/**
 * Abstract implementation which provides basic functionalities for a null-safety test builder.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class AbstractNullSafetyTestBuilder extends AbstractTestBuilder {

	/**
	 * Creates a new builder which will create a test for the given {@code beanClass}.
	 *
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	public AbstractNullSafetyTestBuilder(final Class<?> beanClass) {
		super(beanClass);
	}
	
	@Override
	public Test create() {
		return createTest(this.beanClass, createObjectFactory(), this.excludedPropertyNames);
	}
	
	/**
	 * Abstract factory method to create a new null-safety test for the given parameters.
	 *
	 * @param beanClass the {@link Class} to test
	 * @param objectFactory the {@link ObjectFactory} to use
	 * @param excludedPropertyNames the names of the properties to exclude from the test
	 * @return the instance of the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 */
	protected abstract AbstractNullSafetyTest createTest(
			Class<?> beanClass, 
			ObjectFactory objectFactory, 
			Set<String> excludedPropertyNames);
}
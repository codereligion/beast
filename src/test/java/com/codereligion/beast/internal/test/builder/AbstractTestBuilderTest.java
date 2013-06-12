/**
 * Copyright 2013 www.codereligion.com
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

import org.junit.Test;

/**
 * Abstract implementation which provides basic tests for a test builder.
 *
 * @author Sebastian Gröbler
 * @since 28.10.2012
 */
public abstract class AbstractTestBuilderTest {
	
	/**
	 * Abstract method to retrieve the to be tested bean class.
	 *
	 * @return the bean class to test
	 */
	protected abstract Class<?> getBeanClass();
	
	/**
	 * Abstract factory method to create the test builder for the given {@code beanClass}.
	 *
	 * @return a new instance of {@link AbstractTestBuilder}
	 */
	protected abstract AbstractTestBuilder createBuilder(Class<?> beanClass); 
	
	/**
	 * Factory method to create a test builder.
	 *
	 * @return a new instance of {@link AbstractTestBuilder}
	 */
	protected AbstractTestBuilder createBuilder() {
		return createBuilder(getBeanClass());
	}

	@Test(expected = NullPointerException.class)
	public void givenNullClassShouldThrowNpeWhenCallingTheConstructor() {
		createBuilder(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullPropertyNameShouldThrowNpeWhenCallingAddExcludedPropertyName() {
		final AbstractTestBuilder builder = createBuilder();
		builder.addExcludedPropertyName(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullPropertyNamesShouldThrowNpeWhenCallingAddExcludedPropertyNames() {
		final AbstractTestBuilder builder = createBuilder();
		builder.addExcludedPropertyNames(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullInstanceProviderShouldThrowNpeWhenCallingAddInstanceProvider() {
		final AbstractTestBuilder builder = createBuilder();
		builder.addInstanceProvider(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullInstanceProvidersShouldThrowNpeWhenCallingAddInstanceProviders() {
		final AbstractTestBuilder builder = createBuilder();
		builder.addInstanceProviders(null);
	}
}

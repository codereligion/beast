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
package com.codereligion.beast;

import static org.junit.Assert.assertEquals;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.AbstractTest;
import com.codereligion.beast.internal.test.ToStringFormatTest;
import com.codereligion.beast.internal.test.builder.AbstractTestBuilder;
import com.codereligion.beast.internal.test.builder.AbstractTestBuilderTest;
import com.codereligion.beast.object.ComplexClass;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import org.junit.Test;

/**
 * Tests {@link ToStringFormatTestBuilder}.
 *
 * @author Sebastian Gröbler
 * @since 28.10.2012
 */
public class ToStringFormatTestBuilderTest extends AbstractTestBuilderTest {

	@Override
    public AbstractTestBuilder createBuilder(final Class<?> beanClass) {
	    return new ToStringFormatTestBuilder(beanClass, ToStringFormatTestBuilder.DEFAULT_PATTERN);
    }

    public AbstractTest createTest(
    		final Class<?> beanClass,
    		final ObjectFactory objectFactory,
            final Set<String> excludedPropertyNames) {
	    return new ToStringFormatTest(beanClass, objectFactory, ToStringFormatTestBuilder.DEFAULT_PATTERN, excludedPropertyNames);
    }
	
	@Override
	public Class<?> getBeanClass() {
		return ComplexClass.class;
	}
	
	@Test
	public void givenInstanceProviderShouldBePassedThroughTheObjectFactoryToTheTest() {
		final InstanceProvider instanceProvider = InstanceProvider.create(BigDecimal.ONE, BigDecimal.TEN);
		final ObjectFactory objectFactory = new ObjectFactory(Collections.singleton(instanceProvider));
		final Set<String> excludedPropertyNames = Collections.emptySet();
		
		final AbstractTest expected = createTest(getBeanClass(), objectFactory, excludedPropertyNames);
		final AbstractTestBuilder builder = createBuilder();
		builder.addInstanceProvider(instanceProvider);
		
		assertEquals(expected, builder.create());
	}
	
	@Test
	public void givenInstanceProvidersShouldBePassedThroughTheObjectFactoryToTheTest() {
		final Set<InstanceProvider> instanceProviders = Collections.singleton(InstanceProvider.create(BigDecimal.ONE, BigDecimal.TEN));
		final ObjectFactory objectFactory = new ObjectFactory(instanceProviders);
		final Set<String> excludedPropertyNames = Collections.emptySet();
		
		final AbstractTest expected = createTest(getBeanClass(), objectFactory, excludedPropertyNames);
		final AbstractTestBuilder builder = createBuilder();
		builder.addInstanceProviders(instanceProviders);
		
		assertEquals(expected, builder.create());
	}
	
	@Test
	public void givenExcludedPropertyNameShouldBePassedThroughToTheTest() {
		final ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider>emptySet());
		final String excludedPropertyName = "propertyName";
		final Set<String> excludedPropertyNames = Collections.singleton(excludedPropertyName);
		
		final AbstractTest expected = createTest(getBeanClass(), objectFactory, excludedPropertyNames);
		final AbstractTestBuilder builder = createBuilder();
		builder.addExcludedPropertyName(excludedPropertyName);
		
		assertEquals(expected, builder.create());
	}
	
	@Test
	public void givenExcludedPropertyNamesShouldBePassedThroughToTheTest() {
		final ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider>emptySet());
		final Set<String> excludedPropertyNames = Collections.singleton("propertyName");
		
		final AbstractTest expected = createTest(getBeanClass(), objectFactory, excludedPropertyNames);
		final AbstractTestBuilder builder = createBuilder();
		builder.addExcludedPropertyNames(excludedPropertyNames);
		
		assertEquals(expected, builder.create());
	}
}

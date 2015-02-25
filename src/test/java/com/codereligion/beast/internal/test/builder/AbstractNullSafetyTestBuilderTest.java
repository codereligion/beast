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

import com.codereligion.beast.InstanceProvider;
import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.AbstractTest;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Abstract implementation which provides basic tests for a null-safety test builder.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 28.10.2012
 */
public abstract class AbstractNullSafetyTestBuilderTest extends AbstractTestBuilderTest {

    /**
     * Abstract factory to create a {@link AbstractTest} for the given parameters.
     *
     * @param beanClass         the {@link Class} to test
     * @param objectFactory     the {@link ObjectFactory} to use
     * @param integrityStrategy the {@link IntegrityStrategy} to use
     * @return a new instance of {@link AbstractTest}
     * @throws NullPointerException when any of the given parameters are {@code null}
     */
    protected abstract AbstractTest createTest(Class<?> beanClass, ObjectFactory objectFactory, Set<String> excludedPropertyNames);

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

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
import com.codereligion.beast.internal.test.strategy.AbstractIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.AbstractIntegrityIncludeStrategy;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import com.google.common.collect.Sets;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * TODO update documentation
 * Abstract implementation which provides basic tests for an integrity test builder.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 28.10.2012
 */
public abstract class AbstractIntegrityTestBuilderTest extends AbstractTestBuilderTest {

    @Override
    protected abstract AbstractIntegrityTestBuilder createBuilder(Class<?> beanClass);


    @Override
    protected AbstractIntegrityTestBuilder createBuilder() {
        return createBuilder(getBeanClass());
    }

    /**
     * Abstract factory method to create a new {@link AbstractTest} for the given parameters.
     *
     * @param beanClass         the {@link Class} to test
     * @param objectFactory     the {@link ObjectFactory} to use
     * @param integrityStrategy the {@link IntegrityStrategy} to use
     * @return a new instance of {@link AbstractTest}
     * @throws NullPointerException when any of the given parameters are {@code null}
     */
    protected abstract AbstractTest createTest(Class<?> beanClass, ObjectFactory objectFactory, IntegrityStrategy integrityStrategy);

    /**
     * Abstract factory method to create a new include based {@link IntegrityStrategy} for the given {@code propertyNames}.
     *
     * @param propertyNames the names of the properties to include
     * @return a new instance of {@link IntegrityStrategy}
     * @throws NullPointerException when the given parameter is {@code null}
     */
    protected abstract AbstractIntegrityIncludeStrategy createIntegrityIncludeStrategy(Set<String> propertyNames);

    /**
     * Abstract factory method to create a new include based {@link IntegrityStrategy} with no included property names.
     *
     * @param propertyNames the names of the properties to include
     * @return a new instance of {@link IntegrityStrategy}
     */
    protected AbstractIntegrityIncludeStrategy createIntegrityIncludeStrategy() {
        return createIntegrityIncludeStrategy(Collections.<String>emptySet());
    }

    /**
     * Abstract factory method to create a new exclude based {@link IntegrityStrategy} for the given {@code propertyNames}.
     *
     * @param propertyNames the names of the properties to exclude
     * @return a new instance of the {@link IntegrityStrategy}
     * @throws NullPointerException when the given parameter is {@code null}
     */
    protected abstract AbstractIntegrityExcludeStrategy createIntegrityExcludeStrategy(Set<String> propertyNames);

    /**
     * Abstract factory method to create a new exclude based {@link IntegrityStrategy} with no excluded property names.
     *
     * @param propertyNames the names of the properties to exclude
     * @return a new instance of the {@link IntegrityStrategy}
     */
    protected AbstractIntegrityExcludeStrategy createIntegrityExcludeStrategy() {
        return createIntegrityExcludeStrategy(Collections.<String>emptySet());
    }

    @Test
    public void givenNoIncludesAndNoExcludesShouldResultInTakingExcludeStrategyAsDefaultWhenCallingCreateIntegrityStrategy() {
        final ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider>emptySet());
        final IntegrityStrategy integrityStrategy = createIntegrityExcludeStrategy();

        final AbstractTest expected = createTest(getBeanClass(), objectFactory, integrityStrategy);
        final AbstractIntegrityTestBuilder builder = createBuilder();

        assertEquals(expected, builder.create());
    }

    @Test
    public void givenIncludedPropertyNamesShouldResultInTakingIncludeStrategyWhenCallingCreateIntegrityStrategy() {
        final ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider>emptySet());
        final Set<String> propertyNames = Collections.singleton("propertyName");
        final IntegrityStrategy integrityStrategy = createIntegrityIncludeStrategy(propertyNames);

        final AbstractTest expected = createTest(getBeanClass(), objectFactory, integrityStrategy);
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addIncludedPropertyNames(propertyNames);

        assertEquals(expected, builder.create());
    }

    @Test
    public void givenExcludedPropertyNamesShouldResultInTakingExcludeStrategyWhenCallingCreateIntegrityStrategy() {
        final ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider>emptySet());
        final Set<String> propertyNames = Collections.singleton("propertyName");
        final IntegrityStrategy integrityStrategy = createIntegrityExcludeStrategy(propertyNames);

        final AbstractTest expected = createTest(getBeanClass(), objectFactory, integrityStrategy);
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addExcludedPropertyNames(propertyNames);

        assertEquals(expected, builder.create());
    }

    @Test
    public void givenInstanceProviderShouldBePassedThroughTheObjectFactoryToTheTest() {
        final InstanceProvider instanceProvider = InstanceProvider.create(BigDecimal.ONE, BigDecimal.TEN);
        final ObjectFactory objectFactory = new ObjectFactory(Collections.singleton(instanceProvider));
        final IntegrityStrategy integrityStrategy = createIntegrityExcludeStrategy();

        final AbstractTest expected = createTest(getBeanClass(), objectFactory, integrityStrategy);
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addInstanceProvider(instanceProvider);

        assertEquals(expected, builder.create());
    }

    @Test
    public void givenInstanceProvidersShouldBePassedThroughTheObjectFactoryToTheTest() {
        final Set<InstanceProvider> instanceProviders = Collections.singleton(InstanceProvider.create(BigDecimal.ONE, BigDecimal.TEN));
        final ObjectFactory objectFactory = new ObjectFactory(instanceProviders);
        final IntegrityStrategy integrityStrategy = createIntegrityExcludeStrategy();

        final AbstractTest expected = createTest(getBeanClass(), objectFactory, integrityStrategy);
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addInstanceProviders(instanceProviders);

        assertEquals(expected, builder.create());
    }

    @Test(expected = NullPointerException.class)
    public void givenNullPropertyNameShouldThrowNpeWhenCallingAddIncludedPropertyName() {
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addIncludedPropertyName(null);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullPropertyNamesShouldThrowNpeWhenCallingAddIncludedPropertyNames() {
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addIncludedPropertyNames(null);
    }

    @Test(expected = IllegalStateException.class)
    public void givenAlreadyAddedExcludedPropertyNamesAddingIncludedPropertyNameShouldThrowIseWhenCallingAddIncludedPropertyName() {
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addExcludedPropertyName("propertyName");
        builder.addIncludedPropertyName("propertyName");
    }

    @Test(expected = IllegalStateException.class)
    public void givenAlreadyAddedExcludedPropertyNamesAddingIncludedPropertyNamesShouldThrowIseWhenCallingAddIncludedPropertyNames() {
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addExcludedPropertyName("propertyName");
        builder.addIncludedPropertyNames(Sets.newHashSet("propertyName"));
    }

    @Test(expected = IllegalStateException.class)
    public void givenAlreadyAddedIncludedPropertyNamesAddingExcludedPropertyNameShouldThrowIseWhenCallingAddExcludedPropertyName() {
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addIncludedPropertyName("propertyName");
        builder.addExcludedPropertyName("propertyName");
    }

    @Test(expected = IllegalStateException.class)
    public void givenAlreadyAddedIncludedPropertyNamesAddingExcludedPropertyNamesShouldThrowIseWhenCallingAddExcludedPropertyNames() {
        final AbstractIntegrityTestBuilder builder = createBuilder();
        builder.addIncludedPropertyName("propertyName");
        builder.addExcludedPropertyNames(Sets.newHashSet("propertyName"));
    }
}

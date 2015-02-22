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

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.Test;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import java.util.HashSet;
import java.util.Set;


/**
 * Abstract implementation which provides basic functionalities for an integration test builder.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class AbstractIntegrityTestBuilder extends AbstractTestBuilder {

    /**
     * The names of the properties which should be included in the test.
     */
    protected Set<String> includedPropertyNames = new HashSet<String>();

    /**
     * Creates the concrete {@link AbstractIntegrityTestBuilder} for the given {@code beanClass}.
     *
     * @param beanClass the {@link Class} to generate the test for
     * @return the instance of the created test
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public AbstractIntegrityTestBuilder(final Class<?> beanClass) {
        super(beanClass);
    }

    @Override
    public Test create() {
        return createTest(this.beanClass, createObjectFactory(), createIntegrityStrategy());
    }

    /**
     * Abstract factory method to create a new integrity test for the given parameters.
     *
     * @param beanClass         the {@link Class} to test
     * @param objectFactory     the {@link ObjectFactory} to use
     * @param integrityStrategy the {@link IntegrityStrategy} to use
     * @return a new instance of {@link Test}
     * @throws NullPointerException when any of the given parameters are {@code null}
     */
    protected abstract Test createTest(Class<?> beanClass, ObjectFactory objectFactory, IntegrityStrategy integrityStrategy);

    /**
     * Abstract factory method to create a new include based {@link IntegrityStrategy} for the given {@code propertyNames}.
     *
     * @param propertyNames the names of the properties to include
     * @return a new instance of {@link IntegrityStrategy}
     */
    protected abstract IntegrityStrategy createIntegrityIncludeStrategy(Set<String> propertyNames);

    /**
     * Abstract factory method to create a new exclude based {@link IntegrityStrategy} for the given {@code propertyNames}.
     *
     * @param propertyNames the names of the properties to exclude
     * @return a new instance of the {@link IntegrityStrategy}
     */
    protected abstract IntegrityStrategy createIntegrityExcludeStrategy(Set<String> propertyNames);

    /**
     * Abstract factory method to create either an include or exclude based {@link IntegrityStrategy} based on the inner state of the builder.
     * <p/>
     * <p/>
     * If included properties have been provided, an include based {@link IntegrityStrategy} will be created, otherwise an exclude based {@link
     * IntegrityStrategy} will be created.
     *
     * @return a new instance of {@link IntegrityStrategy}
     */
    protected IntegrityStrategy createIntegrityStrategy() {
        if (!this.includedPropertyNames.isEmpty()) {
            return createIntegrityIncludeStrategy(this.includedPropertyNames);
        }

        // default strategy
        return createIntegrityExcludeStrategy(this.excludedPropertyNames);
    }

    /**
     * Adds a name of a property which should be included in the test.
     *
     * @param propertyName the name of the property
     * @return a reference of this instance
     * @throws NullPointerException  when the given parameter is {@code null}
     * @throws IllegalStateException when {@code excludedPropertyNames} have already been specified
     */
    public AbstractIntegrityTestBuilder addIncludedPropertyName(final String propertyName) {

        if (propertyName == null) {
            throw new NullPointerException("propertyName must not be null.");
        }

        if (!this.excludedPropertyNames.isEmpty()) {
            throw new IllegalStateException("Adding an includedPropertyName is not allowed, when excludedPropertyNames where already provided.");
        }

        this.includedPropertyNames.add(propertyName);
        return this;
    }

    /**
     * Adds names of properties which should be included in the test.
     *
     * @param propertyNames the names of the properties
     * @return a reference of this instance
     * @throws NullPointerException  when the given parameter is {@code null}
     * @throws IllegalStateException when {@code excludedPropertyNames} have already been specified
     */
    public AbstractIntegrityTestBuilder addIncludedPropertyNames(final Set<String> propertyNames) {

        if (propertyNames == null) {
            throw new NullPointerException("propertyNames must not be null.");
        }

        if (!this.excludedPropertyNames.isEmpty()) {
            throw new IllegalStateException("Adding includedPropertyNames is not allowed, when excludedPropertyNames where already provided.");
        }

        this.includedPropertyNames.addAll(propertyNames);
        return this;
    }

    /**
     * Adds a name of a property which should be excluded from the test.
     *
     * @param propertyName the name of the property
     * @return a reference of this instance
     * @throws NullPointerException  when the given parameter is {@code null}
     * @throws IllegalStateException when {@code includedPropertyNames} have already been specified
     */
    @Override
    public AbstractIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {

        if (!this.includedPropertyNames.isEmpty()) {
            throw new IllegalStateException("Adding an excludedPropertyName is not allowed, when includedPropertyNames where already provided.");
        }

        return (AbstractIntegrityTestBuilder) super.addExcludedPropertyName(propertyName);
    }

    /**
     * Adds names of properties which should be excluded from the test.
     *
     * @param propertyNames the names of the properties
     * @return a reference of this instance
     * @throws NullPointerException  when the given parameter is {@code null}
     * @throws IllegalStateException when {@code includedPropertyNames} have already been specified
     */
    @Override
    public AbstractIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {

        if (!this.includedPropertyNames.isEmpty()) {
            throw new IllegalStateException("Adding excludedPropertyNames is not allowed, when includedPropertyNames where already provided.");
        }

        return (AbstractIntegrityTestBuilder) super.addExcludedPropertyNames(propertyNames);
    }
}
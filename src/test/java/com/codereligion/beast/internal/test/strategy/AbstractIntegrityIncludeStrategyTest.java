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
package com.codereligion.beast.internal.test.strategy;

import com.codereligion.beast.object.ComplexClass;
import com.google.common.collect.Sets;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.assertFalse;

/**
 * Abstract implementation which provides basic tests for an integrity include strategies.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 29.10.2012
 */
public abstract class AbstractIntegrityIncludeStrategyTest extends AbstractIntegrityStrategyTest {

    /**
     * Constructs a new instance with the given {@code propertyNames} to be included.
     *
     * @param propertyNames the names of the properties which should be included
     * @throws NullPointerException when the given parameter is {@code null}
     */
    @Override
    protected abstract AbstractIntegrityIncludeStrategy createIntegrityStrategy(Set<String> propertyNames);


    @Test(expected = AssertionError.class)
    public void givenPropertyIsNotIncludedAndIsImplementedShouldThrowAnAssertionError() {

        final String propertyName = "propertyName";
        final Set<String> includedPropertyNames = Collections.emptySet();
        final IntegrityStrategy integrityStrategy = createIntegrityStrategy(includedPropertyNames);

        integrityStrategy.apply("foo", "bar", propertyName);
    }

    @Test(expected = AssertionError.class)
    public void givenAPropertyIsIncludedAndIsNotImplementedShouldThrowAnAssertionError() {

        final String propertyName = "propertyName";
        final Set<String> includedPropertyNames = Sets.newHashSet(propertyName);
        final IntegrityStrategy integrityStrategy = createIntegrityStrategy(includedPropertyNames);

        integrityStrategy.apply("foo", "foo", propertyName);
    }

    @Test
    public void givenPropertyIsIncludedAndIsImplementedShouldNotRaiseAnyErrors() {

        final String propertyName = "propertyName";
        final Set<String> includedPropertyNames = Sets.newHashSet(propertyName);
        final IntegrityStrategy integrityStrategy = createIntegrityStrategy(includedPropertyNames);

        integrityStrategy.apply("foo", "bar", propertyName);
    }

    @Test
    public void givenPropertyIsNotIncludedAndIsNotImplementedShouldNotRaiseAnyErrors() {

        final String propertyName = "propertyName";
        final Set<String> includedPropertyNames = Collections.emptySet();
        final IntegrityStrategy integrityStrategy = createIntegrityStrategy(includedPropertyNames);

        integrityStrategy.apply("foo", "foo", propertyName);
    }

    @Test
    public void givenStrategyWithDifferentIncludesShouldReturnFalseWhenCallingEquals() {

        final IntegrityStrategy integrityStrategyA = createIntegrityStrategy(Sets.newHashSet("a"));
        final IntegrityStrategy integrityStrategyB = createIntegrityStrategy(Sets.newHashSet("b"));

        assertFalse(integrityStrategyA.equals(integrityStrategyB));
    }

    @Test(expected = NullPointerException.class)
    public void fogivenNullPropertyDescriptorShouldThrowNpeWhenCallingHandleInvocationTargetExceptiono() {
        final String propertyName = "complexObject";
        final AbstractIntegrityIncludeStrategy integrityStrategy = createIntegrityStrategy(Sets.newHashSet(propertyName));
        final InvocationTargetException exception = new InvocationTargetException(new RuntimeException());

        integrityStrategy.handleInvocationTargetException(null, exception);
    }

    @Test(expected = NullPointerException.class)
    public void givenNullExceptionShouldThrowNpeWhenCallingHandleInvocationTargetException() throws IntrospectionException {
        final String propertyName = "complexObject";
        final AbstractIntegrityIncludeStrategy integrityStrategy = createIntegrityStrategy(Sets.newHashSet(propertyName));
        final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, ComplexClass.class);

        integrityStrategy.handleInvocationTargetException(propertyDescriptor, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenPropertyNameIsIncludedAndThrowsExceptionOnCallingSetterShouldRaiseAnIae() throws IntrospectionException {
        final String propertyName = "complexObject";
        final AbstractIntegrityIncludeStrategy integrityStrategy = createIntegrityStrategy(Sets.newHashSet(propertyName));
        final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, ComplexClass.class);
        final InvocationTargetException exception = new InvocationTargetException(new RuntimeException());

        integrityStrategy.handleInvocationTargetException(propertyDescriptor, exception);
    }

    @Test
    public void givenPropertyNameIsNotIncludedAndThrowsExceptionOnCallingSetterShouldNotRaiseAnyException() throws IntrospectionException {
        final String propertyName = "complexObject";
        final AbstractIntegrityIncludeStrategy integrityStrategy = createIntegrityStrategy(Collections.<String>emptySet());
        final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, ComplexClass.class);
        final InvocationTargetException exception = new InvocationTargetException(new RuntimeException());

        integrityStrategy.handleInvocationTargetException(propertyDescriptor, exception);
    }
}

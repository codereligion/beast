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

import static org.junit.Assert.assertFalse;
import com.codereligion.beast.object.ComplexClass;
import com.google.common.collect.Sets;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;
import org.junit.Test;

/**
 * Abstract implementation which provides basic tests for an integrity include strategies. 
 *
 * @author Sebastian Gröbler
 * @since 29.10.2012
 */
public abstract class AbstractIntegrityExcludeStrategyTest extends AbstractIntegrityStrategyTest {
	
	/**
     * Constructs a new instance with the given {@code propertyNames} to be excluded.
     *
     * @param propertyNames the names of the properties which should be excluded
     * @throws NullPointerException when the given parameter is {@code null}
     */
	@Override
	protected abstract AbstractIntegrityExcludeStrategy createIntegrityStrategy(Set<String> propertyNames);
	
	@Test
	public void givenPropertyIsNotExcludedAndIsImplementedShouldNotRaiseAnyErrors() {
		
		final String propertyName = "propertyName";
		final Set<String> excludedPropertyNames = Collections.emptySet();
		final IntegrityStrategy integrityStrategy = createIntegrityStrategy(excludedPropertyNames);
		
		integrityStrategy.apply("foo", "bar", propertyName);
	}
	
	@Test
	public void givenAPropertyIsExcludedAndIsNotImplementedShouldNotRaiseAnyErrors() {
		
		final String propertyName = "propertyName";
		final Set<String> excludedPropertyNames = Sets.newHashSet(propertyName);
		final IntegrityStrategy integrityStrategy = createIntegrityStrategy(excludedPropertyNames);
		
		integrityStrategy.apply("foo", "foo", propertyName);
	}
	
	@Test(expected = AssertionError.class)
	public void givenPropertyIsExcludedAndIsImplementedShouldThrowAnAssertionError() {
		
		final String propertyName = "propertyName";
		final Set<String> excludedPropertyNames = Sets.newHashSet(propertyName);
		final IntegrityStrategy integrityStrategy = createIntegrityStrategy(excludedPropertyNames);
		
		integrityStrategy.apply("foo", "bar", propertyName);
	}
	
	@Test(expected = AssertionError.class)
	public void givenPropertyIsNotExcludedAndIsNotImplementedShouldThrowAnAssertionError() {
		
		final String propertyName = "propertyName";
		final Set<String> excludedPropertyNames = Collections.emptySet();
		final IntegrityStrategy integrityStrategy = createIntegrityStrategy(excludedPropertyNames);
		
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
		final AbstractIntegrityExcludeStrategy integrityStrategy = createIntegrityStrategy(Sets.newHashSet(propertyName));
		final InvocationTargetException exception =  new InvocationTargetException(new RuntimeException());
		
		integrityStrategy.handleInvocationTargetException(null, exception);
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullExceptionShouldThrowNpeWhenCallingHandleInvocationTargetException() throws IntrospectionException {
		final String propertyName = "complexObject";
		final AbstractIntegrityExcludeStrategy integrityStrategy = createIntegrityStrategy(Sets.newHashSet(propertyName));
		final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, ComplexClass.class);
		
		integrityStrategy.handleInvocationTargetException(propertyDescriptor, null);
	}
	
	@Test
	public void givenPropertyNameIsExcludedAndThrowsExceptionOnCallingSetterShouldNotRaiseAnyException() throws IntrospectionException {
		final String propertyName = "complexObject";
		final AbstractIntegrityExcludeStrategy integrityStrategy = createIntegrityStrategy(Sets.newHashSet(propertyName));
		final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, ComplexClass.class);
		final InvocationTargetException exception =  new InvocationTargetException(new RuntimeException());
		
		integrityStrategy.handleInvocationTargetException(propertyDescriptor, exception);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenPropertyNameIsNotExcludedAndThrowsExceptionOnCallingSetterShouldRaiseAnIae() throws IntrospectionException {
		final String propertyName = "complexObject";
		final AbstractIntegrityExcludeStrategy integrityStrategy = createIntegrityStrategy(Collections.<String>emptySet());
		final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, ComplexClass.class);
		final InvocationTargetException exception =  new InvocationTargetException(new RuntimeException());
		
		integrityStrategy.handleInvocationTargetException(propertyDescriptor, exception);
	}
}

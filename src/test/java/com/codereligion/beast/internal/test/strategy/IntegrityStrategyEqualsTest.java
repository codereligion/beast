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

package com.codereligion.beast.internal.test.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests that all {@link AbstractIntegrityStrategy} implementations
 * stick to equals contract. Despite the official java documented contract
 * it should be enforced that no mixed type comparison should be possible.
 *
 * @author Sebastian Gröbler
 * @since 30.10.2012
 */
public class IntegrityStrategyEqualsTest {
	
	private static final Set<String> PROPERTY_NAMES = Sets.newHashSet("propertyName");
	private static final Set<IntegrityStrategy> CLASSES = Sets.<IntegrityStrategy>newHashSet(
			new EqualsIntegrityIncludeStrategy(PROPERTY_NAMES),
			new EqualsIntegrityExcludeStrategy(PROPERTY_NAMES),
			new HashCodeIntegrityIncludeStrategy(PROPERTY_NAMES),
			new HashCodeIntegrityExcludeStrategy(PROPERTY_NAMES),
			new ToStringIntegrityIncludeStrategy(PROPERTY_NAMES),
			new ToStringIntegrityExcludeStrategy(PROPERTY_NAMES));


	@Test
	public void test() {
		
		// this one is quite important since the classes are put in a set
		// if the equals implementation is broken this set may only contain
		// a subset of the actual input
		assertEquals(6, CLASSES.size());
		
		// compare each implementation with each other
		for (final IntegrityStrategy integrityStrategyA : CLASSES) {
			for (final IntegrityStrategy integrityStrategyB : CLASSES) {
				testBasicEqualsContract(integrityStrategyA, integrityStrategyB);
			}
		}
	}
	
	private void testBasicEqualsContract(
			final IntegrityStrategy integrityStrategyA,
			final IntegrityStrategy integrityStrategyB) {
		
		assertFalse(integrityStrategyA.equals(null));
		assertFalse(integrityStrategyA.equals("foo"));
		assertTrue(integrityStrategyA.equals(integrityStrategyA));
		
		assertTrue(integrityStrategyA instanceof AbstractIntegrityStrategy);
		assertTrue(integrityStrategyB instanceof AbstractIntegrityStrategy);
		
		if (integrityStrategyA.getClass() == integrityStrategyB.getClass()) {
			// comparing class with itself
			assertTrue(integrityStrategyA.equals(integrityStrategyB));
			assertTrue(integrityStrategyB.equals(integrityStrategyA));
		} else {
			// comparing the class with another class
			assertFalse(integrityStrategyA.equals(integrityStrategyB));
			assertFalse(integrityStrategyB.equals(integrityStrategyA));
		}
		
	}
}

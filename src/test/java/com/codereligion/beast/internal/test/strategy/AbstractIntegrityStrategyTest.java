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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.Sets;
import java.util.Set;
import org.junit.Test;

/**
 * Abstract implementation which provides basic tests for an integrity strategies. 
 *
 * @author Sebastian Gröbler
 * @since 29.10.2012
 */
public abstract class AbstractIntegrityStrategyTest {
	
	/**
     * Constructs a new instance with the given {@code propertyNames} to be used.
     *
     * @param propertyNames the names of the properties which should be used
     * @throws NullPointerException when the given parameter is {@code null}
     */
	protected abstract AbstractIntegrityStrategy createIntegrityStrategy(Set<String> propertyNames);
	
    @Test(expected = NullPointerException.class)
	public void givenNullPropertyNamesShouldThrowNpeWhenCallingTheConstructor() {
		createIntegrityStrategy(null);
	}
	
	@Test
	public void givenStrategyWithTheSameIncludesShouldReturnTrueWhenCallingEqual() {
		
		final IntegrityStrategy integrityStrategyA = createIntegrityStrategy(Sets.newHashSet("a"));
		final IntegrityStrategy integrityStrategyB = createIntegrityStrategy(Sets.newHashSet("a"));
		
		assertTrue(integrityStrategyA.equals(integrityStrategyB));
	}
	
	@Test
	public void givenStrategyWithTheSameIncludesShouldReturnTheSameHashCode() {
		
		final IntegrityStrategy integrityStrategyA = createIntegrityStrategy(Sets.newHashSet("a"));
		final IntegrityStrategy integrityStrategyB = createIntegrityStrategy(Sets.newHashSet("a"));
		
		assertEquals(integrityStrategyA.hashCode(), integrityStrategyB.hashCode());
	}
}

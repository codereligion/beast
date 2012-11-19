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


import static com.codereligion.beast.internal.util.Assert.assertFalse;

import java.util.Set;


/**
 * Tests the integrity of the hashCode implementation by using an exclude strategy. This means the user can provide
 * excluded properties which must not be included in the hashCode implementation. This approach is specifically
 * useful when the class under test has properties of which the majority must be considered in the 
 * hashCode implementation. 
 * 
 * <p>
 * This strategy will throw an {@link AssertionError} if it finds a property which has not been excluded by the user
 * and is not included in the hashCode implementation.
 * 
 * <p>
 * Furthermore it will throw an {@link AssertionError} if it finds a property which has been excluded by the user
 * but is included in the hashCode implementation, thus has been excluded unnecessarily .
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 * @see HashCodeIntegrityIncludeStrategy
 */
public final class HashCodeIntegrityExcludeStrategy extends AbstractIntegrityExcludeStrategy {
	
	/**
     * Constructs an instance with the given {@code propertyNames} to be excluded.
     *
     * @param propertyNames the names of the properties which should be excluded
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public HashCodeIntegrityExcludeStrategy(final Set<String> propertyNames) {
    	super(propertyNames);
    }

    @Override
    public void apply(final Object defaultObject, final Object dirtyObject, final String propertyName) {
    	
    	final boolean hashCodesAreEqual = defaultObject.hashCode() == dirtyObject.hashCode();
    	final boolean isExcluded = this.propertyNames.contains(propertyName);
		final boolean isUnintentionallyMissing = hashCodesAreEqual && !isExcluded;
		
		assertFalse(isUnintentionallyMissing,
					"The property '%s' may not be included in the hashCode implementation or the generated hashCode " +
					"may be to weak to reflect changes in this property. If the property is not included and this is " +
					"intentional add it to the excludedPropertyNames.",
					propertyName);
    	
    	final boolean isUnnecessarilyExcluded = !hashCodesAreEqual && isExcluded;
    	
    	assertFalse(isUnnecessarilyExcluded,
    			"The property '%s' is contained the excludedPropertyNames, but is actually " +
    			"supported by the hashCode implementation. Either remove it from the " +
    			"excludedPropertyNames or the hashCode implementation.",
    			propertyName);
    }

	@Override
    public String toString() {
	    final StringBuilder builder = new StringBuilder();
	    builder.append("HashCodeIntegrityExcludeStrategy [propertyNames=");
	    builder.append(this.propertyNames);
	    builder.append("]");
	    return builder.toString();
    }
}
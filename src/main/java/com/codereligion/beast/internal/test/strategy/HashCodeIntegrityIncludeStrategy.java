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


import static com.codereligion.beast.internal.util.Assert.assertFalse;

import java.util.Set;


/**
 * Tests the integrity of the hashCode implementation by using an include strategy. This means the user can provide
 * included properties which must be included in the hashCode implementation. This approach is specifically
 * useful when the class under test has properties of which only one or a few properties must be considered in 
 * the hashCode implementation e.g. for performance reasons. Good include candidates may be primary keys, foreign keys
 * and alike properties.
 * 
 * <p>
 * This strategy will throw an {@link AssertionError}, if the given {@code propertyName} was not in the includes,
 * but for which the {@code defaultObject} and {@code dirtyObject} have different hashCode results.
 * 
 * <p>
 * Furthermore it will throw an {@link AssertionError}, if the given {@code propertyName} was in the includes,
 * but for which the {@code defaultObject} and {@code dirtyObject} have the same hashCode result. Thus
 * the include was unnecessary.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 * @see HashCodeIntegrityExcludeStrategy
 */
public final class HashCodeIntegrityIncludeStrategy extends AbstractIntegrityIncludeStrategy {
	
	/**
     * Constructs an instance with the given {@code propertyNames} to be included.
     *
     * @param propertyNames the names of the properties which should be included
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public HashCodeIntegrityIncludeStrategy(final Set<String> propertyNames) {
    	super(propertyNames);
    }

    @Override
    public void apply(final Object defaultObject, final Object dirtyObject, final String propertyName) {
    	
    	final boolean hashCodesAreEqual = defaultObject.hashCode() == dirtyObject.hashCode();
    	final boolean isIncluded = this.propertyNames.contains(propertyName);
		final boolean isMissingInIncludes = !hashCodesAreEqual && !isIncluded;
		
		assertFalse(isMissingInIncludes,
					"The property '%s' is supported by the hashCode implementation, but is not specified " +
					"as includedProperty. Either add it to the includedProperties, or remove it " +
					"from the implementation of the hashCode method.",
					propertyName);

		final boolean isMissingInImplementation = hashCodesAreEqual && isIncluded;
    	
    	assertFalse(isMissingInImplementation,
    			"The property '%s' was specified as includedProperty, but may not be supported by the hash code " +
    			"implementation or the generated hashCode may be to weak to reflect changes in this property. " +
    			"Either remove it from the includedProperties, or add it to the implementation of the hash code method. " +
    			"In case the property is already added try to increase the strength of the generated hash code.",
    			propertyName);
    }

	@Override
    public String toString() {
	    final StringBuilder builder = new StringBuilder();
	    builder.append("HashCodeIntegrityIncludeStrategy [propertyNames=");
	    builder.append(this.propertyNames);
	    builder.append("]");
	    return builder.toString();
    }
}
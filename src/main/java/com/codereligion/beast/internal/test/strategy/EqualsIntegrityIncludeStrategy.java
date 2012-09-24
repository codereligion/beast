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
 * Tests the integrity of the equals implementation by using an include strategy. This means the user can provide
 * included properties which must be included in the equals implementation. This approach is specifically
 * useful when the class under test has properties of which only one or a few properties must be considered in 
 * the equals implementation e.g. for performance reasons. Good include candidates may be primary keys, foreign keys
 * and alike properties.
 * 
 * <p>
 * This strategy will throw an {@link AssertionError} if it finds a property which has not been included by the user
 * and is included in the equals implementation.
 * 
 * <p>
 * Furthermore it will throw an {@link AssertionError} if it finds a property which has been included by the user
 * but is excluded in the equals implementation, thus has been included unnecessarily .
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 * @see EqualsIntegrityExcludeStrategy
 */
public final class EqualsIntegrityIncludeStrategy extends AbstractIntegrityIncludeStrategy {
	
	/**
     * Constructs an instance with the given {@code propertyNames} to be included.
     *
     * @param propertyNames the names of the properties which should be included
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public EqualsIntegrityIncludeStrategy(final Set<String> propertyNames) {
    	super(propertyNames);
    }

    @Override
    public void apply(final Object defaultObject, final Object dirtyObject, final String propertyName) {
        
    	final boolean defaultObjectEqualsDirtyObject = defaultObject.equals(dirtyObject);
        final boolean isIncluded = this.propertyNames.contains(propertyName);
        final boolean isUnintentionallyMissing = !defaultObjectEqualsDirtyObject && !isIncluded;
        
        assertFalse(isUnintentionallyMissing, 
				"The property '%s' is supported by the equals implementation, but is not specified " +
				"as includedProperty. Either add it to the includedProperties, or remove it " +
				"from the implementation of the equals method.",
				propertyName);
    	
    	final boolean isUnnecessarilyIncluded = defaultObjectEqualsDirtyObject && isIncluded;
    	
    	assertFalse(isUnnecessarilyIncluded, 
    			"The property '%s' is not supported by the equals implementation, but was specified as " +
    			"includedProperty. Either remove it from the includedProperties, or add it " +
    			"to the implementation of the equals method.",
    			propertyName);
    }

	@Override
    public boolean equals(final Object obj) {
		if (this == obj) {
		    return true;
	    }
	    if (obj == null) {
		    return false;
	    }
	    if (getClass() != obj.getClass()) {
		    return false;
	    }
	    
	    final EqualsIntegrityIncludeStrategy other = (EqualsIntegrityIncludeStrategy) obj;
	    if (!this.propertyNames.equals(other.propertyNames)) {
	    	return false;
	    }
	    return true;
    }

	@Override
    public String toString() {
	    final StringBuilder builder = new StringBuilder();
	    builder.append("EqualsIntegrityIncludeStrategy [propertyNames=");
	    builder.append(this.propertyNames);
	    builder.append("]");
	    return builder.toString();
    }
}
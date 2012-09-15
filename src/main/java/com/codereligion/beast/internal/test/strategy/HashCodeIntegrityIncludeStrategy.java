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
 * TODO update documentation
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class HashCodeIntegrityIncludeStrategy extends IntegrityStrategy {
	
    /**
     * TODO
     * Constructs an instance.
     *
     * @param propertyNames
     */
    public HashCodeIntegrityIncludeStrategy(final Set<String> propertyNames) {
    	super(propertyNames);
    }

    @Override
    public void apply(final Object defaultObject, final Object dirtyObject, final String propertyName) {
    	
    	final boolean hashCodesAreEqual = defaultObject.hashCode() == dirtyObject.hashCode();
    	final boolean isIncluded = this.propertyNames.contains(propertyName);
		final boolean isUnnecessarilyIncluded = hashCodesAreEqual && isIncluded;
		
		assertFalse(isUnnecessarilyIncluded,
				"The property '%s' was specified as includedProperty, but may not be supported by the hash code " +
				"implementation or the generated hashCode may be to weak to reflect changes in this property. " +
				"Either remove it from the includedProperties, or add it to the implementation of the hash code method. " +
				"In case the property is already added try to increase the strength of the generated hash code.",
				propertyName);
		
		final boolean isUnintentionallyMissing = !hashCodesAreEqual && !isIncluded;
		
		assertFalse(isUnintentionallyMissing,
					"The property '%s' is supported by the hashCode implementation, but is not specified " +
					"as includedProperty. Either add it to the includedProperties, or remove it " +
					"from the implementation of the equals method.",
					propertyName);
    }
}
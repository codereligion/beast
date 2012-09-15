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
public final class HashCodeIntegrityExcludeStrategy extends IntegrityStrategy {
	
    /**
     * TODO
     * Constructs an instance.
     *
     * @param propertyNames
     */
    public HashCodeIntegrityExcludeStrategy(final Set<String> propertyNames) {
    	super(propertyNames);
    }

    @Override
    public void apply(final Object defaultObject, final Object dirtyObject, final String propertyName) {
    	
    	final boolean hashCodesAreEqual = defaultObject.hashCode() == dirtyObject.hashCode();
    	final boolean isExcluded = this.propertyNames.contains(propertyName);
		final boolean isUnnecessarilyExcluded = !hashCodesAreEqual && isExcluded;
		
		assertFalse(isUnnecessarilyExcluded,
					"The property '%s' is contained the excludedPropertyNames, but is actually " +
					"supported by the hashCode implementation. Either remove it from the " +
					"excludedPropertyNames or the hashCode implementation.",
					propertyName);
		
		final boolean isUnintentionallyMissing = hashCodesAreEqual && !isExcluded;
		
		assertFalse(isUnintentionallyMissing,
					"The property '%s' may not be included in the hashCode implementation or the generated hashCode " +
					"may be to weak to reflect changes in this property. If the property is not included and this is " +
					"intentional add it to the excludedPropertyNames.",
					propertyName);
    }
}
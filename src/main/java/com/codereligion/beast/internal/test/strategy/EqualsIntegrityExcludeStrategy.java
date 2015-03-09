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


import java.util.Set;
import static com.codereligion.beast.internal.util.Assert.assertFalse;

/**
 * Tests the integrity of the equals implementation by using an exclude strategy. This means the user can provide excluded properties which must not be included
 * in the equals implementation. This approach is specifically useful when the class under test has properties of which the majority must be considered in the
 * equals implementation.
 * <p/>
 * <p/>
 * This strategy will throw an {@link AssertionError} if it finds a property which has not been excluded by the user and is not included in the equals
 * implementation.
 * <p/>
 * <p/>
 * Furthermore it will throw an {@link AssertionError} if it finds a property which has been excluded by the user but is included in the equals implementation,
 * thus has been excluded unnecessarily .
 *
 * @author Sebastian Gr&ouml;bler
 * @see EqualsIntegrityIncludeStrategy
 * @since 11.08.2012
 */
public final class EqualsIntegrityExcludeStrategy extends AbstractIntegrityExcludeStrategy {

    /**
     * Constructs an instance with the given {@code propertyNames} to be excluded.
     *
     * @param propertyNames the names of the properties which should be excluded
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public EqualsIntegrityExcludeStrategy(final Set<String> propertyNames) {
        super(propertyNames);
    }

    @Override
    public void apply(final Object defaultObject, final Object dirtyObject, final String propertyName) {

        final boolean defaultObjectEqualsDirtyObject = defaultObject.equals(dirtyObject);
        final boolean isExcluded = this.propertyNames.contains(propertyName);
        final boolean isUnintentionallyMissing = defaultObjectEqualsDirtyObject && !isExcluded;

        assertFalse(isUnintentionallyMissing,
                    "The property '%s' is not supported by the equals method. If this is intentional add it to the excludedPropertyNames.",
                    propertyName);

        final boolean isUnnecessarilyExcluded = !defaultObjectEqualsDirtyObject && isExcluded;

        assertFalse(isUnnecessarilyExcluded, "The property '%s' is contained the excludedPropertyNames, but is actually " +
                                             "supported by the equals implementation. Either remove it from the " +
                                             "excludedPropertyNames or the equals implementation.", propertyName);
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("EqualsIntegrityExcludeStrategy [propertyNames=");
        builder.append(this.propertyNames);
        builder.append("]");
        return builder.toString();
    }
}
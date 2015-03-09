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
package com.codereligion.beast.internal.test;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.creation.ObjectMethodNames;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import static com.codereligion.beast.internal.util.Assert.fail;


/**
 * Tests the toString implementation of the class under test for the following criteria:
 * <p/>
 * <ul> <li> the toString method must be implemented <li> calling toString for properties with {@code null} values, which have not been excluded must not throw
 * a {@link NullPointerException} <li> calling toString for properties with {@code null} values, which have been excluded must throw a {@link
 * NullPointerException} </ul>
 *
 * @author Sebastian Gr&ouml;bler
 * @since 11.08.2012
 */
public final class ToStringNullSafetyTest extends AbstractNullSafetyTest {

    /**
     * Constructs a new instance of this test for the given {@code beanClass} using the given {@code objectFactory} and {@code excludedPropertyNames}.
     *
     * @param beanClass             the {@link Class} to test
     * @param objectFactory         the {@link ObjectFactory} to use
     * @param excludedPropertyNames the names of the properties to exclude from the test
     * @throws IllegalArgumentException when any of the given parameters are {@code null} or when the given {@code beanClass} cannot be tested
     */
    public ToStringNullSafetyTest(final Class<?> beanClass, final ObjectFactory objectFactory, final Set<String> excludedPropertyNames) {
        super(beanClass, objectFactory, excludedPropertyNames);
    }

    @Override
    public void run() {

        if (!isMethodImplemented(ObjectMethodNames.TO_STRING)) {
            fail("The given class %s does not implement toString.", this.beanClassCanonicalName);
        }

        for (final PropertyDescriptor property : this.writeableProperties) {

            final Class<?> propertyType = property.getPropertyType();

            if (propertyType.isPrimitive()) {
                continue;
            }

            final String propertyName = property.getName();

            if (this.excludedPropertyNames.contains(propertyName)) {
                continue;
            }

            final Object dirtyObject = newBeanObject();

            try {
                setValue(dirtyObject, property, null);
                boolean throwsNullPointerException = false;

                try {
                    dirtyObject.toString();
                } catch (final NullPointerException e) {
                    throwsNullPointerException = true;
                }

                final boolean isExcluded = this.excludedPropertyNames.contains(propertyName);
                final boolean equalsThrowsUnexpectedNullPointerException = !isExcluded && throwsNullPointerException;
                if (equalsThrowsUnexpectedNullPointerException) {
                    fail("If the property '%s' is null, calling the toString method throws a NullPointerException. " +
                         "Add the property name to the excludedPropertyNames, if it can never be null.", propertyName);
                }

                final boolean unnecessarilyExcluded = isExcluded && !throwsNullPointerException;
                if (unnecessarilyExcluded) {
                    fail("The property '%s' is contained the excludedPropertyNames, but is actually handled null-safe. " +
                         "Remove the property from the excludedPropertyNames.", propertyName);
                }
            } catch (final InvocationTargetException e) {
                handleInvocationTargetException(property, e);
            }


//            // TODO should this not better be in another test
//            final boolean areEqualWithNulls = defaultToStringResult.equals(dirtyToString);
//            assertFalse(areEqualWithNulls,
//                        "If the property '%s' is null the toString result should differ from " +
//                        "a toString result of an instance where this property is not null.",
//                        propertyName);
        }
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

        return super.equals(obj);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ToStringNullSafetyTest [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }
}
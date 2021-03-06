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
import com.codereligion.beast.internal.test.strategy.HashCodeIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.HashCodeIntegrityIncludeStrategy;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import static com.codereligion.beast.internal.util.Assert.assertFalse;
import static com.codereligion.beast.internal.util.Assert.fail;


/**
 * Tests the hashCode implementation of the class under test for the following criteria:
 * <p/>
 * <ul> <li> the hashCode method must be implemented <li> the result of hashCode method of two instances must be equal, if the instances are equal according to
 * their equals implementation <li> calling hashCode on instances with different property values must behave as specified in the given {@link IntegrityStrategy}
 * </ul>
 *
 * @author Sebastian Gr&ouml;bler
 * @see HashCodeIntegrityExcludeStrategy
 * @see HashCodeIntegrityIncludeStrategy
 * @since 11.08.2012
 */
public final class HashCodeIntegrityTest extends AbstractIntegrityTest {


    /**
     * Constructs a new instance of this test for the given {@code beanClass} using the given {@code objectFactory} and {@code integrityStrategy}.
     *
     * @param beanClass         the {@link Class} to test
     * @param objectFactory     the {@link ObjectFactory} to use
     * @param integrityStrategy the {@link IntegrityStrategy} to use
     * @throws IllegalArgumentException when any of the given parameters are {@code null} or when the given {@code beanClass} cannot be tested
     */
    public HashCodeIntegrityTest(final Class<?> beanClass, final ObjectFactory objectFactory, final IntegrityStrategy integrityStrategy) {
        super(beanClass, objectFactory, integrityStrategy);
    }

    @Override
    public void run() {

        if (!isMethodImplemented(ObjectMethodNames.HASH_CODE)) {
            fail("The given class %s does not implement hashCode.", this.beanClassCanonicalName);
        }

        final Object defaultObject = newBeanObject();

        for (final PropertyDescriptor property : this.writeableProperties) {

            final String propertyName = property.getName();
            final Class<?> propertyType = property.getPropertyType();
            final Object dirtyObject = newBeanObject();
            final Object dirtyProperty = this.objectFactory.getDirtyObject(propertyType, propertyName);

            try {
                setValue(dirtyObject, property, dirtyProperty);
                final boolean areEqual = defaultObject.equals(dirtyObject);
                final boolean hashCodesAreEqual = defaultObject.hashCode() == dirtyObject.hashCode();
                final boolean isEqualsHashCodeContractViolated = areEqual && !hashCodesAreEqual;

                // hashCode and equals contract must not be violated, disregarding excludes
                assertFalse(isEqualsHashCodeContractViolated, "If the property '%s' is different in two instances, these two " +
                                                              "instances are equal according to the equals method, but their hashCodes " +
                                                              "are different. This is a violation of the equals/hashCode contract.", propertyName);

                this.integrityStrategy.apply(defaultObject, dirtyObject, propertyName);
            } catch (final InvocationTargetException e) {
                handleInvocationTargetException(property, e);
            }
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
        builder.append("HashCodeIntegrityTest [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }
}
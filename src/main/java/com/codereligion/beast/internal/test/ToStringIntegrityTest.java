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
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import com.codereligion.beast.internal.test.strategy.ToStringIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.ToStringIntegrityIncludeStrategy;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import static com.codereligion.beast.internal.util.Assert.fail;

/**
 * Tests the toString implementation of the class under test for the following criteria:
 * <p/>
 * <ul> <li> the toString method must be implemented <li> calling toString on instances with different property values must behave as specified in the given
 * {@link IntegrityStrategy} </ul>
 *
 * @author Sebastian Gröbler
 * @see ToStringIntegrityExcludeStrategy
 * @see ToStringIntegrityIncludeStrategy
 * @since 11.08.2012
 */
public final class ToStringIntegrityTest extends AbstractIntegrityTest {

    /**
     * Constructs a new instance of this test for the given {@code beanClass} using the given {@code objectFactory} and {@code integrityStrategy}.
     *
     * @param beanClass         the {@link Class} to test
     * @param objectFactory     the {@link ObjectFactory} to use
     * @param integrityStrategy the {@link IntegrityStrategy} to use
     * @throws NullPointerException when any of the given parameters are {@code null}
     */
    public ToStringIntegrityTest(final Class<?> beanClass, final ObjectFactory objectFactory, final IntegrityStrategy integrityStrategy) {

        super(beanClass, objectFactory, integrityStrategy);
    }

    @Override
    public void run() {

        if (!isMethodImplemented(ObjectMethodNames.TO_STRING)) {
            fail("The given class %s does not implement toString.", this.beanClassCanonicalName);
        }

        final Object defaultObject = newBeanObject();

        for (final PropertyDescriptor property : this.writeableProperties) {

            final String propertyName = property.getName();
            final Object dirtyObject = newBeanObject();
            final Class<?> propertyType = property.getPropertyType();
            final Object dirtyProperty = this.objectFactory.getDirtyObject(propertyType, propertyName);

            try {
                setValue(dirtyObject, property, dirtyProperty);
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
        builder.append("ToStringIntegrityTest [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }
}
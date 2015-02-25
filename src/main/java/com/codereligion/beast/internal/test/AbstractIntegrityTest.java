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
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;


/**
 * Abstract test which provides the basic functionality for an integrity test.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 11.08.2012
 */
public abstract class AbstractIntegrityTest extends AbstractTest {

    /**
     * The integrity strategy to be applied by this test.
     */
    protected final IntegrityStrategy integrityStrategy;

    /**
     * Constructs a new instance of this test for the given {@code beanClass} using the given {@code objectFactory} and {@code integrityStrategy}.
     *
     * @param beanClass         the {@link Class} to test
     * @param objectFactory     the {@link ObjectFactory} to use
     * @param integrityStrategy the {@link IntegrityStrategy} to use
     * @throws NullPointerException when any of the given parameters are {@code null}
     */
    public AbstractIntegrityTest(final Class<?> beanClass, final ObjectFactory objectFactory, final IntegrityStrategy integrityStrategy) {

        super(beanClass, objectFactory);

        if (objectFactory == null) {
            throw new NullPointerException("objectFactory must not be null.");
        }

        this.integrityStrategy = integrityStrategy;
    }

    @Override
    public void handleInvocationTargetException(final PropertyDescriptor property, final InvocationTargetException exception) {
        this.integrityStrategy.handleInvocationTargetException(property, exception);
    }

    /**
     * Compares the given object by verifying that:
     * <p/>
     * <ul> <li> it is not {@code null} <li> it is an instance of {@link AbstractIntegrityTest} <li> the given object has the same {@code beanClass} as this
     * object <li> the given object has the same {@code objectFactory} as this object <li> the given object has the same {@code integrityStrategy} as this
     * object </ul>
     * <p/>
     * This method should be used by sub-classes to avoid duplication.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AbstractIntegrityTest)) {
            return false;
        }

        final AbstractIntegrityTest other = (AbstractIntegrityTest) obj;

        if (!super.equals(other)) {
            return false;
        }

        if (!this.integrityStrategy.equals(other.integrityStrategy)) {
            return false;
        }
        return true;
    }

    /**
     * Generates a hash code based on the unique and visible members of this class. This method should be used by sub-classes to avoid duplication.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.integrityStrategy.hashCode();
        return result;
    }

    /**
     * Generates a concatenated string which contains key/value pairs of every unique and visible member of this class with its according value. This method
     * should be used by sub-classes to avoid duplication.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", integrityStrategy=");
        builder.append(this.integrityStrategy);
        return builder.toString();
    }
}
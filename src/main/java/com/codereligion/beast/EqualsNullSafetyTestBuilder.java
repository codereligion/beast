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
package com.codereligion.beast;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.AbstractNullSafetyTest;
import com.codereligion.beast.internal.test.EqualsNullSafetyTest;
import com.codereligion.beast.internal.test.builder.AbstractNullSafetyTestBuilder;
import java.util.Set;


/**
 * Builder for the equals null-safety test. The resulting test will apply the following criteria to the class under test:
 * <p/>
 * <ul> <li> the equals method must be implemented <li> calling equals for properties with {@code null} values, which have not been excluded must not throw a
 * {@link NullPointerException} <li> calling equals for properties with {@code null} values, which have been excluded must throw a {@link NullPointerException}
 * </ul>
 *
 * @author Sebastian Gr&ouml;bler
 * @since 11.08.2012
 */
public final class EqualsNullSafetyTestBuilder extends AbstractNullSafetyTestBuilder {

    /**
     * Creates a new builder which will create a test for the given {@code beanClass}.
     *
     * @param beanClass the {@link Class} to be tested
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public EqualsNullSafetyTestBuilder(final Class<?> beanClass) {
        super(beanClass);
    }

    @Override
    protected AbstractNullSafetyTest createTest(final Class<?> beanClass, final ObjectFactory objectFactory, final Set<String> excludedPropertyNames) {
        return new EqualsNullSafetyTest(beanClass, objectFactory, excludedPropertyNames);
    }
}
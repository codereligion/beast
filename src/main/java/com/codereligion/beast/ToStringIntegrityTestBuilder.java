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
import com.codereligion.beast.internal.test.Test;
import com.codereligion.beast.internal.test.ToStringIntegrityTest;
import com.codereligion.beast.internal.test.builder.AbstractIntegrityTestBuilder;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import com.codereligion.beast.internal.test.strategy.ToStringIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.ToStringIntegrityIncludeStrategy;
import java.util.Set;

/**
 * Builder for the toString integrity test. The resulting test will apply the following criteria to the class under test:
 * <p/>
 * <ul> <li> the toString method must be implemented <li> if included properties have been provided <ul> <li> the included properties must be included in the
 * implementation <li> all not included properties must not be included in the implementation </ul> <li> if excluded properties have been provided <ul> <li> the
 * excluded properties must be excluded from the implementation <li> all not excluded properties must be included in the implementation </ul> </ul>
 * <p/>
 * If neither includes nor excludes have been specified, all public writeable properties are expected to be included in the implementation.
 * <p/>
 * <p/>
 * <b>Caution:</b> You can either provide included or excluded properties, not both.
 * <p/>
 * <p/>
 * <b>When to use excludes:</b> Use excludes to make sure that no additional properties can be added to the class under test without either altering the test or
 * extending the toString implementation. This strategy aims for an accurate and complete toString implementation in order to cover as much of the class' state
 * variations as possible.
 * <p/>
 * <p/>
 * <b>When to use includes:</b> Use includes to make sure that no additional properties can be added to the toString implementation without altering the test.
 * This strategy aims for high-performance of the toString implementation, rather than for correctness. This strategy should only be used, when performance is
 * prioritized to correctness.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 11.08.2012
 */
public final class ToStringIntegrityTestBuilder extends AbstractIntegrityTestBuilder {

    /**
     * Creates a new builder which will create a test for the given {@code beanClass}.
     *
     * @param beanClass the {@link Class} to be tested
     * @throws IllegalArgumentException when the given parameter is {@code null}
     */
    public ToStringIntegrityTestBuilder(final Class<?> beanClass) {
        super(beanClass);
    }

    @Override
    protected Test createTest(final Class<?> beanClass, final ObjectFactory objectFactory, final IntegrityStrategy integrityStrategy) {
        return new ToStringIntegrityTest(beanClass, objectFactory, integrityStrategy);
    }

    @Override
    protected IntegrityStrategy createIntegrityIncludeStrategy(final Set<String> propertyNames) {
        return new ToStringIntegrityIncludeStrategy(propertyNames);
    }

    @Override
    protected IntegrityStrategy createIntegrityExcludeStrategy(final Set<String> propertyNames) {
        return new ToStringIntegrityExcludeStrategy(propertyNames);
    }
}
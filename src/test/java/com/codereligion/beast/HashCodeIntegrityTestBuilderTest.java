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
import com.codereligion.beast.internal.test.AbstractIntegrityTest;
import com.codereligion.beast.internal.test.HashCodeIntegrityTest;
import com.codereligion.beast.internal.test.builder.AbstractIntegrityTestBuilder;
import com.codereligion.beast.internal.test.builder.AbstractIntegrityTestBuilderTest;
import com.codereligion.beast.internal.test.strategy.AbstractIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.AbstractIntegrityIncludeStrategy;
import com.codereligion.beast.internal.test.strategy.HashCodeIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.HashCodeIntegrityIncludeStrategy;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import com.codereligion.beast.object.ComplexClass;
import java.util.Set;

/**
 * Tests {@link HashCodeIntegrityTestBuilder}.
 *
 * @author Sebastian Gröbler
 * @since 28.10.2012
 */
public class HashCodeIntegrityTestBuilderTest extends AbstractIntegrityTestBuilderTest {

    @Override
    public AbstractIntegrityTestBuilder createBuilder(final Class<?> beanClass) {
        return new HashCodeIntegrityTestBuilder(beanClass);
    }

    @Override
    public AbstractIntegrityTest createTest(final Class<?> beanClass, final ObjectFactory objectFactory, final IntegrityStrategy integrityStrategy) {
        return new HashCodeIntegrityTest(beanClass, objectFactory, integrityStrategy);
    }

    @Override
    public Class<?> getBeanClass() {
        return ComplexClass.class;
    }

    @Override
    public AbstractIntegrityIncludeStrategy createIntegrityIncludeStrategy(final Set<String> propertyNames) {
        return new HashCodeIntegrityIncludeStrategy(propertyNames);
    }

    @Override
    public AbstractIntegrityExcludeStrategy createIntegrityExcludeStrategy(final Set<String> propertyNames) {
        return new HashCodeIntegrityExcludeStrategy(propertyNames);
    }
}

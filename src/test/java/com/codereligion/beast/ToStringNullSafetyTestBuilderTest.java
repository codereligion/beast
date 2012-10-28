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

package com.codereligion.beast;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.AbstractNullSafetyTest;
import com.codereligion.beast.internal.test.ToStringNullSafetyTest;
import com.codereligion.beast.internal.test.builder.AbstractNullSafetyTestBuilder;
import com.codereligion.beast.internal.test.builder.AbstractNullSafetyTestBuilderTest;
import com.codereligion.beast.object.ComplexClass;
import java.util.Set;

/**
 * Tests {@link ToStringNullSafetyTestBuilder}.
 *
 * @author Sebastian Gröbler
 * @since 28.10.2012
 */
public class ToStringNullSafetyTestBuilderTest extends AbstractNullSafetyTestBuilderTest {

	@Override
    public AbstractNullSafetyTestBuilder createBuilder(final Class<?> beanClass) {
	    return new ToStringNullSafetyTestBuilder(beanClass);
    }

	@Override
	public Class<?> getBeanClass() {
		return ComplexClass.class;
	}

	@Override
    public AbstractNullSafetyTest createTest(
    		final Class<?> beanClass,
    		final ObjectFactory objectFactory,
            final Set<String> excludedPropertyNames) {
	    return new ToStringNullSafetyTest(beanClass, objectFactory, excludedPropertyNames);
    }
}

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
package com.codereligion.beast.integration;

import com.codereligion.beast.HashCodeNullSafetyTestBuilder;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.ExceptionThrowingSetter;
import com.codereligion.beast.object.MissingHashCodeImplementation;
import com.codereligion.beast.object.MissingNullCheckInHashCode;
import com.google.common.collect.Sets;
import com.google.common.hash.HashCode;
import org.junit.Test;

/**
 * Tests {@link HashCode}.
 *
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class HashCodeNullSafetyTestIntegrationTest {

    @Test(expected = NullPointerException.class)
    public void testWithNullClass() {
        new HashCodeNullSafetyTestBuilder(null).create().run();
    }

    @Test
    public void testValidClass() {
        new HashCodeNullSafetyTestBuilder(ComplexClass.class).create().run();
    }

    @Test(expected = AssertionError.class)
    public void testWithMissingImplemention() {
        new HashCodeNullSafetyTestBuilder(MissingHashCodeImplementation.class).create().run();
    }

    @Test(expected = AssertionError.class)
    public void testWithNullPointerInHashCode() {
        new HashCodeNullSafetyTestBuilder(MissingNullCheckInHashCode.class).create().run();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithExceptionThrowingSetter() {
        new HashCodeNullSafetyTestBuilder(ExceptionThrowingSetter.class).create().run();
    }

    @Test
    public void testWithExceptionThrowingSetterForExcludedProperty() {
        new HashCodeNullSafetyTestBuilder(MissingNullCheckInHashCode.class).addExcludedPropertyNames(Sets.newHashSet("complexObject")).create().run();
    }
}

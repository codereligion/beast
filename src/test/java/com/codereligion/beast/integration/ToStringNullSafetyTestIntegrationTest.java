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

import com.codereligion.beast.EqualsIntegrityTestBuilder;
import com.codereligion.beast.ToStringNullSafetyTestBuilder;
import com.codereligion.beast.internal.test.ToStringNullSafetyTest;
import com.codereligion.beast.object.ClassWithEmptyEnumProperty;
import com.codereligion.beast.object.ClassWithOneElementEnumProperty;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.ExceptionThrowingSetter;
import com.codereligion.beast.object.MissingNullCheckInToString;
import com.codereligion.beast.object.MissingToStringImplementation;
import com.google.common.collect.Sets;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests {@link ToStringNullSafetyTest}.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 14.08.2012
 */
public class ToStringNullSafetyTestIntegrationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void testWithNullClass() {
        new ToStringNullSafetyTestBuilder(null).create().run();
    }

    @Test
    public void testValidClass() {
        new ToStringNullSafetyTestBuilder(ComplexClass.class).create().run();
    }

    @Test(expected = AssertionError.class)
    public void testWithMissingImplementation() {
        new ToStringNullSafetyTestBuilder(MissingToStringImplementation.class).create().run();
    }

    @Test(expected = AssertionError.class)
    public void testWithNullPointerInToString() {
        new ToStringNullSafetyTestBuilder(MissingNullCheckInToString.class).create().run();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithExceptionThrowingSetter() {
        new ToStringNullSafetyTestBuilder(ExceptionThrowingSetter.class).create().run();
    }

    @Test
    public void testWithExceptionThrowingSetterForExcludedProperty() {
        new ToStringNullSafetyTestBuilder(MissingNullCheckInToString.class).addExcludedPropertyNames(Sets.newHashSet("complexObject")).create().run();
    }

    @Test
    public void emptyEnumCausesIllegalArgumentException() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Cannot mutate field of type: class com.codereligion.beast.object.EmptyEnum. The enum must hold at least two values.");

        new EqualsIntegrityTestBuilder(ClassWithEmptyEnumProperty.class).create().run();
    }

    @Test
    public void oneElementEnumCausesIllegalArgumentException() {

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(
                "Cannot mutate field of type: class com.codereligion.beast.object.OneElementEnum. The enum must hold at least two values.");

        new EqualsIntegrityTestBuilder(ClassWithOneElementEnumProperty.class).create().run();
    }
}

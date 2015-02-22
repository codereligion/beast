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

import com.codereligion.beast.EqualsNullSafetyTestBuilder;
import com.codereligion.beast.internal.test.EqualsNullSafetyTest;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.ExceptionThrowingSetter;
import com.codereligion.beast.object.MissingEqualsImplementation;
import com.codereligion.beast.object.MissingNullCheckForGivenInEquals;
import com.codereligion.beast.object.MissingNullCheckForThisInEquals;
import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests {@link EqualsNullSafetyTest}.
 *
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class EqualsNullSafetyTestIntegrationTest {

    @Test(expected = NullPointerException.class)
    public void testWithNullClass() {
        new EqualsNullSafetyTestBuilder(null).create().run();
    }

    @Test
    public void testValidClass() {
        new EqualsNullSafetyTestBuilder(ComplexClass.class).create().run();
    }

    @Test(expected = AssertionError.class)
    public void testWithMissingImplemention() {
        new EqualsNullSafetyTestBuilder(MissingEqualsImplementation.class).create().run();
    }

    @Test(expected = AssertionError.class)
    public void testNullPointerOnGivenInEquals() {
        new EqualsNullSafetyTestBuilder(MissingNullCheckForGivenInEquals.class).create().run();
    }

    @Test(expected = AssertionError.class)
    public void testNullPointerOnThisInEquals() {
        new EqualsNullSafetyTestBuilder(MissingNullCheckForThisInEquals.class).create().run();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithExceptionThrowingSetter() {
        new EqualsNullSafetyTestBuilder(ExceptionThrowingSetter.class).create().run();
    }

    @Test
    public void testWithExceptionThrowingSetterWithExcludes() {
        new EqualsNullSafetyTestBuilder(ExceptionThrowingSetter.class).addExcludedPropertyNames(Sets.newHashSet("foo")).create().run();
    }

    @Test
    public void testWithExceptionThrowingSetterForExcludedProperty() {
        new EqualsNullSafetyTestBuilder(MissingNullCheckForGivenInEquals.class).addExcludedPropertyNames(Sets.newHashSet("complexObject")).create().run();
    }
}
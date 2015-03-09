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
import com.codereligion.beast.internal.test.EqualsIntegrityTest;
import com.codereligion.beast.object.AbstractClass;
import com.codereligion.beast.object.AsymmetricGettersAndSetters;
import com.codereligion.beast.object.ClassWithEmptyEnumProperty;
import com.codereligion.beast.object.ClassWithOneElementEnumProperty;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.ExceptionThrowingSetter;
import com.codereligion.beast.object.GenericGetterAndSetter;
import com.codereligion.beast.object.MissingDefaultConstructor;
import com.codereligion.beast.object.MissingEqualsImplementation;
import com.codereligion.beast.object.MissingPropertyInEquals;
import com.codereligion.beast.object.NonReflexiveEqualsClass;
import com.codereligion.beast.object.NonSymmetricEqualsClass;
import com.codereligion.beast.object.PropertyWhichHasNoDefaultConstructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * TODO check if this test can be consolidated with the other integration tests Tests {@link EqualsIntegrityTest}.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 14.08.2012
 */
public class EqualsIntegrityTestIntegrationTest {

    private static final Set<Class<?>> UNSUPPORTED_CLASSES = new HashSet<Class<?>>();

    static {
        // basic types
        UNSUPPORTED_CLASSES.add(Boolean.TYPE);
        UNSUPPORTED_CLASSES.add(Boolean.class);
        UNSUPPORTED_CLASSES.add(AtomicBoolean.class);
        UNSUPPORTED_CLASSES.add(Character.TYPE);
        UNSUPPORTED_CLASSES.add(Character.class);
        UNSUPPORTED_CLASSES.add(Byte.TYPE);
        UNSUPPORTED_CLASSES.add(Byte.class);
        UNSUPPORTED_CLASSES.add(Short.TYPE);
        UNSUPPORTED_CLASSES.add(Short.class);
        UNSUPPORTED_CLASSES.add(Integer.TYPE);
        UNSUPPORTED_CLASSES.add(Integer.class);
        UNSUPPORTED_CLASSES.add(AtomicInteger.class);
        UNSUPPORTED_CLASSES.add(Long.TYPE);
        UNSUPPORTED_CLASSES.add(Long.class);
        UNSUPPORTED_CLASSES.add(AtomicLong.class);
        UNSUPPORTED_CLASSES.add(Float.TYPE);
        UNSUPPORTED_CLASSES.add(Float.class);
        UNSUPPORTED_CLASSES.add(Double.TYPE);
        UNSUPPORTED_CLASSES.add(Double.class);
        UNSUPPORTED_CLASSES.add(BigDecimal.class);
        UNSUPPORTED_CLASSES.add(BigInteger.class);
        UNSUPPORTED_CLASSES.add(String.class);
        UNSUPPORTED_CLASSES.add(Object.class);

        // wicked types
        UNSUPPORTED_CLASSES.add(Test.class);
        UNSUPPORTED_CLASSES.add(Integer[].class);
        UNSUPPORTED_CLASSES.add(ComplexClass.Enumeration.class);
        UNSUPPORTED_CLASSES.add(List.class);
        UNSUPPORTED_CLASSES.add(AbstractClass.class);

        // invalid implementations
        UNSUPPORTED_CLASSES.add(MissingDefaultConstructor.class);
        UNSUPPORTED_CLASSES.add(PropertyWhichHasNoDefaultConstructor.class);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none().handleAssertionErrors();

    @Test
    public void nullBeanClassCausesIllegalArgumentException() {

        // expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("beanClass must not be null."));

        // when
        new EqualsIntegrityTestBuilder(null).create().run();
    }

    @Test
    public void missingImplementationCausesAssertError() {

        // expect
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage(is("com.codereligion.beast.object.MissingEqualsImplementation does not implement equals."));

        // when
        new EqualsIntegrityTestBuilder(MissingEqualsImplementation.class).create().run();
    }

    @Test
    public void validClassDoesNotThrowAssertionError() {
        new EqualsIntegrityTestBuilder(ComplexClass.class).create().run();
    }

    @Test
    public void asymmetricGettersAndSettersAreSkipped() {
        new EqualsIntegrityTestBuilder(AsymmetricGettersAndSetters.class).create().run();
    }

    @Test
    public void canHandleClassWithGenericGettersAndSetters() {
        new EqualsIntegrityTestBuilder(GenericGetterAndSetter.class).create().run();
    }

    @Test
    public void missingPropertyInEqualsImplementationCausesAssertionError() {

        // expect
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage(is(
                "The property 'complexObject' is not supported by the equals method. If this is intentional add it to the excludedPropertyNames."));

        // when
        new EqualsIntegrityTestBuilder(MissingPropertyInEquals.class).create().run();
    }

    @Test
    public void nonReflexiveEqualsImplementationCausesAssertionError() {

        // expect
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage(is("The equals implementation of com.codereligion.beast.object.NonReflexiveEqualsClass is not reflexive."));

        // when
        new EqualsIntegrityTestBuilder(NonReflexiveEqualsClass.class).create().run();
    }

    @Test
    public void nonSymmetricEqualsImplementationCausesAssertionError() {

        // expect
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage(is(
                "The equals implementation of com.codereligion.beast.object.NonSymmetricEqualsClass is not symmetric for property 'foo'."));

        // when
        new EqualsIntegrityTestBuilder(NonSymmetricEqualsClass.class).create().run();
    }

    // TODO this test should be broken into multiple tests because the exception message varies between the different unsupported classes
    @Test
    @Ignore
    public void unsupportedClassesCausesIllegalArgumentException() {
        for (final Class<?> type : UNSUPPORTED_CLASSES) {
            try {
                new EqualsIntegrityTestBuilder(type).create().run();

                fail();
            } catch (final IllegalArgumentException e) {
                assertThat(e.getMessage(), is(type.getCanonicalName() + " is not supported for testing."));
            }
        }
    }

    @Test
    public void unnecessaryExcludeCausesAssertionError() {

        // expect
        expectedException.expect(AssertionError.class);
        expectedException.expectMessage(is("The property 'anotherComplexObject' is contained the excludedPropertyNames, " +
                                           "but is actually supported by the equals implementation. " +
                                           "Either remove it from the excludedPropertyNames or the equals implementation."));

        // when
        new EqualsIntegrityTestBuilder(ComplexClass.class).addExcludedPropertyName("anotherComplexObject").create().run();
    }

    @Test
    public void allowsToExcludeProperty() {
        new EqualsIntegrityTestBuilder(MissingPropertyInEquals.class).addExcludedPropertyName("complexObject").create().run();
    }

    @Test
    public void exceptionThrowingSetterCausesIllegalArgumentException() {

        // expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Calling the setter of the property 'foo' threw an exception. The setter call can be avoided by excluding the property from the test."));

        // when
        new EqualsIntegrityTestBuilder(ExceptionThrowingSetter.class).create().run();
    }

    @Test
    public void allowsToExcludeExceptionThrowingSetter() {
        new EqualsIntegrityTestBuilder(ExceptionThrowingSetter.class).addExcludedPropertyName("foo").create().run();
    }

    @Test
    public void exceptionThrowingSetterOfIncludedPropertyCausesIllegalArgumentException() {

        // expect
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(is("Calling the setter of the property 'foo' threw an exception. The setter call can be avoided by removing the property from the includedPropertyNames."));

        //
        new EqualsIntegrityTestBuilder(ExceptionThrowingSetter.class).addIncludedPropertyName("foo").create().run();
    }

    @Test
    public void ignoresExceptionThrowingSetterOfNonIncludedProperty() {
        new EqualsIntegrityTestBuilder(ExceptionThrowingSetter.class).addIncludedPropertyName("bar").create().run();
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

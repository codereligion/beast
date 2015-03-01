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
package com.codereligion.beast.internal.creation;

import com.codereligion.beast.InstanceProvider;
import com.codereligion.beast.object.AnotherComplexClass;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.EmptyEnum;
import com.google.common.collect.Sets;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link ObjectFactory #getDefaultObject(Class, String)}.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 18.08.2012
 */
public class ObjectFactoryDefaultTest extends AbstractObjectFactoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Override
    public Object getObject(final Class<?> beanClass, final String propertyName) {
        final ObjectFactory objectFactory = new ObjectFactory(Collections.<InstanceProvider>emptySet());
        return objectFactory.getDefaultObject(beanClass, propertyName);
    }

    @Override
    public Object getObject(final Set<InstanceProvider> instanceProviders, final Class<?> beanClass, final String propertyName) {
        final ObjectFactory objectFactory = new ObjectFactory(instanceProviders);
        return objectFactory.getDefaultObject(beanClass, propertyName);
    }

    @Test
    public void givenStringShouldReturnZero() {
        final Object string = getObject(String.class, null);

        assertNotNull(string);
        assertTrue(string instanceof String);
        assertEquals("0", string);
    }

    @Test
    public void givenIntegerArrayShouldReturnAnArrayWithLengthOneAndElementOfValueZero() {
        final Object intArray = getObject(Integer[].class, null);

        assertNotNull(intArray);
        assertTrue(intArray instanceof Integer[]);
        assertEquals(1, ((Integer[]) intArray).length);
        assertEquals(Integer.valueOf(0), ((Integer[]) intArray)[0]);
    }

    @Test
    public void givenEnumShouldReturnTheEnumWithOrdinalZero() {
        final Object enumeration = getObject(ComplexClass.Enumeration.class, null);

        assertNotNull(enumeration);
        assertTrue(enumeration instanceof ComplexClass.Enumeration);
        assertEquals(0, ((Enum<?>) enumeration).ordinal());
    }

    @Test
    public void givenCustomInstanceProviderForBigDecimalSetShouldReturnProxyWhichIsAnInstanceOfSetWithItemOfDefaultValue() {

        final Set<BigDecimal> defaultInstance = Sets.newHashSet(BigDecimal.ONE);
        final Set<BigDecimal> dirtyInstance = Sets.newHashSet(BigDecimal.TEN);
        final InstanceProvider provider = InstanceProvider.create(defaultInstance, dirtyInstance, Set.class);
        final Set<InstanceProvider> providers = Sets.<InstanceProvider>newHashSet(provider);

        final Object result = getObject(providers, Set.class, null);

        assertNotNull(result);
        assertTrue(result instanceof Set);
        assertEquals(BigDecimal.ONE, ((Set<?>) result).iterator().next());
    }

    @Test
    public void givenComplexClassShouldReturnAnInstanceWhichCompliesToEqualsContractAndReturnZeroOnHashCodeAndToString() {
        final Object complexObject1 = getObject(ComplexClass.class, null);

        assertNotNull(complexObject1);
        assertTrue(complexObject1 instanceof ComplexClass);
        assertEquals(0, complexObject1.hashCode());
        assertEquals("0", complexObject1.toString());

        final Object complexObject2 = getObject(ComplexClass.class, null);
        final Object anotherComplexObject = getObject(AnotherComplexClass.class, null);

        assertTrue(complexObject2 instanceof ComplexClass);
        assertTrue(anotherComplexObject instanceof AnotherComplexClass);

        // test equals
        assertFalse(complexObject1.equals(null));
        assertFalse(complexObject1.equals("foo"));

        assertTrue(complexObject1.equals(complexObject1));
        assertTrue(complexObject1.equals(complexObject2));
        assertTrue(complexObject2.equals(complexObject1));

        assertFalse(complexObject1.equals(anotherComplexObject));
        assertFalse(anotherComplexObject.equals(complexObject1));
    }

    @Test
    public void withCustomClassSpecificOverridenDefaultInstanceProvider() {
        final InstanceProvider instanceProvider = InstanceProvider.create(Integer.valueOf(1), Integer.valueOf(0));
        final Set<InstanceProvider> instanceProviders = Sets.newHashSet(instanceProvider);
        final Object object = getObject(instanceProviders, Integer.class, null);

        assertNotNull(object);
        assertTrue(object instanceof Integer);
        assertEquals(instanceProvider.getDefaultInstance().toString(), object.toString());
    }

    @Test
    public void withCustomPropertySpecificOverridenDefaultInstanceProvider() {
        final String propertyName = "propertyName";
        final InstanceProvider instanceProvider = InstanceProvider.create(Integer.valueOf(1), Integer.valueOf(0), propertyName);
        final Set<InstanceProvider> instanceProviders = Sets.newHashSet(instanceProvider);
        final Object object = getObject(instanceProviders, Integer.class, propertyName);

        assertNotNull(object);
        assertTrue(object instanceof Integer);
        assertEquals(instanceProvider.getDefaultInstance().toString(), object.toString());
    }

    @Test
    public void withCustomClassAndPropertySpecificOverridenDefaultInstanceProvider() {
        final String propertyName = "propertyName";
        final InstanceProvider instanceProvider = InstanceProvider.create(Integer.valueOf(1), Integer.valueOf(0), Integer.class, propertyName);
        final Set<InstanceProvider> instanceProviders = Sets.newHashSet(instanceProvider);
        final Object object = getObject(instanceProviders, Integer.class, propertyName);

        assertNotNull(object);
        assertTrue(object instanceof Integer);
        assertEquals(instanceProvider.getDefaultInstance().toString(), object.toString());
    }

    @Test
    public void withCustomClassSpecificInstanceProvider() {
        final Set<Integer> defaultSet = Sets.newHashSet(Integer.valueOf(1));
        final Set<Integer> dirtySet = Sets.newHashSet(Integer.valueOf(0));
        final String propertyName = "propertyName";
        final InstanceProvider instanceProvider = InstanceProvider.create(defaultSet, dirtySet, Set.class);
        final Set<InstanceProvider> instanceProviders = Sets.newHashSet(instanceProvider);
        final Object object = getObject(instanceProviders, Set.class, propertyName);

        assertNotNull(object);
        assertTrue(object instanceof Set);
        assertEquals(instanceProvider.getDefaultInstance().toString(), object.toString());
    }

    @Test
    public void withCustomPropertySpecificInstanceProvider() {
        final BigDecimal defaultValue = BigDecimal.ONE;
        final BigDecimal dirtyValue = BigDecimal.TEN;
        final String propertyName = "propertyName";
        final InstanceProvider instanceProvider = InstanceProvider.create(defaultValue, dirtyValue, propertyName);
        final Set<InstanceProvider> instanceProviders = Sets.newHashSet(instanceProvider);
        final Object object = getObject(instanceProviders, BigDecimal.class, propertyName);

        assertNotNull(object);
        assertTrue(object instanceof BigDecimal);
        assertEquals(instanceProvider.getDefaultInstance().toString(), object.toString());
    }

    @Test
    public void withCustomClassAndPropertySpecificInstanceProvider() {
        final Set<Integer> defaultSet = Sets.newHashSet(Integer.valueOf(1));
        final Set<Integer> dirtySet = Sets.newHashSet(Integer.valueOf(0));
        final String propertyName = "propertyName";
        final InstanceProvider instanceProvider = InstanceProvider.create(defaultSet, dirtySet, Set.class, propertyName);
        final Set<InstanceProvider> instanceProviders = Sets.newHashSet(instanceProvider);
        final Object object = getObject(instanceProviders, Set.class, propertyName);

        assertNotNull(object);
        assertTrue(object instanceof Set);
        assertEquals(instanceProvider.getDefaultInstance().toString(), object.toString());
    }

    @Test
    public void emptyEnumCausesIllegalStateException() {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Can not get DEFAULT value for enum: class com.codereligion.beast.object.EmptyEnum");

        getObject(EmptyEnum.class, null);
    }
}

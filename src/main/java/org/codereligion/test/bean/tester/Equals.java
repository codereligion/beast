package org.codereligion.test.bean.tester;

import java.util.HashSet;
import java.util.Set;


/**
 * TODO update documentation
 * Tests the equals implementation of a java bean.
 *
 * @author sgroebler
 * @since 11.08.2012
 */
public final class Equals {

    /**
     * Tests that all public setable properties are contained in the equals implementation.
     *
     * @param beanClass the {@link Class} to be tested
     * @throws NullPointerException     when the given parameter is {@code null}
     * @throws IllegalArgumentException when the given {@code beanClass} is not supported
     * @throws AssertionError           when a property is not checked correctly in the equals implementation
     */
    public static <T> void testIntegrity(final Class<T> beanClass) {
        testIntegrity(beanClass, new HashSet<String>());
    }

    /**
     * TODO update documentation
     * Tests that all public setable properties which have not been excluded through
     * {@code excludedPropertyNames} are contained in the equals implementation.
     *
     * @param beanClass             the {@link Class} to be tested
     * @param excludedPropertyNames the names of the properties which should be excluded from the test
     * @throws NullPointerException     when any of the given parameters are {@code null}
     * @throws IllegalArgumentException when the given {@code beanClass} is not supported or not testable
     * @throws AssertionError           when a property is not checked correctly in the equals implementation
     */
    public static <T> void testIntegrity(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
        final EqualsIntegrityTester<T> beanTester = new EqualsIntegrityTester<T>(beanClass, excludedPropertyNames);
        beanTester.test();
    }
    

    /**
     * Tests that all public setable properties are contained in the equals implementation.
     *
     * @param beanClass the {@link Class} to be tested
     * @throws NullPointerException     when the given parameter is {@code null}
     * @throws IllegalArgumentException when the given {@code beanClass} is not supported
     * @throws AssertionError           when a property is not checked correctly in the equals implementation
     */
    public static <T> void testNullSafety(final Class<T> beanClass) {
        testNullSafety(beanClass, new HashSet<String>());
    }

    /**
     * Tests that all public setable properties which have not been excluded through
     * {@code excludedPropertyNames} are contained in the equals implementation.
     *
     * @param beanClass             the {@link Class} to be tested
     * @param excludedPropertyNames the names of the properties which should be excluded from the test
     * @throws NullPointerException     when any of the given parameters are {@code null}
     * @throws IllegalArgumentException when the given {@code beanClass} is not supported or not testable
     * @throws AssertionError           when a property is not checked correctly in the equals implementation
     */
    public static <T> void testNullSafety(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
        final EqualsNullSafetyTester<T> beanTester = new EqualsNullSafetyTester<T>(beanClass, excludedPropertyNames);
        beanTester.test();
    }
}
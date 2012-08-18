package org.codereligion.test.bean.tester;

import java.util.HashSet;
import java.util.Set;

import java.util.regex.Pattern;


/**
 * TODO update documentation
 * Tests the toString implementation of a java bean.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public final class ToString {

	/**
	 * Tests that all public setable properties of the given {@code beanClass}
	 * are contained in the toString implementation.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported
	 * @throws AssertionError when a property is not included in the toString implementation
	 */
	public static <T> void testIntegrity(final Class<T> beanClass) {
		testIntegrity(beanClass, new HashSet<String>());
	}
	
	/**
	 * Tests that all public setable properties of the given {@code beanClass}
	 * which have not been excluded through {@code excludedPropertyNames}
	 * are contained in the toString implementation.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @param excludedPropertyNames the names of the properties which should be excluded from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported
	 * @throws AssertionError when a property is not included in the toString implementation
	 */
	public static <T> void testIntegrity(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		final ToStringIntegrityTester<T> beanTester = new ToStringIntegrityTester<T>(beanClass, excludedPropertyNames);
		beanTester.test();
	}
	

	/**
	 * Tests that the hashCode implementation can handle {@code null} values on all public setable
	 * properties. Further more it checks if the toString result differs when a property is set
	 * to {@code null}.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported or not testable
	 * @throws AssertionError when a property with a {@code null} value throws a {@link NullPointerException}
	 * or is not checked correctly in the toString implementation
	 */
	public static <T> void testNullSafety(final Class<T> beanClass) {
		testNullSafety(beanClass, new HashSet<String>());
	}
	
	/**
	 * Tests that the hashCode implementation can handle {@code null} values on all public setable
	 * properties. Further more it checks if the toString result differs when a property is set
	 * to {@code null}.
	 * 
	 * Tests that all public setable properties which have not been excluded through
     * {@code excludedPropertyNames} are contained in the hashCode implementation.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @param excludedPropertyNames the names of the properties which should be excluded from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported or not testable
	 * @throws AssertionError when a property with a {@code null} value throws a {@link NullPointerException}
	 * or is not checked correctly in the toString implementation
	 */
	public static <T> void testNullSafety(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		final ToStringNullSafetyTester<T> beanTester = new ToStringNullSafetyTester<T>(beanClass, excludedPropertyNames);
		beanTester.test();
	}

	/**
	 * Tests that the given {@code regex} matches the result of the given
	 * {@code beanClass}' toString result.
	 *
     * @param beanClass the {@link Class} to be tested
     * @param pattern
     * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported
	 */
	public static <T> void testFormat(final Class<T> beanClass, final Pattern pattern) {
		final ToStringFormatTester<T> beanTester = new ToStringFormatTester<T>(beanClass, pattern);
		beanTester.test();
	}
}
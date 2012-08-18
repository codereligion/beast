package org.codereligion.test.bean.tester;

import java.util.HashSet;

import java.util.Collections;
import java.util.Set;


/**
 * TODO update documentation
 * Tests the hashCode implementation of a java bean.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public final class HashCode {
	
	/**
	 * Tests that all public setable properties are contained in the hashCode implementation.
	 * 
	 * <p>
	 * This test will also check if the hashCode method returns the same
	 * {@code int} values for two instances which are equal according to the equals implementation.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported
	 * @throws AssertionError when a property is not checked correctly in the hashCode implementation
	 */
	public static <T> void testIntegrity(final Class<T> beanClass) {
		testIntegrity(beanClass, Collections.<String>emptySet());
	}
	
	/**
	 * Tests that all public setable properties which have not been excluded through
	 * {@code excludedPropertyNames} are contained in the hashCode implementation.
	 * 
	 * <p>
	 * This test will also check if the hashCode method returns the same
	 * {@code int} values for two instances which are equal according to the equals implementation.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @param excludedPropertyNames the names of the properties which should be excluded from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported or not testable
	 * @throws AssertionError when a property is not checked correctly in the hashCode implementation
	 */
	public static <T> void testIntegrity(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		final HashCodeIntegrityTester<T> beanTester = new HashCodeIntegrityTester<T>(beanClass, excludedPropertyNames);
		beanTester.test();
	}
	
	/**
	 * Tests that the hashCode implementation can handle {@code null} values on all public setable
	 * properties.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported or not testable
	 * @throws AssertionError when a property is not checked correctly in the hashCode implementation
	 */
	public static <T> void testNullSafety(final Class<T> beanClass) {
		testNullSafety(beanClass, new HashSet<String>());
	}
	
	/**
	 * Tests that all public setable properties which have not been excluded through
     * {@code excludedPropertyNames} are contained in the hashCode implementation.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @param excludedPropertyNames the names of the properties which should be excluded from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported or not testable
	 * @throws AssertionError when a property is not checked correctly in the hashCode implementation
	 */
	public static <T> void testNullSafety(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		final HashCodeNullSafetyTester<T> beanTester = new HashCodeNullSafetyTester<T>(beanClass, excludedPropertyNames);
		beanTester.test();
	}
}
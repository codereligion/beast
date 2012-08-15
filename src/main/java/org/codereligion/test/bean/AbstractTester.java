package org.codereligion.test.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.codereligion.test.bean.exception.BeanTestException;


/**
 * TODO document
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public abstract class AbstractTester <T> {
	
	/**
	 * The {@link Class} of the bean to be tested.
	 */
	protected final Class<T> beanClass;
	
	/**
	 * Property names which should be excluded from the hashCode and equals check.
	 */
	protected Set<String> excludedPropertyNames = new HashSet<String>();
	
	/**
	 * Constructs a new instance for the given {@code beanClass}.
	 * 
	 * @param beanClass the {@link Class} to test
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be tested
	 */
	protected AbstractTester(final Class<T> beanClass) {
		this.beanClass = beanClass;
	}

	/**
	 * TODO
	 */
	protected abstract void testIntegrity();
	
	/**
	 * TODO
	 */
	protected abstract void testNullSafety();

	/**
	 * TODO
	 * Sets the property names which should be excluded from the hashCode and equals test.
	 * Per default no properties are excluded.
	 * 
	 * <p>
	 * This setting is <b>optional</b>.
	 * 
	 * 
	 * @param propertyNames a {@link Set} of property names to be excluded
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	protected void setExcludedPropertyNames(final Set<String> propertyNames) {
		this.excludedPropertyNames = Collections.unmodifiableSet(propertyNames);
	}
	
	/**
	 * TODO document
	 * 
	 * @param object
	 * @param property
	 * @param value
	 */
	protected void setValue(final T object, final Method setter, final Object value) {
		try {
			setter.invoke(object, value);
		} catch (final IllegalAccessException e) {
			throw new BeanTestException("Failed to set '" + value + "' on setter '" + setter + "'.", e);
		} catch (final InvocationTargetException e) {
			throw new BeanTestException("Failed to set '" + value + "' on setter '" + setter + "'.", e);
		} catch (final IllegalArgumentException e) {
			throw new BeanTestException("Failed to set '" + value + "' on setter '" + setter + "'.", e);
		}
	}

	/**
	 * Convenience method to throw a formatted {@link AssertionError} if the given condition is not {@code false}.
	 * 
	 * @param condition the boolean condition to be checked
	 * @param message the message to be formatted in cases an {@link AssertionError} is thrown
	 * @param messageArgs the arguments to be used in message formatting
	 */
	protected void assertFalse(final boolean condition, final String message, final Object... messageArgs) {
		if (condition) {
			final String formattedMessage = String.format(message, messageArgs);
			throw new AssertionError(formattedMessage);
		}
	}

	/**
	 * Convenience method to throw a formatted {@link AssertionError} if the given condition is not {@code true}.
	 * 
	 * @param condition the boolean condition to be checked
	 * @param message the message to be formatted in cases an {@link AssertionError} is thrown
	 * @param messageArgs the arguments to be used in message formatting
	 */
	protected void assertTrue(final boolean condition, final String message, final Object... messageArgs) {
		if (!condition) {
			assertFalse(condition, message, messageArgs);
		}
	}
}
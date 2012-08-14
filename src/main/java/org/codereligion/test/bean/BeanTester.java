package org.codereligion.test.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codereligion.test.bean.creation.ObjectFactory;
import org.codereligion.test.bean.exception.BeanTestException;
import org.codereligion.test.bean.reflect.ReflectUtil;


/**
 * TODO document
 * TODO show usuage
 * TODO test
 * TODO reconsider null-safety checks
 * 
 * @param <T>
 * @author sgroebler
 * @since 11.08.2012
 */
public final class BeanTester <T> {
	
	private static final String EQUALS_NOT_NULL_ERROR		=	"Property '%s' is not included in the equals method. If this is " +
													   			"intentional add it to the excludedHashCodeAndEqualsPropertyNames.";
	
	private static final String EQUALS_NOT_REFLEXIVE_ERROR	=	"Equals method for instance of '%s' is not reflexive.";
	
	private static final String EQUALS_NULL_ERROR			=	"If the property '%s' is null on one instance and not null on another " +
																"instance the equals method should return false when applied to those instances.";
	
	private static final String HASH_CODE_EQUALTIY_ERROR 	=	"Property '%s' is not included in the hashCode method. If this is " +
																"intentional add it to the excludedHashCodeAndEqualsPropertyNames.";
	
	private static final String HASH_CODE_NULL_ERROR 		=	"The hashCode method can not handle null values of the property '%s' correctly.";
	
	private static final String TO_STRING_EQUALITY_ERROR 	=	"Property '%s' is not included in the toString method. If this is " +
																"intentional add it to the excludedHashCodeAndEqualsPropertyNames.";

	private static final String TO_STRING_NULL_ERROR 		=	"The toString method can not handle null values of the property '%s' correctly.";
	
	private static final String TO_STRING_PATTERN_ERROR		= 	"The required pattern '%s' was not matches by the toString result: '%s'";
	
	private static final String EQUALS_HASH_CODE_VIOLATION	=	"If the property '%s' is different in two instances, these two " +
																"instances are equal according to the equals method, but their hashCodes " +
																"are different. This is a violation of the equals/hashCode contract.";
	

	/**
	 * Tests that all public setable properties are contained in the equals implementation.
	 * 
	 * <p>
	 * This test will also check if the equals implementation can handle
	 * {@code null} values correctly.
	 * 
	 * @param <T> the type of the given {@code beanClass}
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws AssertionError when a property is not checked correctly in the equals implementation
	 */
	public static <T> void testEquals(final Class<T> beanClass) {
		testEquals(beanClass, new HashSet<String>());
	}

	/**
	 * Tests that all public setable properties which have not been excluded through
	 * {@code excludedPropertyNames} are contained in the equals implementation.
	 * 
	 * <p>
	 * This test will also check if the equals implementation can handle
	 * {@code null} values correctly.
	 * 
	 * @param <T> the type of the given {@code beanClass}
	 * @param beanClass the {@link Class} to be tested
	 * @param excludedPropertyNames the names of the properties which should be excluded from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws AssertionError when a property is not checked correctly in the equals implementation
	 */
	public static <T> void testEquals(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		if (excludedPropertyNames == null) {
			throw new NullPointerException("excludedPropertyNames must not be null.");
		}
		
		final BeanTester<T> beanTester =  new BeanTester<T>(beanClass);
		beanTester.setExcludesForHashCodeAndEqualsTest(excludedPropertyNames);
		beanTester.testEquals();
	}

	/**
	 * Tests that all public setable properties are contained in the hashCode implementation.
	 * 
	 * <p>
	 * This test will also check if the hashCode implementation can handle
	 * {@code null} values and it will assert that the hashCode method returns the same
	 * {@code int} values for two instances which are equal according to the equals implementation.
	 * 
	 * @param <T> the type of the given {@code beanClass}
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws AssertionError when a property is not checked correctly in the hashCode implementation
	 */
	public static <T> void testHashCode(final Class<T> beanClass) {
		testHashCode(beanClass, new HashSet<String>());
	}
	
	/**
	 * Tests that all public setable properties which have not been excluded through
	 * {@code excludedPropertyNames} are contained in the hashCode implementation.
	 * 
	 * <p>
	 * This test will also check if the hashCode implementation can handle
	 * {@code null} values and it will assert that the hashCode method returns the same
	 * {@code int} values for two instances which are equal according to the equals implementation.
	 * 
	 * @param <T> the type of the given {@code beanClass}
	 * @param beanClass the {@link Class} to be tested
	 * @param excludedPropertyNames the names of the properties which should be excluded from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws AssertionError when a property is not checked correctly in the hashCode implementation
	 */
	public static <T> void testHashCode(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		if (excludedPropertyNames == null) {
			throw new NullPointerException("excludedPropertyNames must not be null.");
		}
		
		final BeanTester<T> beanTester = new BeanTester<T>(beanClass);
		beanTester.setExcludesForHashCodeAndEqualsTest(excludedPropertyNames);
		beanTester.testHashCode();
	}
	
	/**
	 * Tests that all public setable properties of the given {@code beanClass}
	 * are contained in the toString implementation.
	 * 
	 * <p>
	 * This test will also check if the toString implementation can handle
	 * {@code null} values correctly.
	 * 
	 * @param <T> the type of the given {@code beanClass}
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws AssertionError when a property is not included in the toString implementation
	 */
	public static <T> void testToString(final Class<T> beanClass) {
		testToString(beanClass, new HashSet<String>());
	}
	
	/**
	 * Tests that all public setable properties of the given {@code beanClass}
	 * which have not been excluded through {@code excludedPropertyNames}
	 * are contained in the toString implementation.
	 * 
	 * <p>
	 * This test will also check if the toString implementation can handle
	 * {@code null} values correctly.
	 * 
	 * @param <T> the type of the given {@code beanClass}
	 * @param beanClass the {@link Class} to be tested
	 * @param excludedPropertyNames the names of the properties which should be excluded from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws AssertionError when a property is not included in the toString implementation
	 */
	public static <T> void testToString(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		testToString(beanClass, excludedPropertyNames, null);
	}

	/**
	 * Tests that all public setable properties of the given {@code beanClass}
	 * which have not been excluded through {@code excludedPropertyNames}
	 * are contained in the toString implementation.
	 * 
	 * <p>
	 * This test will also check if the toString implementation can handle
	 * {@code null} values correctly and if the given {@code regex}, if not
	 * {@code null}, is applied correctly.
	 * 
	 * 
	 * @param <T> the type of the given {@code beanClass}
	 * @param beanClass the {@link Class} to be tested
	 * @param excludedPropertyNames the names of the properties which should be excluded from the test
	 * @param regex the regular expression the toString result should match, is optional and can be {@code null}
	 * @throws NullPointerException when the given {@code beanClass} or {@code excludedPropertyNames} are {@code null}
	 * @throws AssertionError when a property is not included in the toString implementation
	 */
	public static <T> void testToString(final Class<T> beanClass, final Set<String> excludedPropertyNames, final String regex) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		if (excludedPropertyNames == null) {
			throw new NullPointerException("excludedPropertyNames must not be null.");
		}
		
		final BeanTester<T> beanTester = new BeanTester<T>(beanClass);
		beanTester.setExcludesForToStringTest(excludedPropertyNames);
		beanTester.setToStringPattern(regex);
		beanTester.testToString();
	}
	
	/**
	 * The {@link Class} of the bean to be tested.
	 */
	private final Class<T> beanClass;
	
	/**
	 * Property names which should be excluded from the hashCode and equals check.
	 */
	private Set<String> excludedHashCodeAndEqualsPropertyNames = new HashSet<String>();
	
	/**
	 * Property names which should be excluded from the toString check.
	 */
	private Set<String> excludedToStringPropertyNames = new HashSet<String>();
	
	/**
	 * The compiled regular expression the toString result should match.
	 */
	private Pattern toStringPattern;
	
	/**
	 * TODO
	 */
	private boolean testNullSafetyForEquals = false;
	
	/**
	 * TODO
	 */
	private boolean testNullSafetyForHashCode = false;
	
	/**
	 * TODO
	 */
	private boolean testNullSafetyForToString = false;
	
	/**
	 * Constructs a new instance for the given {@code beanClass}.
	 * 
	 * @param beanClass the {@link Class} to test
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be tested
	 */
	private BeanTester(final Class<T> beanClass) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		if (!ObjectFactory.isCreateable(beanClass)) {
			throw new IllegalArgumentException("BeanTester does not support the given class " + beanClass.getName());
		}
		
		this.beanClass = beanClass;
	}

	/**
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
	private void setExcludesForHashCodeAndEqualsTest(final Set<String> propertyNames) {
		
		if (propertyNames == null) {
			throw new NullPointerException("propertyNames must not be null.");
		}
		
		this.excludedHashCodeAndEqualsPropertyNames = propertyNames;
	}

	/**
	 * Sets the property names which should be excluded from the toString test.
	 * Per default no properties are excluded.
	 * 
	 * <p>
	 * This setting is <b>optional</b>.
	 * 
	 * @param propertyNames a {@link Set} of property names to be excluded
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	private void setExcludesForToStringTest(final Set<String> propertyNames) {
		
		if (propertyNames == null) {
			throw new NullPointerException("propertyNames must not be null.");
		}
		
		this.excludedToStringPropertyNames = propertyNames;
	}
	
	/**
	 * Sets the regular expression the toString result should match. If the
	 * given parameter is {@code null} the regular expression will be ignored
	 * in the test. Per default no pattern is applied.
	 * 
	 * <p>
	 * This setting is <b>optional</b>.
	 * 
	 * @param regex the regular expression the toString result should match
	 */
	private void setToStringPattern(final String regex) {
		
		if (regex == null) {
			return;
		}
		
		toStringPattern = Pattern.compile(regex);
	}

	/**
	 * Tests that all public setable properties which have not been excluded through
	 * {@link #setExcludesForHashCodeAndEqualsTest(Set)} are checked correctly
	 * in the equals implementation.
	 * 
	 * <p>
	 * This test will also check if the equals implementation can handle
	 * {@code null} values correctly.
	 * 
	 * @throws AssertionError when a property is not checked correctly in the equals implementation
	 */
	private void testEquals() {
		final T defaultObject = ObjectFactory.newDefaultObject(beanClass);
		final boolean objectIsEqualToItSelf = defaultObject.equals(defaultObject);
		
		assertTrue(objectIsEqualToItSelf, EQUALS_NOT_REFLEXIVE_ERROR, beanClass.getName());
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		
		// test each field with unequal not null data
		for (final PropertyDescriptor property : properties) {
			
			final String propertyName = property.getName();
			if (excludedHashCodeAndEqualsPropertyNames.contains(propertyName)) {
				// property is excluded intentionally so we skip it
				continue;
			}
	
			final T dirtyObject = ObjectFactory.newDirtyObject(beanClass);
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
			final Object dirtyValue = ObjectFactory.newDirtyObject(propertyType);
			
			// test with not equal and not null values
			setValue(dirtyObject, setter, dirtyValue);
			
			assertFalse(defaultObject.equals(dirtyObject), EQUALS_NOT_NULL_ERROR, propertyName);
			assertFalse(dirtyObject.equals(defaultObject), EQUALS_NOT_NULL_ERROR, propertyName);
	
			// test with with null values on non-primitive types
			if (!propertyType.isPrimitive() && testNullSafetyForEquals) {
				setValue(dirtyObject, setter, null);
				
				assertFalse(defaultObject.equals(dirtyObject), EQUALS_NULL_ERROR, propertyName);
				assertFalse(dirtyObject.equals(defaultObject), EQUALS_NULL_ERROR, propertyName);
			}
		}
	}

	/**
	 * Tests that all public setable properties which have not been excluded through
	 * {@link #setExcludesForHashCodeAndEqualsTest(Set)} are considered correctly
	 * in the hashCode implementation.
	 * 
	 * <p>
	 * This test will also check if the hashCode implementation can handle
	 * {@code null} values and it will assert that the hashCode method returns the same
	 * {@code int} values for two instances which are equal according to the equals implementation.
	 * 
	 * @throws AssertionError when a property is not checked correctly in the hashCode implementation
	 */
	private void testHashCode() {
		final T defaultObject = ObjectFactory.newDefaultObject(beanClass);
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		
		for (final PropertyDescriptor property : properties) {
			
			final String propertyName = property.getName();
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
	
			final T dirtyObject = ObjectFactory.newDefaultObject(beanClass);
			final Object dirtyValue = ObjectFactory.newDirtyObject(propertyType);
			
			setValue(dirtyObject, setter, dirtyValue);
			
			final boolean areEqual = defaultObject.equals(dirtyObject);
			final boolean hashCodesAreEqual = defaultObject.hashCode() == dirtyObject.hashCode();
			final boolean isEqualsHashCodeContractViolated = areEqual == true && hashCodesAreEqual == false;
			
			// hashCode and equals contract must not be violated
			assertFalse(isEqualsHashCodeContractViolated, EQUALS_HASH_CODE_VIOLATION, propertyName);
			
			if (excludedHashCodeAndEqualsPropertyNames.contains(propertyName)) {
				// property is excluded intentionally so we skip it
				continue;
			}
			
			assertFalse(hashCodesAreEqual, HASH_CODE_EQUALTIY_ERROR, propertyName);

			// test with with null values on non-primitive types
			if (!propertyType.isPrimitive() && testNullSafetyForHashCode) {
				setValue(dirtyObject, setter, null);
				final boolean hashCodesAreEqualWithNulls = defaultObject.hashCode() == dirtyObject.hashCode();
				assertFalse(hashCodesAreEqualWithNulls, HASH_CODE_NULL_ERROR, propertyName);
			}
		}
	}

	/**
	 * Tests that all public setable properties which have not been excluded
	 * through {@link #setExcludesForToStringTest(Set)} are contained in the
	 * toString implementation.
	 * 
	 * <p>
	 * This test will also check if the toString implementation can handle
	 * {@code null} values correctly and if the {@link toStringPattern},
	 * if not {@code null}, is applied correctly.
	 * 
	 * @throws AssertionError when a property is not included in the toString implementation
	 */
	private void testToString() {
		final T defaultObject = ObjectFactory.newDefaultObject(beanClass);
		final String defaultToStringResult  = defaultObject.toString();
		
		// test pattern
		if (toStringPattern != null) {
			final Matcher matcher = toStringPattern.matcher(defaultToStringResult);
			final boolean toStringMatchesPattern = matcher.matches();
			assertTrue(toStringMatchesPattern, TO_STRING_PATTERN_ERROR, toStringPattern.pattern(), defaultToStringResult);
		}
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		for (final PropertyDescriptor property : properties) {
			
			final String propertyName = property.getName();
			if (excludedToStringPropertyNames.contains(propertyName)) {
				// property is excluded intentionally so we skip it
				continue;
			}
	
			final T dirtyObject = ObjectFactory.newDefaultObject(beanClass);
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
			final Object dirtyValue = ObjectFactory.newDirtyObject(propertyType);
			
			setValue(dirtyObject, setter, dirtyValue);
			
			final boolean areEqual = defaultToStringResult.equals(dirtyObject.toString());
			
			assertFalse(areEqual, TO_STRING_EQUALITY_ERROR, propertyName);

			// test with with null values on non-primitive types
			if (!propertyType.isPrimitive() && testNullSafetyForToString) {
				setValue(dirtyObject, setter, null);
	
				final boolean areEqualWithNulls = defaultToStringResult.equals(dirtyObject.toString());
				assertFalse(areEqualWithNulls, TO_STRING_NULL_ERROR, propertyName);
			}
		}
	}
	

	/**
	 * TODO
	 * 
	 * @param object
	 * @param property
	 * @param value
	 */
	public void setValue(final T object, final Method setter, final Object value) {
		try {
			setter.invoke(object, value);
		} catch (final IllegalAccessException e) {
			throw new BeanTestException("Failed to set '" + value + "' on '" + object.getClass().getCanonicalName() + "' with setter '" + setter + "'.", e);
		} catch (final InvocationTargetException e) {
			throw new BeanTestException("Failed to set '" + value + "' on '" + object.getClass().getCanonicalName() + "' with setter '" + setter + "'.", e);
		} catch (final IllegalArgumentException e) {
			throw new BeanTestException("Failed to set '" + value + "' on '" + object.getClass().getCanonicalName() + "' with setter '" + setter + "'.", e);
		}
	}


	/**
	 * Convenience method to throw a formatted {@link AssertionError} if the given condition is not {@code false}.
	 * 
	 * @param condition the boolean condition to be checked
	 * @param message the message to be formatted in cases an {@link AssertionError} is thrown
	 * @param messageArgs the arguments to be used in message formatting
	 */
	private void assertFalse(final boolean condition, final String message, final Object... messageArgs) {
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
	private void assertTrue(final boolean condition, final String message, final Object... messageArgs) {
		if (!condition) {
			assertFalse(condition, message, messageArgs);
		}
	}
}
package org.codereligion.test.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codereligion.test.bean.creation.ObjectFactory;
import org.codereligion.test.bean.reflect.ReflectUtil;


/**
 * TODO document
 * TODO show usuage
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public class ToStringTester <T> extends AbstractTester<T> {
	
	private static final String TO_STRING_EQUALITY_ERROR 	=	"Property '%s' is not included in the toString method. If this is " +
																"intentional add it to the excludedPropertyNames.";

	private static final String TO_STRING_NULL_ERROR 		=	"The toString method can not handle null values of the property '%s' correctly.";
	
	private static final String TO_STRING_PATTERN_ERROR		= 	"The required pattern '%s' was not matches by the toString result: '%s'";

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
		final ToStringTester<T> beanTester = new ToStringTester<T>(beanClass, excludedPropertyNames);
		beanTester.testIntegrity();
	}

	/**
	 * TODO
	 * @param beanClass
	 */
	public static <T> void testNullSafety(final Class<T> beanClass) {
		final ToStringTester<T> beanTester = new ToStringTester<T>(beanClass);
		beanTester.testNullSafety();
	}

	/**
	 * Tests that the given {@code regex} matches the result of the given
	 * {@code beanClass}' toString result.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @param regex the regular expression the toString result should match, is optional and can be {@code null}
	 * @throws NullPointerException when the given {@code beanClass} or {@code excludedPropertyNames} are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported
	 * @throws AssertionError when a property is not included in the toString implementation
	 */
	public static <T> void testFormat(final Class<T> beanClass, final String regex) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		if (!ObjectFactory.isCreateable(beanClass)) {
			throw new IllegalArgumentException("BeanTester does not support the given class " + beanClass.getCanonicalName());
		}
		
		final ToStringTester<T> beanTester = new ToStringTester<T>(beanClass);
		beanTester.setToStringPattern(regex);
		beanTester.testFormat();
	}
	
	/**
	 * The compiled regular expression the toString result should match.
	 */
	private Pattern toStringPattern;

	/**
	 * Constructs a new instance for the given {@code beanClass}.
	 * 
	 * @param beanClass the {@link Class} to test
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be tested
	 */
	protected ToStringTester(final Class<T> beanClass) {
		super(beanClass);
	}
	
	/**
	 * TODO
	 * 
	 * @param beanClass
	 * @param excludedPropertyNames
	 */
	protected ToStringTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		super(beanClass, excludedPropertyNames);
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
	 * Tests that all public setable properties which have not been excluded
	 * through {@link #setExcludedPropertyNames(Set)} are contained in the
	 * toString implementation.
	 * 
	 * <p>
	 * This test will also check if the toString implementation can handle
	 * {@code null} values correctly and if the {@link toStringPattern},
	 * if not {@code null}, is applied correctly.
	 * 
	 * @throws AssertionError when a property is not included in the toString implementation
	 */
	@Override
	protected void testIntegrity() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final String defaultToStringResult  = defaultObject.toString();
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		for (final PropertyDescriptor property : properties) {
			
			final String propertyName = property.getName();
			if (excludedPropertyNames.contains(propertyName)) {
				// property is excluded intentionally so we skip it
				continue;
			}
	
			final T dirtyObject = ObjectFactory.newBeanObject(beanClass);
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
			final Object dirtyProperty = ObjectFactory.newDirtyProperty(propertyType);
			
			setValue(dirtyObject, setter, dirtyProperty);
			
			final boolean areEqual = defaultToStringResult.equals(dirtyObject.toString());
			
			assertFalse(areEqual, TO_STRING_EQUALITY_ERROR, propertyName);
		}
	}
	
	/**
	 * TODO
	 */
	@Override
	protected void testNullSafety() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final String defaultToStringResult  = defaultObject.toString();
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		for (final PropertyDescriptor property : properties) {
			
			final String propertyName = property.getName();
			final T dirtyObject = ObjectFactory.newBeanObject(beanClass);
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
			
			// test with with null values on non-primitive types
			if (!propertyType.isPrimitive()) {
				setValue(dirtyObject, setter, null);
				
				final boolean areEqualWithNulls = defaultToStringResult.equals(dirtyObject.toString());
				assertFalse(areEqualWithNulls, TO_STRING_NULL_ERROR, propertyName);
			}
		}
	}
	
	/**
	 * Tests that the {@link toStringPattern} matches the {@code beanClass}'s
	 * toString result.
	 * 
	 * @throws AssertionError when a the {@link toStringPattern} does not match
	 */
	protected void testFormat() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final String defaultToStringResult  = defaultObject.toString();
		
		// test pattern
		final Matcher matcher = toStringPattern.matcher(defaultToStringResult);
		final boolean toStringMatchesPattern = matcher.matches();
		assertTrue(toStringMatchesPattern, TO_STRING_PATTERN_ERROR, toStringPattern.pattern(), defaultToStringResult);
	}
}
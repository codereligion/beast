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
 * Tests the toString implementation of a java bean.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public final class ToStringTester <T> extends AbstractTester<T> {
	
	private static final String TO_STRING_EQUALITY_ERROR 		=	"Property '%s' is not included in the toString method. If this is " +
																	"intentional add it to the excludedPropertyNames.";

	private static final String TO_STRING_NPE_ERROR 			=	"If the property '%s' is null, calling the toString method throws a " +
																	"NullPointerException.";
	
	private static final String TO_STRING_NULL_ASYMMETRY_ERROR	=	"If the property '%s' is null the toString result should differ from " +
																	"a toString result of an instance where this property is not null.";
	
	private static final String TO_STRING_PATTERN_ERROR			= 	"The required pattern '%s' was not matches by the toString result: '%s'.";

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
	 * TODO method name does not reflect usage
	 * TODO maybe add special excludes only for null check
	 * 
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
		final ToStringTester<T> beanTester = new ToStringTester<T>(beanClass);
		beanTester.testNullSafety();
	}

	/**
	 * Tests that the given {@code regex} matches the result of the given
	 * {@code beanClass}' toString result.
	 * 
	 *
     * @param beanClass the {@link Class} to be tested
     * @param pattern
     * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported
	 */
	public static <T> void testFormat(final Class<T> beanClass, final Pattern pattern) {
		final ToStringTester<T> beanTester = new ToStringTester<T>(beanClass, pattern);
		beanTester.testFormat();
	}
	
	/**
	 * The compiled regular expression the toString result should match.
	 */
	private Pattern toStringPattern;

	private ToStringTester(final Class<T> beanClass) {
		super(beanClass);
	}
	
	private ToStringTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		super(beanClass, excludedPropertyNames);
	}
	
	private ToStringTester(final Class<T> beanClass, final Pattern pattern) {
		super(beanClass);
		
		if (pattern == null) {
			throw new NullPointerException("pattern must not be null.");
		}
		
		this.toStringPattern = pattern;
	}

	@Override
	protected void testIntegrity() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final String defaultToStringResult  = defaultObject.toString();
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		for (final PropertyDescriptor property : properties) {
			
			final String propertyName = property.getName();
			if (excludedPropertyNames.contains(propertyName)) {
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
	
	@Override
	protected void testNullSafety() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final String defaultToStringResult  = defaultObject.toString();
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		for (final PropertyDescriptor property : properties) {
			
			final Class<?> propertyType = property.getPropertyType();
			
			if (!propertyType.isPrimitive()) {
				
				final String propertyName = property.getName();
				final T dirtyObject = ObjectFactory.newBeanObject(beanClass);
				final Method setter = property.getWriteMethod();
				setValue(dirtyObject, setter, null);
				
				final String dirtyToString;
				
				try {
					dirtyToString = dirtyObject.toString();
				} catch (final NullPointerException e) {
					throw new AssertionError(String.format(TO_STRING_NPE_ERROR, propertyName));
				}
				
				// TODO does this really makes sense?
				final boolean areEqualWithNulls = defaultToStringResult.equals(dirtyToString);
				assertFalse(areEqualWithNulls, TO_STRING_NULL_ASYMMETRY_ERROR, propertyName);
			}
		}
	}
	
	private void testFormat() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final String defaultToStringResult  = defaultObject.toString();

		final Matcher matcher = toStringPattern.matcher(defaultToStringResult);
		final boolean toStringMatchesPattern = matcher.matches();
		assertTrue(toStringMatchesPattern, TO_STRING_PATTERN_ERROR, toStringPattern.pattern(), defaultToStringResult);
	}
}
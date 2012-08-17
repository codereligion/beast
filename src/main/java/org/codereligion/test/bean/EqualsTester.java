package org.codereligion.test.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.codereligion.test.bean.creation.ObjectFactory;
import org.codereligion.test.bean.reflect.ReflectUtil;


/**
 * Tests the equals implementation of a java bean.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public final class EqualsTester <T> extends AbstractTester<T> {

	private static final String EQUALS_NOT_NULL_ERROR			=	"Property '%s' is not included in the equals method. If this is " +
													   				"intentional add it to the excludedPropertyNames.";
	
	private static final String EQUALS_NOT_REFLEXIVE_ERROR		=	"Equals method for instance of '%s' is not reflexive.";
	
	private static final String EQUALS_NPE_ERROR 				=	"If the property '%s' is null, calling the equals method throws a " +
																	"NullPointerException.";
	
	private static final String EQUALS_NULL_ASYMMETRY_ERROR		=	"If the property '%s' is null on one instance and not null on another " +
																	"instance the equals method should return false when applied to those instances.";
	
	/**
	 * Tests that all public setable properties are contained in the equals implementation.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported
	 * @throws AssertionError when a property is not checked correctly in the equals implementation
	 */
	public static <T> void testIntegrity(final Class<T> beanClass) {
		testIntegrity(beanClass, new HashSet<String>());
	}

	/**
	 * Tests that all public setable properties which have not been excluded through
	 * {@code excludedPropertyNames} are contained in the equals implementation.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @param excludedPropertyNames the names of the properties which should be excluded from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported or not testable
	 * @throws AssertionError when a property is not checked correctly in the equals implementation
	 */
	public static <T> void testIntegrity(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		final EqualsTester<T> beanTester = new EqualsTester<T>(beanClass, excludedPropertyNames);
		beanTester.testIntegrity();
	}

	/**
	 * TODO method name does not reflect usage
	 * TODO maybe add special excludes only for null check
	 * 
	 * Tests that the equals implementation can handle {@code null} values on all public setable
	 * properties. Further more it checks if the equals result differs when a property is set
	 * to {@code null}.
	 * 
	 * @param beanClass the {@link Class} to be tested
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is not supported or not testable
	 * @throws AssertionError when a property with a {@code null} value throws a {@link NullPointerException}
	 * or is not checked correctly in the equals implementation
	 */
	public static <T> void testNullSafety(final Class<T> beanClass) {
		final EqualsTester<T> beanTester = new EqualsTester<T>(beanClass);
		beanTester.testNullSafety();
	}

	private EqualsTester(final Class<T> beanClass) {
		super(beanClass);
	}
	
	private EqualsTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		super(beanClass, excludedPropertyNames);
	}

	@Override
	protected void testIntegrity() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final boolean objectIsEqualToItSelf = defaultObject.equals(defaultObject);
		
		assertTrue(objectIsEqualToItSelf, EQUALS_NOT_REFLEXIVE_ERROR, beanClass.getCanonicalName());
		
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
			
			assertFalse(defaultObject.equals(dirtyObject), EQUALS_NOT_NULL_ERROR, propertyName);
			assertFalse(dirtyObject.equals(defaultObject), EQUALS_NOT_NULL_ERROR, propertyName);
		}
	}
	
	@Override
	protected void testNullSafety() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final boolean objectIsEqualToItSelf = defaultObject.equals(defaultObject);
		
		assertTrue(objectIsEqualToItSelf, EQUALS_NOT_REFLEXIVE_ERROR, beanClass.getCanonicalName());
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		for (final PropertyDescriptor property : properties) {
			
			final Class<?> propertyType = property.getPropertyType();
			
			if (!propertyType.isPrimitive()) {
				
				final String propertyName = property.getName();
				final T dirtyObject = ObjectFactory.newBeanObject(beanClass);
				final Method setter = property.getWriteMethod();
				
				setValue(dirtyObject, setter, null);
				
				try {
					// TODO does asserting the result really makes sense in this case?
					assertFalse(dirtyObject.equals(defaultObject), EQUALS_NULL_ASYMMETRY_ERROR, propertyName);
					assertFalse(defaultObject.equals(dirtyObject), EQUALS_NULL_ASYMMETRY_ERROR, propertyName);
				} catch (final NullPointerException e) {
					throw new AssertionError(String.format(EQUALS_NPE_ERROR, propertyName));
				}
			}
		}
	}
}
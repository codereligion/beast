package org.codereligion.test.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.codereligion.test.bean.creation.ObjectFactory;
import org.codereligion.test.bean.reflect.ReflectUtil;


/**
 * TODO document
 * TODO show usuage
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public class EqualsTester <T> extends AbstractTester<T> {
	
	private static final String NO_SETTER_ERROR				= 	"The given class '%s' does not provide any public setters, only properties " +
																"which are setable through public setters can be verified to be included in " +
																"the to be tested method.";
	
	private static final String EQUALS_NOT_NULL_ERROR		=	"Property '%s' is not included in the equals method. If this is " +
													   			"intentional add it to the excludedPropertyNames.";
	
	private static final String EQUALS_NOT_REFLEXIVE_ERROR	=	"Equals method for instance of '%s' is not reflexive.";
	
	private static final String EQUALS_NULL_ERROR			=	"If the property '%s' is null on one instance and not null on another " +
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
	 * TODO
	 * 
	 * @param beanClass
	 */
	public static <T> void testNullSafety(final Class<T> beanClass) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		if (!ObjectFactory.isCreateable(beanClass)) {
			throw new IllegalArgumentException("BeanTester does not support the given class " + beanClass.getCanonicalName());
		}

		if (!ReflectUtil.hasSetableProperties(beanClass)) {
			throw new IllegalArgumentException(String.format(NO_SETTER_ERROR, beanClass.getCanonicalName()));
		}
		
		final EqualsTester<T> beanTester = new EqualsTester<T>(beanClass);
		beanTester.testNullSafety();
	}

	/**
	 * Constructs a new instance for the given {@code beanClass}.
	 * 
	 * @param beanClass the {@link Class} to test
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be tested
	 */
	protected EqualsTester(final Class<T> beanClass) {
		super(beanClass);
	}
	
	/**
	 * TODO
	 * 
	 * @param beanClass
	 * @param excludedPropertyNames
	 */
	protected EqualsTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		super(beanClass, excludedPropertyNames);
	}

	/**
	 * Tests that all public setable properties which have not been excluded through
	 * {@link #setExcludedPropertyNames(Set)} are checked correctly
	 * in the equals implementation.
	 * 
	 * <p>
	 * This test will also check if the equals implementation can handle
	 * {@code null} values correctly.
	 * 
	 * @throws AssertionError when a property is not checked correctly in the equals implementation
	 */
	@Override
	protected void testIntegrity() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final boolean objectIsEqualToItSelf = defaultObject.equals(defaultObject);
		
		assertTrue(objectIsEqualToItSelf, EQUALS_NOT_REFLEXIVE_ERROR, beanClass.getCanonicalName());
		
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
			
			// test with not equal and not null values
			setValue(dirtyObject, setter, dirtyProperty);
			
			// TODO only for debugging
			if (defaultObject.equals(dirtyObject)) {
				
				assertFalse(defaultObject.equals(dirtyObject), EQUALS_NOT_NULL_ERROR, propertyName);
			}
			
			assertFalse(dirtyObject.equals(defaultObject), EQUALS_NOT_NULL_ERROR, propertyName);
		}
	}
	
	/**
	 * TODO
	 */
	@Override
	protected void testNullSafety() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		final boolean objectIsEqualToItSelf = defaultObject.equals(defaultObject);
		
		assertTrue(objectIsEqualToItSelf, EQUALS_NOT_REFLEXIVE_ERROR, beanClass.getCanonicalName());
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		for (final PropertyDescriptor property : properties) {
			
			final String propertyName = property.getName();
			final T dirtyObject = ObjectFactory.newBeanObject(beanClass);
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
			
			
			// test with with null values on non-primitive types
			if (!propertyType.isPrimitive()) {
				setValue(dirtyObject, setter, null);
				
				assertFalse(defaultObject.equals(dirtyObject), EQUALS_NULL_ERROR, propertyName);
				assertFalse(dirtyObject.equals(defaultObject), EQUALS_NULL_ERROR, propertyName);
			}
		}
	}
}
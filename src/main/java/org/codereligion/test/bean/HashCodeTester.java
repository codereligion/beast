package org.codereligion.test.bean;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Collections;
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
public class HashCodeTester <T> extends AbstractTester<T> {
	
	private static final String HASH_CODE_EQUALTIY_ERROR 	=	"Property '%s' is not included in the hashCode method. If this is " +
																"intentional add it to the excludedPropertyNames.";
	
	private static final String HASH_CODE_NULL_ERROR 		=	"The hashCode method can not handle null values of the property '%s' correctly.";
	
	private static final String EQUALS_HASH_CODE_VIOLATION	=	"If the property '%s' is different in two instances, these two " +
																"instances are equal according to the equals method, but their hashCodes " +
																"are different. This is a violation of the equals/hashCode contract.";
	

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
		final HashCodeTester<T> beanTester = new HashCodeTester<T>(beanClass, excludedPropertyNames);
		beanTester.testIntegrity();
	}
	
	/**
	 * TODO
	 * 
	 * @param beanClass
	 */
	public static <T> void testNullSafety(final Class<T> beanClass) {
		final HashCodeTester<T> beanTester = new HashCodeTester<T>(beanClass);
		beanTester.testNullSafety();
	}
	
	/**
	 * Constructs a new instance for the given {@code beanClass}.
	 * 
	 * @param beanClass the {@link Class} to test
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be tested
	 */
	protected HashCodeTester(final Class<T> beanClass) {
		super(beanClass);
	}
	
	/**
	 * TODO
	 * 
	 * @param beanClass
	 */
	protected HashCodeTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		super(beanClass, excludedPropertyNames);
	}

	/**
	 * Tests that all public setable properties which have not been excluded through
	 * {@link #setExcludedPropertyNames(Set)} are considered correctly
	 * in the hashCode implementation.
	 * 
	 * <p>
	 * This test will also check if the hashCode implementation can handle
	 * {@code null} values and it will assert that the hashCode method returns the same
	 * {@code int} values for two instances which are equal according to the equals implementation.
	 * 
	 * @throws AssertionError when a property is not checked correctly in the hashCode implementation
	 */
	@Override
	protected void testIntegrity() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		for (final PropertyDescriptor property : properties) {
			
			final String propertyName = property.getName();
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
	
			final T dirtyObject = ObjectFactory.newBeanObject(beanClass);
			final Object dirtyProperty = ObjectFactory.newDirtyProperty(propertyType);
			
			setValue(dirtyObject, setter, dirtyProperty);
			
			final boolean areEqual = defaultObject.equals(dirtyObject);
			final boolean hashCodesAreEqual = defaultObject.hashCode() == dirtyObject.hashCode();
			final boolean isEqualsHashCodeContractViolated = areEqual == true && hashCodesAreEqual == false;
			
			// hashCode and equals contract must not be violated
			assertFalse(isEqualsHashCodeContractViolated, EQUALS_HASH_CODE_VIOLATION, propertyName);
			
			if (excludedPropertyNames.contains(propertyName)) {
				// property is excluded intentionally so we skip it
				continue;
			}
			
			assertFalse(hashCodesAreEqual, HASH_CODE_EQUALTIY_ERROR, propertyName);
		}
	}
	
	/**
	 * TODO
	 */
	@Override
	protected void testNullSafety() {
		final T defaultObject = ObjectFactory.newBeanObject(beanClass);
		
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);
		for (final PropertyDescriptor property : properties) {
			
			final String propertyName = property.getName();
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
			
			final T dirtyObject = ObjectFactory.newBeanObject(beanClass);
			
			// test with with null values on non-primitive types
			if (!propertyType.isPrimitive()) {
				setValue(dirtyObject, setter, null);
				final boolean hashCodesAreEqualWithNulls = defaultObject.hashCode() == dirtyObject.hashCode();
				assertFalse(hashCodesAreEqualWithNulls, HASH_CODE_NULL_ERROR, propertyName);
			}
		}
	}
}
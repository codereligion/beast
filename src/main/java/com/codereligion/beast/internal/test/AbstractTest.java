/*
 * Copyright 2012 The Beast Authors (www.codereligion.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codereligion.beast.internal.test;


import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.util.ReflectUtil;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;


/**
 * Abstract test which provides the basic functionality for a test.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
abstract class AbstractTest implements Test {

	/**
	 * The {@link Class} of the bean to be tested.
	 */
	protected final Class<?> beanClass;
	
	/**
	 * The canonical name of the {@link Class} to be tested.
	 */
	protected final String beanClassCanonicalName;
	
	/**
	 * All public settable property of the {@link Class} to be tested.
	 */
	protected final Set<PropertyDescriptor> settableProperties;
	
	/**
	 * The {@link ObjectFactory} used to create dirty and default properties.
	 */
	protected final ObjectFactory objectFactory;
	
	/**
	 * Constructs a new test instance for the given parameters.
	 * 
	 * @param beanClass the {@link Class} to test
	 * @param objectFactory the {@link ObjectFactory} to use for creation of property instances
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be tested
	 */
	protected AbstractTest(
			final Class<?> beanClass,
			final ObjectFactory objectFactory) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}

		this.beanClass = beanClass;
		this.beanClassCanonicalName = beanClass.getCanonicalName();
		
		if (objectFactory == null) {
			throw new NullPointerException("objectFactory must not be null.");
		}

		this.objectFactory = objectFactory;
		
		if (!isTestable()) {
			throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " is not supported for testing.");
		}
		
		this.settableProperties = ReflectUtil.getSettableProperties(beanClass);

		final boolean hasNoSettableProperties = this.settableProperties.isEmpty();
		
		if (hasNoSettableProperties) {
			throw new IllegalArgumentException(String.format(
					"The given class %s does not provide any public setters, only properties " +
					"which are settable through public setters can be verified to be included in " +
					"the to be tested method.", this.beanClassCanonicalName));
		}
	}
	
	/**
	 * TODO
	 *
	 * @param propertyName
	 */
	protected abstract void handlePropertySetterExcetion(PropertyDescriptor property, Throwable e);
	
	/**
	 * Determines if the {@code beanClass} can be instantiated.
	 *
	 * <p>
	 * In order to be instantiable the {@code beanClass} must 
	 * 
	 * <ul>
	 * <li> provide a zero parameter constructor
	 * <li> must not be a primitive
	 * <li> must not be an annotation
	 * <li> must not be an array
	 * <li> must not be an enumeration
	 * <li> must not be an interface
	 * <li> must not be an abstract class
	 * </ul>
	 *
	 * @return true if the {@code beanClass} is testable by this test
	 */
	private boolean isTestable()  {
		return !this.beanClass.isPrimitive() &&
			   !this.beanClass.isAnnotation() &&
			   !this.beanClass.isArray() &&
			   !this.beanClass.isEnum() &&
			   !this.beanClass.isInterface() &&
			   !Modifier.isAbstract(this.beanClass.getModifiers()) &&
			   ReflectUtil.hasDefaultConstructor(this.beanClass);
	}

	/**
	 * Creates a new instance of the {@code beanClass} with default properties set.
	 * A default property is defined as to be equivalent to 0.
	 * 
	 * <pre>
	 * public class Foo {
	 * 	private boolean foo = false;
	 * 	private int bar = 0;
	 * 	private double baz = 0d;
	 * }
	 * </pre>
	 *
	 * @return an instance of the {@code beanClass}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be instantiated
	 */
	protected Object newBeanObject() {
		try {
			final Object object = this.beanClass.newInstance();
			
			for (final PropertyDescriptor property : this.settableProperties) {
				final String propertyName = property.getName();
				final Class<?> propertyType = property.getPropertyType();
				final Object value = this.objectFactory.getDefaultObject(propertyType, propertyName);

				try {
					setValue(object, property, value);
				} catch (final InvocationTargetException e) {
					handlePropertySetterExcetion(property, e);
				}
			}

			return object;
		} catch (final IllegalAccessException e) {
			throw new IllegalArgumentException("Could not find a public default constructor for class " + this.beanClassCanonicalName, e);
		} catch (final InstantiationException e) {
			throw new IllegalArgumentException("Could not instantiate object of class " + this.beanClassCanonicalName, e);
		}
	}
	
	/**
	 * Determines whether the given {@code methodName} is declared by the {@code beanClass}.
	 *
	 * @param methodName the name to check
	 * @return true if the {@code beanClass} declares the {@link Method} specified by the given {@code methodName}
	 */
	protected boolean isMethodImplemented(final String methodName) {
		for (final Method method : this.beanClass.getDeclaredMethods()) {
			final boolean isMatchingName = method.getName().equals(methodName);
			if (isMatchingName) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Sets the given {@code value} with the given {@code setter} to the given {@code object}.
	 * 
	 * @param object the object to set the value on
	 * @param setter the {@link Method} to set the given {@code value} with
	 * @param value the value to be set
	 * @throws IllegalStateException when the setter is not accessible or the {@code value} can not be set
	 * @throws InvocationTargetException when the setter threw an exception
	 */
	protected void setValue(final Object object, final PropertyDescriptor property, final Object value) throws InvocationTargetException {
		
		final Method setter = property.getWriteMethod();

		try {
			setter.invoke(object, value);
		} catch (final IllegalAccessException e) {
			// this should never happen
			throw new IllegalStateException("The method " + setter + " is inaccessable, thus can not be used to set test values.", e);
		} catch (final IllegalArgumentException e) {
			// this should never happen
			throw new IllegalStateException("Failed to set '" + value + "' on setter: " + setter + ".", e);
        }
	}
}
/**
 * Copyright 2013 www.codereligion.com
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
import com.codereligion.beast.internal.test.strategy.InvocationTargetExceptionHandler;
import com.codereligion.cherry.reflect.BeanIntrospections;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;


/**
 * Abstract test which provides the basic functionality for a test.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 11.08.2012
 */
public abstract class AbstractTest implements Test, InvocationTargetExceptionHandler {

    /**
     * The {@link Class} of the bean to be tested.
     */
    protected final Class<?> beanClass;

    /**
     * The canonical name of the {@link Class} to be tested.
     */
    protected final String beanClassCanonicalName;

    /**
     * All public writeable property of the {@link Class} to be tested.
     */
    protected final Set<PropertyDescriptor> writeableProperties;

    /**
     * The {@link ObjectFactory} used to create dirty and default properties.
     */
    protected final ObjectFactory objectFactory;

    /**
     * Constructs a new test instance for the given parameters.
     *
     * @param beanClass     the {@link Class} to test
     * @param objectFactory the {@link ObjectFactory} to use for creation of property instances
     * @throws NullPointerException     when any of the given parameters are {@code null}
     * @throws IllegalArgumentException when the given {@code beanClass} cannot be tested
     */
    protected AbstractTest(final Class<?> beanClass, final ObjectFactory objectFactory) {

        if (beanClass == null) {
            throw new NullPointerException("beanClass must not be null.");
        }

        this.beanClass = beanClass;
        this.beanClassCanonicalName = beanClass.getCanonicalName();

        if (objectFactory == null) {
            throw new NullPointerException("objectFactory must not be null.");
        }

        this.objectFactory = objectFactory;

        if (!canBeInstantiated()) {
            throw new IllegalArgumentException("The given class: " + this.beanClassCanonicalName + " is not supported for testing.");
        }

        this.writeableProperties = BeanIntrospections.getWriteableProperties(beanClass);

        final boolean hasNoWriteableProperties = this.writeableProperties.isEmpty();

        if (hasNoWriteableProperties) {
            throw new IllegalArgumentException(String.format("The given class: %s does not provide any public setters, only properties " +
                                                             "which are writeable through public setters can be verified to be included in " +
                                                             "the to be tested method.", this.beanClassCanonicalName));
        }
    }

    /**
     * Determines if the {@code beanClass} can be instantiated.
     * <p/>
     * <p/>
     * In order to be instantiable the {@code beanClass} must
     * <p/>
     * <ul> <li> provide a zero parameter constructor <li> must not be a primitive <li> must not be an annotation <li> must not be an array <li> must not be an
     * enumeration <li> must not be an interface <li> must not be an abstract class </ul>
     *
     * @return true if the {@code beanClass} is instantiatable by this test
     */
    private boolean canBeInstantiated() {
        return !this.beanClass.isPrimitive() &&
               !this.beanClass.isAnnotation() &&
               !this.beanClass.isArray() &&
               !this.beanClass.isEnum() &&
               !this.beanClass.isInterface() &&
               !Modifier.isAbstract(this.beanClass.getModifiers()) &&
               BeanIntrospections.hasDefaultConstructor(this.beanClass);
    }

    /**
     * Creates a new instance of the {@code beanClass} with default properties set. A default property is defined as to be equivalent to 0.
     * <p/>
     * <pre>
     * public class Foo {
     * 	private boolean foo = false;
     * 	private int bar = 0;
     * 	private double baz = 0d;
     * }
     * </pre>
     *
     * @return an instance of the {@code beanClass}
     * @throws IllegalArgumentException when the given {@code beanClass} cannot be instantiated
     */
    protected Object newBeanObject() {
        try {
            final Object object = this.beanClass.newInstance();

            for (final PropertyDescriptor property : this.writeableProperties) {
                final String propertyName = property.getName();
                final Class<?> propertyType = property.getPropertyType();
                final Object value = this.objectFactory.getDefaultObject(propertyType, propertyName);

                try {
                    setValue(object, property, value);
                } catch (final InvocationTargetException e) {
                    handleInvocationTargetException(property, e);
                }
            }

            return object;
        } catch (final IllegalAccessException e) {
            throw new IllegalArgumentException("Could not find a public default constructor for class: " + this.beanClassCanonicalName, e);
        } catch (final InstantiationException e) {
            throw new IllegalArgumentException("Could not instantiate object of class: " + this.beanClassCanonicalName, e);
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
     * TODO update Sets the given {@code value} with the given {@code setter} to the given {@code object}.
     *
     * @param object the object to set the value on
     * @param value  the value to be set
     * @throws IllegalStateException     when the setter is not accessible or the {@code value} cannot be set
     * @throws InvocationTargetException when the setter threw an exception
     */
    protected void setValue(final Object object, final PropertyDescriptor property, final Object value) throws InvocationTargetException {

        final Method setter = property.getWriteMethod();

        try {
            setter.invoke(object, value);
        } catch (final IllegalAccessException e) {
            // this should never happen
            throw new IllegalStateException("The method: " + setter + " is inaccessible, thus cannot be used to set test values.", e);
        } catch (final IllegalArgumentException e) {
            // this should never happen
            throw new IllegalStateException("Failed to set: '" + value + "' on setter: " + setter + ".", e);
        }
    }

    /**
     * Compares the given object by verifying that:
     * <p/>
     * <ul> <li> it is not {@code null} <li> it is an instance of {@link AbstractTest} <li> the given object has the same {@code beanClass} as this object <li>
     * the given object has the same {@code objectFactory} as this object </ul>
     * <p/>
     * This method should be used by sub-classes to avoid duplication.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AbstractTest)) {
            return false;
        }

        final AbstractTest other = (AbstractTest) obj;

        if (!this.beanClass.equals(other.beanClass)) {
            return false;
        }
        if (!this.objectFactory.equals(other.objectFactory)) {
            return false;
        }
        return true;
    }

    /**
     * Generates a hash code based on the unique members of this class. This method should be used by sub-classes to avoid duplication.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.beanClass.hashCode();
        result = prime * result + this.objectFactory.hashCode();
        return result;
    }

    /**
     * Generates a concatenated string which contains key/value pairs of every unique member of this class with its according value. This method should be used
     * by sub-classes to avoid duplication.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("beanClass=");
        builder.append(this.beanClass);
        builder.append(", objectFactory=");
        builder.append(this.objectFactory);
        return builder.toString();
    }
}
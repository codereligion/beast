package com.codereligion.beast;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Set;


/**
 * Abstract tester which provides the basic functionality for all bean tests.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
abstract class AbstractTest <T> {

	/**
	 * The {@link Class} of the bean to be tested.
	 */
	protected final Class<T> beanClass;
	
	/**
	 * The canonical name of the {@link Class} to be tested.
	 */
	protected final String beanClassCanonicalName;
	
	/**
	 * All public settable property of the {@link Class} to be tested.
	 */
	protected final Set<PropertyDescriptor> settableProperties;
	
	/**
	 * Property names which should be excluded from the hashCode and equals check.
	 */
	protected final Set<String> excludedPropertyNames;
	
	/**
	 * The {@link ObjectFactory} used to create dirty and default properties.
	 */
	protected final ObjectFactory objectFactory;
	
	/**
	 * Constructs a new test instance for the given parameters.
	 * 
	 * @param beanClass the {@link Class} to test
	 * @param excludedPropertyNames the names of the properties to exclude from the test
	 * @param objectFactory the {@link ObjectFactory} to use for creation of property instances
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be tested
	 */
	protected AbstractTest(
			final Class<T> beanClass,
			final Set<String> excludedPropertyNames,
			final ObjectFactory objectFactory) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}

		this.beanClass = beanClass;
		this.beanClassCanonicalName = beanClass.getCanonicalName();
		
		if (excludedPropertyNames == null) {
			throw new NullPointerException("excludedPropertyNames must not be null.");
		}

		this.excludedPropertyNames = Collections.unmodifiableSet(excludedPropertyNames);
		
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
	 * Executes this test.
	 */
	protected abstract void run();
	
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
			   hasDefaultConstructor();
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
	protected T newBeanObject() {
		try {
			final T object = this.beanClass.newInstance();
			
			for (final PropertyDescriptor property : this.settableProperties) {
				final Class<?> propertyType = property.getPropertyType();
				final Method setter = property.getWriteMethod();
				final Object value = this.objectFactory.getDefaultObject(propertyType);

				setValue(object, setter, value);
			}

			return object;
		} catch (final IllegalAccessException e) {
			throw new IllegalArgumentException("Could not find a public default constructor for class " + this.beanClassCanonicalName, e);
		} catch (final InstantiationException e) {
			throw new IllegalArgumentException("Could not instantiate object of class " + this.beanClassCanonicalName, e);
		}
	}

	/**
	 * Determines whether the {@code beanClass} has a default constructor.
	 *
	 * @return true if the {@code beanClass} as a default constructor, false otherwise
	 */
	private boolean hasDefaultConstructor() {
		
		final Constructor<?>[] constructors = this.beanClass.getConstructors();
		for (final Constructor<?> constructor : constructors) {
			
			final boolean hasZeroParameter = constructor.getParameterTypes().length == 0;
			if (hasZeroParameter) {
				return true;
			}
		}
		return false;
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
	 * @throws IllegalArgumentException when the {@code setter} threw an exception
	 */
	protected void setValue(final T object, final Method setter, final Object value) {
		try {
			setter.invoke(object, value);
		} catch (final IllegalAccessException e) {
			throw new IllegalStateException(
					"The method " + setter + " is inaccessable, thus can not be used to set test values.", e);
		} catch (final InvocationTargetException e) {
			throw new IllegalArgumentException(
					"The method " + setter + " threw an exception on setting test values. " +
					"If this property should not be tested add it to the excludedPropertyNames.", e);
		} catch (final IllegalArgumentException e) {
			throw new IllegalStateException("Failed to set '" + value + "' on setter: " + setter + ".", e);
		}
	}

    /**
     * Convenience method to throw a formatted {@link AssertionError} with the given {@code message}
     * and {@code messageArgs}.
     *
     * @param message the message used to format
     * @param messageArgs the arguments used to format the message with
     * @throws AssertionError when called
     */
    protected void fail(final String message, final Object... messageArgs) {
        final String formattedMessage = String.format(message, messageArgs);
        throw new AssertionError(formattedMessage);
    }

	/**
	 * Convenience method to throw a formatted {@link AssertionError} if the given condition is not {@code false}.
	 *
	 * @param condition the boolean condition to be checked
	 * @param message the message to be formatted in cases an {@link AssertionError} is thrown
	 * @param messageArgs the arguments to be used in message formatting
	 * @throws AssertionError when the given {@code condition} is true
	 */
	protected void assertFalse(final boolean condition, final String message, final Object... messageArgs) {
		if (condition) {
            fail(message, messageArgs);
		}
	}

    /**
	 * Convenience method to throw a formatted {@link AssertionError} if the given condition is not {@code true}.
	 * 
	 * @param condition the boolean condition to be checked
	 * @param message the message to be formatted in cases an {@link AssertionError} is thrown
	 * @param messageArgs the arguments to be used in message formatting
	 * @throws AssertionError when the given {@code condition} is false
	 */
	protected void assertTrue(final boolean condition, final String message, final Object... messageArgs) {
		if (!condition) {
            fail(message, messageArgs);
		}
	}
}
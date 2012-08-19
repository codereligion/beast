package org.codereligion.test.bean.tester;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Set;
import org.codereligion.test.bean.creation.ObjectFactory;


/**
 * TODO update documentation
 * Abstract tester which provides the basic functionality for all bean testers.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
abstract class AbstractTester <T> {

	/**
	 * The {@link Class} of the bean to be tested.
	 */
	protected final Class<T> beanClass;
	
	/**
	 * TODO
	 */
	protected final String beanClassCanonicalName;
	
	/**
	 * TODO
	 */
	protected final Set<PropertyDescriptor> setableProperties;
	
	/**
	 * Property names which should be excluded from the hashCode and equals check.
	 */
	protected final Set<String> excludedPropertyNames;
	
	/**
	 * Constructs a new instance for the given {@code beanClass}.
	 * 
	 * @param beanClass the {@link Class} to test
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be tested
	 */
	protected AbstractTester(final Class<T> beanClass) {
		this(beanClass, Collections.<String>emptySet());
	}
	
	/**
	 * Constructs a new instance for the given {@code beanClass}.
	 * 
	 * @param beanClass the {@link Class} to test
	 * @param excludedPropertyNames the names of the properties to exclude from the test
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be tested
	 */
	protected AbstractTester(final Class<T> beanClass, final Set<String> excludedPropertyNames) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		if (excludedPropertyNames == null) {
			throw new NullPointerException("excludedPropertyNames must not be null.");
		}

		this.beanClass = beanClass;
		this.beanClassCanonicalName = beanClass.getCanonicalName();
		
		if (!isTestable()) {
			throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " is not supported for testing.");
		}
		
		this.setableProperties = ReflectUtil.getSetableProperties(beanClass);

		final boolean hasNoSetableProperties = this.setableProperties.isEmpty();
		
		if (hasNoSetableProperties) {
			throw new IllegalArgumentException(String.format(
					"The given class %s does not provide any public setters, only properties " +
					"which are setable through public setters can be verified to be included in " +
					"the to be tested method.", this.beanClassCanonicalName));
		}
		
		this.excludedPropertyNames = Collections.unmodifiableSet(excludedPropertyNames);
	}

	/**
	 * TODO
	 */
	protected abstract void test();
	

	/**
	 * Determines if the {@code beanClass} can be instantiated.
	 *
	 * <p>
	 * In order to be instantiable the {@code beanClass} must not be one of
	 * the following java classes:
	 *
	 * <ul>
	 * <li> Boolean or primitive type
	 * <li> AtomicBoolean
	 * <li> Character or primitive type
	 * <li> Byte or primitive type
	 * <li> Short or primitive type
	 * <li> Integer or primitive type
	 * <li> AtomicInteger
	 * <li> Long or primitive type
	 * <li> AtomicLong
	 * <li> Float or primitive type
	 * <li> Double or primitive type
	 * <li> BigDecimal
	 * <li> BigInteger
	 * <li> String
	 * <li> Object
	 * </ul>
	 *
	 * <p>
	 * Further the given {@code beanClass} must provide a zero parameter constructor and
	 * must not be an array, an enumeration, an interface nor an abstract class.
	 *
	 * @param beanClass the {@link Class} to check
	 * @return true if the given {@code beanClass} can be instantiated by this factory, false otherwise
	 * @throws NullPointerException when the given parameter is {@code null}
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
	 * Creates a "default" object of the given {@code beanClass}. A default object
	 * contains values which are equivalent to 0 depending on the actual type.
	 *
	 * <p>
	 * Example:
	 *
	 * <pre>
	 * public class Foo {
	 * 	private boolean foo = false;
	 * 	private int bar = 0;
	 * 	private double baz = 0d;
	 * }
	 * </pre>
	 *
	 * <p>
	 * Note: In order to avoid {@link IllegalArgumentException}s use
	 * {@link ObjectFactory #isCreateable(Class)} to verify your input.
	 *
	 * @param beanClass the {@link Class} to create the default object for
	 * @return an instance of the given {@code beanClass}
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be instantiated
	 * @throws BeanTestException when setting of test values failed
	 */
	protected T newBeanObject() {
		try {
			final T object = this.beanClass.newInstance();
			
			for (final PropertyDescriptor property : this.setableProperties) {
				final Class<?> propertyType = property.getPropertyType();
				final Method setter = property.getWriteMethod();
				final Object value = ObjectFactory.getDefaultObject(propertyType);

				setValue(object, setter, value);
			}

			return object;
		} catch (final IllegalAccessException e) {
			throw new IllegalArgumentException("Could not find a public default constructor for: " + this.beanClassCanonicalName, e);
		} catch (final InstantiationException e) {
			throw new IllegalArgumentException("Could not instantiate: " + this.beanClassCanonicalName, e);
		}
	}

	/**
	 * Retrieves a "dirty" property which is either a cached instance of a common java class
	 * (e.g. {@link String}) or a newly created proxy of the given {@code beanClass}.
	 *
	 * <p>
	 * For common java classes the retrieved property will have a value equivalent to 1.
	 * Proxies will behave on calls to equals, hashCode and toString as if they are of
	 * value 1.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an array type, an array of that type will be
	 * created and returned. The array contains one element which contains a "dirty" value
	 * for the array's component type.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an enumeration the returned object represents
	 * the second value of the enumeration or {@code null} if it does not have a second value.
	 * The order is defined by the enumeration's natural order, see {@link PropertyState #ordinal()}.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an interface or a regular bean a proxy of that
	 * interface or bean is created which will not cascade creation of further sub-instances.
	 * This avoids cycles and out of scope testing of the actual bean under test.
	 *
	 * @param beanClass the {@link Class} to create the dirty object for
	 * @return an instance of the given {@code beanClass}
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when no dirty property can be created for the given {@code beanClass}
	 */
	@SuppressWarnings("unchecked")
	protected <P> P newDirtyProperty(final Class<P> propertyClass) {

		if (propertyClass == null) {
			throw new NullPointerException("propertyClass must not be null.");
		}

		return (P) ObjectFactory.getDirtyObject(propertyClass);
	}

	/**
	 * TODO update documentation
	 * Determines whether the given {@code beanClass} has a default constructor.
	 *
	 * @param beanClass the {@link Class} to check
	 * @return true if the given {@code beanClass} as a default constructor, false otherwise
	 */
	private boolean hasDefaultConstructor() {
		
		final Constructor<?>[] constructors = this.beanClass.getConstructors();
		for (final Constructor<?> constructor : constructors) {
			
			final boolean hasZeroArguments = constructor.getParameterTypes().length == 0;
			if (hasZeroArguments) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * TODO
	 *
	 * @param methodName
	 * @return
	 */
	protected boolean isMethodImplemented(final String methodName) {
		for (final Method method : this.beanClass.getMethods()) {
			final boolean isMatchingName = method.getName().equals(methodName);
			final boolean isNotDefaultObjectImplementation = !method.getDeclaringClass().equals(Object.class);
			
			if (isMatchingName && isNotDefaultObjectImplementation) {
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
	 * @throws BeanTestException when setting the given {@code value} with the given {@code setter}
	 * was not possible
	 */
	protected void setValue(final T object, final Method setter, final Object value) {
		try {
			setter.invoke(object, value);
		} catch (final IllegalAccessException e) {
			throw new IllegalStateException(
					"The method: " + setter + " is inaccessable, thus can not be used to set test values.", e);
		} catch (final InvocationTargetException e) {
			throw new IllegalArgumentException(
					"The method: " + setter + " threw an exception on setting test values. " +
					"If this property should not be tested add it to the excludedPropertyNames.", e);
		} catch (final IllegalArgumentException e) {
			throw new IllegalStateException("Failed to set '" + value + "' on setter: " + setter + ".", e);
		}
	}

    /**
     * TODO
     *
     * @param message
     * @param messageArgs
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
	 */
	protected void assertTrue(final boolean condition, final String message, final Object... messageArgs) {
		if (!condition) {
            fail(message, messageArgs);
		}
	}
}
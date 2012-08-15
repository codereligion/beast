package org.codereligion.test.bean.creation;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.codereligion.test.bean.creation.provider.AbstractProvider;
import org.codereligion.test.bean.creation.provider.AtomicBooleanProvider;
import org.codereligion.test.bean.creation.provider.AtomicIntegerProvider;
import org.codereligion.test.bean.creation.provider.AtomicLongProvider;
import org.codereligion.test.bean.creation.provider.BigDecimalProvider;
import org.codereligion.test.bean.creation.provider.BigIntegerProvider;
import org.codereligion.test.bean.creation.provider.BooleanProvider;
import org.codereligion.test.bean.creation.provider.ByteProvider;
import org.codereligion.test.bean.creation.provider.CharacterProvider;
import org.codereligion.test.bean.creation.provider.DoubleProvider;
import org.codereligion.test.bean.creation.provider.FloatProvider;
import org.codereligion.test.bean.creation.provider.IntegerProvider;
import org.codereligion.test.bean.creation.provider.LongProvider;
import org.codereligion.test.bean.creation.provider.ObjectProvider;
import org.codereligion.test.bean.creation.provider.ShortProvider;
import org.codereligion.test.bean.creation.provider.StringProvider;
import org.codereligion.test.bean.exception.BeanTestException;
import org.codereligion.test.bean.reflect.ReflectUtil;

/**
 * Factory for creating test objects.
 *
 * @author sgroebler
 * @since 11.08.2012
 */
public class ObjectFactory {

	/**
	 * Constant for the name of the {@link Object #toString()} method.
	 */
	private static final String TO_STRING = "toString";

	/**
	 * Constant for the name of the {@link Object #hashCode()} method.
	 */
	private static final String HASH_CODE = "hashCode";

	/**
	 * Constant for the name of the {@link Object #equals(Object)} method.
	 */
	private static final String EQUALS = "equals";

	/**
	 * Stores provider for commonly used classes.
	 */
	private static final Map<String, AbstractProvider<?>> OBJECT_PROVIDER = new HashMap<String, AbstractProvider<?>>();

    private static final Set<Class<?>> UNSUPPORTED_CLASSES = new HashSet<Class<?>>();

	static {
		// initialize provider cache
		OBJECT_PROVIDER.put(Byte.TYPE.getCanonicalName(), ByteProvider.INSTANCE);
		OBJECT_PROVIDER.put(Byte.class.getCanonicalName(), ByteProvider.INSTANCE);
		OBJECT_PROVIDER.put(Character.TYPE.getCanonicalName(), CharacterProvider.INSTANCE);
		OBJECT_PROVIDER.put(Character.class.getCanonicalName(), CharacterProvider.INSTANCE);
		OBJECT_PROVIDER.put(Boolean.TYPE.getCanonicalName(), BooleanProvider.INSTANCE);
		OBJECT_PROVIDER.put(Boolean.class.getCanonicalName(), BooleanProvider.INSTANCE);
		OBJECT_PROVIDER.put(Short.TYPE.getCanonicalName(), ShortProvider.INSTANCE);
		OBJECT_PROVIDER.put(Short.class.getCanonicalName(), ShortProvider.INSTANCE);
		OBJECT_PROVIDER.put(Integer.TYPE.getCanonicalName(), IntegerProvider.INSTANCE);
		OBJECT_PROVIDER.put(Integer.class.getCanonicalName(), IntegerProvider.INSTANCE);
		OBJECT_PROVIDER.put(Long.TYPE.getCanonicalName(), LongProvider.INSTANCE);
		OBJECT_PROVIDER.put(Long.class.getCanonicalName(), LongProvider.INSTANCE);
		OBJECT_PROVIDER.put(Float.TYPE.getCanonicalName(), FloatProvider.INSTANCE);
		OBJECT_PROVIDER.put(Float.class.getCanonicalName(), FloatProvider.INSTANCE);
		OBJECT_PROVIDER.put(Double.TYPE.getCanonicalName(), DoubleProvider.INSTANCE);
		OBJECT_PROVIDER.put(Double.class.getCanonicalName(), DoubleProvider.INSTANCE);
		OBJECT_PROVIDER.put(String.class.getCanonicalName(), StringProvider.INSTANCE);
		OBJECT_PROVIDER.put(Object.class.getCanonicalName(), ObjectProvider.INSTANCE);
		OBJECT_PROVIDER.put(BigInteger.class.getCanonicalName(), BigIntegerProvider.INSTANCE);
		OBJECT_PROVIDER.put(BigDecimal.class.getCanonicalName(), BigDecimalProvider.INSTANCE);
		OBJECT_PROVIDER.put(AtomicLong.class.getCanonicalName(), AtomicLongProvider.INSTANCE);
		OBJECT_PROVIDER.put(AtomicInteger.class.getCanonicalName(), AtomicIntegerProvider.INSTANCE);
		OBJECT_PROVIDER.put(AtomicBoolean.class.getCanonicalName(), AtomicBooleanProvider.INSTANCE);

        UNSUPPORTED_CLASSES.add(Boolean.TYPE);
        UNSUPPORTED_CLASSES.add(Boolean.class);
        UNSUPPORTED_CLASSES.add(AtomicBoolean.class);
        UNSUPPORTED_CLASSES.add(Character.TYPE);
        UNSUPPORTED_CLASSES.add(Character.class);
        UNSUPPORTED_CLASSES.add(Byte.TYPE);
        UNSUPPORTED_CLASSES.add(Byte.class);
        UNSUPPORTED_CLASSES.add(Short.TYPE);
        UNSUPPORTED_CLASSES.add(Short.class);
        UNSUPPORTED_CLASSES.add(Integer.TYPE);
        UNSUPPORTED_CLASSES.add(Integer.class);
        UNSUPPORTED_CLASSES.add(AtomicInteger.class);
        UNSUPPORTED_CLASSES.add(Long.TYPE);
        UNSUPPORTED_CLASSES.add(Long.class);
        UNSUPPORTED_CLASSES.add(AtomicLong.class);
        UNSUPPORTED_CLASSES.add(Float.TYPE);
        UNSUPPORTED_CLASSES.add(Float.class);
        UNSUPPORTED_CLASSES.add(Double.TYPE);
        UNSUPPORTED_CLASSES.add(Double.class);
        UNSUPPORTED_CLASSES.add(BigDecimal.class);
        UNSUPPORTED_CLASSES.add(BigInteger.class);
        UNSUPPORTED_CLASSES.add(String.class);
        UNSUPPORTED_CLASSES.add(Object.class);

	}

	/**
	 * Determines if the given {@code beanClass} can be instantiated by this
	 * factory.
	 *
	 * <p>
	 * In order to be instantiatable the {@code beanClass} must not be one of
	 * the following java classes:
	 *
	 * <ul>
	 * <li>Boolean or primitive type
	 * <li>AtomicBoolean
	 * <li>Character or primitive type
	 * <li>Byte or primitive type
	 * <li>Short or primitive type
	 * <li>Integer or primitive type
	 * <li>AtomicInteger
	 * <li>Long or primitive type
	 * <li>AtomicLong
	 * <li>Float or primitive type
	 * <li>Double or primitive type
	 * <li>BigDecimal
	 * <li>BigInteger
	 * <li>String
	 * <li>Object
	 * </ul>
	 *
	 * <p>
	 * Further the given {@code beanClass} must not be an array, an enumeration,
	 * an interface or an abstract class.
	 *
	 * @param beanClass the {@link Class} to check
	 * @return true if the given {@code beanClass} can be instantiated by this factory, false otherwise
	 * @throws NullPointerException when the given parameter is {@code null}
	 */
	public static <T> boolean isCreateable(final Class<T> beanClass)  {

		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}

		return !UNSUPPORTED_CLASSES.contains(beanClass) &&
			   !beanClass.isArray() &&
			   !beanClass.isEnum() &&
			   !beanClass.isInterface() &&
			   !Modifier.isAbstract(beanClass.getModifiers());
	}

	/**
	 * Creates a "default" object of the given {@code beanClass}. A default object
	 * contains values which are equivalent to 0 depending on the actual type.
	 *
	 * <p>
	 * Example:
	 *
	 * <pre>
	 * public class Foo
	 * 	private boolean foo = false;
	 * 	private int bar = 0;
	 * 	private double baz = 0d;
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
	 */
	public static <T> T newBeanObject(final Class<T> beanClass) {

		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}

		if (!isCreateable(beanClass)) {
			throw new IllegalArgumentException("Instantiation of an object for type " + beanClass.getCanonicalName()+ " is not supported.");
		}

		return createComplexObject(beanClass, ObjectType.DEFAULT);
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
	 * Example:
	 *
	 * <pre>
	 * public class Foo
	 * 	public boolean equals(Object object) {
	 *  	TODO double check
	 * 		return true;
	 * 	}
	 *
	 * 	public int hashCode() {
	 * 		return 1;
	 *  }
	 *
	 *  public String toString() {
	 *  	return "1";
	 *  }
	 * </pre>
	 *
	 * <p>
	 * If the given {@code beanClass} represents an array type, an array of that type will be
	 * created and returned. The array contains one element which contains a "dirty" value
	 * for the array's component type.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an enumeration the returned object represents
	 * the second value of the enumeration or {@code null} if it does not have a second value.
	 * The order is defined by the enumeration's natural order, see {@link ObjectType #ordinal()}.
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
	public static <T> T newDirtyProperty(final Class<T> beanClass) {

		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}

		return (T) getObject(beanClass, ObjectType.DIRTY);
	}

	/**
	 * Creates an object of the given {@code beanClass} with either "default" or "dirty"
	 * values depending on the given {@code objectType}.
	 *
	 * @param beanClass the {@link Class} to create the object for
	 * @param objectType the {@link ObjectType} which determines the how the created object should behave
	 * @return an object of the given {@code beanClass} according to the given {@code objectType}
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 */
	private static <T> T createComplexObject(final Class<T> beanClass, final ObjectType objectType) {
		try {
			final T object = beanClass.newInstance();
			final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(beanClass);

			for (final PropertyDescriptor property : properties) {
				final Class<?> propertyType = property.getPropertyType();
				final Method setter = property.getWriteMethod();
				final Object value = getObject(propertyType, objectType);

				try {
					setter.invoke(object, value);
				} catch (final IllegalAccessException e) {
					throw new BeanTestException("Failed to set '" + value + "' on setter '" + setter + "'.", e);
				} catch (final InvocationTargetException e) {
					throw new BeanTestException("Failed to set '" + value + "' on setter '" + setter + "'.", e);
				} catch (final IllegalArgumentException e) {
					throw new BeanTestException("Failed to set '" + value + "' on setter '" + setter + "'.", e);
				}
			}

			return object;
		} catch (final IllegalAccessException e) {
			throw new BeanTestException("Could not find a public default constructor for: " + beanClass, e);
		} catch (final InstantiationException e) {
			throw new BeanTestException("Could not instantiate: " + beanClass, e);
		}
	}

	/**
	 * Returns either a cached instance of a common java class or creates an instance of the
	 * given {@code beanClass}.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an array type, an array of that type will be
	 * created and returned. The array contains one element behaving according to the given
	 * {@code objectType}.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an enumeration the returned object represents
	 * a value of that enumeration which is either the first or the second according to the given
	 * {@link ObjectType #ordinal()}. If the given enumeration has no first or second value,
	 * {@code null} is returned.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an interface or a regular bean a proxy of that
	 * interface or bean is created which will not cascade creation of further sub-instances.
	 * This avoids cycles and out of scope testing of the actual bean under test.
	 *
	 * @param beanClass the {@link Class} to create the object for
	 * @param objectType the {@link ObjectType} which determines how the created object should behave
	 * @return an object of the given {@code beanClass}
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when no object can be created for the given {@code beanClass}
	 */
	private static Object getObject(final Class<?> beanClass, final ObjectType objectType) {
		final AbstractProvider<?> provider = OBJECT_PROVIDER.get(beanClass.getCanonicalName());

		//  TODO any important types missing?
		if (provider != null) {
			switch (objectType) {
				case DEFAULT: return provider.getDefaultObject();
				case DIRTY: return provider.getDirtyObject();
				default: throw new IllegalStateException("Unknown ObjectType: " + objectType + ".");
			}
		} else if (beanClass.isArray()) {
			return createArray(beanClass.getComponentType(), objectType);
		} else if (beanClass.isEnum()) {
			return getEnumValue(beanClass, objectType);
		} else if (beanClass.isInterface()) {
			return createProxy(beanClass, objectType);
		} else {
			return createProxy(beanClass, objectType);
		}
	}

	/**
	 * Retrieves an enumeration value from the given {@code enumClass}. The given
	 * {@code objectType} defines which one to take. It either returns the first or
	 * the second declared value. If there is no first or second value this method
	 * return {@code null}.
	 *
	 * @param enumClass the {@link Class} to get the enumeration value for
	 * @param objectType the {@link ObjectType} which determines which enumeration value is taken
	 * @return an enumeration value of the given {@code enumClass} or {@code null} if the enumeration was empty
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code enumClass} is not an enumeration
	 */
	private static Object getEnumValue(final Class<?> enumClass, final ObjectType objectType) {
		if (!enumClass.isEnum()) {
			throw new IllegalArgumentException("The given type " + enumClass.getCanonicalName() + " is not an enumeration.");
		}

		final Object[] enums = enumClass.getEnumConstants();

		final boolean isEmptyEnum = enums.length == 0;
		if (isEmptyEnum) {
			return null;
		}

		final boolean indexIsInBounds = objectType.ordinal() <= enums.length - 1;
		if (indexIsInBounds) {
			return enums[objectType.ordinal()];
		}

		return null;
	}

	/**
	 * Creates an array of the given {@code arrayClass}. For example if the given
	 * {@code arrayClass} is a {@link Byte} (short: B) it will create an {@link Byte} array (short: [B).
	 *
	 * @param arrayClass the {@link Class} of which the array should be created
	 * @param objectType the {@link ObjectType} which determines the value of the only element of the array
	 * @return an instance of the given {@code arrayClass} with one element
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 */
	private static Object createArray(final Class<?> arrayClass, final ObjectType objectType) {
		final Object array = Array.newInstance(arrayClass, 1);
		final Object value = getObject(arrayClass, objectType);
		Array.set(array, 0, value);
		return array;
	}

	/**
	 * Creates a proxy for the given {@code beanClass} which will intercept
	 * the method calls of equals, hashCode and toString in order to return
	 * an specific result according to the given {@code objectType}.
	 *
	 * @param beanClass the {@link Class} to create the proxy for
	 * @param objectType the {@link ObjectType} which determines the behavior of the proxy
	 * @return the created proxy
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} is {@code final}
	 */
	@SuppressWarnings("unchecked")
	private static <T> T createProxy(final Class<T> beanClass, final ObjectType objectType) {
		if (Modifier.isFinal(beanClass.getModifiers())) {
			throw new IllegalArgumentException("Can not create proxy for final class " + beanClass.getCanonicalName() + ".");
		}

		final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(
					final Object object,
					final Method method,
					final Object[] args,
					final MethodProxy methodProxy) throws Throwable {

				// TODO maybe always call super to not skip expected side-effects?
				if (method.getName().equals(EQUALS)) {
					// TODO is that so clever?, write test
					return objectType == ObjectType.DIRTY;
				} else if (method.getName().equals(HASH_CODE)) {
					return objectType.ordinal();
				} else if (method.getName().equals(TO_STRING)) {
					return objectType.toString();
				}
				return methodProxy.invokeSuper(object, args);
			}
		});
        return (T) enhancer.create();
	}
}

package org.codereligion.test.bean.creation;

import java.lang.reflect.Constructor;

import org.codereligion.test.bean.creation.provider.ObjectProvider;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.codereligion.test.bean.creation.provider.BooleanProvider;
import org.codereligion.test.bean.creation.provider.ByteProvider;
import org.codereligion.test.bean.creation.provider.CharacterProvider;
import org.codereligion.test.bean.creation.provider.DoubleProvider;
import org.codereligion.test.bean.creation.provider.FloatProvider;
import org.codereligion.test.bean.creation.provider.IntegerProvider;
import org.codereligion.test.bean.creation.provider.LongProvider;
import org.codereligion.test.bean.creation.provider.Provider;
import org.codereligion.test.bean.creation.provider.ShortProvider;
import org.codereligion.test.bean.creation.provider.StringProvider;


/**
 * Factory for creating test objects.
 *
 * @author sgroebler
 * @since 11.08.2012
 */
public final class ObjectFactory {
	
	/**
	 * No public constructor for this utility class.
	 */
	private ObjectFactory() {
		throw new IllegalAccessError("This is an utility class which must not be instantiated.");
	}

	/**
	 * A map which stores a mapping of a class' {@code canonicalName} to its {@link Provider}.
	 */
	private static final Map<String, Provider<?>> OBJECT_PROVIDER = new HashMap<String, Provider<?>>();
	
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
	}

	/**
	 * TODO
	 *
	 * @param beanClass
	 * @return
	 */
	public static Object getDirtyObject(final Class<?> beanClass) {
		return getObject(beanClass, PropertyState.DIRTY);
	}
	
	/**
	 * TODO
	 *
	 * @param beanClass
	 * @return
	 */
	public static Object getDefaultObject(final Class<?> beanClass) {
		return getObject(beanClass, PropertyState.DEFAULT);
	}
	
	/**
	 * TODO move documentation to specific calls
	 * Returns either a cached instance of a common java class or creates an instance of the
	 * given {@code beanClass}.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an array type, an array of that type will be
	 * created and returned. The array contains one element behaving according to the given
	 * {@code propertyState}.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an enumeration the returned object represents
	 * a value of that enumeration which is either the first or the second according to the given
	 * {@link PropertyState #ordinal()}. If the given enumeration has no first or second value,
	 * {@code null} is returned.
	 *
	 * <p>
	 * If the given {@code beanClass} represents an interface or a regular bean a proxy of that
	 * interface or bean is created which will not cascade creation of further sub-instances.
	 * This avoids cycles and out of scope testing of the actual bean under test.
	 *
	 * @param beanClass the {@link Class} to create the object for
	 * @param propertyState the {@link PropertyState} which determines how the created object should behave
	 * @return an object of the given {@code beanClass}
	 * @throws IllegalArgumentException when no object can be created for the given {@code beanClass}
	 */
	private static Object getObject(final Class<?> beanClass, final PropertyState propertyState) {
		final Provider<?> provider = OBJECT_PROVIDER.get(beanClass.getCanonicalName());

		if (provider != null) {
			switch (propertyState) {
				case DEFAULT: return provider.getDefaultObject();
				case DIRTY: return provider.getDirtyObject();
				default: throw new IllegalStateException("Unknown propertyState: " + propertyState + ".");
			}
		} else if (beanClass.isArray()) {
			return createArray(beanClass.getComponentType(), propertyState);
		} else if (beanClass.isEnum()) {
			return getEnumValue(beanClass, propertyState);
		} else {
			// interfaces, abstract classes and concrete classes
			return createProxy(beanClass, propertyState);
		}
	}
	
	/**
	 * Retrieves an enumeration value from the given {@code enumClass}. The given
	 * {@code propertyState} defines which one to take. It either returns the first or
	 * the second declared value. If there is no first or second value this method
	 * return {@code null}.
	 *
	 * @param enumClass the {@link Class} to get the enumeration value for
	 * @param propertyState the {@link PropertyState} which determines which enumeration value is taken
	 * @return an enumeration value of the given {@code enumClass} or {@code null} if the enumeration was empty
	 */
	private static Object getEnumValue(final Class<?> enumClass, final PropertyState propertyState) {
		final Object[] enums = enumClass.getEnumConstants();

		final boolean isEmptyEnum = enums.length == 0;
		if (isEmptyEnum) {
			return null;
		}

		final boolean indexIsInBounds = propertyState.ordinal() <= enums.length - 1;
		if (indexIsInBounds) {
			return enums[propertyState.ordinal()];
		}

		return null;
	}

	/**
	 * Creates an array of the given {@code arrayClass}. For example if the given
	 * {@code arrayClass} is a {@link Byte} (short: B) it will create an {@link Byte} array (short: [B).
	 *
	 * @param arrayClass the {@link Class} of which the array should be created
	 * @param propertyState the {@link PropertyState} which determines the value of the only element of the array
	 * @return an instance of the given {@code arrayClass} with one element
	 */
	private static Object createArray(final Class<?> arrayClass, final PropertyState propertyState) {
		final Object array = Array.newInstance(arrayClass, 1);
		final Object value = getObject(arrayClass, propertyState);
		Array.set(array, 0, value);
		return array;
	}

	/**
	 * Creates a proxy for the given {@code beanClass} which will intercept
	 * the method calls of equals, hashCode and toString in order to return
	 * an specific result according to the given {@code propertyState}.
	 *
	 * @param beanClass the {@link Class} to create the proxy for
	 * @param propertyState the {@link PropertyState} which determines the behavior of the proxy
	 * @return the created proxy
	 * @throws IllegalArgumentException when the given {@code beanClass} is {@code final}
	 */
	@SuppressWarnings("unchecked")
	private static <T> T createProxy(final Class<T> beanClass, final PropertyState propertyState) {
		
		if (Modifier.isFinal(beanClass.getModifiers())) {
			throw new IllegalArgumentException("Can not create proxy for final class " + beanClass.getCanonicalName() + ".");
		}
		
		final boolean isConcreteClass = !beanClass.isInterface() && !Modifier.isAbstract(beanClass.getModifiers());
		
		if (isConcreteClass && !hasDefaultConstructor(beanClass)) {
			throw new IllegalArgumentException(
					"Can not create proxy for property class " + beanClass.getCanonicalName() +
					" because of missing default constructor. Either provide a default constructor " +
					" or add a Provider implementation for that class which will be used instead of a proxy.");
		}

		final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback(new MethodInterceptor() {
        	
        	private final Integer propertyStateFlagAsInteger = Integer.valueOf(propertyState.ordinal());
        	private final String propertyStateFlagAsString = String.valueOf(propertyState.ordinal());
        	
			@Override
			public Object intercept(
					final Object thisObject,
					final Method method,
					final Object[] args,
					final MethodProxy methodProxy) throws Throwable {

				if (method.getName().equals(ObjectMethodNames.EQUALS)) {
					
					final Object thatObject = args[0];
					
					if (thatObject == null) {
						return Boolean.FALSE;
					}
					
					if (thisObject == thatObject) {
						return Boolean.TRUE;
					}
					
					final Class<?> thisSuperClass = thisObject.getClass().getSuperclass();
					final Class<?> thatSuperClass = thatObject.getClass().getSuperclass();
					
					if (!thisSuperClass.equals(thatSuperClass)) {
						return Boolean.FALSE;
					}
					
					if (thisObject.hashCode() == thatObject.hashCode()) {
						return Boolean.TRUE;
					}

					return Boolean.FALSE;
				} else if (method.getName().equals(ObjectMethodNames.HASH_CODE)) {
					return this.propertyStateFlagAsInteger;
				} else if (method.getName().equals(ObjectMethodNames.TO_STRING)) {
					return this.propertyStateFlagAsString;
				}
				return methodProxy.invokeSuper(thisObject, args);
			}
		});
        return (T) enhancer.create();
	}
	

	/**
	 * TODO update documentation
	 * TODO duplicate with AbstractTester
	 * Determines whether the given {@code beanClass} has a zero argument constructor.
	 *
	 * @param beanClass the {@link Class} to check
	 * @return true if the given {@code beanClass} as a zero argument constructor, false otherwise
	 */
	private static boolean hasDefaultConstructor(final Class<?> beanClass) {
		
		final Constructor<?>[] constructors = beanClass.getConstructors();
		for (final Constructor<?> constructor : constructors) {
			
			final boolean hasZeroArguments = constructor.getParameterTypes().length == 0;
			if (hasZeroArguments) {
				return true;
			}
		}
		return false;
	}
}

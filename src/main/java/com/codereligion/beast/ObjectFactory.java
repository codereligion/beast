package com.codereligion.beast;


import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * Factory for creating test objects.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
final class ObjectFactory {
	
	/**
	 * A map which stores a mapping of a class' {@code canonicalName} to its {@link InstanceProvider}.
	 */
	private static final Map<String, InstanceProvider<?>> DEFAULT_INSTANCE_PROVIDER_MAP = new HashMap<String, InstanceProvider<?>>();
	
	static {
		// initialize provider cache
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Byte.TYPE.getCanonicalName(), ByteInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Byte.class.getCanonicalName(), ByteInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Character.TYPE.getCanonicalName(), CharacterInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Character.class.getCanonicalName(), CharacterInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Boolean.TYPE.getCanonicalName(), BooleanInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Boolean.class.getCanonicalName(), BooleanInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Short.TYPE.getCanonicalName(), ShortInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Short.class.getCanonicalName(), ShortInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Integer.TYPE.getCanonicalName(), IntegerInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Integer.class.getCanonicalName(), IntegerInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Long.TYPE.getCanonicalName(), LongInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Long.class.getCanonicalName(), LongInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Float.TYPE.getCanonicalName(), FloatInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Float.class.getCanonicalName(), FloatInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Double.TYPE.getCanonicalName(), DoubleInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Double.class.getCanonicalName(), DoubleInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(String.class.getCanonicalName(), StringInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDER_MAP.put(Object.class.getCanonicalName(), ObjectInstanceProvider.INSTANCE);
	}
	
	/**
	 * TODO
	 */
	private final Map<String, InstanceProvider<?>> instanceProviderMap = new HashMap<String, InstanceProvider<?>>();
	
	/**
	 * Constructs a new instance.
	 *
	 * @param customInstanceProviders
	 */
	ObjectFactory(final Set<InstanceProvider<?>> customInstanceProviders) {
		
		// add default instance providers
		for (final Map.Entry<String, InstanceProvider<?>> entry : DEFAULT_INSTANCE_PROVIDER_MAP.entrySet()) {
			this.instanceProviderMap.put(entry.getKey(), entry.getValue());
		}
		
		// add custom instance providers
		for (final InstanceProvider<?> instanceProvider : customInstanceProviders) {
			this.instanceProviderMap.put(instanceProvider.getInstanceClass().getCanonicalName(), instanceProvider);
		}
	}


	/**
	 * TODO
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
	<T> T getDirtyObject(final Class<T> beanClass) {
		return (T) getObject(beanClass, PropertyState.DIRTY);
	}
	
	/**
	 * TODO
	 *
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	<T> T getDefaultObject(final Class<T> beanClass) {
		return (T) getObject(beanClass, PropertyState.DEFAULT);
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
	private Object getObject(final Class<?> beanClass, final PropertyState propertyState) {
		final InstanceProvider<?> provider = this.instanceProviderMap.get(beanClass.getCanonicalName());

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
	private Object createArray(final Class<?> arrayClass, final PropertyState propertyState) {
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
					" or add a CustomInstanceProvider for that class.");
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

package com.codereligion.beast;


import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
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
	 * Stores a mapping of primitive boxed types to their primitive types.
	 */
	private static final Map<Class<?>, Class<?>> BOXED_TO_PRIMITIVE_MAPPING = new HashMap<Class<?>, Class<?>>();
	
	/**
	 * The default instance providers of this factory.
	 */
	private static final Set<InstanceProvider<?>> DEFAULT_INSTANCE_PROVIDERS = new HashSet<InstanceProvider<?>>();
	
	static {
		// creating the mapping between boxed and primitive types
		BOXED_TO_PRIMITIVE_MAPPING.put(Byte.class, Byte.TYPE);
		BOXED_TO_PRIMITIVE_MAPPING.put(Character.class, Character.TYPE);
		BOXED_TO_PRIMITIVE_MAPPING.put(Boolean.class, Boolean.TYPE);
		BOXED_TO_PRIMITIVE_MAPPING.put(Short.class, Short.TYPE);
		BOXED_TO_PRIMITIVE_MAPPING.put(Integer.class, Integer.TYPE);
		BOXED_TO_PRIMITIVE_MAPPING.put(Long.class, Long.TYPE);
		BOXED_TO_PRIMITIVE_MAPPING.put(Float.class, Float.TYPE);
		BOXED_TO_PRIMITIVE_MAPPING.put(Double.class, Double.TYPE);

		// adding default instance providers
		DEFAULT_INSTANCE_PROVIDERS.add(ByteInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDERS.add(CharacterInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDERS.add(BooleanInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDERS.add(ShortInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDERS.add(IntegerInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDERS.add(LongInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDERS.add(FloatInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDERS.add(DoubleInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDERS.add(StringInstanceProvider.INSTANCE);
		DEFAULT_INSTANCE_PROVIDERS.add(ObjectInstanceProvider.INSTANCE);
	}
	
	/**
	 * Maps the canonical name of a {@link Class} to its instance provider.
	 */
	private final Map<String, InstanceProvider<?>> instanceProviderMap = new HashMap<String, InstanceProvider<?>>();
	
	/**
	 * Constructs a new instance.
	 *
	 * @param customInstanceProviders
	 */
	ObjectFactory(final Set<InstanceProvider<?>> customInstanceProviders) {
		
		// add default instance providers
		for (final InstanceProvider<?> instanceProvider : DEFAULT_INSTANCE_PROVIDERS) {
			addInstanceProviderMapping(instanceProvider);
		}
		
		// add custom instance providers
		for (final InstanceProvider<?> instanceProvider : customInstanceProviders) {
			addInstanceProviderMapping(instanceProvider);
		}
	}
	
	/**
	 * Adds the given {@code instanceProvider} to the {@code instanceProviderMap}.
	 * If the given {@code instanceProvider} is providing for a boxed primitive type
	 * the provider will be added twice. Once for the boxed type's canonical name
	 * and once for it's primitive type's canonical name.
	 * 
	 * <p>
	 * Example for Integer:
	 * 
	 * <pre>
	 *  "int" => InstanceProvider
	 *  "java.lang.Integer" => InstanceProvider
	 * </pre>
	 * 
	 * @param instanceProvider the {@code InstanceProvider} to add
	 */
	private void addInstanceProviderMapping(final InstanceProvider<?> instanceProvider) {
		
		final Class<?> instanceClass = instanceProvider.getInstanceClass();

		// add mapping for the given instanceClass to its instanceProvider
		this.instanceProviderMap.put(instanceClass.getCanonicalName(), instanceProvider);
		
		final Class<?> primitiveInstanceClass = BOXED_TO_PRIMITIVE_MAPPING.get(instanceClass);
		final boolean isPrimitiveType = primitiveInstanceClass != null;
		
		if (isPrimitiveType) {
			// add also a mapping for the primitive type to this instanceProvider
			this.instanceProviderMap.put(primitiveInstanceClass.getCanonicalName(), instanceProvider);
		}
	}


	/**
	 * Retrieves a "dirty" object.
	 * 
	 * <p>
	 * For common java classes the retrieved object will represent an equivalent to 1.
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
	 * If the given {@code beanClass} represents an interface, an abstract class or a regular 
	 * bean a proxy of that class is created which will not cascade creation of further sub-instances.
	 * This avoids cycles and out of scope testing of the actual class under test.
	 *
	 * @param beanClass the {@link Class} to create the dirty object for
	 * @return an instance of the given {@code beanClass}
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when no dirty object can be created for the given {@code beanClass}
	 */
	@SuppressWarnings("unchecked")
	<T> T getDirtyObject(final Class<T> beanClass) {
		return (T) getObject(beanClass, PropertyState.DIRTY);
	}
	
	/**
	 * Retrieves a "default" object.
	 * 
	 * <p>
	 * For common java classes the retrieved object will represent an equivalent to 0.
	 * Proxies will behave on calls to equals, hashCode and toString as if they are of
	 * value 0.
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
	 * If the given {@code beanClass} represents an interface, an abstract class or a regular 
	 * bean a proxy of that class is created which will not cascade creation of further sub-instances.
	 * This avoids cycles and out of scope testing of the actual class under test.
	 *
	 * @param beanClass the {@link Class} to create the dirty object for
	 * @return an instance of the given {@code beanClass}
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when no dirty object can be created for the given {@code beanClass}
	 */
	@SuppressWarnings("unchecked")
	<T> T getDefaultObject(final Class<T> beanClass) {
		return (T) getObject(beanClass, PropertyState.DEFAULT);
	}
	
	/**
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
		
		if (isConcreteClass && !ReflectUtil.hasDefaultConstructor(beanClass)) {
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
}
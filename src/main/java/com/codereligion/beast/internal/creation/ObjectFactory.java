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
package com.codereligion.beast.internal.creation;


import com.codereligion.beast.InstanceProvider;
import com.google.common.base.Optional;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;


/**
 * Factory for creating test objects.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 11.08.2012
 */
public final class ObjectFactory {

    /**
     * Serves as place holder for general type mappings which are not specific to a {@code propertyName}.
     */
    private static final String NO_NAME = "This is not a propertyName.";

    /**
     * Stores a mapping of primitive boxed types to their primitive types.
     */
    private static final Map<Class<?>, Class<?>> BOXED_TO_PRIMITIVE_MAPPING = createBoxedToPrimitiveMapping();

    /**
     * The default instance providers of this factory.
     */
    private static final Set<InstanceProvider> DEFAULT_INSTANCE_PROVIDERS = createDefaultInstanceProviders();

    /**
     * Factory to create an unmodifiable map of primitive boxed types to primitive types.
     *
     * @return an unmodifiable {@link Map}
     */
    private static Map<Class<?>, Class<?>> createBoxedToPrimitiveMapping() {

        final Map<Class<?>, Class<?>> map = new HashMap<Class<?>, Class<?>>();

        // creating the mapping between boxed and primitive types
        map.put(Byte.class, Byte.TYPE);
        map.put(Character.class, Character.TYPE);
        map.put(Boolean.class, Boolean.TYPE);
        map.put(Short.class, Short.TYPE);
        map.put(Integer.class, Integer.TYPE);
        map.put(Long.class, Long.TYPE);
        map.put(Float.class, Float.TYPE);
        map.put(Double.class, Double.TYPE);

        return Collections.unmodifiableMap(map);
    }

    /**
     * Factory to create an unmodifiable set of default instance providers.
     *
     * @return an unmodifiable {@link Set}
     */
    private static Set<InstanceProvider> createDefaultInstanceProviders() {

        final Set<InstanceProvider> set = new HashSet<InstanceProvider>();

        set.add(InstanceProviders.BOOLEAN);
        set.add(InstanceProviders.BYTE);
        set.add(InstanceProviders.CHARACTER);
        set.add(InstanceProviders.DOUBLE);
        set.add(InstanceProviders.FLOAT);
        set.add(InstanceProviders.INTEGER);
        set.add(InstanceProviders.LONG);
        set.add(InstanceProviders.OBJECT);
        set.add(InstanceProviders.SHORT);
        set.add(InstanceProviders.STRING);

        return Collections.unmodifiableSet(set);
    }

    /**
     * Maps a {@link Class} to a map of property name to an instance provider.
     */
    private final Map<Class<?>, Map<String, InstanceProvider>> instanceProviderMap = new HashMap<Class<?>, Map<String, InstanceProvider>>();

    /**
     * Constructs a new instance.
     *
     * @param customInstanceProviders the {@link InstanceProvider InstanceProviders} to add
     */
    public ObjectFactory(final Set<InstanceProvider> customInstanceProviders) {

        // add default instance providers
        for (final InstanceProvider instanceProvider : DEFAULT_INSTANCE_PROVIDERS) {
            createMapping(instanceProvider);
        }

        // add custom instance providers
        for (final InstanceProvider instanceProvider : customInstanceProviders) {
            createMapping(instanceProvider);
        }
    }

    /**
     * Adds the given {@code instanceProvider} to the {@code instanceProviderMap}. If the given {@code instanceProvider} is providing for a boxed primitive type
     * the provider will be added twice. Once for the boxed type and once for it's primitive type.
     * <p/>
     * <p/>
     * Example for Integer:
     * <p/>
     * <pre>
     *  "int" => InstanceProvider
     *  "java.lang.Integer" => InstanceProvider
     * </pre>
     *
     * @param instanceProvider the {@link InstanceProvider} to add
     */
    private void createMapping(final InstanceProvider instanceProvider) {

        final Class<?> instanceClass = instanceProvider.getInstanceClass();

        // add mapping for the given instanceClass to its instanceProvider
        createMapping(instanceClass, instanceProvider);

        final Class<?> primitiveInstanceClass = BOXED_TO_PRIMITIVE_MAPPING.get(instanceClass);

        if (primitiveInstanceClass != null) {
            // add also a mapping for the primitive type to this instanceProvider
            createMapping(primitiveInstanceClass, instanceProvider);
        }
    }

    /**
     * Creates a mapping of the given {@code type} to the given {@code instanceProvider}s {@code propertyName} to the actual {@code instanceProvider}. In case
     * the {@code propertyName} is {@code null} a default property name is chosen which indicates that given {@code instanceProvider} is not specific to a
     * property and can be used for each property of the type the given {@code canonicalName} represents.
     * <p/>
     * <p/>
     * Example:
     * <p/>
     * <pre>
     * MyPropertyClass => myPropertyName => InstanceProvider
     * </pre>
     * <p/>
     * In case that one of the mappings already exists it will be overridden with the given values.
     *
     * @param type             the {@link Class} to be used as the key of the mapping
     * @param instanceProvider the {@link InstanceProvider} to be used as the value of the mapping
     */
    private void createMapping(final Class<?> type, final InstanceProvider instanceProvider) {

        final Map<String, InstanceProvider> propertyNameToInstanceProvider = this.instanceProviderMap.get(type);

        if (propertyNameToInstanceProvider == null) {

            final String propertyName = getPropertyNameOrPlaceholder(instanceProvider);

            // create new mapping for propertyName to instanceProvider
            final Map<String, InstanceProvider> newPropertyNameToInstanceProviderMap = new HashMap<String, InstanceProvider>();

            // add new entry to mapping
            newPropertyNameToInstanceProviderMap.put(propertyName, instanceProvider);

            // add new mapping for the instance class' canonicalName to it's map of propertyName to instanceProvider
            this.instanceProviderMap.put(type, newPropertyNameToInstanceProviderMap);
        } else {
            final String propertyName = getPropertyNameOrPlaceholder(instanceProvider);

            // add new entry to mapping
            propertyNameToInstanceProvider.put(propertyName, instanceProvider);
        }
    }

    /**
     * Retrieves the {@code propertyName} from the given {@code instanceProvider}.
     * <p/>
     * <p/>
     * In case the {@code propertyName} is {@code null}, meaning the given {@code instanceProvider} is not property specific, this method will return {@link
     * ObjectFactory #NO_NAME}, which serves as a place holder.
     *
     * @param instanceProvider the {@link InstanceProvider} to get the {@code propertyName} from
     * @return either the {@code propertyName} or {@link ObjectFactory #NO_NAME}
     */
    private String getPropertyNameOrPlaceholder(final InstanceProvider instanceProvider) {

        if (instanceProvider.isPropertySpecific()) {
            return instanceProvider.getPropertyName();
        }

        return NO_NAME;
    }

    /**
     * Retrieves the "dirty" object from either one of the default or custom {@link InstanceProvider InstanceProviders} for the given {@code beanClass} and
     * {@code propertyName}.
     * <p/>
     * <p/>
     * In case no {@link InstanceProvider} is available for the given parameters an instance will be created which can either be an array, an enumeration or a
     * proxy.
     * <p/>
     * <p/>
     * <b>Arrays:</b> If the given {@code beanClass} represents an array type, an array of that type will be created and returned. The array contains one
     * element behaving according to the given {@code propertyState}.
     * <p/>
     * <p/>
     * <b>Enumerations:</b> If the given {@code beanClass} represents an enumeration the returned object represents a value of that enumeration which is either
     * the second according. If the given enumeration has no second value, {@code null} is returned.
     * <p/>
     * <p/>
     * <b>Proxies:</b> If the given {@code beanClass} represents an interface or a regular bean a proxy of that interface or bean is created which will not
     * cascade creation of further sub-instances. This avoids cycles and out of scope testing of the actual bean under test.
     *
     * @param beanClass    the {@link Class} to create the dirty object for
     * @param propertyName the name of the property for which a specific {@link InstanceProvider} should be found, if {@code null} no specific property {@link
     *                     InstanceProvider} will be retrieved
     * @return an instance of the given {@code beanClass}
     * @throws NullPointerException     when the given {@code beanClass} is {@code null}
     * @throws IllegalArgumentException when no dirty object can be created for the given {@code beanClass}
     */
    public Object getDirtyObject(final Class<?> beanClass, final String propertyName) {
        return getObject(beanClass, propertyName, PropertyState.DIRTY);
    }

    /**
     * Retrieves the "default" object from either one of the default or custom {@link InstanceProvider}s for the given {@code beanClass} and {@code
     * propertyName}.
     * <p/>
     * <p/>
     * In case no {@link InstanceProvider} is available for the given parameters an instance will be created which can either be an array, an enumeration or a
     * proxy.
     * <p/>
     * <p/>
     * <b>Arrays:</b> If the given {@code beanClass} represents an array type, an array of that type will be created and returned. The array contains one
     * element behaving according to the given {@code propertyState}.
     * <p/>
     * <p/>
     * <b>Enumerations:</b> If the given {@code beanClass} represents an enumeration the returned object represents a value of that enumeration which is either
     * the first according. If the given enumeration has no first value, {@code null} is returned.
     * <p/>
     * <p/>
     * <b>Proxies:</b> If the given {@code beanClass} represents an interface or a regular class a proxy of that interface or class is created which will not
     * cascade creation of further sub-instances. This avoids cycles and out of scope testing of the actual bean under test.
     *
     * @param beanClass    the {@link Class} to create the dirty object for
     * @param propertyName the name of the property for which a specific {@link InstanceProvider} should be found
     * @return an instance of the given {@code beanClass}
     * @throws NullPointerException     when the given {@code beanClass} is {@code null}
     * @throws IllegalArgumentException when no dirty object can be created for the given {@code beanClass}
     */
    public Object getDefaultObject(final Class<?> beanClass, final String propertyName) {
        return getObject(beanClass, propertyName, PropertyState.DEFAULT);
    }

    /**
     * Retrieves an object behaving according to the given {@code propertyState} from either one of the default or custom {@link InstanceProvider
     * InstanceProviders} for the given {@code beanClass} and {@code propertyName}.
     * <p/>
     * <p/>
     * In case no {@link InstanceProvider} is available for the given parameters an instance will be created which can either be an array, an enumeration or a
     * proxy.
     * <p/>
     * <p/>
     * <b>Arrays:</b> If the given {@code beanClass} represents an array type, an array of that type will be created and returned. The array contains one
     * element behaving according to the given {@code propertyState}.
     * <p/>
     * <p/>
     * <b>Enumerations:</b> If the given {@code beanClass} represents an enumeration the returned object represents a value of that enumeration which is either
     * the first or the second according to the given {@link PropertyState #ordinal()}. If the given enumeration has no first or second value, {@code null} is
     * returned.
     * <p/>
     * <p/>
     * <b>Proxies:</b> If the given {@code beanClass} represents an interface or a regular class a proxy of that interface or class is created which will not
     * cascade creation of further sub-instances. This avoids cycles and out of scope testing of the actual bean under test.
     *
     * @param beanClass     the {@link Class} to create the object for
     * @param propertyName  the name of the property for which a specific {@link InstanceProvider} should be found
     * @param propertyState the {@link PropertyState} which determines how the created object should behave
     * @return an object of the given {@code beanClass}
     * @throws IllegalArgumentException when no object can be created for the given {@code beanClass}
     */
    private Object getObject(final Class<?> beanClass, @Nullable final String propertyName, final PropertyState propertyState) {

        final Optional<InstanceProvider> instanceProvider = getInstanceProvider(beanClass, propertyName);

        if (instanceProvider.isPresent()) {
            return getObjectFromInstanceProvider(instanceProvider.get(), propertyState);
        } else if (beanClass.isArray()) {
            return createArray(beanClass.getComponentType(), propertyState);
        } else if (beanClass.isEnum()) {
            return getEnumValue(beanClass, propertyState);
        } else {
            // interfaces, abstract classes and concrete classes
            return ProxyFactory.createProxy(beanClass, propertyState);
        }
    }

    /**
     * Retrieves an object for the specified {@code propertyState} from the given {@code instanceProvider}.
     *
     * @param propertyState    the the property state to get the object for
     * @param instanceProvider the instance provider to get the object from
     * @return either a default or dirty object
     */
    private Object getObjectFromInstanceProvider(final InstanceProvider instanceProvider, final PropertyState propertyState) {
        switch (propertyState) {
            case DEFAULT:
                return instanceProvider.getDefaultInstance();
            case DIRTY:
                return instanceProvider.getDirtyInstance();
            default:
                throw new IllegalStateException("Unknown propertyState: " + propertyState + ".");
        }
    }

    /**
     * Tries to find an {@link InstanceProvider} for the given {@code type} and the given {@code propertyName}.
     * <p/>
     * <p/>
     * In case there is a specific {@link InstanceProvider} for the given {@code propertyName} and a general {@link InstanceProvider} for the given {@code type}
     * the more specific will be favored.
     *
     * @param type         the {@link Class} to get the {@link InstanceProvider} for
     * @param propertyName propertyName to get the {@link InstanceProvider} for
     * @return an {@link com.google.common.base.Optional} of a matching {@link InstanceProvider}
     */
    private Optional<InstanceProvider> getInstanceProvider(final Class<?> type, final String propertyName) {

        final Map<String, InstanceProvider> propertyNameToInstanceProvider = this.instanceProviderMap.get(type);
        if (propertyNameToInstanceProvider == null) {
            return Optional.absent();
        }

        final InstanceProvider propertyNameSpecificInstanceProvider = propertyNameToInstanceProvider.get(propertyName);
        final boolean foundPropertyNameSpecificInstanceProvider = propertyNameSpecificInstanceProvider != null;
        if (foundPropertyNameSpecificInstanceProvider) {
            return Optional.of(propertyNameSpecificInstanceProvider);
        }

        final InstanceProvider propertyTypeSpecificInstanceProvider = propertyNameToInstanceProvider.get(NO_NAME);
        final boolean foundPropertyTypeSpecificInstanceProvider = propertyTypeSpecificInstanceProvider != null;
        if (foundPropertyTypeSpecificInstanceProvider) {
            return Optional.of(propertyTypeSpecificInstanceProvider);
        }

        throw new IllegalStateException("There is an empty mapping for class: " + type);
    }

    /**
     * Creates an array of the given {@code arrayClass}. For example if the given {@code arrayClass} is a {@link Byte} (short: B) it will create an {@link Byte}
     * array (short: [B).
     *
     * @param arrayClass    the {@link Class} of which the array should be created
     * @param propertyState the {@link PropertyState} which determines the value of the only element of the array
     * @return an instance of the given {@code arrayClass} with one element
     */
    private Object createArray(final Class<?> arrayClass, final PropertyState propertyState) {

        // inner array types do not have a propertyName, so null is passed
        final Object value = getObject(arrayClass, null, propertyState);

        final Object array = Array.newInstance(arrayClass, 1);
        Array.set(array, 0, value);
        return array;
    }

    /**
     * Retrieves an enumeration value from the given {@code enumClass}. The given {@code propertyState} defines which one to take. It either returns the first
     * or the second declared value. If there is no first or second value this method return {@code null}.
     *
     * @param enumClass     the {@link Class} to get the enumeration value for
     * @param propertyState the {@link PropertyState} which determines which enumeration value is taken
     * @return an enumeration value of the given {@code enumClass} or {@code null} if the enumeration was empty
     */
    private static Object getEnumValue(final Class<?> enumClass, final PropertyState propertyState) {
        final Object[] enums = enumClass.getEnumConstants();

        final boolean isIndexOutOfBounds = PropertyState.values().length > enums.length;
        if (isIndexOutOfBounds) {
            throw new IllegalArgumentException("Cannot mutate field of type: " + enumClass + ". The enum must hold at least two values.");
        }

        return enums[propertyState.ordinal()];
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.instanceProviderMap.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObjectFactory other = (ObjectFactory) obj;
        if (!this.instanceProviderMap.equals(other.instanceProviderMap)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ObjectFactory [instanceProviderMap=");
        builder.append(this.instanceProviderMap);
        builder.append("]");
        return builder.toString();
    }
}

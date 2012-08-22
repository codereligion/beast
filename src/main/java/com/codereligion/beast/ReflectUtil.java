package com.codereligion.beast;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * Reflection utility class.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
final class ReflectUtil {
	
	private static final String SETTER_PREFIX = "set";
	private static final String GETTER_PREFIX = "get";
	private static final String BOOLEAN_GETTER_PREFIX = "is";

	/**
	 * No public constructor for this utility class.
	 */
	private ReflectUtil() {
		throw new IllegalAccessError("This is an utility class which must not be instantiated.");
	}
	
	/**
	 * Determines whether the given {@code beanClass} has a default constructor.
	 *
	 * @param beanClass the {@link Class} to check
	 * @return true if the given {@code beanClass} as a zero argument constructor, false otherwise
	 */
	static boolean hasDefaultConstructor(final Class<?> beanClass) {
		
		final Constructor<?>[] constructors = beanClass.getConstructors();
		for (final Constructor<?> constructor : constructors) {
			
			final boolean hasZeroArguments = constructor.getParameterTypes().length == 0;
			if (hasZeroArguments) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retrieves a {@link Set} of settable properties for the given {@code beanClass}.
	 * This includes all properties which have a public setter.
	 * 
	 * @param beanClass the {@link Class} to get the settable properties for
	 * @return a {@link Set} of {@link PropertyDescriptor}s
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be introspected
	 */
	static Set<PropertyDescriptor> getSettableProperties(final Class<?> beanClass) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
		    final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		    
		    final Set<PropertyDescriptor> settableProperties = new HashSet<PropertyDescriptor>();
		        
		    for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
		    	
		    	final PropertyDescriptor genericTypeAware = getGenericTypeAwarePropertyDescriptor(beanClass, propertyDescriptor);
		    	if (genericTypeAware.getWriteMethod() != null) {
		    		settableProperties.add(genericTypeAware);
		    	}
		    }
		    
		    return settableProperties;
		} catch (final IntrospectionException e) {
			throw new IllegalArgumentException("The given class " + beanClass.getCanonicalName() + " can not be introspected because: " + e.getMessage(), e);
		}
	}

	/**
	 * This method provides a workaround for the java bug documented here: http://bugs.sun.com/view_bug.do?bug_id=6528714
	 * 
	 * @param beanClass the {@link Class} to which the given {@code propertyDescriptor} belongs
	 * @param propertyDescriptor the {@link PropertyDescriptor} to potentially workaround
	 * @return either the given {@code propertyDescriptor} or a new one which reflects the underlying property correctly
	 * TODO update exception documentation
	 * @throws IntrospectionException when instantiation of a new {@link PropertyDescriptor} failed
	 */
	private static PropertyDescriptor getGenericTypeAwarePropertyDescriptor (
			final Class<?> beanClass,
			final PropertyDescriptor propertyDescriptor) {
		
		final Method writeMethod = propertyDescriptor.getWriteMethod();
		final Method readMethod = propertyDescriptor.getReadMethod();
		
		// has a non-null write method and its not a bridge, so the bug did not occur
		if (writeMethod != null && !writeMethod.isBridge()) {
    		return propertyDescriptor;
		}
		
		// we could not find a write method or it was a bridge
		
		// the property actually does not provide a setter
		if (readMethod == null) {
			return propertyDescriptor;
		}
		
		// we has a read method so we can try to find the appropriate write method
		
		// create a setter name from the given getter name
		final String propertyName = propertyDescriptor.getName();
		final String getterName = readMethod.getName();
		final String setterName;
		
		if (getterName.contains(GETTER_PREFIX)) {
			setterName = getterName.replace(GETTER_PREFIX, SETTER_PREFIX);
		} else if (getterName.contains(BOOLEAN_GETTER_PREFIX)) {
			setterName = getterName.replace(BOOLEAN_GETTER_PREFIX, SETTER_PREFIX);
		} else {
			throw new IllegalStateException("Method " + readMethod + " does not comply to java beans conventions.");
		}
		
		// try to find public, non bridged write method matching the name
		final Method potentialWriteMethod = getMatchingNonBridgedPublicReadMethod(setterName, beanClass);
		
		if (potentialWriteMethod == null) {
			// did not find a write method so there is actually none
			return propertyDescriptor;
		}
		
		// almost done, but still the damn read method could still be broken
		
		try {

			if (!readMethod.isBridge()) {
				// so now we have the write method and the read method is also not bridged
				return new PropertyDescriptor(propertyName, readMethod, potentialWriteMethod);
			}
	
			// yuck, even the bloody read method was wrong, try to get the right one
			final Method potentialReadMethod = getMatchingNonBridgedPublicReadMethod(getterName, beanClass);
			
			if (potentialReadMethod != null) {
				// now we have the write method and the read method, hurrah!!! 
				return new PropertyDescriptor(propertyName, potentialReadMethod, potentialWriteMethod);
			}
		} catch (final IntrospectionException e) {
			throw new IllegalArgumentException("Could not instrospect property: '" + propertyName + "' because: " + e.getMessage(), e);
		}

		throw new IllegalStateException(
				"PropertyDescriptor for property '" + propertyDescriptor.getName() + "' references " +
				"a bridged readMethod which does not seam to have a public method to bridge to.");
	}
	
	/**
	 * Tries to find a public non-bridged method in the given {@code beanClass} which matches
	 * the given {@code methodName}.
	 *
	 * @param methodName the name of the {@link Method} to find
	 * @param beanClass the {@link Class} in which the {@link Method} is expected
	 * @return the matching {@link Method} or {@code null} if it could not be found
	 */
	private static Method getMatchingNonBridgedPublicReadMethod(final String methodName, final Class<?> beanClass) {
		for (final Method method : beanClass.getMethods()) {
			final boolean isMatchingName = method.getName().equals(methodName);
			final boolean isNotBridge = !method.isBridge();
			final boolean isPublic = Modifier.isPublic(method.getModifiers());
			
			if (isMatchingName && isNotBridge && isPublic) {
				return method;
			}
		}
		
		return null;
	}
}

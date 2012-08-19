package org.codereligion.test.bean.tester;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * Reflection utility class.
 * 
 * @author sgroebler
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
	 * Retrieve a {@link Set} of setable properties for the given {@code beanClass}.
	 * This includes all properties which have a public setter.
	 * 
	 * @param beanClass the {@link Class} to get the setable properties for
	 * @return a {@link Set} of {@link PropertyDescriptor}s
	 * @throws NullPointerException when the given parameter is {@code null}
	 * @throws IllegalArgumentException when the given {@code beanClass} can not be introspected
	 */
	static Set<PropertyDescriptor> getSetableProperties(final Class<?> beanClass) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
		    final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		    
		    final Set<PropertyDescriptor> setableProperties = new HashSet<PropertyDescriptor>();
		        
		    for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
		    	
		    	final PropertyDescriptor genericTypeAware = getGenericTypeAwarePropertyDescriptor(beanClass, propertyDescriptor);
		    	if (genericTypeAware.getWriteMethod() != null) {
		    		setableProperties.add(genericTypeAware);
		    	}
		    }
		    
		    return setableProperties;
		} catch (final IntrospectionException e) {
			throw new IllegalArgumentException("The given class " + beanClass.getCanonicalName() + " can not be introspected because: " + e.getMessage(), e);
		}
	}
	
	/**
	 * This method provides a workaround for the java bug documented here:
	 * http://bugs.sun.com/view_bug.do?bug_id=6528714 
	 * 
	 * @param beanClass the {@link Class} to which the given {@code propertyDescriptor} belongs
	 * @param propertyDescriptor the {@link PropertyDescriptor} 
	 * @return 
	 * @throws IntrospectionException when instantiation of a new {@link PropertyDescriptor} failed
	 */
	private static PropertyDescriptor getGenericTypeAwarePropertyDescriptor (
			final Class<?> beanClass,
			final PropertyDescriptor propertyDescriptor) throws IntrospectionException {
		
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
			// TODO can this actually happen???, this would mean that there is
			// a getter which does not comply with java beans rules
			return propertyDescriptor;
		}
		
		// try to find public, non bridged write method matching the name
		final Method potentialWriteMethod = getMatchingNonBridgedPublicMethod(setterName, beanClass);
		
		if (potentialWriteMethod == null) {
			// did not find a write method so there is actually none
			return propertyDescriptor;
		}
		
		// almost done, but still the damn read method could still be broken
		
		if (!readMethod.isBridge()) {
			// so now we have the write method and the read method is also not bridged
			return new PropertyDescriptor(propertyName, readMethod, potentialWriteMethod);
		}

		// yuck, even the bloody read method was wrong, try to get the right one
		final Method potentialReadMethod = getMatchingNonBridgedPublicMethod(getterName, beanClass);
		
		if (potentialReadMethod != null) {
			// now we have the write method and the read method, hurrah!!! 
			return new PropertyDescriptor(propertyName, potentialReadMethod, potentialWriteMethod);
		}

		// TODO can this actually happen???, this would mean that there is a bridged read method
		// which has no method it bridges to, but on the other hand there is write method with a 
		// bridge and a bridged method...
		return propertyDescriptor;
	}
	
	/**
	 * TODO
	 *
	 * @param methodName
	 * @param beanClass
	 * @return
	 */
	private static Method getMatchingNonBridgedPublicMethod(final String methodName, final Class<?> beanClass) {
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

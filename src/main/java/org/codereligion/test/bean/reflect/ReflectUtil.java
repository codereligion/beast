package org.codereligion.test.bean.reflect;

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
public final class ReflectUtil {
	
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
	public static Set<PropertyDescriptor> getSetableProperties(final Class<?> beanClass) {
		
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
			throw new IllegalArgumentException("Class: " + beanClass.getCanonicalName() + " can not be introspected.", e);
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
		
		// has a non-null write method, so the bug did not occur
		if (propertyDescriptor.getWriteMethod() != null) {
    		return propertyDescriptor;
		}
		
		// it has a read method so we can try to find the appropriate write method
		if (propertyDescriptor.getReadMethod() != null) {
			
			// create a setter name from the given getter name
			final String propertyName = propertyDescriptor.getName();
			final String getterName = propertyDescriptor.getReadMethod().getName();
			final String setterName = getterName.replace("get", "set");
			
			// try to find the method matching the name
			for (final Method method : beanClass.getMethods()) {
				
				final boolean isMatchingName = method.getName().equals(setterName);
				final boolean isNotBridge = !method.isBridge();
				final boolean isPublic = Modifier.isPublic(method.getModifiers());
				
				if (isMatchingName && isNotBridge && isPublic) {
					return new PropertyDescriptor(propertyName, beanClass);
				}
			}
		}
	
		// the property actually does not provide a setter
		return propertyDescriptor;
	}
}

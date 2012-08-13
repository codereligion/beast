package org.codereligion.test.bean.reflect;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.codereligion.test.bean.exception.BeanTestException;

/**
 * TODO document
 * TODO what about abstract classes?
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public class ReflectUtil {

	/**
	 * TODO
	 * 
	 * @param beanClass
	 * @return
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
			// TODO when can this actually happen? java doc is quite rough
			throw new BeanTestException(e);
		}
	}
	
	/**
	 * TODO
	 * 
	 * @param clazz
	 * @param propertyDescriptor
	 * @return
	 * @throws IntrospectionException
	 */
	private static PropertyDescriptor getGenericTypeAwarePropertyDescriptor (
			final Class<?> clazz,
			final PropertyDescriptor propertyDescriptor) throws IntrospectionException {
		
		if (propertyDescriptor.getWriteMethod() != null) {
    		return propertyDescriptor;
		}
		
		if (propertyDescriptor.getReadMethod() != null) {
			final String propertyName = propertyDescriptor.getName();
			final String getterName = propertyDescriptor.getReadMethod().getName();
			final String setterName = getterName.replaceAll("get", "set");
			for (final Method method : clazz.getMethods()) {
				if (!method.isBridge() && method.getName().equals(setterName)) {
					return new PropertyDescriptor(propertyName, clazz);
				}
			}
		}
	
		return propertyDescriptor;
	}
}

package org.codereligion.test.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public class ReflectUtil {
	
	/**
	 * TODO
	 */
	private enum ObjectType {
		DEFAULT((byte) 0),
		DIRTY((byte) 1);
		
		private final byte bit;
		
		private ObjectType(final byte bit) {
			this.bit = bit;
		}
		
		protected boolean getBoolean(){
			return bit == 1;
		}
		
		protected byte getByte() {
			return bit;
		}
		
		protected char getChar() {
			return (char) bit;
		}
		
		protected short getShort() {
			return bit;
		}

		protected int getInt() {
			return bit;
		}
		
		protected long getLong() {
			return bit;
		}
		
		protected float getFloat() {
			return bit;
		}
		
		protected double getDouble() {
			return bit;
		}
	}

	/**
	 * TODO
	 * 
	 * @param clazz
	 * @return
	 */
	private static <T> T createDefaultComplexObject(final Class<T> clazz) {
		try {
			final T object = clazz.newInstance();
			final Set<PropertyDescriptor> properties = getSetableProperties(clazz);
			
			for (final PropertyDescriptor property : properties) {
				final Class<?> propertyType = property.getPropertyType();
				final Method setter = property.getWriteMethod();
				final Object value = getDefaultObject(propertyType);
				
//				System.out.println("Trying to set '" + value + "' on '" + clazz + "' with setter '" + setter + "'.");
				
				setValue(object, setter, value);
			}
			
			return object;
		} catch (final IllegalAccessException e) {
			throw new RuntimeException("Could not find a public default constructor for: " + clazz, e);
		} catch (final InstantiationException e) {
			throw new RuntimeException("Could not instantiate: " + clazz, e);
		}
	}
	
	/**
	 * TODO
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	private static <T> T createDirtyComplexObject(final Class<T> clazz) {
		try {
			final T object = clazz.newInstance();
			final Set<PropertyDescriptor> properties = getSetableProperties(clazz);
			
			for (final PropertyDescriptor property : properties) {
				final Class<?> propertyType = property.getPropertyType();
				final Method setter = property.getWriteMethod();
				final Object value = getDirtyObject(propertyType);
				
				System.out.println("Trying to set '" + value + "' on '" + clazz + "' with setter '" + setter + "'.");
				
				setValue(object, setter, value);
			}
			
			return object;
		} catch (final IllegalAccessException e) {
			throw new RuntimeException("Could not find a public default constructor for: " + clazz, e);
		} catch (final InstantiationException e) {
			throw new RuntimeException("Could not instantiate: " + clazz, e);
		}
	}

	/**
	 * TODO
	 * 
	 * @param clazz
	 * @return
	 */
	public static Set<PropertyDescriptor> getSetableProperties(final Class<?> clazz) {
		try {
			final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		    final PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		    
		    final Set<PropertyDescriptor> setableProperties = new HashSet<PropertyDescriptor>();
		        
		    for (final PropertyDescriptor propertyDescriptor : propertyDescriptors) {
		    	if (propertyDescriptor.getWriteMethod() != null) {
		    		setableProperties.add(propertyDescriptor);
		    	}
		    }
		    
		    return setableProperties;
		} catch (final IntrospectionException e) {
			// TODO when can this actually happen? java doc is quite rough
			throw new RuntimeException(e);
		}
	}

	/**
	 * Sets 
	 * 
	 * @param object
	 * @param property
	 * @param value
	 */
	public static <T> void setValue(final T object, final Method setter, final Object value) {
		try {
			System.out.println("Trying to set type: " + setter.getParameterTypes()[0] + " to type: " + value.getClass());
			setter.invoke(object, value);
		} catch (final IllegalAccessException e) {
			// TODO internal problem: the introspector will never return a reference to a method which is not accessible
			throw new RuntimeException("Tried to set '" + value + "' on '" + object + "' with setter '" + setter + "'.", e);
		} catch (final InvocationTargetException e) {
			// TODO method call threw an exception, wrap it in a runtime exception
			throw new RuntimeException("Tried to set '" + value + "' on '" + object + "' with setter '" + setter + "'.", e);
		}
	}

	/**
	 * TODO
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getDefaultObject(final Class<T> clazz) {
		return getValue(clazz, ObjectType.DEFAULT);
	}

	/**
	 * TODO
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> T getDirtyObject(final Class<T> clazz) {
		return getValue(clazz, ObjectType.DIRTY);
	}

	/**
	 * TODO document
	 * TODO any important types missing?
	 * 
	 * @param clazz
	 * @param number
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getValue(final Class<T> clazz, final ObjectType objectType) {
		if (isBoolean(clazz)){
			return (T)(Boolean) objectType.getBoolean();
		} else if (clazz.equals(AtomicBoolean.class)) {
			return (T) new AtomicBoolean(objectType.getBoolean());
		} else if (isCharacter(clazz)) {
			return (T)(Character) objectType.getChar();
		} else if (clazz.equals(Byte.TYPE) || clazz.equals(Byte.class)) {
			return (T)(Byte) objectType.getByte();
		} else if (clazz.equals(Short.TYPE) || clazz.equals(Short.class)) {
			return (T)(Short) objectType.getShort();
		} else if (clazz.equals(Integer.TYPE) || clazz.equals(Integer.class)) {
			return (T)(Integer) objectType.getInt();
		} else if (clazz.equals(Long.TYPE) || clazz.equals(Long.class)) {
			return (T)(Long) objectType.getLong();
		} else if (clazz.equals(Float.TYPE) || clazz.equals(Float.class)) {
			return (T)(Float) objectType.getFloat();
		} else if (clazz.equals(Double.TYPE) || clazz.equals(Double.class)) {
			return (T)(Double) objectType.getDouble();
		} else if (clazz.equals(AtomicInteger.class)) {
			return (T) new AtomicInteger(objectType.getInt());
		} else if (clazz.equals(AtomicLong.class)) {
			return (T) new AtomicLong(objectType.getLong());
		} else if (clazz.equals(BigDecimal.class)) {
			return (T) new BigDecimal(objectType.getInt());
		} else if (clazz.equals(BigInteger.class)) {
			return (T) new BigInteger("" + objectType.getInt());
		} else if (clazz.equals(String.class)) {
			return (T) objectType.toString();
		} else if (clazz.isArray()) {
			return (T) createArray(clazz.getComponentType(), objectType);
		} else if (clazz.isEnum()) {
			return (T) createEnumValue(clazz, objectType);
		} else if (clazz.isInterface()) {
			return (T) createProxy(clazz, objectType); 
		} else {
			switch(objectType) {
				case DEFAULT: return createDefaultComplexObject(clazz);
				case DIRTY: return createDirtyComplexObject(clazz);
				default: return null;
			}
		}
	}

	/**
	 * @param clazz
	 * @return
	 */
	private static boolean isCharacter(final Class<?> clazz) {
		return clazz.equals(Character.TYPE) || clazz.equals(Character.class);
	}

	/**
	 * TODO
	 * 
	 * @param clazz
	 * @return
	 */
	private static boolean isBoolean(final Class<?> clazz) {
		return clazz.equals(Boolean.TYPE) || clazz.equals(Boolean.class);
	}

	/**
	 * TODO
	 * 
	 * @param clazz
	 * @param objectType
	 * @return
	 */
	private static Object createArray(final Class<?> clazz, final ObjectType objectType) {
		final Object array = Array.newInstance(clazz, 1);
		try {
			Array.set(array, 0, objectType.getByte());
		} catch (final IllegalArgumentException e) {
			System.out.println("Failed to create array of class '" + clazz + "' for objectType '" + objectType + "'.");
		}
		return array;
	}

	/**
	 * TODO
	 * initialize a proxy for the interface which will return according to the given objectType on
	 * calls of equals, hashCode and toString
	 * 
	 * @param clazz
	 * @param objectType
	 * @return
	 */
	private static Object createProxy(final Class<?> clazz,	final ObjectType objectType) {
		return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz}, new InvocationHandler() {
			public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
				if (method.getName().equals("equals")) {
					return objectType.equals(ObjectType.DIRTY.getByte());
				} else if (method.getName().equals("hashCode")) {
					return objectType.getByte();
				} else if (method.getName().equals("toString")) {
					return objectType.toString();
				}
					
				// TODO when can this happen?
				System.out.println("WTF? Somebody called: " + method + " on proxy: " + proxy + " with these arguments: " + args);
				return null;
			}
		});
	}

	/**
	 * TODO
	 * 
	 * @param clazz
	 * @param number
	 * @return
	 */
	private static Object createEnumValue(final Class<?> clazz, final ObjectType objectType) {
		final Object[] enums = clazz.getEnumConstants();
		
		final boolean isEmptyEnum = enums.length == 0;
		if (isEmptyEnum) {
			return null;
		}
			
		final boolean indexIsInBounds = objectType.getByte() <= enums.length - 1;
		if (indexIsInBounds) {
			return enums[objectType.getByte()];
		}
		
		return null;
	}
}

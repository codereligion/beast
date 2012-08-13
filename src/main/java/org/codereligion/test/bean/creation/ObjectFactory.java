package org.codereligion.test.bean.creation;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.codereligion.test.bean.exception.BeanTestException;
import org.codereligion.test.bean.reflect.ReflectUtil;

/**
 * TODO document
 * TODO what about abstract classes
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public class ObjectFactory {
	
	/**
	 * TODO
	 * 
	 * @param clazz
	 * @return
	 */
	private static <T> T createComplexObject(final Class<T> clazz, final ObjectType objectType) {
		try {
			final T object = clazz.newInstance();
			final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(clazz);
			
			for (final PropertyDescriptor property : properties) {
				final Class<?> propertyType = property.getPropertyType();
				final Method setter = property.getWriteMethod();
				final Object value = getValue(propertyType, objectType);
				
				try {
					setter.invoke(object, value);
				} catch (final IllegalAccessException e) {
					throw new BeanTestException("Tried to set '" + value + "' on '" + object + "' with setter '" + setter + "'.", e);
				} catch (final InvocationTargetException e) {
					throw new BeanTestException("Tried to set '" + value + "' on '" + object + "' with setter '" + setter + "'.", e);
				}
			}
			
			return object;
		} catch (final IllegalAccessException e) {
			throw new BeanTestException("Could not find a public default constructor for: " + clazz, e);
		} catch (final InstantiationException e) {
			throw new BeanTestException("Could not instantiate: " + clazz, e);
		}
	}

	/**
	 * TODO
	 * 
	 * @param beanClass
	 * @return
	 */
	public static <T> T newDefaultObject(final Class<T> beanClass) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		return createComplexObject(beanClass, ObjectType.DEFAULT);
	}

	/**
	 * TODO
	 * 
	 * @param beanClass
	 * @return
	 */
	public static <T> T newDirtyObject(final Class<T> beanClass) {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		return createComplexObject(beanClass, ObjectType.DIRTY);
	}
	
	/**
	 * TODO
	 * 
	 * @param <T>
	 * @param beanClass
	 * @return
	 */
	public static <T> boolean isCreateable(final Class<T> beanClass)  {
		
		if (beanClass == null) {
			throw new NullPointerException("beanClass must not be null.");
		}
		
		return !beanClass.equals(Boolean.TYPE) &&
			   !beanClass.equals(Boolean.class) &&
			   !beanClass.equals(AtomicBoolean.class) &&
			   !beanClass.equals(Character.TYPE) &&
			   !beanClass.equals(Character.class) &&
			   !beanClass.equals(Byte.TYPE) &&
			   !beanClass.equals(Byte.class) &&
			   !beanClass.equals(Short.TYPE) &&
			   !beanClass.equals(Short.class) &&
			   !beanClass.equals(Integer.TYPE) &&
			   !beanClass.equals(Integer.class) &&
			   !beanClass.equals(Long.TYPE) &&
			   !beanClass.equals(Long.class) &&
			   !beanClass.equals(Float.TYPE) &&
			   !beanClass.equals(Float.class) &&
			   !beanClass.equals(Double.TYPE) &&
			   !beanClass.equals(Double.class) && 
			   !beanClass.equals(AtomicInteger.class) &&
			   !beanClass.equals(AtomicLong.class) &&
			   !beanClass.equals(BigDecimal.class) && 
			   !beanClass.equals(BigInteger.class) &&
			   !beanClass.equals(String.class) &&
			   !beanClass.equals(Object.class) &&
			   !beanClass.isArray() &&
			   !beanClass.isEnum() && 
			   !beanClass.isInterface();
	}

	/**
	 * TODO document
	 * TODO any important types missing?
	 * TODO really fucking hard to read
	 * 
	 * @param beanClass
	 * @param number
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getValue(final Class<T> beanClass, final ObjectType objectType) {
		if (beanClass.equals(Boolean.TYPE) || beanClass.equals(Boolean.class)){
			return (T)(Boolean) objectType.getBoolean();
		} else if (beanClass.equals(AtomicBoolean.class)) {
			return (T) new AtomicBoolean(objectType.getBoolean());
		} else if (beanClass.equals(Character.TYPE) || beanClass.equals(Character.class)) {
			return (T)(Character) objectType.getChar();
		} else if (beanClass.equals(Byte.TYPE) || beanClass.equals(Byte.class)) {
			return (T)(Byte) objectType.getByte();
		} else if (beanClass.equals(Short.TYPE) || beanClass.equals(Short.class)) {
			return (T)(Short) objectType.getShort();
		} else if (beanClass.equals(Integer.TYPE) || beanClass.equals(Integer.class)) {
			return (T)(Integer) objectType.getInt();
		} else if (beanClass.equals(Long.TYPE) || beanClass.equals(Long.class)) {
			return (T)(Long) objectType.getLong();
		} else if (beanClass.equals(Float.TYPE) || beanClass.equals(Float.class)) {
			return (T)(Float) objectType.getFloat();
		} else if (beanClass.equals(Double.TYPE) || beanClass.equals(Double.class)) {
			return (T)(Double) objectType.getDouble();
		} else if (beanClass.equals(AtomicInteger.class)) {
			return (T) new AtomicInteger(objectType.getInt());
		} else if (beanClass.equals(AtomicLong.class)) {
			return (T) new AtomicLong(objectType.getLong());
		} else if (beanClass.equals(BigDecimal.class)) {
			return (T) new BigDecimal(objectType.getInt());
		} else if (beanClass.equals(BigInteger.class)) {
			return (T) new BigInteger("" + objectType.getInt());
		} else if (beanClass.equals(String.class)) {
			return (T) objectType.toString();
		} else if (beanClass.equals(Object.class)) {
			return (T) objectType;
		} else if (beanClass.isArray()) {
			return (T) createArray(beanClass.getComponentType(), objectType);
		} else if (beanClass.isEnum()) {
			return (T) createEnumValue(beanClass, objectType);
		} else if (beanClass.isInterface()) {
			return createProxy(beanClass, objectType); 
		} else {
			return createProxy(beanClass, objectType); 
		}
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
		final Object value = getValue(clazz, objectType);
		Array.set(array, 0, value);
		return array;
	}

	/**
	 * TODO
	 * initialize a proxy for the interface or complex object 
	 * which will return according to the given objectType on
	 * calls of equals, hashCode and toString
	 * 
	 * this is an endpoint in order to avoid cycles
	 * 
	 * @param clazz
	 * @param objectType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> T createProxy(final Class<T> clazz,	final ObjectType objectType) {
		final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(
					final Object object,
					final Method method,
					final Object[] args,
					final MethodProxy methodProxy) throws Throwable {
				
				if (method.getName().equals("equals")) {
					return objectType == ObjectType.DIRTY;
				} else if (method.getName().equals("hashCode")) {
					return objectType.getByte();
				} else if (method.getName().equals("toString")) {
					return objectType.toString();
				}
				return methodProxy.invokeSuper(object, args);
			}
		});
        return (T) enhancer.create();
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

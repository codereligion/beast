package org.codereligion.test.bean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.beans.PropertyDescriptor;
import java.util.Set;

import org.codereligion.test.bean.object.ComplexClass;
import org.codereligion.test.bean.object.User;
import org.codereligion.test.bean.reflect.ReflectUtil;
import org.junit.Test;

/**
 * Tests {@link ReflectUtil}.
 * TODO implement parameter check tests
 * 
 * @author sgroebler
 * @since 12.08.2012
 */
public class ReflectUtilTest {

	@Test
	public void testGetSelectableProperties() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(ComplexClass.class);
		
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		
		for (final PropertyDescriptor property : properties) {
			assertNotNull(property.getWriteMethod());
		}
	}
	
	@Test
	public void testIntrospectorBugInGetSelectableProperties() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSetableProperties(User.class);
		
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		
		for (final PropertyDescriptor property : properties) {
			assertNotNull(property.getWriteMethod());
		}
	}
}

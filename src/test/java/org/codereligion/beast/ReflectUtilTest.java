package org.codereligion.beast;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.codereligion.beast.object.ComplexClass;
import org.codereligion.beast.object.User;

import org.codereligion.beast.ReflectUtil;


import java.beans.PropertyDescriptor;
import java.util.Set;

import org.junit.Test;

/**
 * Tests {@link ReflectUtil}.
 * 
 * @author sgroebler
 * @since 12.08.2012
 */
public class ReflectUtilTest {
	
	@Test(expected = NullPointerException.class)
	public void testGetSelectablePropertiesWithNullClass() {
		ReflectUtil.getSetableProperties(null);
	}

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

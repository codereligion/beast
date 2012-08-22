package com.codereligion.beast;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.codereligion.beast.object.ApiUser;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.RestApi;
import com.codereligion.beast.object.TypeMissmatchBetweenReadAndWriteMethods;
import com.codereligion.beast.object.User;
import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.Set;
import org.junit.Test;

/**
 * Tests {@link ReflectUtil}.
 * 
 * @author Sebastian Gröbler
 * @since 12.08.2012
 */
public class ReflectUtilTest {
	
	@Test(expected = NullPointerException.class)
	public void testGetSelectablePropertiesWithNullClass() {
		ReflectUtil.getSettableProperties(null);
	}

	@Test
	public void testGetSelectableProperties() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSettableProperties(ComplexClass.class);
		
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		
		for (final PropertyDescriptor property : properties) {
			assertNotNull(property.getWriteMethod());
			assertNotNull(property.getReadMethod());
		}
	}
	
	@Test
	public void testIntrospectorBugWithUnboundGenerics() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSettableProperties(User.class);
		
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		assertEquals(1, properties.size());
		
		final PropertyDescriptor property = properties.iterator().next();
		assertEquals(Integer.class, property.getPropertyType());
		assertNotNull(property.getWriteMethod());
		assertNotNull(property.getReadMethod());
	}
	
	@Test
	public void testIntrospectorBugWithBoundGenerics() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSettableProperties(RestApi.class);
		
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		assertEquals(1, properties.size());
		
		final PropertyDescriptor property = properties.iterator().next();
		assertEquals(ApiUser.class, property.getPropertyType());
		assertNotNull(property.getWriteMethod());
		assertNotNull(property.getReadMethod());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testTypeMissmatchBetweenReadAndWriteMethods() {
		final Set<PropertyDescriptor> properties = ReflectUtil.getSettableProperties(TypeMissmatchBetweenReadAndWriteMethods.class);
		assertNotNull(properties);
		assertFalse(properties.isEmpty());
		assertEquals(1, properties.size());
		
		final PropertyDescriptor property = properties.iterator().next();
		assertEquals(Date.class, property.getPropertyType());
		assertNotNull(property.getWriteMethod());
		assertNotNull(property.getReadMethod());
	}
}

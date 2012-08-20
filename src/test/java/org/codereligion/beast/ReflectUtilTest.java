package org.codereligion.beast;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.beans.PropertyDescriptor;
import java.util.Set;
import org.codereligion.beast.object.ApiUser;
import org.codereligion.beast.object.ComplexClass;
import org.codereligion.beast.object.RestApi;
import org.codereligion.beast.object.User;
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
}

package org.codereligion.test.bean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.codereligion.test.bean.creation.ObjectFactory;
import org.codereligion.test.bean.object.AnotherComplexObject;
import org.codereligion.test.bean.object.ComplexObject;
import org.junit.Test;

/**
 * Tests {@link ObjectFactory}.
 * TODO implement parameter check tests
 * 
 * @author sgroebler
 * @since 12.08.2012
 */
public class ObjectFactoryTest {

	@Test
	public void testCreateDefaultObject() {
		final ComplexObject defaultObject = ObjectFactory.newDefaultObject(ComplexObject.class);
		assertNotNull(defaultObject);
		assertNotNull(defaultObject.toString());
	}
	
	@Test
	public void testCreateDirtyObject() {
		final ComplexObject defaultObject = ObjectFactory.newDefaultObject(ComplexObject.class);
		final ComplexObject dirtyObject = ObjectFactory.newDirtyObject(ComplexObject.class);
		
		assertNotNull(defaultObject);
		assertNotNull(dirtyObject);

		// check equals
		assertFalse(defaultObject.equals(dirtyObject));
		assertFalse(dirtyObject.equals(defaultObject));
		
		// check hashCode
		assertFalse(defaultObject.hashCode() == dirtyObject.hashCode());
		
		// check toString
		assertFalse(defaultObject.toString().equals(dirtyObject.toString()));
	}
	
	@Test
	public void testIsCreateable() {

		// check positives
		assertTrue(ObjectFactory.isCreateable(ComplexObject.class));
		assertTrue(ObjectFactory.isCreateable(AnotherComplexObject.class));
		
		// check negatives
		assertFalse(ObjectFactory.isCreateable(Boolean.TYPE));
		assertFalse(ObjectFactory.isCreateable(Boolean.class));
		assertFalse(ObjectFactory.isCreateable(Byte.TYPE));
		assertFalse(ObjectFactory.isCreateable(Byte.class));
		assertFalse(ObjectFactory.isCreateable(Character.TYPE));
		assertFalse(ObjectFactory.isCreateable(Character.class));
		assertFalse(ObjectFactory.isCreateable(Short.TYPE));
		assertFalse(ObjectFactory.isCreateable(Short.class));
		assertFalse(ObjectFactory.isCreateable(Integer.TYPE));
		assertFalse(ObjectFactory.isCreateable(Integer.class));
		assertFalse(ObjectFactory.isCreateable(Long.TYPE));
		assertFalse(ObjectFactory.isCreateable(Long.class));
		assertFalse(ObjectFactory.isCreateable(Float.TYPE));
		assertFalse(ObjectFactory.isCreateable(Float.class));
		assertFalse(ObjectFactory.isCreateable(Double.TYPE));
		assertFalse(ObjectFactory.isCreateable(Double.class));
		assertFalse(ObjectFactory.isCreateable(AtomicInteger.class));
		assertFalse(ObjectFactory.isCreateable(AtomicLong.class));
		assertFalse(ObjectFactory.isCreateable(BigInteger.class));
		assertFalse(ObjectFactory.isCreateable(String.class));
		assertFalse(ObjectFactory.isCreateable(Object.class));
		assertFalse(ObjectFactory.isCreateable(int[].class));
		assertFalse(ObjectFactory.isCreateable(List.class));
		assertFalse(ObjectFactory.isCreateable(ComplexObject.Enumeration.class));
		assertFalse(ObjectFactory.isCreateable(AnotherComplexObject.Enumeration.class));
	}
}

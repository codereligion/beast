package org.codereligion.test.bean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.codereligion.test.bean.creation.ObjectFactory;
import org.codereligion.test.bean.object.AbstractClass;
import org.codereligion.test.bean.object.AnotherComplexClass;
import org.codereligion.test.bean.object.ComplexClass;
import org.junit.Test;

/**
 * Tests {@link ObjectFactory}.
 * TODO implement parameter check tests
 * 
 * @author sgroebler
 * @since 12.08.2012
 */
public class ObjectFactoryTest {

//	@Test
	public void performanceTest() {
		final long runs = 10000;
		
		final long startTime = System.currentTimeMillis();

		for (long l=0; l<runs; l++) {
			ObjectFactory.newDefaultObject(ComplexClass.class);
		}
		
		final long endTime = System.currentTimeMillis();
		
		System.out.println(runs + " runs to create default object took " + (endTime-startTime) + "ms.");
	}

	@Test
	public void testCreateDefaultObject() {
		final ComplexClass defaultObject = ObjectFactory.newDefaultObject(ComplexClass.class);
		assertNotNull(defaultObject);
		assertNotNull(defaultObject.toString());
	}
	
	@Test
	public void testCreateDirtyObject() {
		final ComplexClass defaultObject = ObjectFactory.newDefaultObject(ComplexClass.class);
		final ComplexClass dirtyObject = ObjectFactory.newDirtyObject(ComplexClass.class);
		
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
		assertTrue(ObjectFactory.isCreateable(ComplexClass.class));
		assertTrue(ObjectFactory.isCreateable(AnotherComplexClass.class));
		
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
		assertFalse(ObjectFactory.isCreateable(ComplexClass.Enumeration.class));
		assertFalse(ObjectFactory.isCreateable(AnotherComplexClass.Enumeration.class));
		assertFalse(ObjectFactory.isCreateable(AbstractClass.class));;
	}
}

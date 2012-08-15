package org.codereligion.test.bean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.codereligion.test.bean.creation.ObjectFactory;
import org.codereligion.test.bean.object.AbstractClass;
import org.codereligion.test.bean.object.AnotherComplexClass;
import org.codereligion.test.bean.object.ComplexClass;
import org.codereligion.test.bean.object.EmptyEnum;
import org.codereligion.test.bean.object.FinalClass;
import org.codereligion.test.bean.object.OneElementEnum;
import org.junit.Test;

/**
 * Tests {@link ObjectFactory}.
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
			ObjectFactory.newBeanObject(ComplexClass.class);
		}
		
		final long endTime = System.currentTimeMillis();
		
		System.out.println(runs + " runs to create default object took " + (endTime-startTime) + "ms.");
	}
	
	@Test(expected = NullPointerException.class)
	public void testNewBeanObjectWithNullClass() {
		 ObjectFactory.newBeanObject(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNewBeanObjectWithInterface() {
		ObjectFactory.newBeanObject(List.class);
	}
	
	@Test
	public void testNewBeanObject() {
		final ComplexClass defaultObject = ObjectFactory.newBeanObject(ComplexClass.class);
		assertNotNull(defaultObject);
		assertNotNull(defaultObject.toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNewDirtyPropertyWithNullClass() {
		ObjectFactory.newDirtyProperty(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNewDirtyPropertyWithFinalClass() {
		ObjectFactory.newDirtyProperty(FinalClass.class);
	}
	
	@Test
	public void testNewDirtyPropertyWithAbstractClass() {
		ObjectFactory.newDirtyProperty(AbstractClass.class);
	}
	
	@Test
	public void testNewDirtyPropertyWithInterface() {
		ObjectFactory.newDirtyProperty(List.class);
	}
	
	@Test
	public void testNewDirtyPropertyWithEmptyEnum() {
		final EmptyEnum emptyEnum = ObjectFactory.newDirtyProperty(EmptyEnum.class);
		assertNull(emptyEnum);
	}
	
	@Test
	public void testNewDirtyPropertyWithOneElementEnum() {
		final OneElementEnum oneElementEnum = ObjectFactory.newDirtyProperty(OneElementEnum.class);
		assertNull(oneElementEnum);
	}
	
	@Test
	public void testNewDirtyProperty() {
		
		// TODO reimplement
//		final ComplexClass defaultObject = ObjectFactory.newBeanObject(ComplexClass.class);
//		final ComplexClass dirtyObject = ObjectFactory.newDirtyProperty(ComplexClass.class);
//		
//		assertNotNull(defaultObject);
//		assertNotNull(dirtyObject);
//
//		// check equals
//		assertFalse(defaultObject.equals(dirtyObject));
//		
//		// check hashCode
//		assertFalse(defaultObject.hashCode() == dirtyObject.hashCode());
//		
//		// check toString
//		assertFalse(defaultObject.toString().equals(dirtyObject.toString()));
	}
	
	@Test(expected = NullPointerException.class)
	public void testIsCreateableWithNullClass() {
		ObjectFactory.isCreateable(null);
	}
	
	@Test
	public void testIsCreateable() {

		// check positives
		assertTrue(ObjectFactory.isCreateable(ComplexClass.class));
		assertTrue(ObjectFactory.isCreateable(AnotherComplexClass.class));
		
		// check negatives
		assertFalse(ObjectFactory.isCreateable(Boolean.TYPE));
		assertFalse(ObjectFactory.isCreateable(Boolean.class));
		assertFalse(ObjectFactory.isCreateable(AtomicBoolean.class));
		assertFalse(ObjectFactory.isCreateable(Byte.TYPE));
		assertFalse(ObjectFactory.isCreateable(Byte.class));
		assertFalse(ObjectFactory.isCreateable(Character.TYPE));
		assertFalse(ObjectFactory.isCreateable(Character.class));
		assertFalse(ObjectFactory.isCreateable(Short.TYPE));
		assertFalse(ObjectFactory.isCreateable(Short.class));
		assertFalse(ObjectFactory.isCreateable(Integer.TYPE));
		assertFalse(ObjectFactory.isCreateable(Integer.class));
		assertFalse(ObjectFactory.isCreateable(BigInteger.class));
		assertFalse(ObjectFactory.isCreateable(AtomicInteger.class));
		assertFalse(ObjectFactory.isCreateable(Long.TYPE));
		assertFalse(ObjectFactory.isCreateable(Long.class));
		assertFalse(ObjectFactory.isCreateable(AtomicLong.class));
		assertFalse(ObjectFactory.isCreateable(Float.TYPE));
		assertFalse(ObjectFactory.isCreateable(Float.class));
		assertFalse(ObjectFactory.isCreateable(Double.TYPE));
		assertFalse(ObjectFactory.isCreateable(Double.class));
		assertFalse(ObjectFactory.isCreateable(BigDecimal.class));
		assertFalse(ObjectFactory.isCreateable(String.class));
		assertFalse(ObjectFactory.isCreateable(Object.class));
		assertFalse(ObjectFactory.isCreateable(int[].class));
		assertFalse(ObjectFactory.isCreateable(List.class));
		assertFalse(ObjectFactory.isCreateable(ComplexClass.Enumeration.class));
		assertFalse(ObjectFactory.isCreateable(AnotherComplexClass.Enumeration.class));
		assertFalse(ObjectFactory.isCreateable(AbstractClass.class));;
	}
}

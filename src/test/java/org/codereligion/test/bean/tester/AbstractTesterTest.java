package org.codereligion.test.bean.tester;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.codereligion.test.bean.tester.AbstractTester;

import java.util.List;
import org.codereligion.test.bean.object.AbstractClass;
import org.codereligion.test.bean.object.AnotherComplexClass;
import org.codereligion.test.bean.object.ComplexClass;
import org.codereligion.test.bean.object.EmptyEnum;
import org.codereligion.test.bean.object.FinalClass;
import org.codereligion.test.bean.object.OneElementEnum;
import org.junit.Test;

/**
 * Tests helper methods of the {@link AbstractTester}.
 *
 * @author sgroebler
 * @since 18.08.2012
 */
public class AbstractTesterTest {
	
	private static class TesterImpl <T> extends AbstractTester<T> {

		protected TesterImpl(Class<T> beanClass) {
			super(beanClass);
		}

		@Override
		protected void test() {
			// just a stub and not part of this test
		}
	}

	private static AbstractTester<ComplexClass> TESTER = new TesterImpl<ComplexClass>(ComplexClass.class);
	
	@Test(expected = AssertionError.class)
	public void testAssertFalseWithTrue() {
		TESTER.assertFalse(true, "foo");
	}
	
	@Test
	public void testAssertFalseWithFalse() {
		TESTER.assertFalse(false, "foo");
	}
	
	@Test
	public void testAssertTrueWithTrue() {
		TESTER.assertTrue(true, "foo");
	}
	
	@Test(expected = AssertionError.class)
	public void testAssertTrueWithFalse() {
		TESTER.assertTrue(false, "foo");
	}

	@Test(expected = AssertionError.class)
	public void testFail() {
		TESTER.fail("foo");
	}
	
	@Test
	public void testNewBeanObject() {
		final ComplexClass defaultObject = TESTER.newBeanObject();
		assertNotNull(defaultObject);
		assertNotNull(defaultObject.toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void testNewDirtyPropertyWithNullClass() {
		TESTER.newDirtyProperty(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNewDirtyPropertyWithFinalClass() {
		TESTER.newDirtyProperty(FinalClass.class);
	}
	
	@Test
	public void testNewDirtyPropertyWithAbstractClass() {
		TESTER.newDirtyProperty(AbstractClass.class);
	}
	
	@Test
	public void testNewDirtyPropertyWithInterface() {
		TESTER.newDirtyProperty(List.class);
	}
	
	@Test
	public void testNewDirtyPropertyWithEmptyEnum() {
		final EmptyEnum emptyEnum = TESTER.newDirtyProperty(EmptyEnum.class);
		assertNull(emptyEnum);
	}
	
	@Test
	public void testNewDirtyPropertyWithOneElementEnum() {
		final OneElementEnum oneElementEnum = TESTER.newDirtyProperty(OneElementEnum.class);
		assertNull(oneElementEnum);
	}
	
	@Test
	public void testNewDirtyPropertyWithString() {
		final String string = TESTER.newDirtyProperty(String.class);

		assertNotNull(string);
		assertEquals("1", string);
	}
	
	@Test
	public void testNewDirtyPropertyWithArray() {
		final Integer[] intArray = TESTER.newDirtyProperty(Integer[].class);

		assertNotNull(intArray);
		assertEquals(1, intArray.length);
		assertEquals(Integer.valueOf(1), intArray[0]);
	}
	
	@Test
	public void testNewDirtyPropertyWithEnum() {
		final ComplexClass.Enumeration enumeration = TESTER.newDirtyProperty(ComplexClass.Enumeration.class);
		
		assertNotNull(enumeration);
		assertEquals(1, enumeration.ordinal());
	}
	
	@Test
	public void testNewDirtyPropertyWithComplexClass() {
		final ComplexClass dirtyComplexObject1 = TESTER.newDirtyProperty(ComplexClass.class);
		
		assertNotNull(dirtyComplexObject1);
		assertEquals(1, dirtyComplexObject1.hashCode());
		assertEquals("1", dirtyComplexObject1.toString());

		final ComplexClass dirtyComplexObject2 = TESTER.newDirtyProperty(ComplexClass.class);
		final AnotherComplexClass anotherDirtyComplexObject = TESTER.newDirtyProperty(AnotherComplexClass.class);

		// test equals
		assertFalse(dirtyComplexObject1.equals(null));
		assertFalse(dirtyComplexObject1.equals("foo"));
		
		assertTrue(dirtyComplexObject1.equals(dirtyComplexObject1));
		assertTrue(dirtyComplexObject1.equals(dirtyComplexObject2));
		assertTrue(dirtyComplexObject2.equals(dirtyComplexObject1));
		
		assertFalse(dirtyComplexObject1.equals(anotherDirtyComplexObject));
		assertFalse(anotherDirtyComplexObject.equals(dirtyComplexObject1));
		
		final ComplexClass defaultObject = TESTER.newBeanObject();
		assertFalse(defaultObject.equals(dirtyComplexObject1));
		assertFalse(dirtyComplexObject1.equals(defaultObject));
	}
}

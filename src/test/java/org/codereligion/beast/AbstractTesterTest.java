package org.codereligion.beast;

import static org.junit.Assert.assertNotNull;

import org.codereligion.beast.object.ComplexClass;
import org.junit.Test;
import org.testng.collections.Sets;

/**
 * Tests helper methods of the {@link AbstractTest}.
 *
 * @author sgroebler
 * @since 18.08.2012
 */
public class AbstractTesterTest {
	
	private static class TesterImpl <T> extends AbstractTest<T> {

		protected TesterImpl(Class<T> beanClass) {
			super(beanClass, Sets.<String>newHashSet(), new ObjectFactory(Sets.<InstanceProvider<?>>newHashSet()));
		}

		@Override
		protected void run() {
			// just a stub and not part of this test
		}
	}

	private static AbstractTest<ComplexClass> TESTER = new TesterImpl<ComplexClass>(ComplexClass.class);
	
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
}

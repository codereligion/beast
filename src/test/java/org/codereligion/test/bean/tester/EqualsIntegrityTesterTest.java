package org.codereligion.test.bean.tester;

import static org.junit.Assert.fail;

import org.codereligion.test.bean.object.WithPropertyWhichHasNoDefaultConstructor;

import com.google.common.collect.Sets;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.codereligion.test.bean.object.AbstractClass;
import org.codereligion.test.bean.object.AsymmetricGettersAndSetters;
import org.codereligion.test.bean.object.ComplexClass;
import org.codereligion.test.bean.object.GenericGetterAndSetter;
import org.codereligion.test.bean.object.MissingEqualsImplementation;
import org.codereligion.test.bean.object.MissingPropertyInEquals;
import org.codereligion.test.bean.object.NoDefaultConstructor;
import org.codereligion.test.bean.object.NonReflexiveEqualsClass;
import org.codereligion.test.bean.object.NonSymmetricEqualsClass;
import org.junit.Test;

/**
 * Tests {@link Equals}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class EqualsIntegrityTesterTest {
	
    /**
     * TODO
     */
    private static final Set<Class<?>> UNSUPPORTED_CLASSES = new HashSet<Class<?>>();

	static {
		// basic types
        UNSUPPORTED_CLASSES.add(Boolean.TYPE);
        UNSUPPORTED_CLASSES.add(Boolean.class);
        UNSUPPORTED_CLASSES.add(AtomicBoolean.class);
        UNSUPPORTED_CLASSES.add(Character.TYPE);
        UNSUPPORTED_CLASSES.add(Character.class);
        UNSUPPORTED_CLASSES.add(Byte.TYPE);
        UNSUPPORTED_CLASSES.add(Byte.class);
        UNSUPPORTED_CLASSES.add(Short.TYPE);
        UNSUPPORTED_CLASSES.add(Short.class);
        UNSUPPORTED_CLASSES.add(Integer.TYPE);
        UNSUPPORTED_CLASSES.add(Integer.class);
        UNSUPPORTED_CLASSES.add(AtomicInteger.class);
        UNSUPPORTED_CLASSES.add(Long.TYPE);
        UNSUPPORTED_CLASSES.add(Long.class);
        UNSUPPORTED_CLASSES.add(AtomicLong.class);
        UNSUPPORTED_CLASSES.add(Float.TYPE);
        UNSUPPORTED_CLASSES.add(Float.class);
        UNSUPPORTED_CLASSES.add(Double.TYPE);
        UNSUPPORTED_CLASSES.add(Double.class);
        UNSUPPORTED_CLASSES.add(BigDecimal.class);
        UNSUPPORTED_CLASSES.add(BigInteger.class);
        UNSUPPORTED_CLASSES.add(String.class);
        UNSUPPORTED_CLASSES.add(Object.class);
        
        // wicked types
        UNSUPPORTED_CLASSES.add(Test.class);
        UNSUPPORTED_CLASSES.add(Integer[].class);
        UNSUPPORTED_CLASSES.add(ComplexClass.Enumeration.class);
        UNSUPPORTED_CLASSES.add(List.class);
        UNSUPPORTED_CLASSES.add(AbstractClass.class);
        
        // invalid implementations
        UNSUPPORTED_CLASSES.add(NoDefaultConstructor.class);
        UNSUPPORTED_CLASSES.add(MissingEqualsImplementation.class);
        UNSUPPORTED_CLASSES.add(WithPropertyWhichHasNoDefaultConstructor.class);
	}

	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		Equals.testIntegrity(null);
	}
	
	@Test
	public void testValidClass() {
		Equals.testIntegrity(ComplexClass.class);
	}
	
	@Test
	public void testWithAsymmetricGetterSetters() {
		Equals.testIntegrity(AsymmetricGettersAndSetters.class);
	}
	
	@Test
	public void testWithGenericClass() {
		Equals.testIntegrity(GenericGetterAndSetter.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInEquals() {
		Equals.testIntegrity(MissingPropertyInEquals.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testWithNonReflexiveClass() {
		Equals.testIntegrity(NonReflexiveEqualsClass.class);
	}
	
	@Test(expected = AssertionError.class)
	public void testWithNonSymmetricEqualsClass() {
		Equals.testIntegrity(NonSymmetricEqualsClass.class);
	}
	
	@Test
	public void testWithUnsupportedClass() {
		for (final Class<?> type : UNSUPPORTED_CLASSES) {
			try {
				Equals.testIntegrity(type);
				fail();
			} catch (IllegalArgumentException e) {
				// success
			}
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullClass() {
		Equals.testIntegrity(null, Collections.<String>emptySet());
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithExcludesWithNullExcludes() {
		Equals.testIntegrity(MissingPropertyInEquals.class, null);
	}

	@Test
	public void testValidClassWithEmptyExcludes() {
		Equals.testIntegrity(ComplexClass.class, Collections.<String>emptySet());
	}
	
	@Test(expected = AssertionError.class)
	public void testValidClassWithUnnecessaryExclude() {
		Equals.testIntegrity(ComplexClass.class, Sets.newHashSet("anotherComplexObject"));
	}
	
	@Test
	public void testWithMissingPropertyInEqualsWithExcludes() {
		Equals.testIntegrity(MissingPropertyInEquals.class, Sets.newHashSet("complexObject"));
	}

	@Test
	public void testWithExcludesWithUnsupportedClass() {
		for (final Class<?> type : UNSUPPORTED_CLASSES) {
			try {
				Equals.testIntegrity(type, new HashSet<String>());
				fail();
			} catch (IllegalArgumentException e) {
				// success
			}
		}
	}
}

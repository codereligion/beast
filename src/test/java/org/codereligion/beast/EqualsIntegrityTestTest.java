package org.codereligion.beast;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.codereligion.beast.object.AbstractClass;
import org.codereligion.beast.object.AsymmetricGettersAndSetters;
import org.codereligion.beast.object.ComplexClass;
import org.codereligion.beast.object.GenericGetterAndSetter;
import org.codereligion.beast.object.MissingEqualsImplementation;
import org.codereligion.beast.object.MissingPropertyInEquals;
import org.codereligion.beast.object.NoDefaultConstructor;
import org.codereligion.beast.object.NonReflexiveEqualsClass;
import org.codereligion.beast.object.NonSymmetricEqualsClass;
import org.codereligion.beast.object.PropertyWhichHasNoDefaultConstructor;
import org.junit.Test;

/**
 * Tests {@link EqualsIntegrityTest}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class EqualsIntegrityTestTest {
	
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
        UNSUPPORTED_CLASSES.add(PropertyWhichHasNoDefaultConstructor.class);
	}

	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new EqualsIntegrityTestBuilder()
			.create(null)
			.run();
	}
	
	@Test
	public void testValidClass() {
		new EqualsIntegrityTestBuilder()
			.create(ComplexClass.class)
			.run();
	}
	
	@Test
	public void testWithAsymmetricGetterSetters() {
		new EqualsIntegrityTestBuilder()
			.create(AsymmetricGettersAndSetters.class)
			.run();
	}
	
	@Test
	public void testWithGenericClass() {
		new EqualsIntegrityTestBuilder()
			.create(GenericGetterAndSetter.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithMissingPropertyInEquals() {
		new EqualsIntegrityTestBuilder()
			.create(MissingPropertyInEquals.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithNonReflexiveClass() {
		new EqualsIntegrityTestBuilder()
			.create(NonReflexiveEqualsClass.class)
			.run();
	}
	
	@Test(expected = AssertionError.class)
	public void testWithNonSymmetricEqualsClass() {
		new EqualsIntegrityTestBuilder()
			.create(NonSymmetricEqualsClass.class)
			.run();
	}
	
	@Test
	public void testWithUnsupportedClass() {
		for (final Class<?> type : UNSUPPORTED_CLASSES) {
			try {
				new EqualsIntegrityTestBuilder()
					.create(type)
					.run();
				
				fail();
			} catch (IllegalArgumentException e) {
				// success
			}
		}
	}
	
	@Test(expected = AssertionError.class)
	public void testValidClassWithUnnecessaryExclude() {
		new EqualsIntegrityTestBuilder()
			.addExcludedPropertyName("anotherComplexObject")
			.create(ComplexClass.class)
			.run();
	}
	
	@Test
	public void testWithMissingPropertyInEqualsWithExcludes() {
		new EqualsIntegrityTestBuilder()
			.addExcludedPropertyName("complexObject")
			.create(MissingPropertyInEquals.class)
			.run();
	}
}

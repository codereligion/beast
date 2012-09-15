/*
 * Copyright 2012 The Beast Authors (www.codereligion.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codereligion.beast.internal.test;

import static org.junit.Assert.fail;

import com.codereligion.beast.internal.test.EqualsIntegrityTest;


import com.codereligion.beast.object.AbstractClass;
import com.codereligion.beast.object.AsymmetricGettersAndSetters;
import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.GenericGetterAndSetter;
import com.codereligion.beast.object.MissingEqualsImplementation;
import com.codereligion.beast.object.MissingPropertyInEquals;
import com.codereligion.beast.object.NoDefaultConstructor;
import com.codereligion.beast.object.NonReflexiveEqualsClass;
import com.codereligion.beast.object.NonSymmetricEqualsClass;
import com.codereligion.beast.object.PropertyWhichHasNoDefaultConstructor;

import com.codereligion.beast.EqualsIntegrityTestBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.Test;

/**
 * Tests {@link EqualsIntegrityTest}.
 * 
 * @author Sebastian Gröbler
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

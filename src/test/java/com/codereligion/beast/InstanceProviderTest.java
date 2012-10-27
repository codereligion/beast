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

package com.codereligion.beast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import com.codereligion.beast.object.ConstantHashCodeResult;
import com.codereligion.beast.object.ConstantToStringResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests {@link InstanceProvider}.
 *
 * @author Sebastian Gröbler
 * @since 20.08.2012
 */
public class InstanceProviderTest {
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsDefaultInstanceShouldThrowNpeWhenCallingDefaultFactory() {
		InstanceProvider.create(null, "foo");
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsDirtyInstanceShouldThrowNpeWhenCallingDefaultFactory() {
		InstanceProvider.create("foo", null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void givenEqualDefaultAndDirtyInstancesShouldThrowIaeWhenCallingDefaultFactory() {
		InstanceProvider.create("foo", "foo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenDifferentClassesShouldThrowIaeWhenCallingDefaultFactory() {
		InstanceProvider.create(Lists.newArrayList("foo"), Sets.newHashSet("bar"));
	}
	@Test(expected = IllegalArgumentException.class)
	public void givenInstancesWithSameToStringResultShouldThrowIaeWhenCallingDefaultFactory() {
		InstanceProvider.create(new ConstantToStringResult(0), new ConstantToStringResult(1));
	}
	@Test(expected = IllegalArgumentException.class)
	public void givenInstancesWithSameHashCodeResultShouldThrowIaeWhenCallingDefaultFactory() {
		InstanceProvider.create(new ConstantHashCodeResult(0), new ConstantHashCodeResult(1));
	}
	@Test(expected = IllegalArgumentException.class)
	public void givenArraysShouldThrowIaeWhenCallingDefaultFactory() {
		final Integer[] defaultObject = new Integer[]{Integer.valueOf(21)};
		final Integer[] dirtyObject = new Integer[]{Integer.valueOf(42)};
		InstanceProvider.create(defaultObject, dirtyObject);
	}
	
	@Test
	public void givenValidInputShouldProduceValidOutputWhenCallingDefaultFactory() {
		final String defaultInstance = "foo";
		final String dirtyInstance = "bar";
		final InstanceProvider provider = InstanceProvider.create(defaultInstance, dirtyInstance);
		
		assertNotNull(provider);
		assertFalse(provider.isPropertySpecific());
		assertEquals(String.class, provider.getInstanceClass());
		assertEquals(defaultInstance, provider.getDefaultInstance());
		assertEquals(dirtyInstance, provider.getDirtyInstance());
		assertNull(provider.getPropertyName());
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsDefaultInstanceShouldThrowNpeWhenCallingPropertySpecificFactory() {
		InstanceProvider.create(null, "foo", "somePropertyName");
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsDirtyInstanceShouldThrowNpeWhenCallingPropertySpecificFactory() {
		InstanceProvider.create("foo", null, "somePropertyName");
	}

	@Test(expected = NullPointerException.class)
	public void givenNullAsPropertyNameShouldThrowNpeWhenCallingPropertySpecificFactory() {
		InstanceProvider.create("foo", "bar", (String) null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenEqualDefaultAndDirtyInstancesShouldThrowIaeWhenCallingPropertySpecificFactory() {
		InstanceProvider.create("foo", "foo", "somePropertyName");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenDifferentClassesShouldThrowIaeWhenCallingPropertySpecificFactory() {
		InstanceProvider.create(Lists.newArrayList("foo"), Sets.newHashSet("bar"), "somePropertyName");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenInstancesWithSameToStringResultShouldThrowIaeWhenCallingPropertySpecificFactory() {
		InstanceProvider.create(new ConstantToStringResult(0), new ConstantToStringResult(1), "somePropertyName");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenInstancesWithSameHashCodeResultShouldThrowIaeWhenCallingPropertySpecificFactory() {
		InstanceProvider.create(new ConstantHashCodeResult(0), new ConstantHashCodeResult(1), "somePropertyName");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenArraysShouldThrowIaeWhenCallingPropertySpecificFactory() {
		final Integer[] defaultObject = new Integer[]{Integer.valueOf(21)};
		final Integer[] dirtyObject = new Integer[]{Integer.valueOf(42)};
		InstanceProvider.create(defaultObject, dirtyObject, "somePropertyName");
	}
	
	@Test
	public void givenValidInputShouldProduceValidOutputWhenCallingPropertySpecificFactory() {
		final String defaultInstance = "foo";
		final String dirtyInstance = "bar";
		final String propertyName = "somePropertyName";
		final InstanceProvider provider = InstanceProvider.create(defaultInstance, dirtyInstance, propertyName);
		
		assertNotNull(provider);
		assertTrue(provider.isPropertySpecific());
		assertEquals(String.class, provider.getInstanceClass());
		assertEquals(defaultInstance, provider.getDefaultInstance());
		assertEquals(dirtyInstance, provider.getDirtyInstance());
		assertEquals(propertyName, provider.getPropertyName());
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsDefaultInstanceShouldThrowNpeWhenCallingClassSpecificFactory() {
		InstanceProvider.create(null, "foo", String.class);
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsDirtyInstanceShouldThrowNpeWhenCallingClassSpecificFactory() {
		InstanceProvider.create("foo", null, String.class);
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsClassShouldThrowNpeWhenCallingClassSpecificFactory() {
		InstanceProvider.create("foo", "bar", (Class<?>) null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenEqualDefaultAndDirtyInstancesShouldThrowIaeWhenCallingClassSpecificFactory() {
		InstanceProvider.create("foo", "foo", String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenDifferentClassThanTheInstanceClassesShouldThrowIaeWhenCallingClassSpecificFactory() {
		InstanceProvider.create("foo", "bar", Double.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenDifferentClassesShouldThrowIaeWhenCallingClassSpecificFactory() {
		InstanceProvider.create(Lists.newArrayList("foo"), Sets.newHashSet("bar"), String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenInstancesWithSameToStringResultShouldThrowIaeWhenCallingClassSpecificFactory() {
		InstanceProvider.create(new ConstantToStringResult(0), new ConstantToStringResult(1), String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenInstancesWithSameHashCodeResultShouldThrowIaeWhenCallingClassSpecificFactory() {
		InstanceProvider.create(new ConstantHashCodeResult(0), new ConstantHashCodeResult(1), String.class);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenArraysShouldThrowIaeWhenCallingClassSpecificFactory() {
		final Integer[] defaultObject = new Integer[]{Integer.valueOf(21)};
		final Integer[] dirtyObject = new Integer[]{Integer.valueOf(42)};
		InstanceProvider.create(defaultObject, dirtyObject, String.class);
	}
	
	@Test
	public void givenValidInputShouldProduceValidOutputWhenCallingClassSpecificFactory() {
		final String defaultInstance = "foo";
		final String dirtyInstance = "bar";
		final InstanceProvider provider = InstanceProvider.create(defaultInstance, dirtyInstance, String.class);
		
		assertNotNull(provider);
		assertFalse(provider.isPropertySpecific());
		assertEquals(String.class, provider.getInstanceClass());
		assertEquals(defaultInstance, provider.getDefaultInstance());
		assertEquals(dirtyInstance, provider.getDirtyInstance());
		assertNull(provider.getPropertyName());
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsDefaultInstanceShouldThrowNpeWhenCallingClassAndPropertySpecificFactory() {
		InstanceProvider.create(null, "foo", String.class, "somePropertyName");
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsDirtyInstanceShouldThrowNpeWhenCallingClassAndPropertySpecificFactory() {
		InstanceProvider.create("foo", null, String.class, "somePropertyName");
	}
	@Test(expected = NullPointerException.class)
	public void givenNullAsClassShouldThrowNpeWhenCallingClasAndPropertySpecificFactory() {
		InstanceProvider.create("foo", "bar", (Class<?>) null, "somePropertyName");
	}
	
	@Test(expected = NullPointerException.class)
	public void givenNullAsPropertyNameShouldThrowNpeWhenCallingClassAndPropertySpecificFactory() {
		InstanceProvider.create("foo", "bar", String.class, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenEqualDefaultAndDirtyInstancesShouldThrowIaeWhenCallingClassAndPropertySpecificFactory() {
		InstanceProvider.create("foo", "foo", String.class, "somePropertyName");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenDifferentClassThanTheInstanceClassesShouldThrowIaeWhenCallingClassAndPropertySpecificFactory() {
		InstanceProvider.create("foo", "bar", Double.class, "somePropertyName");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenDifferentClassesShouldThrowIaeWhenCallingClassAndPropertySpecificFactory() {
		InstanceProvider.create(Lists.newArrayList("foo"), Sets.newHashSet("bar"), String.class, "somePropertyName");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenInstancesWithSameToStringResultShouldThrowIaeWhenCallingClassAndPropertySpecificFactory() {
		InstanceProvider.create(new ConstantToStringResult(0), new ConstantToStringResult(1), String.class, "somePropertyName");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenInstancesWithSameHashCodeResultShouldThrowIaeWhenCallingClassAndPropertySpecificFactory() {
		InstanceProvider.create(new ConstantHashCodeResult(0), new ConstantHashCodeResult(1), String.class, "somePropertyName");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void givenArraysShouldThrowIaeWhenCallingClassAndPropertySpecificFactory() {
		final Integer[] defaultObject = new Integer[]{Integer.valueOf(21)};
		final Integer[] dirtyObject = new Integer[]{Integer.valueOf(42)};
		InstanceProvider.create(defaultObject, dirtyObject, String.class, "somePropertyName");
	}

	@Test
	public void givenValidInputShouldProduceValidOutputWhenCallingClassAndPropertySpecificFactory() {
		final String defaultInstance = "foo";
		final String dirtyInstance = "bar";
		final String propertyName = "somePropertyName";
		final InstanceProvider provider = InstanceProvider.create(defaultInstance, dirtyInstance, String.class, propertyName);
		
		assertNotNull(provider);
		assertTrue(provider.isPropertySpecific());
		assertEquals(String.class, provider.getInstanceClass());
		assertEquals(defaultInstance, provider.getDefaultInstance());
		assertEquals(dirtyInstance, provider.getDirtyInstance());
		assertEquals(propertyName, provider.getPropertyName());
	}
	
	/**
	 * Generics are not known at runtime (type erasure) anymore so two different
	 * hashSets will be considered equal. Proves the need for the class specific factory.
	 */
	@Test
	public void givenGenericInstancesOfDirrentTypesCallingEqualsWillReturnTrue() {
		final InstanceProvider integerHashSetProvider = InstanceProvider.create(Sets.newHashSet(Integer.valueOf(0)), Sets.newHashSet(Integer.valueOf(1)));
		final InstanceProvider stringHashSetProvider = InstanceProvider.create(Sets.newHashSet("0"), Sets.newHashSet("1"));
		
		assertTrue(integerHashSetProvider.equals(stringHashSetProvider));
		assertFalse(integerHashSetProvider.getDefaultInstance().equals(stringHashSetProvider.getDefaultInstance()));
	}
	
	@Test
	public void givenInstanceProvidersWithDifferentInstancesAndSamePropertyNameWhenCallingEqualsShouldReturnTrue() {
		
		// property specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", "somePropertyName");
			final InstanceProvider instanceProviderB = InstanceProvider.create("fizz", "bazz", "somePropertyName");
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}
		
		// class and property specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", String.class, "somePropertyName");
			final InstanceProvider instanceProviderB = InstanceProvider.create("fizz", "bazz", String.class, "somePropertyName");
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}
	}
	
	@Test
	public void givenInstanceProvidersWithDifferentInstancesAndSameClassWhenCallingEqualsShouldReturnTrue() {
		
		// default factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar");
			final InstanceProvider instanceProviderB = InstanceProvider.create("fizz", "bazz");
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}
		
		// class specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", String.class);
			final InstanceProvider instanceProviderB = InstanceProvider.create("fizz", "bazz", String.class);
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}
	}

	@Test
	public void givenInstanceProvidersWithTheSameStateWhenCallingEqualsShouldReturnTrue() {

		// default factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar");
			final InstanceProvider instanceProviderB = InstanceProvider.create("foo", "bar");
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}

		// property specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", "somePropertyName");
			final InstanceProvider instanceProviderB = InstanceProvider.create("foo", "bar", "somePropertyName");
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}
		
		// class specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", String.class);
			final InstanceProvider instanceProviderB = InstanceProvider.create("foo", "bar", String.class);
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}
		
		// class and property specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", String.class, "somePropertyName");
			final InstanceProvider instanceProviderB = InstanceProvider.create("foo", "bar", String.class, "somePropertyName");
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}
	}
	
	@Test
	public void givenInstanceProvidersForDifferentClassesWhenCallingEqualsShouldReturnFalse() {

		// default factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar");
			final InstanceProvider instanceProviderB = InstanceProvider.create(BigDecimal.ONE, BigDecimal.TEN);
			
			assertFalse(instanceProviderA.equals(instanceProviderB));
		}
		
		// property specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", "somePropertyName");
			final InstanceProvider instanceProviderB = InstanceProvider.create(BigDecimal.ONE, BigDecimal.TEN, "somePropertyName");
			
			assertFalse(instanceProviderA.equals(instanceProviderB));
		}
		
		// class specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", String.class);
			final InstanceProvider instanceProviderB = InstanceProvider.create(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.class);
			
			assertFalse(instanceProviderA.equals(instanceProviderB));
		}
		
		// class and property specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", String.class, "somePropertyName");
			final InstanceProvider instanceProviderB = InstanceProvider.create(BigDecimal.ONE, BigDecimal.TEN, BigDecimal.class, "somePropertyName");
			
			assertFalse(instanceProviderA.equals(instanceProviderB));
		}
	}

	@Test
	public void equalsImplementationShouldComplyWithBasicContract() {
		
		final InstanceProvider instanceProvider = InstanceProvider.create("foo", "bar");
		
		// never equal to null
		assertFalse(instanceProvider.equals(null));
		
		// reflexivity
		assertTrue(instanceProvider.equals(instanceProvider));
		
		// not equal to another class...yeah thats not a really good test
		assertFalse(instanceProvider.equals(BigDecimal.ONE));
	}
	
	@Test
	public void givenTheSamePropertySpecificInstanceProvidersWhenCallingEqualsShouldReturnTrue() {

		// property specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", "somePropertyName");
			final InstanceProvider instanceProviderB = InstanceProvider.create("foo", "bar", "somePropertyName");
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}
		
		// class and property specific factory
		{
			final InstanceProvider instanceProviderA = InstanceProvider.create("foo", "bar", String.class, "somePropertyName");
			final InstanceProvider instanceProviderB = InstanceProvider.create("foo", "bar", String.class, "somePropertyName");
			
			assertTrue(instanceProviderA.equals(instanceProviderB));
		}
	}
	
	@Test
	public void givenTheSameInstancesWhenComparingPropertySpecificWithNonPropertySpecificInstanceProvidersEqualsShouldReturnFalse() {
		final InstanceProvider defaultInstanceProvider = InstanceProvider.create("foo", "bar");
		final InstanceProvider classSpecificInstanceProvider = InstanceProvider.create("foo", "bar", String.class);
		final InstanceProvider propertySpecificInstanceProvider = InstanceProvider.create("foo", "bar", "somePropertyName");
		final InstanceProvider classAndpropertySpecificInstanceProvider = InstanceProvider.create("foo", "bar", String.class, "somePropertyName");
		
		// compare default instance provider with property specific providers
		assertFalse(defaultInstanceProvider.equals(propertySpecificInstanceProvider));
		assertFalse(propertySpecificInstanceProvider.equals(defaultInstanceProvider));
		
		assertFalse(defaultInstanceProvider.equals(classAndpropertySpecificInstanceProvider));
		assertFalse(classAndpropertySpecificInstanceProvider.equals(defaultInstanceProvider));
		
		// compare class specific provider with property specific providers
		assertFalse(classSpecificInstanceProvider.equals(propertySpecificInstanceProvider));
		assertFalse(propertySpecificInstanceProvider.equals(classSpecificInstanceProvider));
		
		assertFalse(classSpecificInstanceProvider.equals(classAndpropertySpecificInstanceProvider));
		assertFalse(classAndpropertySpecificInstanceProvider.equals(classSpecificInstanceProvider));
	}
	
	@Test
	public void whenCallingToStringTheResultShouldReflectTheStateOfTheObject() {

		final String defaultInstance = "foo";
		final String dirtyInstance = "bar";
		final Class<String> instanceClass = String.class;
		final String propertyName = "somePropertyName";
		
		final InstanceProvider instanceProvider = InstanceProvider.create(defaultInstance, dirtyInstance, instanceClass, propertyName);
		
		final String expected = "InstanceProvider [" +
				"defaultInstance=" + defaultInstance +
				", dirtyInstance=" + dirtyInstance +
				", instanceClass=" + instanceClass +
				", propertyName=" + propertyName + "]";
		
		assertEquals(expected, instanceProvider.toString());
	}
}

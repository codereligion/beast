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

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import java.util.Set;
import org.junit.Ignore;

import com.codereligion.beast.object.ConstantHashCodeResult;
import com.codereligion.beast.object.ConstantToStringResult;

import com.codereligion.beast.InstanceProvider;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collection;
import org.junit.Test;

/**
 * TODO implement more tests for  new constructor
 * Tests {@link InstanceProvider}.
 *
 * @author Sebastian Gröbler
 * @since 20.08.2012
 */
@SuppressWarnings("unused")
public class InstanceProviderTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithNullDefaultInstance() {
		InstanceProvider.create(null, "foo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithNullDirtyInstance() {
		InstanceProvider.create("foo", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEqualDefaultAndDirtyInstances() {
		InstanceProvider.create("foo", "foo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithDifferentClasses() {
		InstanceProvider.create(Lists.newArrayList("foo"), Sets.newHashSet("bar"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEqualToString() {
		InstanceProvider.create(new ConstantToStringResult(0), new ConstantToStringResult(1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEqualHashCode() {
		InstanceProvider.create(new ConstantHashCodeResult(0), new ConstantHashCodeResult(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithArray() {
		final Integer[] defaultObject = new Integer[]{Integer.valueOf(21)};
		final Integer[] dirtyObject = new Integer[]{Integer.valueOf(42)};
		InstanceProvider.create(defaultObject, dirtyObject);
	}
	
	/**
	 * Generics are not known at runtime anymore so two different hashSets will be considered equal.
	 */
	@Test
	public void testWithTwoDifferentGenericHashSets() {
		final InstanceProvider integerHashSetProvider = InstanceProvider.create(Sets.newHashSet(Integer.valueOf(0)), Sets.newHashSet(Integer.valueOf(1)));
		final InstanceProvider stringHashSetProvider = InstanceProvider.create(Sets.newHashSet("0"), Sets.newHashSet("1"));
		
		assertTrue(integerHashSetProvider.equals(stringHashSetProvider));
		assertFalse(integerHashSetProvider.getDefaultInstance().equals(stringHashSetProvider.getDefaultInstance()));
	}
}

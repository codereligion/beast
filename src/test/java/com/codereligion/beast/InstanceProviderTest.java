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
		new InstanceProvider<String>(null, "foo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithNullDirtyInstance() {
		new InstanceProvider<String>("foo", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEqualDefaultAndDirtyInstances() {
		new InstanceProvider<String>("foo", "foo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithDifferentClasses() {
		new InstanceProvider<Collection<String>>(Lists.newArrayList("foo"), Sets.newHashSet("bar"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEqualToString() {
		new InstanceProvider<ConstantToStringResult>(new ConstantToStringResult(0), new ConstantToStringResult(1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEqualHashCode() {
		new InstanceProvider<ConstantHashCodeResult>(new ConstantHashCodeResult(0), new ConstantHashCodeResult(1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWithArray() {
		final Integer[] defaultObject = new Integer[]{Integer.valueOf(21)};
		final Integer[] dirtyObject = new Integer[]{Integer.valueOf(42)};
		new InstanceProvider<Integer[]>(defaultObject, dirtyObject);
	}
	
	/**
	 * Generics are not known at runtime anymore so two different hashSets will be considered equal.
	 */
	@Test
	public void testWithTwoDifferentGenericHashSets() {
		final InstanceProvider<HashSet<Integer>> integerHashSetProvider = new InstanceProvider<HashSet<Integer>>(Sets.newHashSet(Integer.valueOf(0)), Sets.newHashSet(Integer.valueOf(1)));
		final InstanceProvider<HashSet<String>> stringHashSetProvider = new InstanceProvider<HashSet<String>>(Sets.newHashSet("0"), Sets.newHashSet("1"));
		
		assertTrue(integerHashSetProvider.equals(stringHashSetProvider));
		assertFalse(integerHashSetProvider.getDefaultObject().equals(stringHashSetProvider.getDefaultObject()));
	}
}

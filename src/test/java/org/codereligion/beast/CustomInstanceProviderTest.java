package org.codereligion.beast;

import static junit.framework.Assert.assertEquals;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collection;
import org.codereligion.beast.object.ConstantHashCodeResult;
import org.codereligion.beast.object.ConstantToStringResult;
import org.junit.Test;

/**
 * Tests {@link CustomInstanceProvider}.
 *
 * @author Sebastian Gröbler
 * @since 20.08.2012
 */
@SuppressWarnings("unused")
public class CustomInstanceProviderTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithNullDefaultInstance() {
		new CustomInstanceProvider<String>(null, "foo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithNullDirtyInstance() {
		new CustomInstanceProvider<String>("foo", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEqualDefaultAndDirtyInstances() {
		new CustomInstanceProvider<String>("foo", "foo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithDifferentClasses() {
		new CustomInstanceProvider<Collection<String>>(Lists.newArrayList("foo"), Sets.newHashSet("bar"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEqualToString() {
		new CustomInstanceProvider<ConstantToStringResult>(new ConstantToStringResult(0), new ConstantToStringResult(1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWithEqualHashCode() {
		new CustomInstanceProvider<ConstantHashCodeResult>(new ConstantHashCodeResult(0), new ConstantHashCodeResult(1));
	}
}

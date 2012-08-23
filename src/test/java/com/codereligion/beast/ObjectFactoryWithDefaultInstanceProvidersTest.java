package com.codereligion.beast;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.common.collect.Sets;
import java.util.Set;
import org.junit.Test;

/**
 * Tests helper methods of the {@link ObjectFactory}.
 * 
 * TODO extend test with custom intance providers
 * - overriding: object, string, boxed and unboxed primitive types
 * - adding: additional providers custom providers
 * - test with enums
 * - test with arrays
 * - test with propertyNames
 *
 * @author Sebastian Gröbler
 * @since 18.08.2012
 */
public class ObjectFactoryWithDefaultInstanceProvidersTest {

	@Test
	public void testWithCustomInstanceProviderOfHashSet() {
		
		final Set<Integer> defaultInstance = Sets.newHashSet(Integer.valueOf(21));
		final Set<Integer> dirtyInstance = Sets.newHashSet(Integer.valueOf(42));
		final InstanceProvider<?> provider = new InstanceProvider<Set<Integer>>(defaultInstance, dirtyInstance, Set.class);
		final Set<InstanceProvider<?>> providers = Sets.<InstanceProvider<?>>newHashSet(provider);
		
		final ObjectFactory objectFactory = new ObjectFactory(providers);
		
		@SuppressWarnings("unchecked")
        final Set<Integer> result = objectFactory.getDefaultObject(Set.class, null);
		
		assertNotNull(result);
		assertEquals(Integer.valueOf(21), result.iterator().next());
	}
}

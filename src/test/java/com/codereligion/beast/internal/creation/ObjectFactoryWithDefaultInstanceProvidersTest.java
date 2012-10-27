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

package com.codereligion.beast.internal.creation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.codereligion.beast.InstanceProvider;
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
		final InstanceProvider provider = InstanceProvider.create(defaultInstance, dirtyInstance, Set.class);
		final Set<InstanceProvider> providers = Sets.<InstanceProvider>newHashSet(provider);
		
		final ObjectFactory objectFactory = new ObjectFactory(providers);
		
        final Object result = objectFactory.getDefaultObject(Set.class, null);
		
		assertNotNull(result);
		assertTrue(result instanceof Set);
		assertEquals(Integer.valueOf(21), ((Set<?>)result).iterator().next());
	}
}

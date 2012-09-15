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

import static org.junit.Assert.assertNotNull;

import com.codereligion.beast.InstanceProvider;

import com.codereligion.beast.internal.creation.ObjectFactory;

import com.codereligion.beast.internal.test.AbstractTest;


import com.codereligion.beast.object.ComplexClass;
import com.google.common.collect.Sets;
import org.junit.Test;

/**
 * Tests helper methods of the {@link AbstractTest}.
 *
 * @author Sebastian Gröbler
 * @since 18.08.2012
 */
public class AbstractTesterTest {
	
	private static class TesterImpl <T> extends AbstractTest<T> {

		protected TesterImpl(Class<T> beanClass) {
			super(beanClass, new ObjectFactory(Sets.<InstanceProvider<?>>newHashSet()));
		}

		@Override
		public void run() {
			// just a stub and not part of this test
		}
	}

	private static AbstractTest<ComplexClass> TESTER = new TesterImpl<ComplexClass>(ComplexClass.class);
	
	@Test
	public void testNewBeanObject() {
		final ComplexClass defaultObject = TESTER.newBeanObject();
		assertNotNull(defaultObject);
		assertNotNull(defaultObject.toString());
	}
}

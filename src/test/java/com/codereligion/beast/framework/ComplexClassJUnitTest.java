/**
 * Copyright 2013 www.codereligion.com
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
package com.codereligion.beast.framework;

import com.codereligion.beast.object.ComplexClass;

/**
 * Tests {@link ComplexClass} with the JUnit implementation.
 * 
 * @author Sebastian Gröbler
 * @since 15.08.2012
 */
public class ComplexClassJUnitTest extends JUnitBeast<ComplexClass>{

	@Override
	protected Class<ComplexClass> getClazz() {
		return ComplexClass.class;
	}
}
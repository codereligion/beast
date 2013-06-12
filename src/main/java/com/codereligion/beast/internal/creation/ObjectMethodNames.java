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
package com.codereligion.beast.internal.creation;

/**
 * Constant class to store names of methods which are subject to tests of this framework.
 *
 * @author Sebastian Gröbler
 * @since 18.08.2012
 */
public final class ObjectMethodNames {
	
	/**
	 * No public constructor for this utility class.
	 */
	private ObjectMethodNames() {
		throw new IllegalAccessError("This is an utility class which must not be instantiated.");
	}

	/**
	 * Constant for the name of the {@link Object #toString()} method.
	 */
	public static final String TO_STRING = "toString";

	/**
	 * Constant for the name of the {@link Object #hashCode()} method.
	 */
	public static final String HASH_CODE = "hashCode";

	/**
	 * Constant for the name of the {@link Object #equals(Object)} method.
	 */
	public static final String EQUALS = "equals";
	
	/**
	 * Constant for the name of the {@link Object #clone()} method.
	 */
	public static final String CLONE = "clone";
}

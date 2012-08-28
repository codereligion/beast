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

/**
 * TODO
 *
 * @author Sebastian Gröbler
 * @since 18.08.2012
 */
final class ObjectMethodNames {
	
	/**
	 *  No public constructor for this utility class.
	 */
	private ObjectMethodNames() {
		throw new IllegalAccessError("This is an utility class which must not be instantiated.");
	}

	/**
	 * Constant for the name of the {@link Object #toString()} method.
	 */
	static final String TO_STRING = "toString";

	/**
	 * Constant for the name of the {@link Object #hashCode()} method.
	 */
	static final String HASH_CODE = "hashCode";

	/**
	 * Constant for the name of the {@link Object #equals(Object)} method.
	 */
	static final String EQUALS = "equals";
	
	/**
	 * Constant for the name of the {@link Object #clone()} method.
	 */
	static final String CLONE = "clone";
}

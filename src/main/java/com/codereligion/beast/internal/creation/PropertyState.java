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

/**
 * Enumeration of available property states. Default properties contain
 * values which are equivalent to 0 while dirty properties contain
 * values which are equivalent to 1 depending on the actual type.
 * 
 * <p>
 * Dirty Example:
 * 
 * <pre>
 * public class Foo {
 * 	private boolean foo = true;
 * 	private int bar = 1;
 * 	private double baz = 1d;
 * }
 * </pre>
 * 
 * <p>
 * Default Example:
 * 
 * <pre>
 * public class Foo {
 * 	private boolean foo = false;
 * 	private int bar = 0;
 * 	private double baz = 0d;
 * }
 * </pre>
 * 
 * @author Sebastian Gröbler
 * @since 13.08.2012
 */
enum PropertyState {

	/**
	 * Relates to values equivalent to 0.
	 */
	DEFAULT,
	
	/**
	 * Relates to values equivalent to 1.
	 */
	DIRTY;
}

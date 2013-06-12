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

package com.codereligion.beast.internal.util;

/**
 * Utility class for handling and throwing of {@link AssertionError}s.
 *
 * @author Sebastian Gröbler
 * @since 11.09.2012
 */
public final class Assert {
	
	/**
	 * Prohibits public instantiation.
	 */
	private Assert() {
		throw new IllegalAccessError("This is a static utility class which must not be instantiated.");
	}


    /**
     * Convenience method to throw a formatted {@link AssertionError} with the given {@code message}
     * and {@code messageArgs}.
     *
     * @param message the message used to format
     * @param messageArgs the arguments used to format the message with
     * @throws AssertionError when called
     */
	public static void fail(final String message, final Object... messageArgs) {
        final String formattedMessage = String.format(message, messageArgs);
        throw new AssertionError(formattedMessage);
    }

	/**
	 * Convenience method to throw a formatted {@link AssertionError} if the given condition is not {@code false}.
	 *
	 * @param condition the boolean condition to be checked
	 * @param message the message to be formatted in cases an {@link AssertionError} is thrown
	 * @param messageArgs the arguments to be used in message formatting
	 * @throws AssertionError when the given {@code condition} is true
	 */
	public static void assertFalse(final boolean condition, final String message, final Object... messageArgs) {
		if (condition) {
            fail(message, messageArgs);
		}
	}

    /**
	 * Convenience method to throw a formatted {@link AssertionError} if the given condition is not {@code true}.
	 * 
	 * @param condition the boolean condition to be checked
	 * @param message the message to be formatted in cases an {@link AssertionError} is thrown
	 * @param messageArgs the arguments to be used in message formatting
	 * @throws AssertionError when the given {@code condition} is false
	 */
	public static void assertTrue(final boolean condition, final String message, final Object... messageArgs) {
		if (!condition) {
            fail(message, messageArgs);
		}
	}
}

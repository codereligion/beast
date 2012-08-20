package org.codereligion.beast;

/**
 * TODO
 *
 * @author sgroebler
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

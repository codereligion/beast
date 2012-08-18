package org.codereligion.test.bean.creation;

/**
 * TODO
 *
 * @author sgroebler
 * @since 18.08.2012
 */
public final class ObjectMethodNames {
	
	/**
	 *  No public constructor for this utility class.
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

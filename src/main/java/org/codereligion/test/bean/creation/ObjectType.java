package org.codereligion.test.bean.creation;

/**
 * TODO double check
 * Enumeration of available object types. Default objects contain
 * values which are equivalent to 0 while dirty objects contain
 * values which are equivalent to 1 depending on the actual type.
 * 
 * <p>
 * Dirty Example:
 * 
 * <pre>
 * public class Foo
 * 	private boolean foo = true;
 * 	private int bar = 1;
 * 	private double baz = 1d;
 * </pre>
 * 
 * <p>
 * Default Example:
 * 
 * <pre>
 * public class Foo
 * 	private boolean foo = false;
 * 	private int bar = 0;
 * 	private double baz = 0d;
 * </pre>
 * 
 * @author sgroebler
 * @since 13.08.2012
 */
public enum ObjectType {

	/**
	 * Relates to values equivalent to 0.
	 */
	DEFAULT,
	
	/**
	 * Relates to values equivalent to 1.
	 */
	DIRTY;
}

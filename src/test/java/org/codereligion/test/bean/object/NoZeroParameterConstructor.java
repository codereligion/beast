package org.codereligion.test.bean.object;

/**
 * Test class without a zero parameter constructor.
 *
 * @author sgroebler
 * @since 16.08.2012
 */
public class NoZeroParameterConstructor {

	private final int foo;
	
	public NoZeroParameterConstructor(final int foo) {
		this.foo = foo;
	}
	
	public int getFoo() {
		return foo;
	}
}

package org.codereligion.beast.object;

/**
 * Test final class.
 * 
 * @author sgroebler
 * @since 12.08.2012
 */
public final class FinalClass {

	private int foo;
	
	public void set(final int foo) {
		this.foo = foo;
	}
	
	public int get() {
		return this.foo;
	}
}
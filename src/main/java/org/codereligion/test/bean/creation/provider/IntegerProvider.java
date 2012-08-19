package org.codereligion.test.bean.creation.provider;



/**
 * Provider for dirty and default objects of class {@link Integer}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public final class IntegerProvider implements Provider<Integer> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<Integer> INSTANCE = new IntegerProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Integer DIRTY = Integer.valueOf(1);
	
	/**
	 * Cached default object.
	 */
	private static final Integer DEFAULT = Integer.valueOf(0);
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private IntegerProvider() {
		// nothing to do
	}
	
	@Override
	public Integer getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Integer getDefaultObject() {
		return DEFAULT;
	}
}
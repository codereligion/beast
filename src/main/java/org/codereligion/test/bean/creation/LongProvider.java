package org.codereligion.test.bean.creation;


/**
 * Provider for dirty and default objects of class {@link Long}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class LongProvider implements Provider<Long> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<Long> INSTANCE = new LongProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Long DIRTY = Long.valueOf(1);
	
	/**
	 * Cached default object.
	 */
	private static final Long DEFAULT = Long.valueOf(0);
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private LongProvider() {
		// nothing to do
	}
	
	@Override
	public Long getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Long getDefaultObject() {
		return DEFAULT;
	}
}
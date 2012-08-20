package org.codereligion.beast;



/**
 * Provider for dirty and default objects of class {@link Long}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class LongInstanceProvider implements InstanceProvider<Long> {

	/**
	 * Instance of this class.
	 */
	public static final InstanceProvider<Long> INSTANCE = new LongInstanceProvider();
	
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
	private LongInstanceProvider() {
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
	
	@Override
	public Class<Long> getInstanceClass() {
		return Long.class;
	}
}
package org.codereligion.beast;



/**
 * Provider for dirty and default objects of class {@link Integer}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class IntegerInstanceProvider implements InstanceProvider<Integer> {

	/**
	 * Instance of this class.
	 */
	public static final InstanceProvider<Integer> INSTANCE = new IntegerInstanceProvider();
	
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
	private IntegerInstanceProvider() {
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

	@Override
	public Class<Integer> getInstanceClass() {
		return Integer.class;
	}
}
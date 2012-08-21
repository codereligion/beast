package com.codereligion.beast;


/**
 * Provider for dirty and default objects of class {@link Short}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
final class ShortInstanceProvider implements InstanceProvider<Short> {

	/**
	 * Instance of this class.
	 */
	public static final InstanceProvider<Short> INSTANCE = new ShortInstanceProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Short DIRTY = new Short("1");
	
	/**
	 * Cached default object.
	 */
	private static final Short DEFAULT = new Short("0");
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private ShortInstanceProvider() {
		// nothing to do
	}
	
	@Override
	public Short getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Short getDefaultObject() {
		return DEFAULT;
	}
	
	@Override
	public Class<Short> getInstanceClass() {
		return Short.class;
	}
}
package com.codereligion.beast;



/**
 * Provider for dirty and default objects of class {@link Float}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
final class FloatInstanceProvider implements InstanceProvider<Float> {

	/**
	 * Instance of this class.
	 */
	public static final InstanceProvider<Float> INSTANCE = new FloatInstanceProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Float DIRTY = new Float("1");
	
	/**
	 * Cached default object.
	 */
	private static final Float DEFAULT = new Float("0");
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private FloatInstanceProvider() {
		// nothing to do
	}
	
	@Override
	public Float getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Float getDefaultObject() {
		return DEFAULT;
	}

	@Override
	public Class<Float> getInstanceClass() {
		return Float.class;
	}
}
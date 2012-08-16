package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Float}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class FloatProvider implements Provider<Float> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<Float> INSTANCE = new FloatProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Float DIRTY = new Float("1");
	
	/**
	 * Cached default object.
	 */
	private static final Float DEFAULT = new Float("0");
	
	@Override
	public Float getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Float getDefaultObject() {
		return DEFAULT;
	}
}
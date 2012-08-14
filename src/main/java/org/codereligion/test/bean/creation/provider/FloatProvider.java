package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Float}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class FloatProvider extends AbstractProvider<Float> {

	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<Float> INSTANCE = new FloatProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Float DIRTY = 1F;
	
	/**
	 * Cached default object.
	 */
	private static final Float DEFAULT = 0F;
	
	@Override
	public Float getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Float getDefaultObject() {
		return DEFAULT;
	}
}
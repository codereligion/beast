package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of {@link Byte}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class BooleanProvider extends AbstractProvider<Boolean> {

	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<Boolean> INSTANCE = new BooleanProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Boolean DIRTY = true;
	
	/**
	 * Cached default object.
	 */
	private static final Boolean DEFAULT = false;
	
	@Override
	public Boolean getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Boolean getDefaultObject() {
		return DEFAULT;
	}
}
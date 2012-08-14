package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Short}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class ShortProvider extends AbstractProvider<Short> {

	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<Short> INSTANCE = new ShortProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Short DIRTY = 1;
	
	/**
	 * Cached default object.
	 */
	private static final Short DEFAULT = 0;
	
	@Override
	public Short getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Short getDefaultObject() {
		return DEFAULT;
	}
}
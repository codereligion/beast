package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Object}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class ObjectProvider implements Provider<Object> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<Object> INSTANCE = new ObjectProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Object DIRTY = 1;
	
	/**
	 * Cached default object.
	 */
	private static final Object DEFAULT = 0;
	
	@Override
	public Object getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Object getDefaultObject() {
		return DEFAULT;
	}
}
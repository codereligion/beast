package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Object}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public final class ObjectProvider implements Provider<Object> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<Object> INSTANCE = new ObjectProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Object DIRTY = new Short("1");
	
	/**
	 * Cached default object.
	 */
	private static final Object DEFAULT = new Short("0");
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private ObjectProvider() {
		// nothing to do
	}
	
	@Override
	public Object getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Object getDefaultObject() {
		return DEFAULT;
	}
}
package org.codereligion.beast;


/**
 * Provider for dirty and default objects of class {@link Object}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class ObjectInstanceProvider implements InstanceProvider<Object> {

	/**
	 * Instance of this class.
	 */
	public static final InstanceProvider<Object> INSTANCE = new ObjectInstanceProvider();
	
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
	private ObjectInstanceProvider() {
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
	
	@Override
	public Class<Object> getInstanceClass() {
		return Object.class;
	}
}
package org.codereligion.beast;



/**
 * Provider for dirty and default objects of class {@link Byte}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class ByteInstanceProvider implements InstanceProvider<Byte> {

	/**
	 * Instance of this class.
	 */
	public static final InstanceProvider<Byte> INSTANCE = new ByteInstanceProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Byte DIRTY = new Byte("1");
	
	/**
	 * Cached default object.
	 */
	private static final Byte DEFAULT = new Byte("0");
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private ByteInstanceProvider() {
		// nothing to do
	}
	
	@Override
	public Byte getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Byte getDefaultObject() {
		return DEFAULT;
	}

	@Override
	public Class<Byte> getInstanceClass() {
		return Byte.class;
	}
}
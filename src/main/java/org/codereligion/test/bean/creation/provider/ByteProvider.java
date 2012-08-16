package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Byte}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class ByteProvider implements Provider<Byte> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<Byte> INSTANCE = new ByteProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Byte DIRTY = new Byte("1");
	
	/**
	 * Cached default object.
	 */
	private static final Byte DEFAULT = new Byte("0");
	
	@Override
	public Byte getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Byte getDefaultObject() {
		return DEFAULT;
	}	
}
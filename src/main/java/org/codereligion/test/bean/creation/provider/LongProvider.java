package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Long}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class LongProvider extends AbstractProvider<Long> {

	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<Long> INSTANCE = new LongProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Long DIRTY = 1L;
	
	/**
	 * Cached default object.
	 */
	private static final Long DEFAULT = 0L;
	
	@Override
	public Long getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Long getDefaultObject() {
		return DEFAULT;
	}
}
package org.codereligion.test.bean.creation.provider;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Provider for dirty and default objects of class {@link AtomicLong}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class AtomicLongProvider extends AbstractProvider<AtomicLong> {

	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<AtomicLong> INSTANCE = new AtomicLongProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final AtomicLong DIRTY = new AtomicLong(1L);
	
	/**
	 * Cached default object.
	 */
	private static final AtomicLong DEFAULT = new AtomicLong(0L);
	
	@Override
	public AtomicLong getDirtyObject() {
		return DIRTY;
	}

	@Override
	public AtomicLong getDefaultObject() {
		return DEFAULT;
	}
}
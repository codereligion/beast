package org.codereligion.test.bean.creation;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Provider for dirty and default objects of class {@link AtomicLong}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class AtomicLongProvider implements Provider<AtomicLong> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<AtomicLong> INSTANCE = new AtomicLongProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final AtomicLong DIRTY = new AtomicLong(1L);
	
	/**
	 * Cached default object.
	 */
	private static final AtomicLong DEFAULT = new AtomicLong(0L);
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private AtomicLongProvider() {
		// nothing to do
	}
	
	@Override
	public AtomicLong getDirtyObject() {
		return DIRTY;
	}

	@Override
	public AtomicLong getDefaultObject() {
		return DEFAULT;
	}
}
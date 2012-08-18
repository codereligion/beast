package org.codereligion.test.bean.creation;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Provider for dirty and default objects of class {@link AtomicBoolean}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class AtomicBooleanProvider implements Provider<AtomicBoolean> {
	
	/**
	 * Instance of this class.
	 */
	public static final Provider<AtomicBoolean> INSTANCE = new AtomicBooleanProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final AtomicBoolean DIRTY = new AtomicBoolean(true);

	/**
	 * Cached default object.
	 */
	private static final AtomicBoolean DEFAULT = new AtomicBoolean(false);
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private AtomicBooleanProvider() {
		// nothing to do
	}
	
	@Override
	public AtomicBoolean getDirtyObject() {
		return DIRTY;
	}

	@Override
	public AtomicBoolean getDefaultObject() {
		return DEFAULT;
	}
}
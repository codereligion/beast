package org.codereligion.test.bean.creation;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provider for dirty and default objects of {@link AtomicInteger}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class AtomicIntegerProvider implements Provider<AtomicInteger> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<AtomicInteger> INSTANCE = new AtomicIntegerProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final AtomicInteger DIRTY = new AtomicInteger(1);
	
	/**
	 * Cached default object.
	 */
	private static final AtomicInteger DEFAULT = new AtomicInteger(0);
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private AtomicIntegerProvider() {
		// nothing to do
	}
	
	@Override
	public AtomicInteger getDirtyObject() {
		return DIRTY;
	}

	@Override
	public AtomicInteger getDefaultObject() {
		return DEFAULT;
	}
}
package org.codereligion.test.bean.creation.provider;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provider for dirty and default objects of {@link AtomicInteger}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class AtomicIntegerProvider extends AbstractProvider<AtomicInteger> {

	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<AtomicInteger> INSTANCE = new AtomicIntegerProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final AtomicInteger DIRTY = new AtomicInteger(1);
	
	/**
	 * Cached default object.
	 */
	private static final AtomicInteger DEFAULT = new AtomicInteger(0);
	
	@Override
	public AtomicInteger getDirtyObject() {
		return DIRTY;
	}

	@Override
	public AtomicInteger getDefaultObject() {
		return DEFAULT;
	}
}
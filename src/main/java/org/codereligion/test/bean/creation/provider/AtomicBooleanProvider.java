package org.codereligion.test.bean.creation.provider;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Provider for dirty and default objects of class {@link AtomicBoolean}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class AtomicBooleanProvider extends AbstractProvider<AtomicBoolean> {
	
	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<AtomicBoolean> INSTANCE = new AtomicBooleanProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final AtomicBoolean DIRTY = new AtomicBoolean(true);

	/**
	 * Cached default object.
	 */
	private static final AtomicBoolean DEFAULT = new AtomicBoolean(false);
	
	@Override
	public AtomicBoolean getDirtyObject() {
		return DIRTY;
	}

	@Override
	public AtomicBoolean getDefaultObject() {
		return DEFAULT;
	}
}
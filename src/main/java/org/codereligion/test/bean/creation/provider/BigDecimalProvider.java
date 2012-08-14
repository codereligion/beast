package org.codereligion.test.bean.creation.provider;

import java.math.BigDecimal;

/**
 * Provider for dirty and default objects of class {@link BigDecimal}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class BigDecimalProvider extends AbstractProvider<BigDecimal> {

	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<BigDecimal> INSTANCE = new BigDecimalProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final BigDecimal DIRTY = new BigDecimal("1");
	
	/**
	 * Cached default object.
	 */
	private static final BigDecimal DEFAULT = new BigDecimal("0");
	
	@Override
	public BigDecimal getDirtyObject() {
		return DIRTY;
	}

	@Override
	public BigDecimal getDefaultObject() {
		return DEFAULT;
	}
}
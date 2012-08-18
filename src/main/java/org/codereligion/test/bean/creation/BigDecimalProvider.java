package org.codereligion.test.bean.creation;

import java.math.BigDecimal;

/**
 * Provider for dirty and default objects of class {@link BigDecimal}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class BigDecimalProvider implements Provider<BigDecimal> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<BigDecimal> INSTANCE = new BigDecimalProvider();
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private BigDecimalProvider() {
		// nothing to do
	}
	
	@Override
	public BigDecimal getDirtyObject() {
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal getDefaultObject() {
		return BigDecimal.ZERO;
	}
}
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
	
	@Override
	public BigDecimal getDirtyObject() {
		return BigDecimal.ONE;
	}

	@Override
	public BigDecimal getDefaultObject() {
		return BigDecimal.ZERO;
	}
}
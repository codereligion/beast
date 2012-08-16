package org.codereligion.test.bean.creation.provider;

import java.math.BigInteger;

/**
 * Provider for dirty and default objects of class {@link BigInteger}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class BigIntegerProvider implements Provider<BigInteger> {
	
	/**
	 * Instance of this class.
	 */
	public static final Provider<BigInteger> INSTANCE = new BigIntegerProvider();

    @Override
	public BigInteger getDirtyObject() {
		return BigInteger.ONE;
	}

	@Override
	public BigInteger getDefaultObject() {
		return BigInteger.ZERO;
	}
}
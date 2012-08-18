package org.codereligion.test.bean.creation;

import java.math.BigInteger;

/**
 * Provider for dirty and default objects of class {@link BigInteger}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
final class BigIntegerProvider implements Provider<BigInteger> {
	
	/**
	 * Instance of this class.
	 */
	public static final Provider<BigInteger> INSTANCE = new BigIntegerProvider();
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private BigIntegerProvider() {
		// nothing to do
	}

    @Override
	public BigInteger getDirtyObject() {
		return BigInteger.ONE;
	}

	@Override
	public BigInteger getDefaultObject() {
		return BigInteger.ZERO;
	}
}
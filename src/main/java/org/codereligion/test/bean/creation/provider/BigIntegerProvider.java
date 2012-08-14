package org.codereligion.test.bean.creation.provider;

import java.math.BigInteger;

/**
 * Provider for dirty and default objects of class {@link BigInteger}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class BigIntegerProvider extends AbstractProvider<BigInteger> {
	
	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<BigInteger> INSTANCE = new BigIntegerProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final BigInteger DIRTY = new BigInteger("1");
	
	/**
	 * Cached default object.
	 */
	private static final BigInteger DEFAULT = new BigInteger("0");

	@Override
	public BigInteger getDirtyObject() {
		return DIRTY;
	}

	@Override
	public BigInteger getDefaultObject() {
		return DEFAULT;
	}
}
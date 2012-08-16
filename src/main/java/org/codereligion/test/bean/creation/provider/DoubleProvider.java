package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Double}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class DoubleProvider implements Provider<Double> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<Double> INSTANCE = new DoubleProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Double DIRTY = new Double("1");
	
	/**
	 * Cached default object.
	 */
	private static final Double DEFAULT = new Double("0");
	
	@Override
	public Double getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Double getDefaultObject() {
		return DEFAULT;
	}
}
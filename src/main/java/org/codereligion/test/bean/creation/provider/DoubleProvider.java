package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Double}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class DoubleProvider extends AbstractProvider<Double> {

	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<Double> INSTANCE = new DoubleProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Double DIRTY = 1D;
	
	/**
	 * Cached default object.
	 */
	private static final Double DEFAULT = 0D;
	
	@Override
	public Double getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Double getDefaultObject() {
		return DEFAULT;
	}
}
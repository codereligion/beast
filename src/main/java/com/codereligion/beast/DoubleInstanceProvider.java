package com.codereligion.beast;



/**
 * Provider for dirty and default objects of class {@link Double}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
final class DoubleInstanceProvider implements InstanceProvider<Double> {

	/**
	 * Instance of this class.
	 */
	public static final InstanceProvider<Double> INSTANCE = new DoubleInstanceProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Double DIRTY = new Double("1");
	
	/**
	 * Cached default object.
	 */
	private static final Double DEFAULT = new Double("0");
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private DoubleInstanceProvider() {
		// nothing to do
	}
	
	@Override
	public Double getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Double getDefaultObject() {
		return DEFAULT;
	}
	
	@Override
	public Class<Double> getInstanceClass() {
		return Double.class;
	}
}
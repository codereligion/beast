package org.codereligion.beast;



/**
 * Provider for dirty and default objects of {@link Boolean}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
final class BooleanInstanceProvider implements InstanceProvider<Boolean> {

	/**
	 * Instance of this class.
	 */
	public static final InstanceProvider<Boolean> INSTANCE = new BooleanInstanceProvider();
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private BooleanInstanceProvider() {
		// nothing to do
	}

    @Override
	public Boolean getDirtyObject() {
		return Boolean.TRUE;
	}

	@Override
	public Boolean getDefaultObject() {
		return Boolean.FALSE;
	}
	
	@Override
	public Class<Boolean> getInstanceClass() {
		return Boolean.class;
	}
}
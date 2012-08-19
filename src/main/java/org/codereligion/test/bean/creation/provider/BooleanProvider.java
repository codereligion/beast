package org.codereligion.test.bean.creation.provider;



/**
 * Provider for dirty and default objects of {@link Byte}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public final class BooleanProvider implements Provider<Boolean> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<Boolean> INSTANCE = new BooleanProvider();
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private BooleanProvider() {
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
}
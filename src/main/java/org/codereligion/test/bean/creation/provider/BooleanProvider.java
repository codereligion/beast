package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of {@link Byte}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class BooleanProvider extends AbstractProvider<Boolean> {

	/**
	 * Instance of this class.
	 */
	public static final AbstractProvider<Boolean> INSTANCE = new BooleanProvider();

    @Override
	public Boolean getDirtyObject() {
		return Boolean.TRUE;
	}

	@Override
	public Boolean getDefaultObject() {
		return Boolean.FALSE;
	}
}
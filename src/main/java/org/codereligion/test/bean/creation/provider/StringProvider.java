package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link String}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class StringProvider implements Provider<String> {

	/**
	 * Instance of this class.
	 */
	public static final Provider<String> INSTANCE = new StringProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final String DIRTY = "1";
	
	/**
	 * Cached default object.
	 */
	private static final String DEFAULT = "0";
	
	@Override
	public String getDirtyObject() {
		return DIRTY;
	}
	
	@Override
	public String getDefaultObject() {
		return DEFAULT;
	}
}
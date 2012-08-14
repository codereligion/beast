package org.codereligion.test.bean.creation.provider;

/**
 * Provides a dirty and a default instance of the class specified by type {@code T}.
 * 
 * @param <T> the type to provide the dirty and default object for
 * @author sgroebler
 * @since 15.08.2012
 */
public abstract class AbstractProvider<T> {
	
	/**
	 * Returns a dirty version of the object specified by type {@code T}.
	 * 
	 * @return an instance of {@code T}
	 */
	public abstract T getDirtyObject();

	/**
	 * Returns a default version of the object specified by type {@code T}.
	 * 
	 * @return an instance of {@code T}
	 */
	public abstract T getDefaultObject();
}
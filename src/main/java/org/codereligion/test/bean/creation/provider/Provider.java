package org.codereligion.test.bean.creation.provider;

/**
 * TODO mostly used for providing instances of final classes or classes without a default constructor which can not be proxied
 * Provides a "dirty" and a "default" instance of the class specified by type {@code T}.
 * 
 * @param <T> the type to provide the dirty and default object for
 * @author sgroebler
 * @since 15.08.2012
 */
public interface Provider<T> {
	
	/**
	 * Returns a dirty version of the object specified by type {@code T}.
	 * 
	 * @return an instance of {@code T}
	 */
	T getDirtyObject();

	/**
	 * Returns a default version of the object specified by type {@code T}.
	 * 
	 * @return an instance of {@code T}
	 */
	T getDefaultObject();
}
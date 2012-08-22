package com.codereligion.beast;

/**
 * TODO mostly used for providing instances of final classes or classes without a default constructor which can not be proxied
 * 
 * Provides a "dirty" and a "default" instance of the class specified by type {@code T}.
 * 
 * @param <T> the type to provide the dirty and default object for
 * @author Sebastian Gröbler
 * @since 15.08.2012
 */
interface InstanceProvider<T> {
	
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
	
	/**
	 * The {@link Class} of which this {@link InstanceProvider} provides instances.
	 * 
	 * @return the {@link Class}, must not be {@code null}
	 */
	Class<? extends T> getInstanceClass();
}
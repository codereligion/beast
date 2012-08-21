package com.codereligion.beast.object;

/**
 * TODO
 *
 * @author Sebastian Gröbler
 * @since 20.08.2012
 * @param <T>
 */
public interface Api <T extends User> {

	T getUser();
	void setUser(T user);
}

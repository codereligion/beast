package com.codereligion.beast.object;

/**
 * Test class for java introspector bug.
 * 
 * @author Sebastian Gröbler
 * @since 13.08.2012
 */
public interface Identifiable<T> {
	T getId();
}
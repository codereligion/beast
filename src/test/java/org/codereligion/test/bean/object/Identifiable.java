package org.codereligion.test.bean.object;

/**
 * Test class for java introspector bug.
 * 
 * @author sgroebler
 * @since 13.08.2012
 */
public interface Identifiable<T> {
	T getId();
}
package org.codereligion.test.bean.object;

/**
 * TODO
 *
 * @author sgroebler
 * @since 19.08.2012
 * @param <T>
 */
public interface GenericGetterAndSetterInterface <T> {
	T getId();
	void setId(final T id);
}
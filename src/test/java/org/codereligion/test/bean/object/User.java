package org.codereligion.test.bean.object;

/**
 * Test class for java introspector bug.
 * 
 * @author sgroebler
 * @since 12.08.2012
 */
public class User implements Identifiable<Integer> {

	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}
}
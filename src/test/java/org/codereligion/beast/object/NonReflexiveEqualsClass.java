package org.codereligion.beast.object;

/**
 * Test non reflexive class.
 *
 * @author Sebastian Gröbler
 * @since 17.08.2012
 */
public class NonReflexiveEqualsClass {

    private String foo;

    public String getFoo() {
        return this.foo;
    }

    public void setFoo(final String foo) {
        this.foo = foo;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.foo == null) ? 0 : this.foo.hashCode());
		return result;
	}

    @Override
    public boolean equals(final Object that) {
        return that != this && super.equals(that);
    }
}

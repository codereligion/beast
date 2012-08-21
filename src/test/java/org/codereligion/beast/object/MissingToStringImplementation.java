package org.codereligion.beast.object;

/**
 * TODO
 *
 * @author Sebastian Gröbler
 * @since 19.08.2012
 */
public class MissingToStringImplementation {

	private String foo;
	private boolean bar;
	
	public String getFoo() {
		return this.foo;
	}
	
	public void setFoo(final String foo) {
		this.foo = foo;
	}
	
	public boolean isBar() {
		return this.bar;
	}
	
	public void setBar(final boolean bar) {
		this.bar = bar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.bar ? 1231 : 1237);
		result = prime * result + ((this.foo == null) ? 0 : this.foo.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MissingToStringImplementation other = (MissingToStringImplementation) obj;
		if (this.bar != other.bar)
			return false;
		if (this.foo == null) {
			if (other.foo != null)
				return false;
		} else if (!this.foo.equals(other.foo))
			return false;
		return true;
	}
}

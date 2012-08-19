package org.codereligion.test.bean.object;

/**
 * TODO
 *
 * @author sgroebler
 * @since 19.08.2012
 */
public class MissingHashCodeImplementation {

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
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("MissingEqualsImplementation [foo=");
		builder.append(this.foo);
		builder.append(", bar=");
		builder.append(this.bar);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MissingHashCodeImplementation other = (MissingHashCodeImplementation) obj;
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

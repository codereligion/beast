package org.codereligion.beast.object;

/**
 * Test class with missing property in equals and hashCode implementation.
 *
 * @author Sebastian Gröbler
 * @since 16.08.2012
 */
public class MissingPropertyInEqualsAndHashCode {
	
	private int foo;
	private boolean bar;

	public int getFoo() {
		return this.foo;
	}

	public void setFoo(final int foo) {
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
		result = prime * result + this.foo;
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
		final MissingPropertyInEqualsAndHashCode other = (MissingPropertyInEqualsAndHashCode) obj;
		if (this.foo != other.foo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("MissingPropertyInEquals [foo=");
		builder.append(this.foo);
		builder.append(", bar=");
		builder.append(this.bar);
		builder.append("]");
		return builder.toString();
	}
}
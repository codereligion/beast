package org.codereligion.beast.object;

/**
 * Test class with missing null check in hashCode implementation.
 *
 * @author Sebastian Gröbler
 * @since 16.08.2012
 */
public class MissingNullCheckInHashCode {

	private int foo;
	private ComplexClass bar;

	public int getFoo() {
		return this.foo;
	}

	public void setFoo(final int foo) {
		this.foo = foo;
	}

	public ComplexClass getBar() {
		return this.bar;
	}

	public void setBar(final ComplexClass bar) {
		this.bar = bar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.bar.hashCode();
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
		final MissingNullCheckInHashCode other = (MissingNullCheckInHashCode) obj;
		if (this.bar == null) {
			if (other.bar != null)
				return false;
		} else if (!this.bar.equals(other.bar))
			return false;
		if (this.foo != other.foo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("MissingNullCheckInEquals [foo=");
		builder.append(this.foo);
		builder.append(", bar=");
		builder.append(this.bar);
		builder.append("]");
		return builder.toString();
	}

}
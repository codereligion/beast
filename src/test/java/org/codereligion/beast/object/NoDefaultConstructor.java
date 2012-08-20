package org.codereligion.beast.object;

/**
 * Test class without a default constructor.
 *
 * @author sgroebler
 * @since 16.08.2012
 */
public class NoDefaultConstructor {

	private final int foo;
	private boolean bar;
	
	public NoDefaultConstructor(final int foo) {
		this.foo = foo;
	}
	
	public int getFoo() {
		return this.foo;
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
		final NoDefaultConstructor other = (NoDefaultConstructor) obj;
		if (this.bar != other.bar)
			return false;
		if (this.foo != other.foo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("NoDefaultConstructor [foo=");
		builder.append(this.foo);
		builder.append(", bar=");
		builder.append(this.bar);
		builder.append("]");
		return builder.toString();
	}
	
}

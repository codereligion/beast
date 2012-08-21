package org.codereligion.beast.object;

/**
 * Test class a property without a default constructor.
 *
 * @author Sebastian Gröbler
 * @since 16.08.2012
 */
public class PropertyWhichHasNoDefaultConstructor {

	private String foo;
	private NoDefaultConstructor bar;
	public String getFoo() {
		return this.foo;
	}
	public void setFoo(final String foo) {
		this.foo = foo;
	}
	public NoDefaultConstructor getBar() {
		return this.bar;
	}
	public void setBar(final NoDefaultConstructor bar) {
		this.bar = bar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.bar == null) ? 0 : this.bar.hashCode());
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
		final PropertyWhichHasNoDefaultConstructor other = (PropertyWhichHasNoDefaultConstructor) obj;
		if (this.bar == null) {
			if (other.bar != null)
				return false;
		} else if (!this.bar.equals(other.bar))
			return false;
		if (this.foo == null) {
			if (other.foo != null)
				return false;
		} else if (!this.foo.equals(other.foo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("WithPropertyWhichHasNoDefaultConstructor [foo=");
		builder.append(this.foo);
		builder.append(", bar=");
		builder.append(this.bar);
		builder.append("]");
		return builder.toString();
	}
	
	
}

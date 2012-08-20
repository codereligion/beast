package org.codereligion.beast.object;

/**
 * TODO
 *
 * @author sgroebler
 * @since 20.08.2012
 */
public class ConstantHashCodeResult {
	
	private final int foo;

	public ConstantHashCodeResult(int foo) {
		this.foo = foo;
	}

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ConstantHashCodeResult other = (ConstantHashCodeResult) obj;
		if (this.foo != other.foo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotEqualButSameHashCode [foo=");
		builder.append(this.foo);
		builder.append("]");
		return builder.toString();
	}
}
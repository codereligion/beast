package com.codereligion.beast.object;

/**
 * TODO
 *
 * @author Sebastian Gröbler
 * @since 20.08.2012
 */
public class ConstantToStringResult {
	
	private final int foo;
	
	public ConstantToStringResult(int foo) {
		this.foo = foo;
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
		final ConstantToStringResult other = (ConstantToStringResult) obj;
		if (this.foo != other.foo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("NotEqualButSameToString []");
		return builder.toString();
	}
}
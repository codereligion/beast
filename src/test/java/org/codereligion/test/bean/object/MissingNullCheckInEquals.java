package org.codereligion.test.bean.object;

public class MissingNullCheckInEquals {

	private int foo;
	private ComplexClass bar;

	public int getFoo() {
		return foo;
	}

	public void setFoo(int foo) {
		this.foo = foo;
	}

	public ComplexClass getBar() {
		return bar;
	}

	public void setBar(ComplexClass bar) {
		this.bar = bar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bar == null) ? 0 : bar.hashCode());
		result = prime * result + foo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MissingNullCheckInEquals other = (MissingNullCheckInEquals) obj;
		if (!bar.equals(other.bar))
			return false;
		if (foo != other.foo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MissingNullCheckInEquals [foo=");
		builder.append(foo);
		builder.append(", bar=");
		builder.append(bar);
		builder.append("]");
		return builder.toString();
	}

}
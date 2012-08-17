package org.codereligion.test.bean.object;

/**
 * Test class with missing property in toString implementation.
 *
 * @author sgroebler
 * @since 16.08.2012
 */
public class MissingPropertyInToString {
	private int foo;
	private boolean bar;

	public int getFoo() {
		return foo;
	}

	public void setFoo(int foo) {
		this.foo = foo;
	}

	public boolean isBar() {
		return bar;
	}

	public void setBar(boolean bar) {
		this.bar = bar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bar ? 1231 : 1237);
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
		MissingPropertyInToString other = (MissingPropertyInToString) obj;
		if (bar != other.bar)
			return false;
		if (foo != other.foo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MissingPropertyInEquals [foo=");
		builder.append(foo);
		builder.append("]");
		return builder.toString();
	}
}

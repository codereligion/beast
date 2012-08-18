package org.codereligion.test.bean.object;

/**
 * Test class with missing property in toString implementation.
 * 
 * @author sgroebler
 * @since 16.08.2012
 */
public class NullPointerInHashCode {

	private int foo;
	private boolean bar;
	private ComplexClass complexObject;

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

	public ComplexClass getComplexObject() {
		return this.complexObject;
	}

	public void setComplexObject(final ComplexClass complexObject) {
		this.complexObject = complexObject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.bar ? 1231 : 1237);
		result = prime * result	+ this.complexObject.hashCode();
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
		final NullPointerInHashCode other = (NullPointerInHashCode) obj;
		if (this.bar != other.bar)
			return false;
		if (this.complexObject == null) {
			if (other.complexObject != null)
				return false;
		} else if (!this.complexObject.equals(other.complexObject))
			return false;
		if (this.foo != other.foo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("NullPointerInToString [foo=");
		builder.append(this.foo);
		builder.append(", bar=");
		builder.append(this.bar);
		builder.append(", complexObject=");
		builder.append(this.complexObject);
		builder.append("]");
		return builder.toString();
	}

}

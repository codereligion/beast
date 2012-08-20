package org.codereligion.beast.object;

/**
 * Test class with missing property in equals implemetation
 *
 * @author sgroebler
 * @since 16.08.2012
 */
public class MissingPropertyInEquals {

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
		result = prime * result + ((this.complexObject == null) ? 0 : this.complexObject.hashCode());
		result = prime * result + this.foo;
		return result;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MissingPropertyInEquals)) {
			return false;
		}
		final MissingPropertyInEquals other = (MissingPropertyInEquals) obj;
		if (this.bar != other.bar) {
			return false;
		}
//		if (complexObject == null) {
//			if (other.complexObject != null) {
//				return false;
//			}
//		} else if (!complexObject.equals(other.complexObject)) {
//			return false;
//		}
		if (this.foo != other.foo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("MissingPropertyInEquals [foo=");
		builder.append(this.foo);
		builder.append(", bar=");
		builder.append(this.bar);
		builder.append(", complexObject=");
		builder.append(this.complexObject);
		builder.append("]");
		return builder.toString();
	}
}
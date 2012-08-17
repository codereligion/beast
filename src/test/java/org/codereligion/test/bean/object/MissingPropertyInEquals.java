package org.codereligion.test.bean.object;

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

	public ComplexClass getComplexObject() {
		return complexObject;
	}

	public void setComplexObject(ComplexClass complexObject) {
		this.complexObject = complexObject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bar ? 1231 : 1237);
		result = prime * result + ((complexObject == null) ? 0 : complexObject.hashCode());
		result = prime * result + foo;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MissingPropertyInEquals)) {
			return false;
		}
		MissingPropertyInEquals other = (MissingPropertyInEquals) obj;
		if (bar != other.bar) {
			return false;
		}
//		if (complexObject == null) {
//			if (other.complexObject != null) {
//				return false;
//			}
//		} else if (!complexObject.equals(other.complexObject)) {
//			return false;
//		}
		if (foo != other.foo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MissingPropertyInEquals [foo=");
		builder.append(foo);
		builder.append(", bar=");
		builder.append(bar);
		builder.append(", complexObject=");
		builder.append(complexObject);
		builder.append("]");
		return builder.toString();
	}
}
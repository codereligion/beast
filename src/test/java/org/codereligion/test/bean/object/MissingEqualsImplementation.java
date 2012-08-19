package org.codereligion.test.bean.object;

/**
 * TODO
 *
 * @author sgroebler
 * @since 19.08.2012
 */
public class MissingEqualsImplementation {

	private String foo;
	private boolean bar;
	
	public String getFoo() {
		return this.foo;
	}
	
	public void setFoo(final String foo) {
		this.foo = foo;
	}
	
	public boolean isBar() {
		return this.bar;
	}
	
	public void setBar(final boolean bar) {
		this.bar = bar;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("MissingEqualsImplementation [foo=");
		builder.append(this.foo);
		builder.append(", bar=");
		builder.append(this.bar);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.bar ? 1231 : 1237);
		result = prime * result + ((this.foo == null) ? 0 : this.foo.hashCode());
		return result;
	}
}

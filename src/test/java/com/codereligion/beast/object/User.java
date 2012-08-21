package com.codereligion.beast.object;

/**
 * Test class for java introspector bug.
 * 
 * @author Sebastian Gröbler
 * @since 12.08.2012
 */
public class User implements Identifiable<Integer> {

	private Integer id;

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
		final User other = (User) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(this.id);
		builder.append("]");
		return builder.toString();
	}

}
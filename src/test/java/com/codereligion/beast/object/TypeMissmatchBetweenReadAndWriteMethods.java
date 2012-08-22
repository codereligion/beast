package com.codereligion.beast.object;

import java.util.Date;

/**
 * Tests colliding read and write methods with same naming but different types.
 *
 * @author sgroebler
 * @since 21.08.2012
 */
public class TypeMissmatchBetweenReadAndWriteMethods {

	private Date created;
	private String state;

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isCreated() {
		return this.state == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.created == null) ? 0 : this.created.hashCode());
		result = prime * result + ((this.state == null) ? 0 : this.state.hashCode());
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
		TypeMissmatchBetweenReadAndWriteMethods other = (TypeMissmatchBetweenReadAndWriteMethods) obj;
		if (this.created == null) {
			if (other.created != null)
				return false;
		} else if (!this.created.equals(other.created))
			return false;
		if (this.state == null) {
			if (other.state != null)
				return false;
		} else if (!this.state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeMissmatchBetweenReadAndWriteMethods [created=");
		builder.append(this.created);
		builder.append(", state=");
		builder.append(this.state);
		builder.append("]");
		return builder.toString();
	}

}

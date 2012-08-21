package com.codereligion.beast.object;

/**
 * Test class for java introspector bug.
 * 
 * @author Sebastian Gröbler
 * @since 12.08.2012
 */
public class ApiUser extends User {

	private Integer apiId;

	public Integer getApiId() {
		return this.apiId;
	}

	public void setApiId(final Integer apiId) {
		this.apiId = apiId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.apiId == null) ? 0 : this.apiId.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ApiUser other = (ApiUser) obj;
		if (this.apiId == null) {
			if (other.apiId != null)
				return false;
		} else if (!this.apiId.equals(other.apiId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ApiUser [apiId=");
		builder.append(this.apiId);
		builder.append("]");
		return builder.toString();
	}
}
package com.codereligion.beast.object;

/**
 * TODO
 *
 * @author Sebastian Gröbler
 * @since 20.08.2012
 */
public class RestApi implements Api<ApiUser> {
	
	private ApiUser user;

	@Override
	public ApiUser getUser() {
		return this.user;
	}

	@Override
	public void setUser(final ApiUser user) {
		this.user = user;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.user == null) ? 0 : this.user.hashCode());
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
		final RestApi other = (RestApi) obj;
		if (this.user == null) {
			if (other.user != null)
				return false;
		} else if (!this.user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("RestApi [user=");
		builder.append(this.user);
		builder.append("]");
		return builder.toString();
	}
}

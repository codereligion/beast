/*
 * Copyright 2012 The Beast Authors (www.codereligion.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codereligion.beast.object;

/**
 * Test class with a missing hashCode implementation.
 *
 * @author Sebastian Gröbler
 * @since 19.08.2012
 */
public class MissingHashCodeImplementation {

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
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MissingHashCodeImplementation other = (MissingHashCodeImplementation) obj;
		if (this.bar != other.bar)
			return false;
		if (this.foo == null) {
			if (other.foo != null)
				return false;
		} else if (!this.foo.equals(other.foo))
			return false;
		return true;
	}
}

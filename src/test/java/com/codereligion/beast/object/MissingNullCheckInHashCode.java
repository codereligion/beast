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
 * Test class with missing null check in hashCode implementation.
 *
 * @author Sebastian Gröbler
 * @since 16.08.2012
 */
public class MissingNullCheckInHashCode {

	private int foo;
	private ComplexClass bar;

	public int getFoo() {
		return this.foo;
	}

	public void setFoo(final int foo) {
		this.foo = foo;
	}

	public ComplexClass getBar() {
		return this.bar;
	}

	public void setBar(final ComplexClass bar) {
		this.bar = bar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.bar.hashCode();
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
		final MissingNullCheckInHashCode other = (MissingNullCheckInHashCode) obj;
		if (this.bar == null) {
			if (other.bar != null)
				return false;
		} else if (!this.bar.equals(other.bar))
			return false;
		if (this.foo != other.foo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("MissingNullCheckInEquals [foo=");
		builder.append(this.foo);
		builder.append(", bar=");
		builder.append(this.bar);
		builder.append("]");
		return builder.toString();
	}

}
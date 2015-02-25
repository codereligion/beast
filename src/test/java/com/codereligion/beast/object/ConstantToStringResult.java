/**
 * Copyright 2013 www.codereligion.com
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
 * Test class with a constant toString result.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 20.08.2012
 */
public class ConstantToStringResult {

    private final int foo;

    public ConstantToStringResult(int foo) {
        this.foo = foo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.foo;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final ConstantToStringResult other = (ConstantToStringResult) obj;
        if (this.foo != other.foo) return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("NotEqualButSameToString []");
        return builder.toString();
    }
}
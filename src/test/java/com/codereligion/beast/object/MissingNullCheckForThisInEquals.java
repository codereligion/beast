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
 * Test class with missing property in equals implemetation
 *
 * @author Sebastian Gröbler
 * @since 16.08.2012
 */
public class MissingNullCheckForThisInEquals {

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
        if (!(obj instanceof MissingNullCheckForThisInEquals)) {
            return false;
        }
        final MissingNullCheckForThisInEquals other = (MissingNullCheckForThisInEquals) obj;
        if (this.bar != other.bar) {
            return false;
        }
        if (!this.complexObject.equals(other.complexObject)) {
            return false;
        }
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
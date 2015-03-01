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
 * Test class holding a property with an empty enum.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 01.03.2015
 */
public class ClassWithEmptyEnumProperty {

    private EmptyEnum emptyEnum;

    public EmptyEnum getEmptyEnum() {
        return emptyEnum;
    }

    public void setEmptyEnum(final EmptyEnum emptyEnum) {
        this.emptyEnum = emptyEnum;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ClassWithEmptyEnumProperty that = (ClassWithEmptyEnumProperty) o;

        if (emptyEnum != that.emptyEnum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return emptyEnum != null ? emptyEnum.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClassWithEmptyEnumProperty[");
        sb.append("emptyEnum=").append(emptyEnum);
        sb.append(']');
        return sb.toString();
    }
}

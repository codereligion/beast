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
 * Test class holding a property with an one element enum.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 01.03.2015
 */
public class ClassWithOneElementEnumProperty {

    private OneElementEnum oneElementEnum;

    public OneElementEnum getOneElementEnum() {
        return oneElementEnum;
    }

    public void setOneElementEnum(final OneElementEnum oneElementEnum) {
        this.oneElementEnum = oneElementEnum;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ClassWithOneElementEnumProperty that = (ClassWithOneElementEnumProperty) o;

        if (oneElementEnum != that.oneElementEnum) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return oneElementEnum != null ? oneElementEnum.hashCode() : 0;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClassWithOneElementEnumProperty{");
        sb.append("oneElementEnum=").append(oneElementEnum);
        sb.append('}');
        return sb.toString();
    }
}

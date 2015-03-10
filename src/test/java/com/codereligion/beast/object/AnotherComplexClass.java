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

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Test complex class.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 12.08.2012
 */
public class AnotherComplexClass {

    public enum Enumeration {
        FOO, BAR
    }

    ;

    private boolean booleanPrimitive;
    private Boolean booleanBoxed;
    private AtomicBoolean atomicBoolean;

    private byte bytePrimitive;
    private Byte byteBoxed;

    private char charPrimitive;
    private Character charBoxed;

    private short shortPrimitive;
    private Short shortBoxed;

    private int intPrimitive;
    private Integer intBoxed;
    private AtomicInteger atomicInteger;

    private long longPrimitive;
    private Long longBoxed;
    private AtomicLong atomicLong;

    private float floatPrimitive;
    private Float floatBoxed;

    private double doublePrimitive;
    private Double doubleBoxed;

    private Enumeration enumeration;
    private String string;

    public boolean isBooleanPrimitive() {
        return this.booleanPrimitive;
    }

    public void setBooleanPrimitive(final boolean booleanPrimitive) {
        this.booleanPrimitive = booleanPrimitive;
    }

    public Boolean getBooleanBoxed() {
        return this.booleanBoxed;
    }

    public void setBooleanBoxed(final Boolean booleanBoxed) {
        this.booleanBoxed = booleanBoxed;
    }

    public AtomicBoolean getAtomicBoolean() {
        return this.atomicBoolean;
    }

    public void setAtomicBoolean(final AtomicBoolean atomicBoolean) {
        this.atomicBoolean = atomicBoolean;
    }

    public byte getBytePrimitive() {
        return this.bytePrimitive;
    }

    public void setBytePrimitive(final byte bytePrimitive) {
        this.bytePrimitive = bytePrimitive;
    }

    public Byte getByteBoxed() {
        return this.byteBoxed;
    }

    public void setByteBoxed(final Byte byteBoxed) {
        this.byteBoxed = byteBoxed;
    }

    public char getCharPrimitive() {
        return this.charPrimitive;
    }

    public void setCharPrimitive(final char charPrimitive) {
        this.charPrimitive = charPrimitive;
    }

    public Character getCharBoxed() {
        return this.charBoxed;
    }

    public void setCharBoxed(final Character charBoxed) {
        this.charBoxed = charBoxed;
    }

    public short getShortPrimitive() {
        return this.shortPrimitive;
    }

    public void setShortPrimitive(final short shortPrimitive) {
        this.shortPrimitive = shortPrimitive;
    }

    public Short getShortBoxed() {
        return this.shortBoxed;
    }

    public void setShortBoxed(final Short shortBoxed) {
        this.shortBoxed = shortBoxed;
    }

    public int getIntPrimitive() {
        return this.intPrimitive;
    }

    public void setIntPrimitive(final int intPrimitive) {
        this.intPrimitive = intPrimitive;
    }

    public Integer getIntBoxed() {
        return this.intBoxed;
    }

    public void setIntBoxed(final Integer intBoxed) {
        this.intBoxed = intBoxed;
    }

    public AtomicInteger getAtomicInteger() {
        return this.atomicInteger;
    }

    public void setAtomicInteger(final AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    public long getLongPrimitive() {
        return this.longPrimitive;
    }

    public void setLongPrimitive(final long longPrimitive) {
        this.longPrimitive = longPrimitive;
    }

    public Long getLongBoxed() {
        return this.longBoxed;
    }

    public void setLongBoxed(final Long longBoxed) {
        this.longBoxed = longBoxed;
    }

    public AtomicLong getAtomicLong() {
        return this.atomicLong;
    }

    public void setAtomicLong(final AtomicLong atomicLong) {
        this.atomicLong = atomicLong;
    }

    public float getFloatPrimitive() {
        return this.floatPrimitive;
    }

    public void setFloatPrimitive(final float floatPrimitive) {
        this.floatPrimitive = floatPrimitive;
    }

    public Float getFloatBoxed() {
        return this.floatBoxed;
    }

    public void setFloatBoxed(final Float floatBoxed) {
        this.floatBoxed = floatBoxed;
    }

    public double getDoublePrimitive() {
        return this.doublePrimitive;
    }

    public void setDoublePrimitive(final double doublePrimitive) {
        this.doublePrimitive = doublePrimitive;
    }

    public Double getDoubleBoxed() {
        return this.doubleBoxed;
    }

    public void setDoubleBoxed(final Double doubleBoxed) {
        this.doubleBoxed = doubleBoxed;
    }

    public Enumeration getEnumeration() {
        return this.enumeration;
    }

    public void setEnumeration(final Enumeration enumeration) {
        this.enumeration = enumeration;
    }

    public String getString() {
        return this.string;
    }

    public void setString(final String string) {
        this.string = string;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.atomicBoolean == null) ? 0 : this.atomicBoolean.hashCode());
        result = prime * result + ((this.atomicInteger == null) ? 0 : this.atomicInteger.hashCode());
        result = prime * result + ((this.atomicLong == null) ? 0 : this.atomicLong.hashCode());
        result = prime * result + ((this.booleanBoxed == null) ? 0 : this.booleanBoxed.hashCode());
        result = prime * result + (this.booleanPrimitive ? 1231 : 1237);
        result = prime * result + ((this.byteBoxed == null) ? 0 : this.byteBoxed.hashCode());
        result = prime * result + this.bytePrimitive;
        result = prime * result + ((this.charBoxed == null) ? 0 : this.charBoxed.hashCode());
        result = prime * result + this.charPrimitive;
        result = prime * result + ((this.doubleBoxed == null) ? 0 : this.doubleBoxed.hashCode());
        long temp;
        temp = Double.doubleToLongBits(this.doublePrimitive);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((this.enumeration == null) ? 0 : this.enumeration.hashCode());
        result = prime * result + ((this.floatBoxed == null) ? 0 : this.floatBoxed.hashCode());
        result = prime * result + Float.floatToIntBits(this.floatPrimitive);
        result = prime * result + ((this.intBoxed == null) ? 0 : this.intBoxed.hashCode());
        result = prime * result + this.intPrimitive;
        result = prime * result + ((this.longBoxed == null) ? 0 : this.longBoxed.hashCode());
        result = prime * result + (int) (this.longPrimitive ^ (this.longPrimitive >>> 32));
        result = prime * result + ((this.shortBoxed == null) ? 0 : this.shortBoxed.hashCode());
        result = prime * result + this.shortPrimitive;
        result = prime * result + ((this.string == null) ? 0 : this.string.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final AnotherComplexClass other = (AnotherComplexClass) obj;
        if (this.atomicBoolean == null) {
            if (other.atomicBoolean != null) return false;
        } else if (!this.atomicBoolean.equals(other.atomicBoolean)) return false;
        if (this.atomicInteger == null) {
            if (other.atomicInteger != null) return false;
        } else if (!this.atomicInteger.equals(other.atomicInteger)) return false;
        if (this.atomicLong == null) {
            if (other.atomicLong != null) return false;
        } else if (!this.atomicLong.equals(other.atomicLong)) return false;
        if (this.booleanBoxed == null) {
            if (other.booleanBoxed != null) return false;
        } else if (!this.booleanBoxed.equals(other.booleanBoxed)) return false;
        if (this.booleanPrimitive != other.booleanPrimitive) return false;
        if (this.byteBoxed == null) {
            if (other.byteBoxed != null) return false;
        } else if (!this.byteBoxed.equals(other.byteBoxed)) return false;
        if (this.bytePrimitive != other.bytePrimitive) return false;
        if (this.charBoxed == null) {
            if (other.charBoxed != null) return false;
        } else if (!this.charBoxed.equals(other.charBoxed)) return false;
        if (this.charPrimitive != other.charPrimitive) return false;
        if (this.doubleBoxed == null) {
            if (other.doubleBoxed != null) return false;
        } else if (!this.doubleBoxed.equals(other.doubleBoxed)) return false;
        if (Double.doubleToLongBits(this.doublePrimitive) != Double.doubleToLongBits(other.doublePrimitive)) return false;
        if (this.enumeration != other.enumeration) return false;
        if (this.floatBoxed == null) {
            if (other.floatBoxed != null) return false;
        } else if (!this.floatBoxed.equals(other.floatBoxed)) return false;
        if (Float.floatToIntBits(this.floatPrimitive) != Float.floatToIntBits(other.floatPrimitive)) return false;
        if (this.intBoxed == null) {
            if (other.intBoxed != null) return false;
        } else if (!this.intBoxed.equals(other.intBoxed)) return false;
        if (this.intPrimitive != other.intPrimitive) return false;
        if (this.longBoxed == null) {
            if (other.longBoxed != null) return false;
        } else if (!this.longBoxed.equals(other.longBoxed)) return false;
        if (this.longPrimitive != other.longPrimitive) return false;
        if (this.shortBoxed == null) {
            if (other.shortBoxed != null) return false;
        } else if (!this.shortBoxed.equals(other.shortBoxed)) return false;
        if (this.shortPrimitive != other.shortPrimitive) return false;
        if (this.string == null) {
            if (other.string != null) return false;
        } else if (!this.string.equals(other.string)) return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ComplexObject [booleanPrimitive=");
        builder.append(this.booleanPrimitive);
        builder.append(", booleanBoxed=");
        builder.append(this.booleanBoxed);
        builder.append(", atomicBoolean=");
        builder.append(this.atomicBoolean);
        builder.append(", bytePrimitive=");
        builder.append(this.bytePrimitive);
        builder.append(", byteBoxed=");
        builder.append(this.byteBoxed);
        builder.append(", charPrimitive=");
        builder.append(this.charPrimitive);
        builder.append(", charBoxed=");
        builder.append(this.charBoxed);
        builder.append(", shortPrimitive=");
        builder.append(this.shortPrimitive);
        builder.append(", shortBoxed=");
        builder.append(this.shortBoxed);
        builder.append(", intPrimitive=");
        builder.append(this.intPrimitive);
        builder.append(", intBoxed=");
        builder.append(this.intBoxed);
        builder.append(", atomicInteger=");
        builder.append(this.atomicInteger);
        builder.append(", longPrimitive=");
        builder.append(this.longPrimitive);
        builder.append(", longBoxed=");
        builder.append(this.longBoxed);
        builder.append(", atomicLong=");
        builder.append(this.atomicLong);
        builder.append(", floatPrimitive=");
        builder.append(this.floatPrimitive);
        builder.append(", floatBoxed=");
        builder.append(this.floatBoxed);
        builder.append(", doublePrimitive=");
        builder.append(this.doublePrimitive);
        builder.append(", doubleBoxed=");
        builder.append(this.doubleBoxed);
        builder.append(", enumeration=");
        builder.append(this.enumeration);
        builder.append(", string=");
        builder.append(this.string);
        builder.append("]");
        return builder.toString();
    }
}

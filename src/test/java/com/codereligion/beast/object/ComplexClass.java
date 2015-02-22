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

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Text complex class.
 *
 * @author Sebastian Gröbler
 * @since 12.08.2012
 */
public class ComplexClass {

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
    private ComplexClass complexObject;
    private AnotherComplexClass anotherComplexObject;
    private String string;
    private Object object;

    private Calendar calendar;
    private Date date;

    private boolean[] booleanArray;
    private byte[] byteArray;
    private char[] charArray;
    private short[] shortArray;
    private int[] intArray;
    private long[] longArray;
    private float[] floatArray;

    private AnotherComplexClass[] anotherComplexObjectArray;
    private Enumeration[] enumerationArray;

    private Collection<Integer> integerCollection;
    private List<Integer> integerList;
    private Set<Integer> integerSet;
    private Map<Integer, Integer> integerMap;

    private Collection<AnotherComplexClass> anotherComplexObjectCollection;
    private List<AnotherComplexClass> anotherComplexObjectList;
    private Set<AnotherComplexClass> anotherComplexObjectSet;
    private Map<AnotherComplexClass, ComplexClass> anotherComplexObjectMap;

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

    public ComplexClass getComplexObject() {
        return this.complexObject;
    }

    public void setComplexObject(final ComplexClass complexObject) {
        this.complexObject = complexObject;
    }

    public AnotherComplexClass getAnotherComplexObject() {
        return this.anotherComplexObject;
    }

    public void setAnotherComplexObject(final AnotherComplexClass anotherComplexObject) {
        this.anotherComplexObject = anotherComplexObject;
    }

    public String getString() {
        return this.string;
    }

    public void setString(final String string) {
        this.string = string;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(final Object object) {
        this.object = object;
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    public void setCalendar(final Calendar calendar) {
        this.calendar = calendar;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public boolean[] getBooleanArray() {
        return this.booleanArray;
    }

    public void setBooleanArray(final boolean[] booleanArray) {
        this.booleanArray = booleanArray;
    }

    public byte[] getByteArray() {
        return this.byteArray;
    }

    public void setByteArray(final byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public char[] getCharArray() {
        return this.charArray;
    }

    public void setCharArray(final char[] charArray) {
        this.charArray = charArray;
    }

    public short[] getShortArray() {
        return this.shortArray;
    }

    public void setShortArray(final short[] shortArray) {
        this.shortArray = shortArray;
    }

    public int[] getIntArray() {
        return this.intArray;
    }

    public void setIntArray(final int[] intArray) {
        this.intArray = intArray;
    }

    public long[] getLongArray() {
        return this.longArray;
    }

    public void setLongArray(final long[] longArray) {
        this.longArray = longArray;
    }

    public float[] getFloatArray() {
        return this.floatArray;
    }

    public void setFloatArray(final float[] floatArray) {
        this.floatArray = floatArray;
    }

    public AnotherComplexClass[] getAnotherComplexObjectArray() {
        return this.anotherComplexObjectArray;
    }

    public void setAnotherComplexObjectArray(final AnotherComplexClass[] anotherComplexObjectArray) {
        this.anotherComplexObjectArray = anotherComplexObjectArray;
    }

    public Enumeration[] getEnumerationArray() {
        return this.enumerationArray;
    }

    public void setEnumerationArray(final Enumeration[] enumerationArray) {
        this.enumerationArray = enumerationArray;
    }

    public Collection<Integer> getIntegerCollection() {
        return this.integerCollection;
    }

    public void setIntegerCollection(final Collection<Integer> integerCollection) {
        this.integerCollection = integerCollection;
    }

    public List<Integer> getIntegerList() {
        return this.integerList;
    }

    public void setIntegerList(final List<Integer> integerList) {
        this.integerList = integerList;
    }

    public Set<Integer> getIntegerSet() {
        return this.integerSet;
    }

    public void setIntegerSet(final Set<Integer> integerSet) {
        this.integerSet = integerSet;
    }

    public Map<Integer, Integer> getIntegerMap() {
        return this.integerMap;
    }

    public void setIntegerMap(final Map<Integer, Integer> integerMap) {
        this.integerMap = integerMap;
    }

    public Collection<AnotherComplexClass> getAnotherComplexObjectCollection() {
        return this.anotherComplexObjectCollection;
    }

    public void setAnotherComplexObjectCollection(final Collection<AnotherComplexClass> anotherComplexObjectCollection) {
        this.anotherComplexObjectCollection = anotherComplexObjectCollection;
    }

    public List<AnotherComplexClass> getAnotherComplexObjectList() {
        return this.anotherComplexObjectList;
    }

    public void setAnotherComplexObjectList(final List<AnotherComplexClass> anotherComplexObjectList) {
        this.anotherComplexObjectList = anotherComplexObjectList;
    }

    public Set<AnotherComplexClass> getAnotherComplexObjectSet() {
        return this.anotherComplexObjectSet;
    }

    public void setAnotherComplexObjectSet(final Set<AnotherComplexClass> anotherComplexObjectSet) {
        this.anotherComplexObjectSet = anotherComplexObjectSet;
    }

    public Map<AnotherComplexClass, ComplexClass> getAnotherComplexObjectMap() {
        return this.anotherComplexObjectMap;
    }

    public void setAnotherComplexObjectMap(final Map<AnotherComplexClass, ComplexClass> anotherComplexObjectMap) {
        this.anotherComplexObjectMap = anotherComplexObjectMap;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.anotherComplexObject == null) ? 0 : this.anotherComplexObject.hashCode());
        result = prime * result + Arrays.hashCode(this.anotherComplexObjectArray);
        result = prime * result + ((this.anotherComplexObjectCollection == null) ? 0 : this.anotherComplexObjectCollection.hashCode());
        result = prime * result + ((this.anotherComplexObjectList == null) ? 0 : this.anotherComplexObjectList.hashCode());
        result = prime * result + ((this.anotherComplexObjectMap == null) ? 0 : this.anotherComplexObjectMap.hashCode());
        result = prime * result + ((this.anotherComplexObjectSet == null) ? 0 : this.anotherComplexObjectSet.hashCode());
        result = prime * result + ((this.atomicBoolean == null) ? 0 : this.atomicBoolean.hashCode());
        result = prime * result + ((this.atomicInteger == null) ? 0 : this.atomicInteger.hashCode());
        result = prime * result + ((this.atomicLong == null) ? 0 : this.atomicLong.hashCode());
        result = prime * result + Arrays.hashCode(this.booleanArray);
        result = prime * result + ((this.booleanBoxed == null) ? 0 : this.booleanBoxed.hashCode());
        result = prime * result + (this.booleanPrimitive ? 1231 : 1237);
        result = prime * result + Arrays.hashCode(this.byteArray);
        result = prime * result + ((this.byteBoxed == null) ? 0 : this.byteBoxed.hashCode());
        result = prime * result + this.bytePrimitive;
        result = prime * result + ((this.calendar == null) ? 0 : this.calendar.hashCode());
        result = prime * result + Arrays.hashCode(this.charArray);
        result = prime * result + ((this.charBoxed == null) ? 0 : this.charBoxed.hashCode());
        result = prime * result + this.charPrimitive;
        result = prime * result + ((this.complexObject == null) ? 0 : this.complexObject.hashCode());
        result = prime * result + ((this.date == null) ? 0 : this.date.hashCode());
        result = prime * result + ((this.doubleBoxed == null) ? 0 : this.doubleBoxed.hashCode());
        long temp;
        temp = Double.doubleToLongBits(this.doublePrimitive);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((this.enumeration == null) ? 0 : this.enumeration.hashCode());
        result = prime * result + Arrays.hashCode(this.enumerationArray);
        result = prime * result + Arrays.hashCode(this.floatArray);
        result = prime * result + ((this.floatBoxed == null) ? 0 : this.floatBoxed.hashCode());
        result = prime * result + Float.floatToIntBits(this.floatPrimitive);
        result = prime * result + Arrays.hashCode(this.intArray);
        result = prime * result + ((this.intBoxed == null) ? 0 : this.intBoxed.hashCode());
        result = prime * result + this.intPrimitive;
        result = prime * result + ((this.integerCollection == null) ? 0 : this.integerCollection.hashCode());
        result = prime * result + ((this.integerList == null) ? 0 : this.integerList.hashCode());
        result = prime * result + ((this.integerMap == null) ? 0 : this.integerMap.hashCode());
        result = prime * result + ((this.integerSet == null) ? 0 : this.integerSet.hashCode());
        result = prime * result + Arrays.hashCode(this.longArray);
        result = prime * result + ((this.longBoxed == null) ? 0 : this.longBoxed.hashCode());
        result = prime * result + (int) (this.longPrimitive ^ (this.longPrimitive >>> 32));
        result = prime * result + ((this.object == null) ? 0 : this.object.hashCode());
        result = prime * result + Arrays.hashCode(this.shortArray);
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
        final ComplexClass other = (ComplexClass) obj;
        if (this.anotherComplexObject == null) {
            if (other.anotherComplexObject != null) return false;
        } else if (!this.anotherComplexObject.equals(other.anotherComplexObject)) return false;
        if (!Arrays.equals(this.anotherComplexObjectArray, other.anotherComplexObjectArray)) return false;
        if (this.anotherComplexObjectCollection == null) {
            if (other.anotherComplexObjectCollection != null) return false;
        } else if (!this.anotherComplexObjectCollection.equals(other.anotherComplexObjectCollection)) return false;
        if (this.anotherComplexObjectList == null) {
            if (other.anotherComplexObjectList != null) return false;
        } else if (!this.anotherComplexObjectList.equals(other.anotherComplexObjectList)) return false;
        if (this.anotherComplexObjectMap == null) {
            if (other.anotherComplexObjectMap != null) return false;
        } else if (!this.anotherComplexObjectMap.equals(other.anotherComplexObjectMap)) return false;
        if (this.anotherComplexObjectSet == null) {
            if (other.anotherComplexObjectSet != null) return false;
        } else if (!this.anotherComplexObjectSet.equals(other.anotherComplexObjectSet)) return false;
        if (this.atomicBoolean == null) {
            if (other.atomicBoolean != null) return false;
        } else if (!this.atomicBoolean.equals(other.atomicBoolean)) return false;
        if (this.atomicInteger == null) {
            if (other.atomicInteger != null) return false;
        } else if (!this.atomicInteger.equals(other.atomicInteger)) return false;
        if (this.atomicLong == null) {
            if (other.atomicLong != null) return false;
        } else if (!this.atomicLong.equals(other.atomicLong)) return false;
        if (!Arrays.equals(this.booleanArray, other.booleanArray)) return false;
        if (this.booleanBoxed == null) {
            if (other.booleanBoxed != null) return false;
        } else if (!this.booleanBoxed.equals(other.booleanBoxed)) return false;
        if (this.booleanPrimitive != other.booleanPrimitive) return false;
        if (!Arrays.equals(this.byteArray, other.byteArray)) return false;
        if (this.byteBoxed == null) {
            if (other.byteBoxed != null) return false;
        } else if (!this.byteBoxed.equals(other.byteBoxed)) return false;
        if (this.bytePrimitive != other.bytePrimitive) return false;
        if (this.calendar == null) {
            if (other.calendar != null) return false;
        } else if (!this.calendar.equals(other.calendar)) return false;
        if (!Arrays.equals(this.charArray, other.charArray)) return false;
        if (this.charBoxed == null) {
            if (other.charBoxed != null) return false;
        } else if (!this.charBoxed.equals(other.charBoxed)) return false;
        if (this.charPrimitive != other.charPrimitive) return false;
        if (this.complexObject == null) {
            if (other.complexObject != null) return false;
        } else if (!this.complexObject.equals(other.complexObject)) return false;
        if (this.date == null) {
            if (other.date != null) return false;
        } else if (!this.date.equals(other.date)) return false;
        if (this.doubleBoxed == null) {
            if (other.doubleBoxed != null) return false;
        } else if (!this.doubleBoxed.equals(other.doubleBoxed)) return false;
        if (Double.doubleToLongBits(this.doublePrimitive) != Double.doubleToLongBits(other.doublePrimitive)) return false;
        if (this.enumeration != other.enumeration) return false;
        if (!Arrays.equals(this.enumerationArray, other.enumerationArray)) return false;
        if (!Arrays.equals(this.floatArray, other.floatArray)) return false;
        if (this.floatBoxed == null) {
            if (other.floatBoxed != null) return false;
        } else if (!this.floatBoxed.equals(other.floatBoxed)) return false;
        if (Float.floatToIntBits(this.floatPrimitive) != Float.floatToIntBits(other.floatPrimitive)) return false;
        if (!Arrays.equals(this.intArray, other.intArray)) return false;
        if (this.intBoxed == null) {
            if (other.intBoxed != null) return false;
        } else if (!this.intBoxed.equals(other.intBoxed)) return false;
        if (this.intPrimitive != other.intPrimitive) return false;
        if (this.integerCollection == null) {
            if (other.integerCollection != null) return false;
        } else if (!this.integerCollection.equals(other.integerCollection)) return false;
        if (this.integerList == null) {
            if (other.integerList != null) return false;
        } else if (!this.integerList.equals(other.integerList)) return false;
        if (this.integerMap == null) {
            if (other.integerMap != null) return false;
        } else if (!this.integerMap.equals(other.integerMap)) return false;
        if (this.integerSet == null) {
            if (other.integerSet != null) return false;
        } else if (!this.integerSet.equals(other.integerSet)) return false;
        if (!Arrays.equals(this.longArray, other.longArray)) return false;
        if (this.longBoxed == null) {
            if (other.longBoxed != null) return false;
        } else if (!this.longBoxed.equals(other.longBoxed)) return false;
        if (this.longPrimitive != other.longPrimitive) return false;
        if (this.object == null) {
            if (other.object != null) return false;
        } else if (!this.object.equals(other.object)) return false;
        if (!Arrays.equals(this.shortArray, other.shortArray)) return false;
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
        builder.append("ComplexClass [booleanPrimitive=");
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
        builder.append(", complexObject=");
        builder.append(this.complexObject);
        builder.append(", anotherComplexObject=");
        builder.append(this.anotherComplexObject);
        builder.append(", string=");
        builder.append(this.string);
        builder.append(", object=");
        builder.append(this.object);
        builder.append(", calendar=");
        builder.append(this.calendar);
        builder.append(", date=");
        builder.append(this.date);
        builder.append(", booleanArray=");
        builder.append(Arrays.toString(this.booleanArray));
        builder.append(", byteArray=");
        builder.append(Arrays.toString(this.byteArray));
        builder.append(", charArray=");
        builder.append(Arrays.toString(this.charArray));
        builder.append(", shortArray=");
        builder.append(Arrays.toString(this.shortArray));
        builder.append(", intArray=");
        builder.append(Arrays.toString(this.intArray));
        builder.append(", longArray=");
        builder.append(Arrays.toString(this.longArray));
        builder.append(", floatArray=");
        builder.append(Arrays.toString(this.floatArray));
        builder.append(", anotherComplexObjectArray=");
        builder.append(Arrays.toString(this.anotherComplexObjectArray));
        builder.append(", enumerationArray=");
        builder.append(Arrays.toString(this.enumerationArray));
        builder.append(", integerCollection=");
        builder.append(this.integerCollection);
        builder.append(", integerList=");
        builder.append(this.integerList);
        builder.append(", integerSet=");
        builder.append(this.integerSet);
        builder.append(", integerMap=");
        builder.append(this.integerMap);
        builder.append(", anotherComplexObjectCollection=");
        builder.append(this.anotherComplexObjectCollection);
        builder.append(", anotherComplexObjectList=");
        builder.append(this.anotherComplexObjectList);
        builder.append(", anotherComplexObjectSet=");
        builder.append(this.anotherComplexObjectSet);
        builder.append(", anotherComplexObjectMap=");
        builder.append(this.anotherComplexObjectMap);
        builder.append("]");
        return builder.toString();
    }

}

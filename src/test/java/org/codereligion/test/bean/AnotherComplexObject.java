package org.codereligion.test.bean;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO
 * 
 * @author sgroebler
 * @since 12.08.2012
 */
public class AnotherComplexObject {

	public enum Enumeration {
		FOO, BAR
	};

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

//	private boolean[] booleanArray;
//	private byte[] byteArray;
//	private char[] charArray;
//	private short[] shortArray;
//	private int[] intArray;
//	private long[] longArray;
//	private float[] floatArray;
//
//	private ComplexObject[] complexArray;
//	private Enumeration[] enumerationArray;
//
//	private Collection<Integer> integerCollection;
//	private List<Integer> integerList;
//	private Set<Integer> integerSet;
//	private Map<Integer, Integer> integerMap;
//
//	private Collection<ComplexObject> complexObjectCollection;
//	private List<ComplexObject> complexObjectList;
//	private Set<ComplexObject> complexObjectSet;
//	private Map<ComplexObject, ComplexObject> complexObjectMap;

	public boolean isBooleanPrimitive() {
		return booleanPrimitive;
	}

	public void setBooleanPrimitive(boolean booleanPrimitive) {
		this.booleanPrimitive = booleanPrimitive;
	}

	public Boolean getBooleanBoxed() {
		return booleanBoxed;
	}

	public void setBooleanBoxed(Boolean booleanBoxed) {
		this.booleanBoxed = booleanBoxed;
	}

	public AtomicBoolean getAtomicBoolean() {
		return atomicBoolean;
	}

	public void setAtomicBoolean(AtomicBoolean atomicBoolean) {
		this.atomicBoolean = atomicBoolean;
	}

	public byte getBytePrimitive() {
		return bytePrimitive;
	}

	public void setBytePrimitive(byte bytePrimitive) {
		this.bytePrimitive = bytePrimitive;
	}

	public Byte getByteBoxed() {
		return byteBoxed;
	}

	public void setByteBoxed(Byte byteBoxed) {
		this.byteBoxed = byteBoxed;
	}

	public char getCharPrimitive() {
		return charPrimitive;
	}

	public void setCharPrimitive(char charPrimitive) {
		this.charPrimitive = charPrimitive;
	}

	public Character getCharBoxed() {
		return charBoxed;
	}

	public void setCharBoxed(Character charBoxed) {
		this.charBoxed = charBoxed;
	}

	public short getShortPrimitive() {
		return shortPrimitive;
	}

	public void setShortPrimitive(short shortPrimitive) {
		this.shortPrimitive = shortPrimitive;
	}

	public Short getShortBoxed() {
		return shortBoxed;
	}

	public void setShortBoxed(Short shortBoxed) {
		this.shortBoxed = shortBoxed;
	}

	public int getIntPrimitive() {
		return intPrimitive;
	}

	public void setIntPrimitive(int intPrimitive) {
		this.intPrimitive = intPrimitive;
	}

	public Integer getIntBoxed() {
		return intBoxed;
	}

	public void setIntBoxed(Integer intBoxed) {
		this.intBoxed = intBoxed;
	}

	public AtomicInteger getAtomicInteger() {
		return atomicInteger;
	}

	public void setAtomicInteger(AtomicInteger atomicInteger) {
		this.atomicInteger = atomicInteger;
	}

	public long getLongPrimitive() {
		return longPrimitive;
	}

	public void setLongPrimitive(long longPrimitive) {
		this.longPrimitive = longPrimitive;
	}

	public Long getLongBoxed() {
		return longBoxed;
	}

	public void setLongBoxed(Long longBoxed) {
		this.longBoxed = longBoxed;
	}

	public AtomicLong getAtomicLong() {
		return atomicLong;
	}

	public void setAtomicLong(AtomicLong atomicLong) {
		this.atomicLong = atomicLong;
	}

	public float getFloatPrimitive() {
		return floatPrimitive;
	}

	public void setFloatPrimitive(float floatPrimitive) {
		this.floatPrimitive = floatPrimitive;
	}

	public Float getFloatBoxed() {
		return floatBoxed;
	}

	public void setFloatBoxed(Float floatBoxed) {
		this.floatBoxed = floatBoxed;
	}

	public double getDoublePrimitive() {
		return doublePrimitive;
	}

	public void setDoublePrimitive(double doublePrimitive) {
		this.doublePrimitive = doublePrimitive;
	}

	public Double getDoubleBoxed() {
		return doubleBoxed;
	}

	public void setDoubleBoxed(Double doubleBoxed) {
		this.doubleBoxed = doubleBoxed;
	}

	public Enumeration getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(Enumeration enumeration) {
		this.enumeration = enumeration;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
//
//	public boolean[] getBooleanArray() {
//		return booleanArray;
//	}
//
//	public void setBooleanArray(boolean[] booleanArray) {
//		this.booleanArray = booleanArray;
//	}
//
//	public byte[] getByteArray() {
//		return byteArray;
//	}
//
//	public void setByteArray(byte[] byteArray) {
//		this.byteArray = byteArray;
//	}
//
//	public char[] getCharArray() {
//		return charArray;
//	}
//
//	public void setCharArray(char[] charArray) {
//		this.charArray = charArray;
//	}
//
//	public short[] getShortArray() {
//		return shortArray;
//	}
//
//	public void setShortArray(short[] shortArray) {
//		this.shortArray = shortArray;
//	}
//
//	public int[] getIntArray() {
//		return intArray;
//	}
//
//	public void setIntArray(int[] intArray) {
//		this.intArray = intArray;
//	}
//
//	public long[] getLongArray() {
//		return longArray;
//	}
//
//	public void setLongArray(long[] longArray) {
//		this.longArray = longArray;
//	}
//
//	public float[] getFloatArray() {
//		return floatArray;
//	}
//
//	public void setFloatArray(float[] floatArray) {
//		this.floatArray = floatArray;
//	}
//
//	public ComplexObject[] getComplexArray() {
//		return complexArray;
//	}
//
//	public void setComplexArray(ComplexObject[] complexArray) {
//		this.complexArray = complexArray;
//	}
//
//	public Enumeration[] getEnumerationArray() {
//		return enumerationArray;
//	}
//
//	public void setEnumerationArray(Enumeration[] enumerationArray) {
//		this.enumerationArray = enumerationArray;
//	}

//	public Collection<Integer> getIntegerCollection() {
//		return integerCollection;
//	}
//
//	public void setIntegerCollection(Collection<Integer> integerCollection) {
//		this.integerCollection = integerCollection;
//	}
//
//	public List<Integer> getIntegerList() {
//		return integerList;
//	}
//
//	public void setIntegerList(List<Integer> integerList) {
//		this.integerList = integerList;
//	}
//
//	public Set<Integer> getIntegerSet() {
//		return integerSet;
//	}
//
//	public void setIntegerSet(Set<Integer> integerSet) {
//		this.integerSet = integerSet;
//	}
//
//	public Map<Integer, Integer> getIntegerMap() {
//		return integerMap;
//	}
//
//	public void setIntegerMap(Map<Integer, Integer> integerMap) {
//		this.integerMap = integerMap;
//	}
//
//	public Collection<ComplexObject> getComplexObjectCollection() {
//		return complexObjectCollection;
//	}
//
//	public void setComplexObjectCollection(Collection<ComplexObject> complexObjectCollection) {
//		this.complexObjectCollection = complexObjectCollection;
//	}
//
//	public List<ComplexObject> getComplexObjectList() {
//		return complexObjectList;
//	}
//
//	public void setComplexObjectList(List<ComplexObject> complexObjectList) {
//		this.complexObjectList = complexObjectList;
//	}
//
//	public Set<ComplexObject> getComplexObjectSet() {
//		return complexObjectSet;
//	}
//
//	public void setComplexObjectSet(Set<ComplexObject> complexObjectSet) {
//		this.complexObjectSet = complexObjectSet;
//	}
//
//	public Map<ComplexObject, ComplexObject> getComplexObjectMap() {
//		return complexObjectMap;
//	}
//
//	public void setComplexObjectMap(Map<ComplexObject, ComplexObject> complexObjectMap) {
//		this.complexObjectMap = complexObjectMap;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((atomicBoolean == null) ? 0 : atomicBoolean.hashCode());
		result = prime * result
				+ ((atomicInteger == null) ? 0 : atomicInteger.hashCode());
		result = prime * result
				+ ((atomicLong == null) ? 0 : atomicLong.hashCode());
//		result = prime * result + Arrays.hashCode(booleanArray);
		result = prime * result
				+ ((booleanBoxed == null) ? 0 : booleanBoxed.hashCode());
		result = prime * result + (booleanPrimitive ? 1231 : 1237);
//		result = prime * result + Arrays.hashCode(byteArray);
		result = prime * result
				+ ((byteBoxed == null) ? 0 : byteBoxed.hashCode());
		result = prime * result + bytePrimitive;
//		result = prime * result + Arrays.hashCode(charArray);
		result = prime * result
				+ ((charBoxed == null) ? 0 : charBoxed.hashCode());
		result = prime * result + charPrimitive;
//		result = prime * result + Arrays.hashCode(complexArray);
//		result = prime
//				* result
//				+ ((complexObjectCollection == null) ? 0
//						: complexObjectCollection.hashCode());
//		result = prime
//				* result
//				+ ((complexObjectList == null) ? 0 : complexObjectList
//						.hashCode());
//		result = prime
//				* result
//				+ ((complexObjectMap == null) ? 0 : complexObjectMap.hashCode());
//		result = prime
//				* result
//				+ ((complexObjectSet == null) ? 0 : complexObjectSet.hashCode());
		result = prime * result
				+ ((doubleBoxed == null) ? 0 : doubleBoxed.hashCode());
		long temp;
		temp = Double.doubleToLongBits(doublePrimitive);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((enumeration == null) ? 0 : enumeration.hashCode());
//		result = prime * result + Arrays.hashCode(enumerationArray);
//		result = prime * result + Arrays.hashCode(floatArray);
		result = prime * result
				+ ((floatBoxed == null) ? 0 : floatBoxed.hashCode());
		result = prime * result + Float.floatToIntBits(floatPrimitive);
//		result = prime * result + Arrays.hashCode(intArray);
		result = prime * result
				+ ((intBoxed == null) ? 0 : intBoxed.hashCode());
		result = prime * result + intPrimitive;
//		result = prime
//				* result
//				+ ((integerCollection == null) ? 0 : integerCollection
//						.hashCode());
//		result = prime * result
//				+ ((integerList == null) ? 0 : integerList.hashCode());
//		result = prime * result
//				+ ((integerMap == null) ? 0 : integerMap.hashCode());
//		result = prime * result
//				+ ((integerSet == null) ? 0 : integerSet.hashCode());
//		result = prime * result + Arrays.hashCode(longArray);
		result = prime * result
				+ ((longBoxed == null) ? 0 : longBoxed.hashCode());
		result = prime * result
				+ (int) (longPrimitive ^ (longPrimitive >>> 32));
//		result = prime * result + Arrays.hashCode(shortArray);
		result = prime * result
				+ ((shortBoxed == null) ? 0 : shortBoxed.hashCode());
		result = prime * result + shortPrimitive;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnotherComplexObject other = (AnotherComplexObject) obj;
		if (atomicBoolean == null) {
			if (other.atomicBoolean != null)
				return false;
		} else if (!atomicBoolean.equals(other.atomicBoolean))
			return false;
		if (atomicInteger == null) {
			if (other.atomicInteger != null)
				return false;
		} else if (!atomicInteger.equals(other.atomicInteger))
			return false;
		if (atomicLong == null) {
			if (other.atomicLong != null)
				return false;
		} else if (!atomicLong.equals(other.atomicLong))
			return false;
//		if (!Arrays.equals(booleanArray, other.booleanArray))
//			return false;
		if (booleanBoxed == null) {
			if (other.booleanBoxed != null)
				return false;
		} else if (!booleanBoxed.equals(other.booleanBoxed))
			return false;
		if (booleanPrimitive != other.booleanPrimitive)
			return false;
//		if (!Arrays.equals(byteArray, other.byteArray))
//			return false;
		if (byteBoxed == null) {
			if (other.byteBoxed != null)
				return false;
		} else if (!byteBoxed.equals(other.byteBoxed))
			return false;
		if (bytePrimitive != other.bytePrimitive)
			return false;
//		if (!Arrays.equals(charArray, other.charArray))
//			return false;
		if (charBoxed == null) {
			if (other.charBoxed != null)
				return false;
		} else if (!charBoxed.equals(other.charBoxed))
			return false;
		if (charPrimitive != other.charPrimitive)
			return false;
//		if (!Arrays.equals(complexArray, other.complexArray))
//			return false;
//		if (complexObjectCollection == null) {
//			if (other.complexObjectCollection != null)
//				return false;
//		} else if (!complexObjectCollection
//				.equals(other.complexObjectCollection))
//			return false;
//		if (complexObjectList == null) {
//			if (other.complexObjectList != null)
//				return false;
//		} else if (!complexObjectList.equals(other.complexObjectList))
//			return false;
//		if (complexObjectMap == null) {
//			if (other.complexObjectMap != null)
//				return false;
//		} else if (!complexObjectMap.equals(other.complexObjectMap))
//			return false;
//		if (complexObjectSet == null) {
//			if (other.complexObjectSet != null)
//				return false;
//		} else if (!complexObjectSet.equals(other.complexObjectSet))
//			return false;
		if (doubleBoxed == null) {
			if (other.doubleBoxed != null)
				return false;
		} else if (!doubleBoxed.equals(other.doubleBoxed))
			return false;
		if (Double.doubleToLongBits(doublePrimitive) != Double
				.doubleToLongBits(other.doublePrimitive))
			return false;
		if (enumeration != other.enumeration)
			return false;
//		if (!Arrays.equals(enumerationArray, other.enumerationArray))
//			return false;
//		if (!Arrays.equals(floatArray, other.floatArray))
//			return false;
		if (floatBoxed == null) {
			if (other.floatBoxed != null)
				return false;
		} else if (!floatBoxed.equals(other.floatBoxed))
			return false;
		if (Float.floatToIntBits(floatPrimitive) != Float
				.floatToIntBits(other.floatPrimitive))
			return false;
//		if (!Arrays.equals(intArray, other.intArray))
//			return false;
		if (intBoxed == null) {
			if (other.intBoxed != null)
				return false;
		} else if (!intBoxed.equals(other.intBoxed))
			return false;
		if (intPrimitive != other.intPrimitive)
			return false;
//		if (integerCollection == null) {
//			if (other.integerCollection != null)
//				return false;
//		} else if (!integerCollection.equals(other.integerCollection))
//			return false;
//		if (integerList == null) {
//			if (other.integerList != null)
//				return false;
//		} else if (!integerList.equals(other.integerList))
//			return false;
//		if (integerMap == null) {
//			if (other.integerMap != null)
//				return false;
//		} else if (!integerMap.equals(other.integerMap))
//			return false;
//		if (integerSet == null) {
//			if (other.integerSet != null)
//				return false;
//		} else if (!integerSet.equals(other.integerSet))
//			return false;
//		if (!Arrays.equals(longArray, other.longArray))
//			return false;
		if (longBoxed == null) {
			if (other.longBoxed != null)
				return false;
		} else if (!longBoxed.equals(other.longBoxed))
			return false;
		if (longPrimitive != other.longPrimitive)
			return false;
//		if (!Arrays.equals(shortArray, other.shortArray))
//			return false;
		if (shortBoxed == null) {
			if (other.shortBoxed != null)
				return false;
		} else if (!shortBoxed.equals(other.shortBoxed))
			return false;
		if (shortPrimitive != other.shortPrimitive)
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComplexObject [booleanPrimitive=");
		builder.append(booleanPrimitive);
		builder.append(", booleanBoxed=");
		builder.append(booleanBoxed);
		builder.append(", atomicBoolean=");
		builder.append(atomicBoolean);
		builder.append(", bytePrimitive=");
		builder.append(bytePrimitive);
		builder.append(", byteBoxed=");
		builder.append(byteBoxed);
		builder.append(", charPrimitive=");
		builder.append(charPrimitive);
		builder.append(", charBoxed=");
		builder.append(charBoxed);
		builder.append(", shortPrimitive=");
		builder.append(shortPrimitive);
		builder.append(", shortBoxed=");
		builder.append(shortBoxed);
		builder.append(", intPrimitive=");
		builder.append(intPrimitive);
		builder.append(", intBoxed=");
		builder.append(intBoxed);
		builder.append(", atomicInteger=");
		builder.append(atomicInteger);
		builder.append(", longPrimitive=");
		builder.append(longPrimitive);
		builder.append(", longBoxed=");
		builder.append(longBoxed);
		builder.append(", atomicLong=");
		builder.append(atomicLong);
		builder.append(", floatPrimitive=");
		builder.append(floatPrimitive);
		builder.append(", floatBoxed=");
		builder.append(floatBoxed);
		builder.append(", doublePrimitive=");
		builder.append(doublePrimitive);
		builder.append(", doubleBoxed=");
		builder.append(doubleBoxed);
		builder.append(", enumeration=");
		builder.append(enumeration);
		builder.append(", string=");
		builder.append(string);
//		builder.append(", booleanArray=");
//		builder.append(Arrays.toString(booleanArray));
//		builder.append(", byteArray=");
//		builder.append(Arrays.toString(byteArray));
//		builder.append(", charArray=");
//		builder.append(Arrays.toString(charArray));
//		builder.append(", shortArray=");
//		builder.append(Arrays.toString(shortArray));
//		builder.append(", intArray=");
//		builder.append(Arrays.toString(intArray));
//		builder.append(", longArray=");
//		builder.append(Arrays.toString(longArray));
//		builder.append(", floatArray=");
//		builder.append(Arrays.toString(floatArray));
//		builder.append(", complexArray=");
//		builder.append(Arrays.toString(complexArray));
//		builder.append(", enumerationArray=");
//		builder.append(Arrays.toString(enumerationArray));
//		builder.append(", integerCollection=");
//		builder.append(integerCollection);
//		builder.append(", integerList=");
//		builder.append(integerList);
//		builder.append(", integerSet=");
//		builder.append(integerSet);
//		builder.append(", integerMap=");
//		builder.append(integerMap);
//		builder.append(", complexObjectCollection=");
//		builder.append(complexObjectCollection);
//		builder.append(", complexObjectList=");
//		builder.append(complexObjectList);
//		builder.append(", complexObjectSet=");
//		builder.append(complexObjectSet);
//		builder.append(", complexObjectMap=");
//		builder.append(complexObjectMap);
		builder.append("]");
		return builder.toString();
	}
}

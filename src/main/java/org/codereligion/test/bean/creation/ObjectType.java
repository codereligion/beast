package org.codereligion.test.bean.creation;

/**
 * TODO
 * 
 * @author sgroebler
 * @since 13.08.2012
 */
public enum ObjectType {

	DEFAULT((byte) 0),
	DIRTY((byte) 1);
	
	private final byte bit;
	
	/**
	 * TODO
	 * 
	 * @param bit
	 */
	private ObjectType(final byte bit) {
		this.bit = bit;
	}
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	protected boolean getBoolean(){
		return bit == 1;
	}
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	protected byte getByte() {
		return bit;
	}
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	protected char getChar() {
		return (char) bit;
	}
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	protected short getShort() {
		return bit;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	protected int getInt() {
		return bit;
	}
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	protected long getLong() {
		return bit;
	}
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	protected float getFloat() {
		return bit;
	}
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	protected double getDouble() {
		return bit;
	}
}

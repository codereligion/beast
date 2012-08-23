package com.codereligion.beast;

/**
 * Provides default instance providers.
 *
 * @author sgroebler
 * @since 23.08.2012
 */
public final class InstanceProviders {
	
	/**
	 * Forbids construction of instances of this class.
	 */
	private InstanceProviders() {
		throw new IllegalAccessError("This class must not be instantiated.");
	}
	
	/**
	 * Constant for a "dirty" value.
	 */
	private static final String ONE = "1";
	
	/**
	 * Constant for a "default" value.
	 */
	private static final String ZERO = "0";
	
	/**
	 * Default instance provider for type {@link Boolean}.
	 */
	public static InstanceProvider<Boolean> BOOLEAN = new InstanceProvider<Boolean>(Boolean.FALSE, Boolean.TRUE);
	
	/**
	 * Default instance provider for type {@link BoByteolean}.
	 */
	public static InstanceProvider<Byte> BYTE = new InstanceProvider<Byte>(Byte.valueOf(ZERO), Byte.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Character}.
	 */
	public static InstanceProvider<Character> CHARACTER = new InstanceProvider<Character>(Character.valueOf((char) 0), Character.valueOf((char) 1));
	
	/**
	 * Default instance provider for type {@link Double}.
	 */
	public static InstanceProvider<Double> DOUBLE = new InstanceProvider<Double>(Double.valueOf(ZERO), Double.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Float}.
	 */
	public static InstanceProvider<Float> FLOAT = new InstanceProvider<Float>(Float.valueOf(ZERO), Float.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Integer}.
	 */
	public static InstanceProvider<Integer> INTEGER = new InstanceProvider<Integer>(Integer.valueOf(ZERO), Integer.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Long}.
	 */
	public static InstanceProvider<Long> LONG = new InstanceProvider<Long>(Long.valueOf(ZERO), Long.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Object}.
	 */
	public static InstanceProvider<Object> OBJECT = new InstanceProvider<Object>(Short.valueOf(ZERO), Short.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Short}.
	 */
	public static InstanceProvider<Short> SHORT = new InstanceProvider<Short>(Short.valueOf(ZERO), Short.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link String}.
	 */
	public static InstanceProvider<String> STRING = new InstanceProvider<String>(String.valueOf(ZERO), String.valueOf(ONE));
}

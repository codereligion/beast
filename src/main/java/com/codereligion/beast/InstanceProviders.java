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

package com.codereligion.beast;

/**
 * Provides default instance providers.
 * TODO maybe add some interface like map, set, list, vector etc. for public api
 *
 * @author sgroebler
 * @since 23.08.2012
 */
final class InstanceProviders {
	
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
	static InstanceProvider<Boolean> BOOLEAN = new InstanceProvider<Boolean>(Boolean.FALSE, Boolean.TRUE);
	
	/**
	 * Default instance provider for type {@link BoByteolean}.
	 */
	static InstanceProvider<Byte> BYTE = new InstanceProvider<Byte>(Byte.valueOf(ZERO), Byte.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Character}.
	 */
	static InstanceProvider<Character> CHARACTER = new InstanceProvider<Character>(Character.valueOf((char) 0), Character.valueOf((char) 1));
	
	/**
	 * Default instance provider for type {@link Double}.
	 */
	static InstanceProvider<Double> DOUBLE = new InstanceProvider<Double>(Double.valueOf(ZERO), Double.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Float}.
	 */
	static InstanceProvider<Float> FLOAT = new InstanceProvider<Float>(Float.valueOf(ZERO), Float.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Integer}.
	 */
	static InstanceProvider<Integer> INTEGER = new InstanceProvider<Integer>(Integer.valueOf(ZERO), Integer.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Long}.
	 */
	static InstanceProvider<Long> LONG = new InstanceProvider<Long>(Long.valueOf(ZERO), Long.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Object}.
	 */
	static InstanceProvider<Object> OBJECT = new InstanceProvider<Object>(Short.valueOf(ZERO), Short.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link Short}.
	 */
	static InstanceProvider<Short> SHORT = new InstanceProvider<Short>(Short.valueOf(ZERO), Short.valueOf(ONE));
	
	/**
	 * Default instance provider for type {@link String}.
	 */
	static InstanceProvider<String> STRING = new InstanceProvider<String>(String.valueOf(ZERO), String.valueOf(ONE));
}

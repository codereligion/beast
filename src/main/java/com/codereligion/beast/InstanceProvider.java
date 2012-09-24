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
 * TODO document
 * TODO test
 *
 * @param <T>
 * @author Sebastian Gröbler
 * @author 20.08.2012
 */
public final class InstanceProvider <T> {
	
	private final T defaultInstance;
	private final T dirtyInstance;
	private final Class<?> instanceClass;
	private final String propertyName;
	
	/**
	 * TODO
	 *
	 * @param <T>
	 * @param defaultInstance
	 * @param dirtyInstance
	 * @return
	 */
	public static <T> InstanceProvider<T> create(final T defaultInstance, final T dirtyInstance) {
		return new InstanceProvider<T>(defaultInstance, dirtyInstance);
	}
	
	/**
	 * TODO
	 *
	 * @param <T>
	 * @param defaultInstance
	 * @param dirtyInstance
	 * @param instanceClass
	 * @return
	 */
	public static <T> InstanceProvider<T> create(
			final T defaultInstance,
			final T dirtyInstance, 
			final Class<T> instanceClass) {
		
		return new InstanceProvider<T>(defaultInstance, dirtyInstance, instanceClass);
	}
	
	/**
	 * TODO
	 *
	 * @param <T>
	 * @param defaultInstance
	 * @param dirtyInstance
	 * @param propertyName
	 * @return
	 */
	public static <T> InstanceProvider<T> create(final T defaultInstance, final T dirtyInstance, final String propertyName) {
		return new InstanceProvider<T>(defaultInstance, dirtyInstance, propertyName);
	}
	
	/**
	 * TODO
	 *
	 * @param <T>
	 * @param defaultInstance
	 * @param dirtyInstance
	 * @param instanceClass
	 * @param propertyName
	 * @return
	 */
	public static <T> InstanceProvider<T> create(
			final T defaultInstance,
			final T dirtyInstance,
			final Class<T> instanceClass,
			final String propertyName) {
		return new InstanceProvider<T>(defaultInstance, dirtyInstance, instanceClass, propertyName);
	}

	
    InstanceProvider(final T defaultInstance, final T dirtyInstance) {
		this(defaultInstance, dirtyInstance, (String) null);
	}

    InstanceProvider(final T defaultInstance, final T dirtyInstance, final String propertyName) {
    	if (defaultInstance == null) {
    		throw new IllegalArgumentException("defaultInstance must not be null.");
    	}
    	
    	if (dirtyInstance == null) {
    		throw new IllegalArgumentException("dirtyInstance must not be null.");
    	}
    	
    	if (!dirtyInstance.getClass().equals(defaultInstance.getClass())) {
    		throw new IllegalArgumentException("defaultInstance and dirtyInstance must be instances of the same class.");
    	}
    	
    	if (defaultInstance.getClass().isArray() || defaultInstance.getClass().isArray()) {
    		throw new IllegalArgumentException("Arrays are not supported for custom instances. " +
    		"Provide a custom instance for the arrays component type instead.");
    	}
    	
    	if (dirtyInstance.equals(defaultInstance)) {
    		throw new IllegalArgumentException("defaultInstance and dirtyInstance must not be equal.");
    	}
    	
    	if (dirtyInstance.toString().equals(defaultInstance.toString())) {
    		throw new IllegalArgumentException("toString result of defaultInstance and dirtyInstance must not be equal.");
    	}
    	
    	if (dirtyInstance.hashCode() == defaultInstance.hashCode()) {
    		throw new IllegalArgumentException("hashCodes of defaultInstance and dirtyInstance must not be equal.");
    	}
    	
    	this.defaultInstance = defaultInstance;
    	this.dirtyInstance = dirtyInstance;
    	this.instanceClass = defaultInstance.getClass();
    	this.propertyName = propertyName;
    }

    InstanceProvider(final T defaultInstance, final T dirtyInstance, final Class<T> instanceClass) {
    	this(defaultInstance, dirtyInstance, instanceClass, null);
    }
    
    InstanceProvider(final T defaultInstance, final T dirtyInstance, final Class<T> instanceClass, final String propertyName) {
    	if (defaultInstance == null) {
    		throw new IllegalArgumentException("defaultInstance must not be null.");
    	}
    	
    	if (dirtyInstance == null) {
    		throw new IllegalArgumentException("dirtyInstance must not be null.");
    	}
    	
    	if (instanceClass == null) {
    		throw new IllegalArgumentException("instanceClass must not be null.");
    	}
    	
    	if (!dirtyInstance.getClass().equals(defaultInstance.getClass())) {
    		throw new IllegalArgumentException("defaultInstance and dirtyInstance must be instances of the same class.");
    	}
    	
    	if (!instanceClass.isAssignableFrom(defaultInstance.getClass())) {
    		throw new IllegalArgumentException("defaultInstance is not an instance of instanceClass.");
    	}
    	
    	if (!instanceClass.isAssignableFrom(dirtyInstance.getClass())) {
    		throw new IllegalArgumentException("dirtyInstance is not an instance of instanceClass.");
    	}
    	
    	if (defaultInstance.getClass().isArray() || defaultInstance.getClass().isArray()) {
    		throw new IllegalArgumentException("Arrays are not supported for custom instances. " +
    		"Provide a custom instance for the arrays component type instead.");
    	}
    	
    	if (dirtyInstance.equals(defaultInstance)) {
    		throw new IllegalArgumentException("defaultInstance and dirtyInstance must not be equal.");
    	}
    	
    	if (dirtyInstance.toString().equals(defaultInstance.toString())) {
    		throw new IllegalArgumentException("toString result of defaultInstance and dirtyInstance must not be equal.");
    	}
    	
    	if (dirtyInstance.hashCode() == defaultInstance.hashCode()) {
    		throw new IllegalArgumentException("hashCodes of defaultInstance and dirtyInstance must not be equal.");
    	}
    	
    	this.defaultInstance = defaultInstance;
    	this.dirtyInstance = dirtyInstance;
    	this.instanceClass = instanceClass;
    	this.propertyName = propertyName;
    }
    	

	/**
	 * TODO
	 *
	 * @return
	 */
	public T getDirtyObject() {
		return this.dirtyInstance;
	}

	/**
	 * TODO
	 *
	 * @return
	 */
	public T getDefaultObject() {
		return this.defaultInstance;
	}

	/**
	 * TODO
	 *
	 * @return
	 */
	public Class<? extends T> getInstanceClass() {
		@SuppressWarnings("unchecked")
		final Class<? extends T> returnValue = (Class<? extends T>) this.instanceClass;
		return returnValue;
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	public String getPropertyName() {
		return this.propertyName;
	}

	/**
	 * The hashCode is based only on the {@code instanceClass} which is the common
	 * type of {@code dirtyInstance} and {@code defaultInstance}.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.instanceClass.hashCode();
		result = prime * result + ((this.propertyName == null) ? 0 : this.propertyName.hashCode());
		return result;
	}

	/**
	 * Two instances of this class are considered equal, when they are either
	 * the same instance or their {@code instanceClass} which is the common type
	 * of {@code dirtyInstance} and {@code defaultInstance} is equal and their
	 * {@code propertyName} is equal.
	 */
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    
	    @SuppressWarnings("unchecked")
        final InstanceProvider<T> other = (InstanceProvider<T>) obj;
	    if (!this.instanceClass.equals(other.instanceClass))
		    return false;
	    if (this.propertyName == null) {
		    if (other.propertyName != null)
			    return false;
	    } else if (!this.propertyName.equals(other.propertyName))
		    return false;
	    return true;
    }
	
	@Override
    public String toString() {
	    final StringBuilder builder = new StringBuilder();
	    builder.append("CustomInstanceProvider [defaultInstance=");
	    builder.append(this.defaultInstance);
	    builder.append(", dirtyInstance=");
	    builder.append(this.dirtyInstance);
	    builder.append(", instanceClass=");
	    builder.append(this.instanceClass);
	    builder.append(", propertyName=");
	    builder.append(this.propertyName);
	    builder.append("]");
	    return builder.toString();
    }
}
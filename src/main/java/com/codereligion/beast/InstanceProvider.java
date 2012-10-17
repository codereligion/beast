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
 * Use this class to construct custom instance providers which the test can use to
 * set "default" and "dirty" values in order to define state on the object of the
 * class under test. Instance providers can be used to provide instances for classes
 * of which the instantiation may not be supported or may not be possible.
 * 
 * <p>
 * An instance provider can be specific to a concrete class, an interface or abstract
 * class and it can be constructed so that it is only applied for a specific property.
 * 
 * <p>
 * TODO re-evaluate that
 * TODO what about nested arrays?
 * Custom instance providers for arrays are not supported, because array initialization
 * is handled by the framework. However you can provide a custom instance provider for
 * the component type of the array and the framework will do the rest.
 * 
 * @author Sebastian Gröbler
 * @author 20.08.2012
 */
public final class InstanceProvider {
	
	/**
	 * Stores the "default" instance to provide.
	 */
	private final Object defaultInstance;
	
	/**
	 * Stores the "dirty" instance to provide.
	 */
	private final Object dirtyInstance;
	
	/**
	 * Stores either the explicitly provided {@code instanceClass} or the common
	 * class of the specified {@code defaultInstance} and {@code dirtyInstance}.
	 * 
	 * <p>
	 * In case the {@code instanceClass} has been provided explicitly it must not
	 * necessarily be the same class as the class of the provided instances, but
	 * the classes of the instances must at least be sub-classes of the specified
	 * {@code instanceClass}.
	 */
	private final Class<?> instanceClass;
	
	/**
	 * The name of the property this instance provider should provider for.
	 * May be {@code null}, which indicates that this instance provider is not
	 * specific to a property.
	 */
	private final String propertyName;
	
	/**
	 * Creates a new {@link InstanceProvider} for the given {@code defaultInstance} and
	 * {@code dirtyInstance}.
	 * 
	 * <p>
	 * The instances must be of the same class and must represent distinct objects, which
	 * differ in their toString, hashCode and equals result.
	 * 
	 * <p>
	 * The resulting provider will be used for all properties of the type which is the common
	 * class of the given instances.
	 *
	 * @param defaultInstance	the "default" instance to provide
     * @param dirtyInstance		the "dirty" instance to provide
     * @throws NullPointerException		when the given {@code defaultInstance} or {@code dirtyInstance} are {@code null}
     * @throws IllegalArgumentException when the given {@code defaultInstance} and {@code dirtyInstance}
     * 									are not of the same class, or the given instances do not differ in their toString,
     * 									hashCode and equals results, or are array types
     */
	public static InstanceProvider create(final Object defaultInstance, final Object dirtyInstance) {
		return new InstanceProvider(defaultInstance, dirtyInstance, null);
	}

	/**
	 * Creates a new {@link InstanceProvider} for the given {@code defaultInstance},
	 * {@code dirtyInstance} and {@code propertyName}.
	 * 
	 * <p>
	 * The instances must be of the same class and must represent distinct objects, which
	 * differ in their toString, hashCode and equals result.
	 * 
	 * <p>
	 * The resulting provider will be used only for the property defined by the given
	 * {@code propertyName} which matches the common class of the given instances.
	 *
     * @param defaultInstance	the "default" instance to provide
     * @param dirtyInstance		the "dirty" instance to provide
     * @param propertyName		the name of the property this instance provider should provide for
     * @throws NullPointerException		when the given {@code defaultInstance}, {@code dirtyInstance} or {@code propertyName}
     * 									are {@code null}
     * @throws IllegalArgumentException when the given {@code defaultInstance} and {@code dirtyInstance}
     * 									are not of the same class, or the given instances do not differ in their toString,
     * 									hashCode and equals results, or are array types
     */
	public static InstanceProvider create(
			final Object defaultInstance,
			final Object dirtyInstance,
			final String propertyName) {
		
		if (propertyName == null) {
			throw new NullPointerException("propertyName must not be null.");
		}
		
		return new InstanceProvider(defaultInstance, dirtyInstance, propertyName);
	}
	
	/**
     * Creates a new {@link InstanceProvider} for the given {@code defaultInstance} and
	 * {@code dirtyInstance} and {@code instanceClass}.
	 * 
	 * <p>
	 * The instances must be of the same class and must represent distinct objects, which
	 * differ in their toString, hashCode and equals result.
	 * 
	 * <p>
	 * The resulting provider will be used for all properties of the type which is the common
	 * class of the given instances.
	 * 
	 * <p>
	 * The instances must be equal or sub-classes to the given {@code instanceClass}.
     *
     * @param defaultInstance	the "default" instance to provide
     * @param dirtyInstance		the "dirty" instance to provide
     * @param instanceClass 	the {@link Class} to which this instance provider should provide for
     * @throws NullPointerException		when the given {@code defaultInstance}, {@code dirtyInstance}
     * 									or {@code instanceClass} are {@code null}
     * @throws IllegalArgumentException when the given {@code defaultInstance} and {@code dirtyInstance}
     * 									are not of the same class and are not instances of the given
     * 									{@code instanceClass}, or the given instances do not differ in their toString,
     * 									hashCode and equals results, or are array types
     */
	public static InstanceProvider create(
			final Object defaultInstance,
			final Object dirtyInstance, 
			final Class<?> instanceClass) {
		
		return new InstanceProvider(defaultInstance, dirtyInstance, instanceClass, null);
	}
	
	/**
     * Creates a new {@link InstanceProvider} for the given {@code defaultInstance} and
	 * {@code dirtyInstance}, {@code instanceClass} and {@code propertyName}.
	 * 
	 * <p>
	 * The instances must be of the same class and must represent distinct objects, which
	 * differ in their toString, hashCode and equals result.
	 * 
	 * <p>
	 * The resulting provider will be used only for the property defined by the given
	 * {@code propertyName} which matches the specified {@code instanceClass}.
	 * 
	 * <p>
	 * The instances must be equal or sub-classes to the given {@code instanceClass}.
     *
     * @param defaultInstance	the "default" instance to provide
     * @param dirtyInstance		the "dirty" instance to provide
     * @param instanceClass 	the {@link Class} to which this instance provider should provide for
     * @param propertyName 		the name of the property this instance provider should provide for
     * @throws NullPointerException		when the given {@code defaultInstance}, {@code dirtyInstance},
     * 									{@code propertyName} or {@code instanceClass} are {@code null}
     * @throws IllegalArgumentException when the given {@code defaultInstance} and {@code dirtyInstance}
     * 									are not of the same class and are not instances of the given
     * 									{@code instanceClass}, or the given instances do not differ in their toString,
     * 									hashCode and equals results, or are array types
     */
	public static InstanceProvider create(
			final Object defaultInstance,
			final Object dirtyInstance,
			final Class<?> instanceClass,
			final String propertyName) {
		
		if (propertyName == null) {
			throw new NullPointerException("propertyName must not be null.");
		}
		
		return new InstanceProvider(defaultInstance, dirtyInstance, instanceClass, propertyName);
	}
	
    /**
     * Private constructor to create a new instance of this class for the given parameters.
     *
     * @param defaultInstance	the "default" instance to provide
     * @param dirtyInstance		the "dirty" instance to provide
     * @param propertyName		the name of the property this instance provider should provide for, may be {@code null}
     * 							to indicate, that this instance provider is not property specific
     * @throws NullPointerException		when the given {@code defaultInstance} or {@code dirtyInstance} are {@code null}
     * @throws IllegalArgumentException when the given {@code defaultInstance} and {@code dirtyInstance}
     * 									are not of the same class, or the given instances do not differ in their toString,
     * 									hashCode and equals results, or are array types
     */
    private InstanceProvider(
    		final Object defaultInstance,
    		final Object dirtyInstance,
    		final String propertyName) {
    	
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

    /**
     * Private constructor to create a new instance of this class for the given parameters.
     *
     * @param defaultInstance	the "default" instance to provide
     * @param dirtyInstance		the "dirty" instance to provide
     * @param instanceClass 	the {@link Class} to which this instance provider should provide for
     * @param propertyName		the name of the property this instance provider should provide for, may be {@code null}
     * 							to indicate, that this instance provider is not property specific
     * @throws NullPointerException		when the given {@code defaultInstance}, {@code dirtyInstance}
     * 									or {@code instanceClass} are {@code null}
     * @throws IllegalArgumentException when the given {@code defaultInstance} and {@code dirtyInstance}
     * 									are not of the same class and are not instances of the given
     * 									{@code instanceClass}, or the given instances do not differ in their toString,
     * 									hashCode and equals results, or are array types
     */
    private InstanceProvider(
    		final Object defaultInstance,
    		final Object dirtyInstance,
    		final Class<?> instanceClass,
    		final String propertyName) {
    	
    	if (defaultInstance == null) {
    		throw new NullPointerException("defaultInstance must not be null.");
    	}
    	
    	if (dirtyInstance == null) {
    		throw new NullPointerException("dirtyInstance must not be null.");
    	}
    	
    	if (instanceClass == null) {
    		throw new NullPointerException("instanceClass must not be null.");
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
     * The {@code dirtyInstance} provided on construction of this object.
     *
     * @return the {@link Object} which represents the {@code dirtyInstance}
     */
	public Object getDirtyInstance() {
		return this.dirtyInstance;
	}

	/**
	 * The {@code defaultInstance} provided on construction of this object.
	 *
	 * @return the {@link Object} which represents the {@code defaultInstance}
	 */
	public Object getDefaultInstance() {
		return this.defaultInstance;
	}

	/**
	 * The {@link Class} which is the type of the specified {@code defaultInstance}
	 * and {@code dirtyInstnace}.
	 *
	 * @return a {@link Class} object
	 */
	public Class<?> getInstanceClass() {
		return this.instanceClass;
	}
	
	/**
	 * The name of the property to which this instance provider should provide.
	 * This may be {@code null}, which indicates that this instance provider
	 * is not specific to a propertyName.
	 *
	 * @return
	 * @see {@link InstanceProvider #isPropertySpecific}
	 */
	public String getPropertyName() {
		return this.propertyName;
	}
	
	/**
	 * Determines whether this instance provider provides for a specific property
	 * or for all properties which match the given {@code defaultInstance}'s / {@code dirtyInstance}'s
	 * {@link Class}. 
	 * 
	 * @return true if this instance provides for a specific property
	 * @see {@link InstanceProvider #getPropertyName}
	 */
	public boolean isPropertySpecific() {
		return this.propertyName != null;
	}

	/**
	 * The hashCode is based only on the {@code propertyName} and the {@code instanceClass}
	 * which is the common type of {@code dirtyInstance} and {@code defaultInstance}.
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
	 * Two instances of this class are considered equal, when they are the same instance
	 * or their {@code propertyName} and {@code instanceClass} which is the common type
	 * of {@code dirtyInstance} and {@code defaultInstance} are equal.
	 */
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    
        final InstanceProvider other = (InstanceProvider) obj;
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
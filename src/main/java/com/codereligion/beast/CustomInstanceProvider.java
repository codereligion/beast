package com.codereligion.beast;

/**
 * TODO document
 * TODO test
 *
 * @param <T>
 * @author Sebastian Gröbler
 * @author 20.08.2012
 */
public class CustomInstanceProvider <T> implements InstanceProvider<T> {
	
	private final T defaultInstance;
	private final T dirtyInstance;
	private final Class<?> instanceClass;
	
	/**
	 * TODO document
	 * TODO what about enums with just one enumeration value?
	 * TODO what about interfaces?
	 * TODO what about abstract classes?
	 * 
	 * Constructs a new instance.
	 *
	 * @param defaultInstance
	 * @param dirtyInstance
	 * @throws IllegalArgumentException
	 */
    public CustomInstanceProvider(final T defaultInstance, final T dirtyInstance) {
    	
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
	}

    /**
     * TODO document
     * Constructs an instance.
     *
     * @param defaultInstance
     * @param dirtyInstance
     * @param instanceClass
     */
    public CustomInstanceProvider(final T defaultInstance, final T dirtyInstance, final Class<?> instanceClass) {
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
    }

    @Override
	public T getDirtyObject() {
		return this.dirtyInstance;
	}

	@Override
	public T getDefaultObject() {
		return this.defaultInstance;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<? extends T> getInstanceClass() {
		return (Class<? extends T>) this.instanceClass;
	}
}
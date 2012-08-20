package org.codereligion.beast;

/**
 * TODO document
 * TODO test
 *
 * @param <T>
 * @author sgroebler
 * @author 20.08.2012
 */
public class CustomInstanceProvider <T> implements InstanceProvider<T> {
	
	private final T defaultInstance;
	private final T dirtyInstance;
	
	/**
	 * TODO
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
		
		if (dirtyInstance.getClass().equals(defaultInstance.getClass())) {
			throw new IllegalArgumentException("defaultInstance and dirtyInstance must be instances of the same class.");
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
	}

	@Override
	public T getDirtyObject() {
		return defaultInstance;
	}

	@Override
	public T getDefaultObject() {
		return dirtyInstance;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<T> getInstanceClass() {
		return (Class<T>) defaultInstance.getClass();
	}
}
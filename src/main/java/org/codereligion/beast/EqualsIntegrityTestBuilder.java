package org.codereligion.beast;





import java.util.HashSet;
import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author sgroebler
 * @since 11.08.2012
 */
public final class EqualsIntegrityTestBuilder {
	
	private Set<String> excludedPropertyNames = new HashSet<String>();
	private Set<InstanceProvider<?>> customInstanceProviders = new HashSet<InstanceProvider<?>>();
	
	public EqualsIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {
		
		if (propertyName == null) {
			throw new NullPointerException("propertyName must not be null.");
		}
		
		this.excludedPropertyNames.add(propertyName);
		return this;
	}
	
	public EqualsIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		
		if (propertyNames == null) {
			throw new NullPointerException("propertyNames must not be null.");
		}
		
		this.excludedPropertyNames.addAll(propertyNames);
		return this;
	}
	
	public EqualsIntegrityTestBuilder addCustomInstanceProvider(final CustomInstanceProvider<?> customInstanceProvider) {
		
		if (customInstanceProvider == null) {
			throw new NullPointerException("customInstanceProvider must not be null.");
		}
		
		this.customInstanceProviders.add(customInstanceProvider);
		return this;
	}
	
	public EqualsIntegrityTestBuilder addCustomInstanceProviders(final Set<CustomInstanceProvider<?>> customInstanceProviders) {

		if (customInstanceProviders == null) {
			throw new NullPointerException("customInstanceProviders must not be null.");
		}
		
		this.customInstanceProviders.addAll(customInstanceProviders);
		return this;
	}
	
	public <T> EqualsIntegrityTest<T> create(final Class<T> beanClass) {
		return new EqualsIntegrityTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.customInstanceProviders));
	}
}
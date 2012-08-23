package com.codereligion.beast;

import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class EqualsIntegrityTestBuilder extends AbstractTestBuilder {
	
	@Override
	public EqualsIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {
		return (EqualsIntegrityTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public EqualsIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (EqualsIntegrityTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public EqualsIntegrityTestBuilder addCustomInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (EqualsIntegrityTestBuilder) super.addCustomInstanceProvider(instanceProvider);
	}
	
	@Override
	public EqualsIntegrityTestBuilder addCustomInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (EqualsIntegrityTestBuilder) super.addCustomInstanceProviders(instanceProviders);
	}
	
	public <T> EqualsIntegrityTest<T> create(final Class<T> beanClass) {
		return new EqualsIntegrityTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.customInstanceProviders));
	}
}
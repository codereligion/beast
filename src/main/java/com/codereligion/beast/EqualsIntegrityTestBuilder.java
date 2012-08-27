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
	public EqualsIntegrityTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (EqualsIntegrityTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public EqualsIntegrityTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (EqualsIntegrityTestBuilder) super.addInstanceProviders(instanceProviders);
	}
	
	public <T> EqualsIntegrityTest<T> create(final Class<T> beanClass) {
		return new EqualsIntegrityTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.instanceProviders));
	}
}
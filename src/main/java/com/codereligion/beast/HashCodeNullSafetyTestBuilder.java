package com.codereligion.beast;




import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class HashCodeNullSafetyTestBuilder extends AbstractTestBuilder {

	@Override
	public HashCodeNullSafetyTestBuilder addExcludedPropertyName(final String propertyName) {
		return (HashCodeNullSafetyTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public HashCodeNullSafetyTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (HashCodeNullSafetyTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public HashCodeNullSafetyTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (HashCodeNullSafetyTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public HashCodeNullSafetyTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (HashCodeNullSafetyTestBuilder) super.addInstanceProviders(instanceProviders);
	}
	
	public <T> HashCodeNullSafetyTest<T> create(final Class<T> beanClass) {
		return new HashCodeNullSafetyTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.instanceProviders));
	}
}
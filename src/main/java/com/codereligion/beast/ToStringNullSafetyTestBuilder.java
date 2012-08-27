package com.codereligion.beast;




import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringNullSafetyTestBuilder extends AbstractTestBuilder {

	@Override
	public ToStringNullSafetyTestBuilder addExcludedPropertyName(final String propertyName) {
		return (ToStringNullSafetyTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public ToStringNullSafetyTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (ToStringNullSafetyTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public ToStringNullSafetyTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (ToStringNullSafetyTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public ToStringNullSafetyTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (ToStringNullSafetyTestBuilder) super.addInstanceProviders(instanceProviders);
	}
	
	public <T> ToStringNullSafetyTest<T> create(final Class<T> beanClass) {
		return new ToStringNullSafetyTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.instanceProviders));
	}
}
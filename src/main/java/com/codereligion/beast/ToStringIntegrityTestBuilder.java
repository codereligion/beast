package com.codereligion.beast;




import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringIntegrityTestBuilder extends AbstractTestBuilder {

	@Override
	public ToStringIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {
		return (ToStringIntegrityTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public ToStringIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (ToStringIntegrityTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public ToStringIntegrityTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (ToStringIntegrityTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public ToStringIntegrityTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (ToStringIntegrityTestBuilder) super.addInstanceProviders(instanceProviders);
	}
	
	public <T> ToStringIntegrityTest<T> create(final Class<T> beanClass) {
		return new ToStringIntegrityTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.instanceProviders));
	}
}
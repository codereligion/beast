package org.codereligion.beast;




import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author sgroebler
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
	public HashCodeNullSafetyTestBuilder addCustomInstanceProvider(final CustomInstanceProvider<?> instanceProvider) {
		return (HashCodeNullSafetyTestBuilder) super.addCustomInstanceProvider(instanceProvider);
	}
	
	@Override
	public HashCodeNullSafetyTestBuilder addCustomInstanceProviders(final Set<CustomInstanceProvider<?>> instanceProviders) {
		return (HashCodeNullSafetyTestBuilder) super.addCustomInstanceProviders(instanceProviders);
	}
	
	public <T> HashCodeNullSafetyTest<T> create(final Class<T> beanClass) {
		return new HashCodeNullSafetyTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.customInstanceProviders));
	}
}
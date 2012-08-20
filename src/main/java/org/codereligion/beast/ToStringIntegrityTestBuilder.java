package org.codereligion.beast;




import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author sgroebler
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
	public ToStringIntegrityTestBuilder addCustomInstanceProvider(final CustomInstanceProvider<?> instanceProvider) {
		return (ToStringIntegrityTestBuilder) super.addCustomInstanceProvider(instanceProvider);
	}
	
	@Override
	public ToStringIntegrityTestBuilder addCustomInstanceProviders(final Set<CustomInstanceProvider<?>> instanceProviders) {
		return (ToStringIntegrityTestBuilder) super.addCustomInstanceProviders(instanceProviders);
	}
	
	public <T> ToStringIntegrityTest<T> create(final Class<T> beanClass) {
		return new ToStringIntegrityTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.customInstanceProviders));
	}
}
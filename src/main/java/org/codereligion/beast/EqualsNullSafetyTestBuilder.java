package org.codereligion.beast;




import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author sgroebler
 * @since 11.08.2012
 */
public final class EqualsNullSafetyTestBuilder extends AbstractTestBuilder {
	
	@Override
	public EqualsNullSafetyTestBuilder addExcludedPropertyName(final String propertyName) {
		return (EqualsNullSafetyTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public EqualsNullSafetyTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (EqualsNullSafetyTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public EqualsNullSafetyTestBuilder addCustomInstanceProvider(final CustomInstanceProvider<?> instanceProvider) {
		return (EqualsNullSafetyTestBuilder) super.addCustomInstanceProvider(instanceProvider);
	}
	
	@Override
	public EqualsNullSafetyTestBuilder addCustomInstanceProviders(final Set<CustomInstanceProvider<?>> instanceProviders) {
		return (EqualsNullSafetyTestBuilder) super.addCustomInstanceProviders(instanceProviders);
	}
	
	public <T> EqualsNullSafetyTest<T> create(final Class<T> beanClass) {
		return new EqualsNullSafetyTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.customInstanceProviders));
	}
}
package org.codereligion.beast;




import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class HashCodeIntegrityTestBuilder extends AbstractTestBuilder {

	@Override
	public HashCodeIntegrityTestBuilder addExcludedPropertyName(final String propertyName) {
		return (HashCodeIntegrityTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public HashCodeIntegrityTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (HashCodeIntegrityTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public HashCodeIntegrityTestBuilder addCustomInstanceProvider(final CustomInstanceProvider<?> instanceProvider) {
		return (HashCodeIntegrityTestBuilder) super.addCustomInstanceProvider(instanceProvider);
	}
	
	@Override
	public HashCodeIntegrityTestBuilder addCustomInstanceProviders(final Set<CustomInstanceProvider<?>> instanceProviders) {
		return (HashCodeIntegrityTestBuilder) super.addCustomInstanceProviders(instanceProviders);
	}
	
	public <T> HashCodeIntegrityTest<T> create(final Class<T> beanClass) {
		return new HashCodeIntegrityTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.customInstanceProviders));
	}
}
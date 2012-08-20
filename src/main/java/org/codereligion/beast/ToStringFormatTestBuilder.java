package org.codereligion.beast;




import java.util.Set;
import java.util.regex.Pattern;



/**
 * TODO document
 * TODO test null check
 *
 * @author sgroebler
 * @since 11.08.2012
 */
public final class ToStringFormatTestBuilder extends AbstractTestBuilder {
	
	@Override
	public ToStringFormatTestBuilder addExcludedPropertyName(final String propertyName) {
		return (ToStringFormatTestBuilder) super.addExcludedPropertyName(propertyName);
	}
	
	@Override
	public ToStringFormatTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		return (ToStringFormatTestBuilder) super.addExcludedPropertyNames(propertyNames);
	}
	
	@Override
	public ToStringFormatTestBuilder addCustomInstanceProvider(final CustomInstanceProvider<?> instanceProvider) {
		return (ToStringFormatTestBuilder) super.addCustomInstanceProvider(instanceProvider);
	}
	
	@Override
	public ToStringFormatTestBuilder addCustomInstanceProviders(final Set<CustomInstanceProvider<?>> instanceProviders) {
		return (ToStringFormatTestBuilder) super.addCustomInstanceProviders(instanceProviders);
	}
	
	public <T> ToStringFormatTest<T> create(final Class<T> beanClass, final Pattern pattern) {
		return new ToStringFormatTest<T>(beanClass, this.excludedPropertyNames, new ObjectFactory(this.customInstanceProviders), pattern);
	}
}
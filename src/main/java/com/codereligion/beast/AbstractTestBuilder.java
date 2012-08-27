package com.codereligion.beast;

import java.util.HashSet;
import java.util.Set;



/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
abstract class AbstractTestBuilder {
	
	protected Set<String> excludedPropertyNames = new HashSet<String>();
	protected Set<InstanceProvider<?>> instanceProviders = new HashSet<InstanceProvider<?>>();
	
	protected AbstractTestBuilder addExcludedPropertyName(final String propertyName) {
		
		if (propertyName == null) {
			throw new NullPointerException("propertyName must not be null.");
		}
		
		this.excludedPropertyNames.add(propertyName);
		return this;
	}
	
	protected AbstractTestBuilder addExcludedPropertyNames(final Set<String> propertyNames) {
		
		if (propertyNames == null) {
			throw new NullPointerException("propertyNames must not be null.");
		}
		
		this.excludedPropertyNames.addAll(propertyNames);
		return this;
	}
	
	protected AbstractTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		
		if (instanceProvider == null) {
			throw new NullPointerException("instanceProvider must not be null.");
		}
		
		this.instanceProviders.add(instanceProvider);
		return this;
	}
	
	protected AbstractTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {

		if (instanceProviders == null) {
			throw new NullPointerException("instanceProviders must not be null.");
		}
		
		this.instanceProviders.addAll(instanceProviders);
		return this;
	}
}
/*
 * Copyright 2012 The Beast Authors (www.codereligion.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codereligion.beast;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Set;


/**
 * TODO update documentation
 * Tests the hashCode implementation of a java bean.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
final class HashCodeIntegrityTest <T> extends AbstractTest<T> {
	
	/**
	 * TODO
	 * Constructs an instance.
	 *
	 * @param beanClass
	 * @param excludedPropertyNames
	 */
	HashCodeIntegrityTest(
			final Class<T> beanClass,
			final Set<String> excludedPropertyNames,
			final ObjectFactory objectFactory) {
		
		super(beanClass, excludedPropertyNames, objectFactory);

        if (!isMethodImplemented(ObjectMethodNames.HASH_CODE)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement hashCode.");
        }
	}

	@Override
	public void run() {
		final T defaultObject = newBeanObject();
		
		for (final PropertyDescriptor property : this.settableProperties) {
			
			final String propertyName = property.getName();
			final Class<?> propertyType = property.getPropertyType();
			final Method setter = property.getWriteMethod();
			final T dirtyObject = newBeanObject();
			final Object dirtyProperty = this.objectFactory.getDirtyObject(propertyType, propertyName);
			
			setValue(dirtyObject, setter, dirtyProperty);
			
			final boolean areEqual = defaultObject.equals(dirtyObject);
			final boolean hashCodesAreEqual = defaultObject.hashCode() == dirtyObject.hashCode();
			final boolean isEqualsHashCodeContractViolated = areEqual && !hashCodesAreEqual;
			
			// hashCode and equals contract must not be violated, disregarding excludes
			assertFalse(isEqualsHashCodeContractViolated,
						"If the property '%s' is different in two instances, these two " +
						"instances are equal according to the equals method, but their hashCodes " +
						"are different. This is a violation of the equals/hashCode contract.",
						propertyName);
			
			final boolean isExcluded = this.excludedPropertyNames.contains(propertyName);
			final boolean isUnnecessarilyExcluded = !hashCodesAreEqual && isExcluded;
			
			assertFalse(isUnnecessarilyExcluded,
						"The property '%s' is contained the excludedPropertyNames, but is actually " +
						"supported by the hashCode implementation. Either remove it from the " +
						"excludedPropertyNames or the hashCode implementation.",
						propertyName);
			
			final boolean isUnintentionallyMissing = hashCodesAreEqual && !isExcluded;
			
			assertFalse(isUnintentionallyMissing,
						"The property '%s' may not be included in the hashCode implementation or the generated hashCode " +
						"may be to weak to reflect changes in this property. If the property is not included and this is " +
						"intentional add it to the excludedPropertyNames.",
						propertyName);
		}
	}
}
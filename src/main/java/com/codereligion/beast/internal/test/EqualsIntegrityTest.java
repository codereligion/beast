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

package com.codereligion.beast.internal.test;

import static com.codereligion.beast.internal.util.Assert.assertFalse;
import static com.codereligion.beast.internal.util.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.creation.ObjectMethodNames;
import com.codereligion.beast.internal.test.strategy.EqualsIntegrityExcludeStrategy;
import com.codereligion.beast.internal.test.strategy.EqualsIntegrityIncludeStrategy;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import java.beans.PropertyDescriptor;


/**
 * Tests the equals implementation of the class under test for the following criteria:
 * 
 * <ul>
 * <li> the method must be implemented
 * <li> calling equals with {@code null} must always be false
 * <li> calling equals on the same instance must always be true
 * <li> calling on two different instances must be symmetric
 * <li> calling equals on instances with different property values set must behave as specified in the given {@link IntegrityStrategy}
 * </ul>
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 * @see EqualsIntegrityExcludeStrategy
 * @see EqualsIntegrityIncludeStrategy
 */
public final class EqualsIntegrityTest<T> extends AbstractIntegrityTest<T> {

	/**
	 * Constructs a new instance of this test for the given {@code beanClass}
	 * using the given {@code objectFactory} and {@code integrityStrategy}.
	 *
	 * @param beanClass the {@link Class} to test
	 * @param objectFactory the {@link ObjectFactory} to use
	 * @param integrityStrategy the {@link IntegrityStrategy} to use
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 */
    public EqualsIntegrityTest(
    		final Class<T> beanClass,
    		final ObjectFactory objectFactory,
    		final IntegrityStrategy integrityStrategy) {
    	
	    super(beanClass, objectFactory, integrityStrategy);

        if (!isMethodImplemented(ObjectMethodNames.EQUALS)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement equals.");
        }
    }

	@Override
    public void run() {
        final T defaultObject = newBeanObject();
        
        assertFalse(defaultObject.equals(null),
				"Equals method for instances of %s is equals to null.",
				this.beanClassCanonicalName);

        assertTrue(defaultObject.equals(defaultObject),
        		"Equals method for instances of %s is not reflexive.",
        		this.beanClassCanonicalName);

        for (final PropertyDescriptor property : this.settableProperties) {

            final String propertyName = property.getName();
            final T dirtyObject = newBeanObject();
            final Class<?> propertyType = property.getPropertyType();
            final Object dirtyValue = this.objectFactory.getDirtyObject(propertyType, propertyName);

            try {
	            setValue(dirtyObject, property, dirtyValue);
	            
	            final boolean defaultObjectEqualsDirtyObject = defaultObject.equals(dirtyObject);
	            final boolean dirtyObjectEqualsDefaultObject = dirtyObject.equals(defaultObject);
	            final boolean isNotSymmetric = defaultObjectEqualsDirtyObject != dirtyObjectEqualsDefaultObject;
	            
	            assertFalse(isNotSymmetric,
	            		"Equals method for instances of %s is not symmetric for property '%s'.",
	            		this.beanClassCanonicalName,
	            		propertyName);
	            
	            this.integrityStrategy.apply(defaultObject, dirtyObject, propertyName);
            } catch (final InvocationTargetException e) {
            	handlePropertySetterExcetion(property, e);
            }
        }
    }

	@Override
    public boolean equals(final Object obj) {
	    if (this == obj) {
		    return true;
	    }
	    if (obj == null) {
		    return false;
	    }
	    if (getClass() != obj.getClass()) {
		    return false;
	    }
	    
	    @SuppressWarnings("rawtypes")
        final EqualsIntegrityTest other = (EqualsIntegrityTest) obj;
	    
	    if (!this.beanClass.equals(other.beanClass)) {
		    return false;
	    } 
	    if (!this.integrityStrategy.equals(other.integrityStrategy)) {
	    	return false;
	    } 
	    if (!this.beanClassCanonicalName.equals(other.beanClassCanonicalName)) {
		    return false;
	    } 
	    if (!this.objectFactory.equals(other.objectFactory)) {
		    return false;
	    } 
	    if (!this.settableProperties.equals(other.settableProperties)) {
		    return false;
	    }
    	return true;
    }

	@Override
    public String toString() {
	    final StringBuilder builder = new StringBuilder();
	    builder.append("EqualsIntegrityTest [beanClass=");
	    builder.append(this.beanClass);
	    builder.append(", integrityStrategy=");
	    builder.append(this.integrityStrategy);
	    builder.append(", beanClassCanonicalName=");
	    builder.append(this.beanClassCanonicalName);
	    builder.append(", settableProperties=");
	    builder.append(this.settableProperties);
	    builder.append(", objectFactory=");
	    builder.append(this.objectFactory);
	    builder.append("]");
	    return builder.toString();
    }
}
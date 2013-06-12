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
import static com.codereligion.beast.internal.util.Assert.fail;

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
 * <li> the equals method must be implemented
 * <li> calling equals with {@code null} must always be false
 * <li> calling equals on the same instance must always be true
 * <li> calling on two different instances must be symmetric
 * <li> calling equals on instances with different property values must behave as specified in the given {@link IntegrityStrategy}
 * </ul>
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 * @see EqualsIntegrityExcludeStrategy
 * @see EqualsIntegrityIncludeStrategy
 */
public final class EqualsIntegrityTest extends AbstractIntegrityTest {

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
    		final Class<?> beanClass,
    		final ObjectFactory objectFactory,
    		final IntegrityStrategy integrityStrategy) {
    	
	    super(beanClass, objectFactory, integrityStrategy);
    }

	@Override
    public void run() {
		
		if (!isMethodImplemented(ObjectMethodNames.EQUALS)) {
			fail("The given class %s does not implement equals.", this.beanClassCanonicalName);
		}

		final Object defaultObject = newBeanObject();
        
        assertFalse(defaultObject.equals(null),
				"Equals method for instances of %s is equals to null.",
				this.beanClassCanonicalName);

        assertTrue(defaultObject.equals(defaultObject),
        		"Equals method for instances of %s is not reflexive.",
        		this.beanClassCanonicalName);

        for (final PropertyDescriptor property : this.writeableProperties) {

            final String propertyName = property.getName();
            final Object dirtyObject = newBeanObject();
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
            	handleInvocationTargetException(property, e);
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
	    
    	return super.equals(obj);
    }

	@Override
    public String toString() {
	    final StringBuilder builder = new StringBuilder();
	    builder.append("EqualsIntegrityTest [");
	    builder.append(super.toString());
	    builder.append("]");
	    return builder.toString();
    }
}
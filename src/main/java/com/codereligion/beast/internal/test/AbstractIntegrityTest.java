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


import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.strategy.IntegrityStrategy;
import java.beans.PropertyDescriptor;


/**
 * Abstract test which provides the basic functionality for an integrity test.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
abstract class AbstractIntegrityTest extends AbstractTest {
	
	/**
	 * The integrity strategy to be applied by this test.
	 */
	protected final IntegrityStrategy integrityStrategy;

	/**
	 * Constructs a new instance of this test for the given {@code beanClass}
	 * using the given {@code objectFactory} and {@code integrityStrategy}.
	 *
	 * @param beanClass the {@link Class} to test
	 * @param objectFactory the {@link ObjectFactory} to use
	 * @param integrityStrategy the {@link IntegrityStrategy} to use
	 * @throws NullPointerException when any of the given parameters are {@code null}
	 */
	public AbstractIntegrityTest(
    		final Class<?> beanClass,
    		final ObjectFactory objectFactory,
    		final IntegrityStrategy integrityStrategy) {
    	
        super(beanClass, objectFactory);
        
        if (objectFactory == null) {
	        throw new NullPointerException("objectFactory must not be null.");
        }
        
        this.integrityStrategy = integrityStrategy;
    }
	
	@Override
	protected void handlePropertySetterExcetion(final PropertyDescriptor property, final Throwable e) {
		this.integrityStrategy.handlePropertySetterException(property, e);
	}
    
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + this.beanClass.hashCode();
	    result = prime * result + this.integrityStrategy.hashCode();
	    result = prime * result + this.beanClassCanonicalName.hashCode();
	    result = prime * result + this.objectFactory.hashCode();
	    result = prime * result + this.settableProperties.hashCode();
	    return result;
    }
}
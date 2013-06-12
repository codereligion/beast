/**
 * Copyright 2013 www.codereligion.com
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
package com.codereligion.beast.internal.test.strategy;


import java.util.Collections;

import java.util.Set;


/**
 * Abstract implementation which provides basic functionalities for an integrity test.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class AbstractIntegrityStrategy implements IntegrityStrategy {
	
	/**
	 * Names of properties to be handled specifically in the concrete strategy.
	 */
	protected final Set<String> propertyNames;

    /**
     * Constructs an instance with the given {@code propertyNames} to be handled
     * specifically according to the concrete implementation of this class.
     *
     * @param propertyNames the names of the properties which should be excluded
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public AbstractIntegrityStrategy(final Set<String> propertyNames) {
    	
    	if (propertyNames == null) {
    		throw new NullPointerException("propertyNames must not be null.");
    	}
    	
    	this.propertyNames = Collections.unmodifiableSet(propertyNames);
    }
    
    @Override
    public int hashCode() {
	    return this.propertyNames.hashCode();
    }
    
	/**
	 * Compares the given object with this instance.
	 * This method will return true if:
	 * 
	 * <ul>
	 * <li> the given object is not {@code null}
	 * <li> is an instance of {@link AbstractIntegrityStrategy}
	 * <li> is of the same concrete type defined by {@link AbstractIntegrityStrategy #getType()}
	 * <li> has the same {@code propertyNames} 
	 * </ul>
	 */
	@Override
    public final boolean equals(final Object obj) {
	    
		if (!(obj instanceof AbstractIntegrityStrategy)) {
			return false;
		}
		
	    final AbstractIntegrityStrategy other = (AbstractIntegrityStrategy) obj;
	    
	    if (other.getClass() != this.getClass()) {
	    	return false;
	    }
	    
	    if (!this.propertyNames.equals(other.propertyNames)) {
	    	return false;
	    }
	    return true;
    }
}
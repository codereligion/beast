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


/**
 * Common interface for an integrity test.
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public interface IntegrityStrategy extends InvocationTargetExceptionHandler {

    /**
     * Applies this strategy on the given {@code defaultObject} and {@code dirtyObject} expecting that the property specified by the given {@code propertyName}
     * has been altered on the given {@code dirtyObject}
     *
     * @param defaultObject the default object to check against the dirty one
     * @param dirtyObject   the dirty object on which the property was altered
     * @param propertyName  the name of the property which was altered on the {@code dirtyObject}
     */
    void apply(Object defaultObject, Object dirtyObject, String propertyName);
}
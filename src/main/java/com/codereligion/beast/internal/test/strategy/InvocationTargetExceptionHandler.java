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

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * Common interface for a class which handles {@link InvocationTargetException}.
 *
 * @author Sebastian Gröbler
 * @since 13.10.2012
 */
public interface InvocationTargetExceptionHandler {

    /**
     * Handles the given {@link InvocationTargetException exception} for the given {@code property}.
     *
     * @param property  the property which caused the given exception
     * @param exception the {@link InvocationTargetException} to handle for the given {@code property}
     * @throws NullPointerException when any of the given parameters are {@code null}
     */
    void handleInvocationTargetException(PropertyDescriptor property, InvocationTargetException exception);
}
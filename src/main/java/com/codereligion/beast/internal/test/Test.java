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
package com.codereligion.beast.internal.test;

/**
 * Common interface for a test. This class extends {@link Runnable} to fit it into the testing context, while at the same time providing the ability to use it
 * in through the {@link Runnable} interface e.g. for multi-threaded execution.
 *
 * @author Sebastian Gr&ouml;bler
 * @since 11.08.2012
 */
public interface Test extends Runnable {
    // no other methods than the inherited ones
}
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
package com.codereligion.beast.internal.creation;

import com.codereligion.beast.InstanceProvider;

/**
 * Provides default instance providers.
 *
 * @author sgroebler
 * @since 23.08.2012
 */
public final class InstanceProviders {

    /**
     * Forbids construction of instances of this class.
     */
    private InstanceProviders() {
        throw new IllegalAccessError("This class must not be instantiated.");
    }

    /**
     * Constant for a "dirty" value.
     */
    private static final String ONE = "1";

    /**
     * Constant for a "default" value.
     */
    private static final String ZERO = "0";

    /**
     * Default instance provider for type {@link Boolean}.
     */
    public static final InstanceProvider BOOLEAN = InstanceProvider.create(Boolean.FALSE, Boolean.TRUE);

    /**
     * Default instance provider for type {@link Byte}.
     */
    public static final InstanceProvider BYTE = InstanceProvider.create(Byte.valueOf(ZERO), Byte.valueOf(ONE));

    /**
     * Default instance provider for type {@link Character}.
     */
    public static final InstanceProvider CHARACTER = InstanceProvider.create((char) 0, (char) 1);

    /**
     * Default instance provider for type {@link Double}.
     */
    public static final InstanceProvider DOUBLE = InstanceProvider.create(Double.valueOf(ZERO), Double.valueOf(ONE));

    /**
     * Default instance provider for type {@link Float}.
     */
    public static final InstanceProvider FLOAT = InstanceProvider.create(Float.valueOf(ZERO), Float.valueOf(ONE));

    /**
     * Default instance provider for type {@link Integer}.
     */
    public static final InstanceProvider INTEGER = InstanceProvider.create(Integer.valueOf(ZERO), Integer.valueOf(ONE));

    /**
     * Default instance provider for type {@link Long}.
     */
    public static final InstanceProvider LONG = InstanceProvider.create(Long.valueOf(ZERO), Long.valueOf(ONE));

    /**
     * Default instance provider for type {@link Object}.
     */
    public static final InstanceProvider OBJECT = InstanceProvider.create(Short.valueOf(ZERO), Short.valueOf(ONE));

    /**
     * Default instance provider for type {@link Short}.
     */
    public static final InstanceProvider SHORT = InstanceProvider.create(Short.valueOf(ZERO), Short.valueOf(ONE));

    /**
     * Default instance provider for type {@link String}.
     */
    public static final InstanceProvider STRING = InstanceProvider.create(String.valueOf(ZERO), String.valueOf(ONE));
}

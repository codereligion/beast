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
package com.codereligion.beast;

import com.codereligion.beast.internal.test.Test;
import com.codereligion.beast.internal.test.ToStringFormatTest;
import com.codereligion.beast.internal.test.builder.AbstractTestBuilder;
import java.util.regex.Pattern;


/**
 * Builder for the toString format test. The resulting test will apply the following criteria to the class under test:
 * <p/>
 * <ul> <li> the toString method must be implemented <li> the result must comply to the specified {@link Pattern} or if no pattern has been specified to the
 * {@link ToStringFormatTestBuilder #DEFAULT_PATTERN} </ul>
 *
 * @author Sebastian Gr&ouml;bler
 * @since 11.08.2012
 */
public final class ToStringFormatTestBuilder extends AbstractTestBuilder {

    /**
     * The default pattern which matches to strings of this structure:
     * <p/>
     * <pre>
     * 	ClassName [propertyName=value, anotherPropertyName=value]
     * </pre>
     */
    public static final Pattern DEFAULT_PATTERN = Pattern.compile(".+\\[(.+=.+, )*(.+=.+)?\\]");

    /**
     * The pattern to be applied.
     */
    private Pattern pattern = DEFAULT_PATTERN;

    /**
     * Creates a new builder which will create a test for the given {@code beanClass}.
     *
     * @param beanClass the {@link Class} to be tested
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public ToStringFormatTestBuilder(final Class<?> beanClass) {
        super(beanClass);
    }

    /**
     * Creates a new builder which will create a test for the given {@code beanClass}.
     *
     * @param beanClass the {@link Class} to be tested
     * @param pattern   the {@link Pattern} to be applied
     * @throws NullPointerException when the given parameter is {@code null}
     */
    public ToStringFormatTestBuilder(final Class<?> beanClass, final Pattern pattern) {
        this(beanClass);
        this.pattern = pattern;
    }

    @Override
    public Test create() {
        return new ToStringFormatTest(this.beanClass, createObjectFactory(), this.pattern, this.excludedPropertyNames);
    }
}
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
package com.codereligion.beast.framework;

import com.codereligion.beast.EqualsIntegrityTestBuilder;
import com.codereligion.beast.EqualsNullSafetyTestBuilder;
import com.codereligion.beast.HashCodeIntegrityTestBuilder;
import com.codereligion.beast.HashCodeNullSafetyTestBuilder;
import com.codereligion.beast.InstanceProvider;
import com.codereligion.beast.ToStringFormatTestBuilder;
import com.codereligion.beast.ToStringIntegrityTestBuilder;
import com.codereligion.beast.ToStringNullSafetyTestBuilder;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * TODo maybe extend documentation, if this class should be shown as an example in the wiki. Example implementation of the "beast" for JUnit.
 *
 * @author sgroebler
 * @since 11.08.2012
 */
public abstract class JUnitBeast<T> {

    @Test
    public void testEqualsIntegrity() {
        new EqualsIntegrityTestBuilder(getClazz()).addExcludedPropertyNames(getExcludesForEqualsIntegrityTest())
                                                  .addIncludedPropertyNames(getIncludesForEqualsIntegrityTest())
                                                  .addInstanceProviders(getInstanceProviders())
                                                  .create()
                                                  .run();
    }

    @Test
    public void testEqualsNullSafety() {
        new EqualsNullSafetyTestBuilder(getClazz()).addExcludedPropertyNames(getExcludesForEqualsNullSafetyTest())
                                                   .addInstanceProviders(getInstanceProviders())
                                                   .create()
                                                   .run();
    }

    @Test
    public void testHashCodeIntegrity() {
        new HashCodeIntegrityTestBuilder(getClazz()).addExcludedPropertyNames(getExcludesForHashCodeIntegrityTest())
                                                    .addIncludedPropertyNames(getIncludesForHashCodeIntegrityTest())
                                                    .addInstanceProviders(getInstanceProviders())
                                                    .create()
                                                    .run();
    }

    @Test
    public void testHashCodeNullSafety() {
        new HashCodeNullSafetyTestBuilder(getClazz()).addExcludedPropertyNames(getExcludesForEqualsNullSafetyTest())
                                                     .addInstanceProviders(getInstanceProviders())
                                                     .create()
                                                     .run();
    }

    @Test
    public void testToStringIntegrity() {
        new ToStringIntegrityTestBuilder(getClazz()).addExcludedPropertyNames(getExcludesForToStringIntegrityTest())
                                                    .addIncludedPropertyNames(getIncludesForToStringIntegrityTest())
                                                    .addInstanceProviders(getInstanceProviders())
                                                    .create()
                                                    .run();
    }

    @Test
    public void testToStringNullSafety() {
        new ToStringNullSafetyTestBuilder(getClazz()).addExcludedPropertyNames(getExcludesForToStringNullSafetyTest())
                                                     .addInstanceProviders(getInstanceProviders())
                                                     .create()
                                                     .run();
    }

    @Test
    public void testToStringFormat() {
        new ToStringFormatTestBuilder(getClazz(), getToStringPattern()).addInstanceProviders(getInstanceProviders()).create().run();
    }

    protected abstract Class<T> getClazz();

    protected Set<String> getExcludesForHashCodeIntegrityTest() {
        return Collections.emptySet();
    }

    protected Set<String> getIncludesForHashCodeIntegrityTest() {
        return Collections.emptySet();
    }

    protected Set<String> getExcludesForEqualsIntegrityTest() {
        return Collections.emptySet();
    }

    protected Set<String> getIncludesForEqualsIntegrityTest() {
        return Collections.emptySet();
    }

    protected Set<String> getExcludesForToStringIntegrityTest() {
        return Collections.emptySet();
    }

    protected Set<String> getIncludesForToStringIntegrityTest() {
        return Collections.emptySet();
    }

    protected Set<String> getExcludesForHashCodeNullSafetyTest() {
        return Collections.emptySet();
    }

    protected Set<String> getExcludesForEqualsNullSafetyTest() {
        return Collections.emptySet();
    }

    protected Set<String> getExcludesForToStringNullSafetyTest() {
        return Collections.emptySet();
    }

    protected Set<InstanceProvider> getInstanceProviders() {
        return Collections.emptySet();
    }

    protected Pattern getToStringPattern() {
        // TODO does not reflect nested toString calls, e.g. for collections etc.
//		return Pattern.compile(".+\\{(.+=.+, )*(.+=.+)?\\}");
        return Pattern.compile(".*");
    }
}

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

package com.codereligion.beast.framework;

import com.codereligion.beast.InstanceProvider;
import com.codereligion.beast.EqualsIntegrityTestBuilder;
import com.codereligion.beast.EqualsNullSafetyTestBuilder;
import com.codereligion.beast.HashCodeIntegrityTestBuilder;
import com.codereligion.beast.HashCodeNullSafetyTestBuilder;
import com.codereligion.beast.ToStringFormatTestBuilder;
import com.codereligion.beast.ToStringIntegrityTestBuilder;
import com.codereligion.beast.ToStringNullSafetyTestBuilder;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Example implementation of the "beast" for JUnit.
 * 
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public abstract class JUnitBeast <T> {
	
	@Test
	public void testEqualsIntegrity() {
		new EqualsIntegrityTestBuilder()
			.addExcludedPropertyNames(getExcludesForEqualsIntegrity())
			.addInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testEqualsNullSafety() {
		new EqualsNullSafetyTestBuilder()
			.addExcludedPropertyNames(getExcludesForEqualsNullSafety())
			.addInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testHashCodeIntegrity() {
		new HashCodeIntegrityTestBuilder()
			.addExcludedPropertyNames(getExcludesForHashCodeIntegrity())
			.addInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testHashCodeNullSafety() {
		new HashCodeNullSafetyTestBuilder()
			.addExcludedPropertyNames(getExcludesForEqualsNullSafety())
			.addInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testToStringIntegrity() {
		new ToStringIntegrityTestBuilder()
			.addExcludedPropertyNames(getExcludesToStringIntegrity())
			.addInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testToStringNullSafety() {
		new ToStringNullSafetyTestBuilder()
			.addExcludedPropertyNames(getExcludesToStringNullSafety())
			.addInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testToStringFormat() {
		new ToStringFormatTestBuilder()
			.addExcludedPropertyNames(getExcludesForToStringFormatTest())
			.addInstanceProviders(getInstanceProviders())
			.create(getClazz(), getToStringPattern())
			.run();
	}

	/**
	 * Provides the {@link Class} which should be tested.
	 * 
	 * @return the {@link Class} of the bean to be tested, must not be {@code null}
	 */
	protected abstract Class<T> getClazz();
	
	/**
	 * TODO update
	 * Optional {@link Set} of to be excluded property names from the
	 * hashCode and equals test.
	 * 
	 * <p>
	 * The default implementation returns an empty {@link Set}, meaning
	 * nothing is excluded by default.
	 * 
	 * <p>
	 * Sub-classes should override this method, if necessary.
	 * 
	 * @return a {@link Set} of property names, must not be {@code null}
	 */
	protected Set<String> getExcludesForHashCodeIntegrity() {
		return new HashSet<String>();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesForHashCodeNullSafety() {
		return new HashSet<String>();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesForEqualsIntegrity() {
		return new HashSet<String>();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesForEqualsNullSafety() {
		return new HashSet<String>();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesToStringIntegrity() {
		return new HashSet<String>();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesToStringNullSafety() {
		return new HashSet<String>();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesForToStringFormatTest() {
		return new HashSet<String>();
	}
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	protected Set<InstanceProvider<?>> getInstanceProviders() {
		return new HashSet<InstanceProvider<?>>();
	}
	
	/**
	 * TODO maybe throw a NotImplementedException???
	 * Optional regular expression to which the toString result should be checked against.
	 * 
	 * <p>
	 * Sub-classes should override this method, if necessary they want to execute testToStringFormat.
	 * 
	 * @return the regular expression to be applied, or {@code null} if the pattern should not be checked
	 */
	protected Pattern getToStringPattern() {
		return Pattern.compile(".+ \\[(.+=.+, )*(.+=.+)?\\]");
	}
}

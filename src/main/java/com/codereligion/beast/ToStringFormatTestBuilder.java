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

package com.codereligion.beast;

import com.codereligion.beast.internal.creation.ObjectFactory;
import com.codereligion.beast.internal.test.Test;
import com.codereligion.beast.internal.test.ToStringFormatTest;
import java.util.Set;
import java.util.regex.Pattern;


/**
 * TODO document
 * TODO test null check
 *
 * @author Sebastian Gröbler
 * @since 11.08.2012
 */
public final class ToStringFormatTestBuilder extends AbstractTestBuilder {
	
	// TODO extend to be able to cover lists and array and other complex objects as values
	private static Pattern DEFAULT_PATTERN = Pattern.compile(".+\\{(.+=.+, )*(.+=.+)?\\}");
	
	private Pattern pattern = DEFAULT_PATTERN;
	
	/**
	 * TODO
	 */
	@Override
	public <T> Test create(final Class<T> beanClass) {
		return create(beanClass, this.pattern);
	}
	
	/**
	 * TODO
	 *
	 * @param beanClass
	 * @param pattern
	 * @return
	 */
	public <T> Test create(final Class<T> beanClass, final Pattern pattern) {
		return new ToStringFormatTest<T>(beanClass, new ObjectFactory(this.instanceProviders), pattern);
	}
	
	/**
	 * TODO
	 *
	 * @param beanClass
	 * @param pattern
	 */
	public <T> void createAndRun(final Class<T> beanClass, final Pattern pattern) {
		create(beanClass, pattern).run();
	}

	@Override
	public ToStringFormatTestBuilder addInstanceProvider(final InstanceProvider<?> instanceProvider) {
		return (ToStringFormatTestBuilder) super.addInstanceProvider(instanceProvider);
	}
	
	@Override
	public ToStringFormatTestBuilder addInstanceProviders(final Set<InstanceProvider<?>> instanceProviders) {
		return (ToStringFormatTestBuilder) super.addInstanceProviders(instanceProviders);
	}
}
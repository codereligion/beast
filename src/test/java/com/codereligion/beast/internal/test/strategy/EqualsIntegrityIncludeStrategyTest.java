package com.codereligion.beast.internal.test.strategy;

import java.util.Set;

/**
 * Tests {@link EqualsIntegrityIncludeStrategy}.
 *
 * @author Sebastian Gröbler
 * @since 29.10.2012
 */
public class EqualsIntegrityIncludeStrategyTest extends AbstractIntegrityIncludeStrategyTest {
	
	@Override
    public AbstractIntegrityIncludeStrategy createIntegrityStrategy(final Set<String> propertyNames) {
	    return new EqualsIntegrityIncludeStrategy(propertyNames);
    }
}

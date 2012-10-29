package com.codereligion.beast.internal.test.strategy;

import java.util.Set;

/**
 * Tests {@link ToStringIntegrityIncludeStrategy}.
 *
 * @author Sebastian Gröbler
 * @since 29.10.2012
 */
public class ToStringIntegrityExcludeStrategyTest extends AbstractIntegrityExcludeStrategyTest {
	
	@Override
    public AbstractIntegrityExcludeStrategy createIntegrityStrategy(final Set<String> propertyNames) {
	    return new ToStringIntegrityExcludeStrategy(propertyNames);
    }
}

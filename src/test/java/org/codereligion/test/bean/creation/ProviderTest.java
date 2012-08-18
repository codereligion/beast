package org.codereligion.test.bean.creation;

import static org.junit.Assert.assertFalse;

import org.codereligion.test.bean.creation.AtomicBooleanProvider;
import org.codereligion.test.bean.creation.AtomicIntegerProvider;
import org.codereligion.test.bean.creation.AtomicLongProvider;
import org.codereligion.test.bean.creation.BigDecimalProvider;
import org.codereligion.test.bean.creation.BigIntegerProvider;
import org.codereligion.test.bean.creation.BooleanProvider;
import org.codereligion.test.bean.creation.ByteProvider;
import org.codereligion.test.bean.creation.CharacterProvider;
import org.codereligion.test.bean.creation.DoubleProvider;
import org.codereligion.test.bean.creation.FloatProvider;
import org.codereligion.test.bean.creation.IntegerProvider;
import org.codereligion.test.bean.creation.LongProvider;
import org.codereligion.test.bean.creation.ObjectProvider;
import org.codereligion.test.bean.creation.Provider;
import org.codereligion.test.bean.creation.ShortProvider;
import org.codereligion.test.bean.creation.StringProvider;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests if all {@link Provider} implementations provide non-equal results for their
 * implementations of {@link Provider #getDefaultObject()} and {@link Provider #getDirtyObject()}.  
 *
 * @author sgroebler
 * @since 16.08.2012
 */
public class ProviderTest {
	

    private static final Set<Provider<?>> PROVIDER = new HashSet<Provider<?>>();

	static {
		PROVIDER.add(AtomicBooleanProvider.INSTANCE);
		PROVIDER.add(AtomicIntegerProvider.INSTANCE);
		PROVIDER.add(AtomicLongProvider.INSTANCE);
		PROVIDER.add(BigDecimalProvider.INSTANCE);
		PROVIDER.add(BigIntegerProvider.INSTANCE);
        PROVIDER.add(BooleanProvider.INSTANCE);
        PROVIDER.add(ByteProvider.INSTANCE);
        PROVIDER.add(CharacterProvider.INSTANCE);
        PROVIDER.add(DoubleProvider.INSTANCE);
        PROVIDER.add(FloatProvider.INSTANCE);
        PROVIDER.add(IntegerProvider.INSTANCE);
        PROVIDER.add(LongProvider.INSTANCE);
        PROVIDER.add(ObjectProvider.INSTANCE);
        PROVIDER.add(StringProvider.INSTANCE);
        PROVIDER.add(ShortProvider.INSTANCE);
	}

	@Test
	public void testProvider() {
		for (final Provider<?> provider : PROVIDER) {
			assertFalse(provider.getDefaultObject().equals(provider.getDirtyObject()));
		}
	}
}

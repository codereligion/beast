package org.codereligion.test.bean.creation.provider;

import static org.junit.Assert.assertFalse;

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

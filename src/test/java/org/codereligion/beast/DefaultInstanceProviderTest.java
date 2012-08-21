package org.codereligion.beast;

import static org.junit.Assert.assertFalse;



import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * Tests if all {@link InstanceProvider} implementations provide non-equal results for their
 * implementations of {@link InstanceProvider #getDefaultObject()} and {@link InstanceProvider #getDirtyObject()}.  
 *
 * @author Sebastian Gröbler
 * @since 16.08.2012
 */
public class DefaultInstanceProviderTest {
	

    private static final Set<InstanceProvider<?>> PROVIDER = new HashSet<InstanceProvider<?>>();

	static {
        PROVIDER.add(BooleanInstanceProvider.INSTANCE);
        PROVIDER.add(ByteInstanceProvider.INSTANCE);
        PROVIDER.add(CharacterInstanceProvider.INSTANCE);
        PROVIDER.add(DoubleInstanceProvider.INSTANCE);
        PROVIDER.add(FloatInstanceProvider.INSTANCE);
        PROVIDER.add(IntegerInstanceProvider.INSTANCE);
        PROVIDER.add(LongInstanceProvider.INSTANCE);
        PROVIDER.add(StringInstanceProvider.INSTANCE);
        PROVIDER.add(ShortInstanceProvider.INSTANCE);
	}

	@Test
	public void testProvider() {
		for (final InstanceProvider<?> provider : PROVIDER) {
			assertFalse(provider.getDefaultObject().equals(provider.getDirtyObject()));
		}
	}
}

package org.codereligion.beast.framework;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.codereligion.beast.CustomInstanceProvider;
import org.codereligion.beast.EqualsIntegrityTestBuilder;
import org.codereligion.beast.EqualsNullSafetyTestBuilder;
import org.codereligion.beast.HashCodeIntegrityTestBuilder;
import org.codereligion.beast.HashCodeNullSafetyTestBuilder;
import org.codereligion.beast.ToStringFormatTestBuilder;
import org.codereligion.beast.ToStringIntegrityTestBuilder;
import org.codereligion.beast.ToStringNullSafetyTestBuilder;

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
			.addCustomInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testEqualsNullSafety() {
		new EqualsNullSafetyTestBuilder()
			.addExcludedPropertyNames(getExcludesForEqualsNullSafety())
			.addCustomInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testHashCodeIntegrity() {
		new HashCodeIntegrityTestBuilder()
			.addExcludedPropertyNames(getExcludesForHashCodeIntegrity())
			.addCustomInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testHashCodeNullSafety() {
		new HashCodeNullSafetyTestBuilder()
			.addExcludedPropertyNames(getExcludesForEqualsNullSafety())
			.addCustomInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testToStringIntegrity() {
		new ToStringIntegrityTestBuilder()
			.addExcludedPropertyNames(getExcludesToStringIntegrity())
			.addCustomInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testToStringNullSafety() {
		new ToStringNullSafetyTestBuilder()
			.addExcludedPropertyNames(getExcludesToStringNullSafety())
			.addCustomInstanceProviders(getInstanceProviders())
			.create(getClazz())
			.run();
	}
	
	@Test
	public void testToStringFormat() {
		new ToStringFormatTestBuilder()
			.addExcludedPropertyNames(getExcludesForToStringFormatTest())
			.addCustomInstanceProviders(getInstanceProviders())
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
	protected Set<CustomInstanceProvider<?>> getInstanceProviders() {
		return new HashSet<CustomInstanceProvider<?>>();
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

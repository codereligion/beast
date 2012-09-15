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
 * TODO document
 * Example implementation of the "beast" for JUnit.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public abstract class JUnitBeast <T> {
	
	@Test
	public void testEqualsIntegrity() {
		new EqualsIntegrityTestBuilder()
			.addExcludedPropertyNames(getExcludesForEqualsIntegrityTest())
			.addIncludedPropertyNames(getIncludesForEqualsIntegrityTest())
			.addInstanceProviders(getInstanceProviders())
			.createAndRun(getClazz());
	}
	
	@Test
	public void testEqualsNullSafety() {
		new EqualsNullSafetyTestBuilder()
			.addExcludedPropertyNames(getExcludesForEqualsNullSafetyTest())
			.addInstanceProviders(getInstanceProviders())
			.createAndRun(getClazz());
	}
	
	@Test
	public void testHashCodeIntegrity() {
		new HashCodeIntegrityTestBuilder()
			.addExcludedPropertyNames(getExcludesForHashCodeIntegrityTest())
			.addIncludedPropertyNames(getIncludesForHashCodeIntegrityTest())
			.addInstanceProviders(getInstanceProviders())
			.createAndRun(getClazz());
	}
	
	@Test
	public void testHashCodeNullSafety() {
		new HashCodeNullSafetyTestBuilder()
			.addExcludedPropertyNames(getExcludesForEqualsNullSafetyTest())
			.addInstanceProviders(getInstanceProviders())
			.createAndRun(getClazz());
	}
	
	@Test
	public void testToStringIntegrity() {
		new ToStringIntegrityTestBuilder()
			.addExcludedPropertyNames(getExcludesForToStringIntegrityTest())
			.addIncludedPropertyNames(getIncludesForToStringIntegrityTest())
			.addInstanceProviders(getInstanceProviders())
			.createAndRun(getClazz());
	}
	
	@Test
	public void testToStringNullSafety() {
		new ToStringNullSafetyTestBuilder()
			.addExcludedPropertyNames(getExcludesForToStringNullSafetyTest())
			.addInstanceProviders(getInstanceProviders())
			.createAndRun(getClazz());
	}
	
	@Test
	public void testToStringFormat() {
		new ToStringFormatTestBuilder()
			.addInstanceProviders(getInstanceProviders())
			.createAndRun(getClazz(), getToStringPattern());
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
	protected Set<String> getExcludesForHashCodeIntegrityTest() {
		return Collections.emptySet();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getIncludesForHashCodeIntegrityTest() {
		return Collections.emptySet();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesForEqualsIntegrityTest() {
		return Collections.emptySet();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getIncludesForEqualsIntegrityTest() {
		return Collections.emptySet();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesForToStringIntegrityTest() {
		return Collections.emptySet();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getIncludesForToStringIntegrityTest() {
		return Collections.emptySet();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesForHashCodeNullSafetyTest() {
		return Collections.emptySet();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesForEqualsNullSafetyTest() {
		return Collections.emptySet();
	}
	
	/**
	 * TODO
	 *
	 * @return
	 */
	protected Set<String> getExcludesForToStringNullSafetyTest() {
		return Collections.emptySet();
	}
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	protected Set<InstanceProvider<?>> getInstanceProviders() {
		return Collections.emptySet();
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
		// TODO does not reflect nested toString calls, e.g. for collections etc.
//		return Pattern.compile(".+\\{(.+=.+, )*(.+=.+)?\\}");
		return Pattern.compile(".*");
	}
}

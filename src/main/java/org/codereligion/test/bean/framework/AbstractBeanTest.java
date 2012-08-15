package org.codereligion.test.bean.framework;

import org.codereligion.test.bean.EqualsTester;
import org.codereligion.test.bean.HashCodeTester;
import org.codereligion.test.bean.ToStringTester;

import java.util.HashSet;
import java.util.Set;

/**
 * TODO document
 * TODO test
 * TODO usuage
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public abstract class AbstractBeanTest <T> {
	
	/**
	 * Default implementation of the equals test.
	 */
	public void testEquals() {
		EqualsTester.testIntegrity(getClazz(), getExcludedHashCodeAndEqualsPropertyNames());
	}
	
	/**
	 * Default implementation of the equals null-safety test.
	 */
	public void testEqualsNullSafety() {
		EqualsTester.testNullSafety(getClazz());
	}
	
	/**
	 * Default implementation of the hashCode test.
	 */
	public void testHashCode() {
		HashCodeTester.testIntegrity(getClazz(), getExcludedHashCodeAndEqualsPropertyNames());
	}
	
	/**
	 * Default implementation of the hashCode null-safety test.
	 */
	public void testHashCodeNullSafety() {
		HashCodeTester.testNullSafety(getClazz());
	}
	
	/**
	 * Default implementation of the toString test.
	 */
	public void testToString() {
		ToStringTester.testIntegrity(getClazz(), getExcludedToStringPropertyNames());
	}
	
	/**
	 * Default implementation of the toString test.
	 */
	public void testToStringNullSafety() {
		ToStringTester.testNullSafety(getClazz());
	}
	
	/**
	 * Default implementation of the toString format test.
	 */
	public void testToStringFormat() {
		ToStringTester.testFormat(getClazz(), getToStringPattern());
	}

	/**
	 * Provides the {@link Class} which should be tested.
	 * 
	 * @return the {@link Class} of the bean to be tested, must not be {@code null}
	 */
	protected abstract Class<T> getClazz();
	
	/**
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
	protected Set<String> getExcludedHashCodeAndEqualsPropertyNames() {
		return new HashSet<String>();
	}
	
	/**
	 * Optional {@link Set} of to be excluded property names from the toString test.
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
	protected Set<String> getExcludedToStringPropertyNames() {
		return new HashSet<String>();
	}
	
	/**
	 * Optional regular expression to which the toString result should be checked against.
	 * 
	 * <p>
	 * The default implementation return null, meaning no regular expression is applied.
	 * 
	 * <p>
	 * Sub-classes should override this method, if necessary.
	 * 
	 * @return the regular expression to be applied, or {@code null} if the pattern should not be checked
	 */
	protected String getToStringPattern() {
		return null;
	}
}

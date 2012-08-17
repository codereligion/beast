package org.codereligion.test.bean.framework;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.codereligion.test.bean.EqualsTester;
import org.codereligion.test.bean.HashCodeTester;
import org.codereligion.test.bean.ToStringTester;

/**
 * Abstract implementation of a bean test which can be used to build an
 * abstract or concrete class for the testing framework of choice by
 * extending the provided implementations and annotating them with the 
 * framework specific test run annotation.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public abstract class AbstractBeanTest <T> {
	
	/**
	 * Default implementation of the equals integrity test.
	 */
	public void testEqualsIntegrity() {
		EqualsTester.testIntegrity(getClazz(), getExcludedHashCodeAndEqualsPropertyNames());
	}
	
	/**
	 * Default implementation of the equals null-safety test.
	 */
	public void testEqualsNullSafety() {
		EqualsTester.testNullSafety(getClazz());
	}
	
	/**
	 * Default implementation of the hashCode integrity test.
	 */
	public void testHashCodeIntegrity() {
		HashCodeTester.testIntegrity(getClazz(), getExcludedHashCodeAndEqualsPropertyNames());
	}
	
	/**
	 * Default implementation of the hashCode null-safety test.
	 */
	public void testHashCodeNullSafety() {
		HashCodeTester.testNullSafety(getClazz());
	}
	
	/**
	 * Default implementation of the toString integrity test.
	 */
	public void testToStringIntegrity() {
		ToStringTester.testIntegrity(getClazz(), getExcludedToStringPropertyNames());
	}
	
	/**
	 * Default implementation of the toString null-safety test.
	 */
	public void testToStringNullSafety() {
		ToStringTester.testNullSafety(getClazz());
	}
	
	/**
	 * Default implementation of the toString format test.
	 * 
	 * @throws NullPointerException when the {@link AbstractBeanTest #getToStringRegex()} 
	 * is not overridden by a sub-class and does not provide a non-null value
	 */
	public void testToStringFormat() {
		
		final Pattern pattern = getToStringPattern();
		
		if (pattern == null) {
			throw new NullPointerException(
                "Calling the testToStringFormat method requires setting a pattern. Override the getToStringPattern method.");
		}
		
		
		ToStringTester.testFormat(getClazz(), pattern);
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
	 * Sub-classes should override this method, if necessary they want to execute testToStringFormat.
	 * 
	 * @return the regular expression to be applied, or {@code null} if the pattern should not be checked
	 */
	protected Pattern getToStringPattern() {
		return null;
	}
}

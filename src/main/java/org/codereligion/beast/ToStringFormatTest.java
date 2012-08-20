package org.codereligion.beast;


import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * TODO update documentation
 * Tests the toString implementation of a java bean.
 * 
 * @author sgroebler
 * @since 11.08.2012
 */
public final class ToStringFormatTest <T> extends AbstractTest<T> {
	
	/**
	 * The compiled regular expression the toString result should match.
	 */
	private Pattern toStringPattern;

	/**
	 * TODO 
	 * Constructs an instance.
	 *
	 * @param beanClass
	 * @param pattern
	 */
	ToStringFormatTest(
			final Class<T> beanClass,
			final Set<String> excludedPropertyNames,
			final ObjectFactory objectFactory,
			final Pattern pattern) {
		
		super(beanClass, excludedPropertyNames, objectFactory);
		
		if (pattern == null) {
			throw new NullPointerException("pattern must not be null.");
		}

        if (!isMethodImplemented(ObjectMethodNames.TO_STRING)) {
        	throw new IllegalArgumentException("The given class " + this.beanClassCanonicalName + " does not implement toString.");
        }
		
		this.toStringPattern = pattern;
	}
	
	@Override
	public void run() {
		// TODO check if not excluded propertyNames appear in the toString result?
		final T defaultObject = newBeanObject();
		final String defaultToStringResult  = defaultObject.toString();

		final Matcher matcher = this.toStringPattern.matcher(defaultToStringResult);
		final boolean toStringMatchesPattern = matcher.matches();
		
		assertTrue(toStringMatchesPattern, 
				"The required pattern '%s' was not matched by the toString result: '%s'.",
				this.toStringPattern.pattern(),
				defaultToStringResult);
	}
}
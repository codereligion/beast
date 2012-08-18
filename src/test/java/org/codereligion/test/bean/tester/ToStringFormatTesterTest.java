package org.codereligion.test.bean.tester;

import java.util.regex.Pattern;
import org.codereligion.test.bean.object.ComplexClass;
import org.codereligion.test.bean.object.User;
import org.codereligion.test.bean.object.WrongFormatInToString;
import org.junit.Test;

/**
 * Tests {@link ToString}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class ToStringFormatTesterTest {

	private static final Pattern ECLIPSE_TO_STRING_PATTERN = Pattern.compile(".+ \\[(.+=.+, )*(.+=.+)?\\]");
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		ToString.testFormat(null, Pattern.compile(".*"));
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithNullPattern() {
		ToString.testFormat(WrongFormatInToString.class, null);	
	}
	
	@Test
	public void testValidClass() {
		ToString.testFormat(ComplexClass.class, ECLIPSE_TO_STRING_PATTERN);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNonImplementingClass() {
		ToString.testFormat(User.class, ECLIPSE_TO_STRING_PATTERN);
	}

	@Test(expected = AssertionError.class)
	public void testWithWrongFormat() {
        ToString.testFormat(WrongFormatInToString.class, ECLIPSE_TO_STRING_PATTERN);
	}
}

package com.codereligion.beast;

import com.codereligion.beast.object.ComplexClass;
import com.codereligion.beast.object.MissingToStringImplementation;
import com.codereligion.beast.object.WrongFormatInToString;

import com.codereligion.beast.ToStringFormatTestBuilder;



import java.util.regex.Pattern;
import org.junit.Test;

/**
 * Tests {@link ToStringFormatTestBuilder}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
public class ToStringFormatTesterTest {

	private static final Pattern ECLIPSE_TO_STRING_PATTERN = Pattern.compile(".+ \\[(.+=.+, )*(.+=.+)?\\]");
	
	@Test(expected = NullPointerException.class)
	public void testWithNullClass() {
		new ToStringFormatTestBuilder()
			.create(null, Pattern.compile(".*"))
			.run();
	}
	
	@Test(expected = NullPointerException.class)
	public void testWithNullPattern() {
		new ToStringFormatTestBuilder()
			.create(WrongFormatInToString.class, null)
			.run();	
	}
	
	@Test
	public void testValidClass() {
		new ToStringFormatTestBuilder()
			.create(ComplexClass.class, ECLIPSE_TO_STRING_PATTERN)
			.run();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMissingImplementingClass() {
		new ToStringFormatTestBuilder()
			.create(MissingToStringImplementation.class, ECLIPSE_TO_STRING_PATTERN)
			.run();
	}

	@Test(expected = AssertionError.class)
	public void testWithWrongFormat() {
		new ToStringFormatTestBuilder()
			.create(WrongFormatInToString.class, ECLIPSE_TO_STRING_PATTERN)
			.run();
	}
}

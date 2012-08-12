package org.codereligion.test.bean;

import org.codereligion.test.bean.ReflectUtil;
import org.junit.Test;

/**
 * Tests {@link ReflectUtil}.
 * TODO implement parameter check tests
 * 
 * @author sgroebler
 * @since 12.08.2012
 */
public class ReflectUtilTest {

	@Test
	public void testCreateDefaultObject() {
		final ComplexObject object = ReflectUtil.getDefaultObject(ComplexObject.class);
	}
	
//	@Test
//	public void testCreateDirtyObject() {
//		ReflectUtil.getDirtyObject(null);
//	}
//	
//	@Test
//	public void testGetSelectableProperties() {
//		ReflectUtil.getSetableProperties(null);
//	}
//	
//	@Test
//	public void testIntrospectorBugInGetSelectableProperties() {
//		ReflectUtil.getSetableProperties(null);
//	}
//	
//	@Test
//	public void testSetValue() {
//		ReflectUtil.setValue(null, null, null);
//	}
}

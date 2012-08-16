package org.codereligion.test.bean.creation.provider;


/**
 * Provider for dirty and default objects of class {@link Character}.
 * 
 * @author sgroebler
 * @since 14.08.2012
 */
public class CharacterProvider implements Provider<Character> {
	
	/**
	 * Instance of this class.
	 */
	public static final Provider<Character> INSTANCE = new CharacterProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Character DIRTY =  Character.valueOf((char) 1);
	
	/**
	 * Cached default object.
	 */
	private static final Character DEFAULT = Character.valueOf((char) 0);
	
	@Override
	public Character getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Character getDefaultObject() {
		return DEFAULT;
	}
}
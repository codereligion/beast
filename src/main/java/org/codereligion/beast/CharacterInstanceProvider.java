package org.codereligion.beast;



/**
 * Provider for dirty and default objects of class {@link Character}.
 * 
 * @author Sebastian Gröbler
 * @since 14.08.2012
 */
final class CharacterInstanceProvider implements InstanceProvider<Character> {
	
	/**
	 * Instance of this class.
	 */
	public static final InstanceProvider<Character> INSTANCE = new CharacterInstanceProvider();
	
	/**
	 * Cached dirty object.
	 */
	private static final Character DIRTY = Character.valueOf((char) 1);
	
	/**
	 * Cached default object.
	 */
	private static final Character DEFAULT = Character.valueOf((char) 0);
	
	/**
	 * This is a singleton class which must not be instantiated from outside.
	 */
	private CharacterInstanceProvider() {
		// nothing to do
	}
	
	@Override
	public Character getDirtyObject() {
		return DIRTY;
	}

	@Override
	public Character getDefaultObject() {
		return DEFAULT;
	}

	@Override
	public Class<Character> getInstanceClass() {
		return Character.class;
	}
}
package org.codereligion.test.bean.exception;

/**
 * TODO
 * 
 * @author sgroebler
 * @since 13.08.2012
 */
public class BeanTestException extends RuntimeException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1775052091408787611L;

	/**
	 * Constructs a new instance.
	 */
	public BeanTestException() {
		super();
	}

	/**
	 * Constructs a new instance with the given {@code message}.
	 * 
	 * @param message the message of this exception
	 */
	public BeanTestException(final String message) {
		super(message);
	}

	/**
	 * Constructs a new instance with the given {@code cause}.
	 * 
	 * @param cause the {@link Throwable} which represents the cause exception
	 */
	public BeanTestException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new instance with the given {@code message} and {@code cause}.
	 * 
	 * @param cause the {@link Throwable} which represents the cause exception
	 * @param message the message of this exception
	 */
	public BeanTestException(final String message, final Throwable cause) {
		super(message, cause);
	}
}

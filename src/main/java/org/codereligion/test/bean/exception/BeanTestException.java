/**
 * 
 */
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
	 * 
	 */
	public BeanTestException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public BeanTestException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BeanTestException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BeanTestException(String message, Throwable cause) {
		super(message, cause);
	}
}

/**
 * @FileName  : PasswordMismatchException.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.exception;

/**
 * @author bluepoet
 *
 */
public class PasswordMismatchException extends Exception {
	public PasswordMismatchException() {
		super();
	}

	public PasswordMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordMismatchException(String message) {
		super(message);
	}

	public PasswordMismatchException(Throwable cause) {
		super(cause);
	}
}
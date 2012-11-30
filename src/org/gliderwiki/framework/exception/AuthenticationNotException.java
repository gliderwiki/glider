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
public class AuthenticationNotException extends Exception {
	public AuthenticationNotException() {
		super();
	}

	public AuthenticationNotException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationNotException(String message) {
		super(message);
	}

	public AuthenticationNotException(Throwable cause) {
		super(cause);
	}
}
/**
 * @FileName  : MsgException
 * @Project   : NightHawk
 * @Date      : 2011-12-05
 * @작성자    : @author Yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.exception;

@SuppressWarnings("serial")
public class MsgException extends RuntimeException {

	public MsgException() {
        super();
    }

    public MsgException(String message, Throwable cause) {
        super(message, cause);
    }

    public MsgException(String message) {
        super(message);
    }

    public MsgException(Throwable cause) {
        super(cause);
    }
}

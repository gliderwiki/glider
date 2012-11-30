
package org.gliderwiki.framework.exception;

/**
 * 
 * @author yion
 *
 */
public class DBHandleException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String customMsg;
	private Throwable cause;
	private int status;


	// 생성자 
	public DBHandleException(String customMsg) {
		this.customMsg = customMsg;
	}
	
	public DBHandleException(String customMsg, int status) {
		this.customMsg = customMsg;
		this.status = status;
	}
	
	public DBHandleException(String customMsg, Throwable cause) {
		super(customMsg, cause);
		this.customMsg = customMsg;
		this.cause = cause;
	}

	public DBHandleException(String customMsg, Throwable cause, int status) {
		super(customMsg, cause);
		this.customMsg = customMsg;
		this.cause = cause;
		this.status = status;
	}
	
	public String getCustomMsg() {
		return customMsg;
	}

	public void setCustomMsg(String customMsg) {
		this.customMsg = customMsg;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Throwable getCause() { 
		return cause;
	}
	
	public void setCause(Throwable cause) {
		this.cause = cause;
	}
}
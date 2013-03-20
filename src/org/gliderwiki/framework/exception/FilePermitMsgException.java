package org.gliderwiki.framework.exception;

public class FilePermitMsgException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String customMsg;


	// 생성자 
	public FilePermitMsgException(String customMsg) {
		this.customMsg = customMsg;
	}

	public String getCustomMsg() {
		return customMsg;
	}

	public void setCustomMsg(String customMsg) {
		this.customMsg = customMsg;
	}
}
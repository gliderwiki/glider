package org.gliderwiki.framework.exception;

public class PageMoveException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String alertMsg;
	private String gotoUrl;
	private int status;
	
	public PageMoveException(String alertMsg, String gotoUrl, int status) {
		this.alertMsg = alertMsg;
		this.gotoUrl = gotoUrl;
		this.status = status;
	}	

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	public String getGotoUrl() {
		return gotoUrl;
	}

	public void setGotoUrl(String gotoUrl) {
		this.gotoUrl = gotoUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

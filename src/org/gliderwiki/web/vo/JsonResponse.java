/**
 * @FileName  : JsonResponse.java
 * @Project   : NightHawk
 * @Date      : 2012. 3. 31. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

import java.io.Serializable;

/**
 * @author ganji
 *
 */
public class JsonResponse implements Serializable{

	private static final long serialVersionUID = 5599194014834036299L;

	private ResponseStatus status;
	
	private ResultStatusInfo response;
	
	private Object result;
	
	public ResponseStatus getStatus() {
		return status;
	}
	
	public void setStatus(ResponseStatus status){
		this.status = status;
	}
	
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	public ResultStatusInfo getResponse() {
		return response;
	}

	public void setResponse(ResultStatusInfo response) {
		this.response = response;
	}



	public static class ResultStatusInfo implements Serializable{
		
		private static final long serialVersionUID = -5554464957529022795L;
		
		private String message;
		private String redirectUrl;
		private String errorMsg;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getRedirectUrl() {
			return redirectUrl;
		}

		public void setRedirectUrl(String redirectUrl) {
			this.redirectUrl = redirectUrl;
		}

		public String getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}
		
	}
	
	public enum ResponseStatus {
		SUCCESS("성공"),
		FAIL("실패");
		
		ResponseStatus(String description){
			this.description = description;
		}
		
		private String description;
		
		public String getDescription() {
			return description;
		}
	}
	
}

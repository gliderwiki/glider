package org.gliderwiki.web.vo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject
public class BaseObjectBean implements Serializable {
	
	private static final long serialVersionUID = 8468009001928148089L;
	
	/**
	 * 결과 값 int 
	 */
	private Integer rtnResult;
	
	/**
	 * 결과 코드 String 
	 */
	private String rtnStatus;
	
	
	/**
	 * 결과 메시지 
	 */
	private String rtnMsg;
	
	/**
	 * 이동 URL
	 */
	private String rtnUrl;
	
	
	/**
	 * 객체 toString
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * @return the rtnResult
	 */
	public Integer getRtnResult() {
		return rtnResult;
	}

	/**
	 * @param rtnResult the rtnResult to set
	 */
	public void setRtnResult(Integer rtnResult) {
		this.rtnResult = rtnResult;
	}

	/**
	 * @return the rtnStatus
	 */
	public String getRtnStatus() {
		return rtnStatus;
	}

	/**
	 * @param rtnStatus the rtnStatus to set
	 */
	public void setRtnStatus(String rtnStatus) {
		this.rtnStatus = rtnStatus;
	}

	/**
	 * @return the rtnMsg
	 */
	public String getRtnMsg() {
		return rtnMsg;
	}

	/**
	 * @param rtnMsg the rtnMsg to set
	 */
	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

	/**
	 * @return the rtnUrl
	 */
	public String getRtnUrl() {
		return rtnUrl;
	}

	/**
	 * @param rtnUrl the rtnUrl to set
	 */
	public void setRtnUrl(String rtnUrl) {
		this.rtnUrl = rtnUrl;
	}
	
}

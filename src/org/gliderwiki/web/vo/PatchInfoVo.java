/**
 * @FileName  : PatchInfoVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 18. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

import java.util.Date;

/**
 * @author yion
 *
 */
public class PatchInfoVo extends BaseObjectBean {

	String weDomain;
	String weActiveKey;
	String weEmail;
	Date weInstallDate;
	Date weAuthDate;
	String weAuthYn;
	String weVersionInfo;
	String weServerVerionInfo;
	
	
	
	
	/**
	 * @return the weServerVerionInfo
	 */
	public String getWeServerVerionInfo() {
		return weServerVerionInfo;
	}
	/**
	 * @param weServerVerionInfo the weServerVerionInfo to set
	 */
	public void setWeServerVerionInfo(String weServerVerionInfo) {
		this.weServerVerionInfo = weServerVerionInfo;
	}
	/**
	 * @return the weDomain
	 */
	public String getWeDomain() {
		return weDomain;
	}
	/**
	 * @param weDomain the weDomain to set
	 */
	public void setWeDomain(String weDomain) {
		this.weDomain = weDomain;
	}
	/**
	 * @return the weActiveKey
	 */
	public String getWeActiveKey() {
		return weActiveKey;
	}
	/**
	 * @param weActiveKey the weActiveKey to set
	 */
	public void setWeActiveKey(String weActiveKey) {
		this.weActiveKey = weActiveKey;
	}
	/**
	 * @return the weEmail
	 */
	public String getWeEmail() {
		return weEmail;
	}
	/**
	 * @param weEmail the weEmail to set
	 */
	public void setWeEmail(String weEmail) {
		this.weEmail = weEmail;
	}
	/**
	 * @return the weInstallDate
	 */
	public Date getWeInstallDate() {
		return weInstallDate;
	}
	/**
	 * @param weInstallDate the weInstallDate to set
	 */
	public void setWeInstallDate(Date weInstallDate) {
		this.weInstallDate = weInstallDate;
	}
	/**
	 * @return the weAuthDate
	 */
	public Date getWeAuthDate() {
		return weAuthDate;
	}
	/**
	 * @param weAuthDate the weAuthDate to set
	 */
	public void setWeAuthDate(Date weAuthDate) {
		this.weAuthDate = weAuthDate;
	}
	/**
	 * @return the weAuthYn
	 */
	public String getWeAuthYn() {
		return weAuthYn;
	}
	/**
	 * @param weAuthYn the weAuthYn to set
	 */
	public void setWeAuthYn(String weAuthYn) {
		this.weAuthYn = weAuthYn;
	}
	/**
	 * @return the weVersionInfo
	 */
	public String getWeVersionInfo() {
		return weVersionInfo;
	}
	/**
	 * @param weVersionInfo the weVersionInfo to set
	 */
	public void setWeVersionInfo(String weVersionInfo) {
		this.weVersionInfo = weVersionInfo;
	}
	
}

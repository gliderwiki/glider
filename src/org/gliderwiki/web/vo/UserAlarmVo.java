/**
 * @FileName  : UserAlarmVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 13. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

import org.gliderwiki.framework.orm.sql.annotation.Column;

/**
 * @author yion
 *
 */
public class UserAlarmVo {
	
	/**
	 * 사용자번호
	 */
	private Integer weUserIdx;
	
	/**
	 * 메타순번
	 */
	private Integer weMetaIdx;
		 
	/**
	 * 사용여부
	 */
	private String weUseYn;
		 
	/**
	 * 입력일
	 */
	private String weInsDate;
	
	/**
	 * 입력자
	 */
	private String weInsUser;
		 
	/**
	 * 수정일
	 */
	private String weUpdDate;
		 
	/**
	 * 수정자 
	 */
	private String weUpdUser;

	/**
	 * @return the weUserIdx
	 */
	public Integer getWeUserIdx() {
		return weUserIdx;
	}

	/**
	 * @param weUserIdx the weUserIdx to set
	 */
	public void setWeUserIdx(Integer weUserIdx) {
		this.weUserIdx = weUserIdx;
	}

	/**
	 * @return the weMetaIdx
	 */
	public Integer getWeMetaIdx() {
		return weMetaIdx;
	}

	/**
	 * @param weMetaIdx the weMetaIdx to set
	 */
	public void setWeMetaIdx(Integer weMetaIdx) {
		this.weMetaIdx = weMetaIdx;
	}

	/**
	 * @return the weUseYn
	 */
	public String getWeUseYn() {
		return weUseYn;
	}

	/**
	 * @param weUseYn the weUseYn to set
	 */
	public void setWeUseYn(String weUseYn) {
		this.weUseYn = weUseYn;
	}

	/**
	 * @return the weInsDate
	 */
	public String getWeInsDate() {
		return weInsDate;
	}

	/**
	 * @param weInsDate the weInsDate to set
	 */
	public void setWeInsDate(String weInsDate) {
		this.weInsDate = weInsDate;
	}

	/**
	 * @return the weInsUser
	 */
	public String getWeInsUser() {
		return weInsUser;
	}

	/**
	 * @param weInsUser the weInsUser to set
	 */
	public void setWeInsUser(String weInsUser) {
		this.weInsUser = weInsUser;
	}

	/**
	 * @return the weUpdDate
	 */
	public String getWeUpdDate() {
		return weUpdDate;
	}

	/**
	 * @param weUpdDate the weUpdDate to set
	 */
	public void setWeUpdDate(String weUpdDate) {
		this.weUpdDate = weUpdDate;
	}

	/**
	 * @return the weUpdUser
	 */
	public String getWeUpdUser() {
		return weUpdUser;
	}

	/**
	 * @param weUpdUser the weUpdUser to set
	 */
	public void setWeUpdUser(String weUpdUser) {
		this.weUpdUser = weUpdUser;
	}
	
	
}

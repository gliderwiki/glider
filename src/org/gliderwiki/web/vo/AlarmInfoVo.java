/**
 * @FileName  : AlarmInfoVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 13.
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
public class AlarmInfoVo {

	/**
	 * 알람 인덱스
	 */
	private Integer weAlarmIdx;


	/**
	 * 메타 인덱스
	 */
	private Integer weMetaIdx;

	/**
	 * 알람 타입
	 */
	private String weAlarmType;

	/**
	 * 알람 업무명
	 */
	private String weAlarmDomain;

	/**
	 * 알람 사용자 순번
	 */
	private Integer weUserIdx;

	/**
	 * 타겟 사용자 순번
	 */
	private Integer weTargetUserIdx;

	/**
	 * 알람 텍스트
	 */
	private String weAlarmText;

	/**
	 * 사용여부
	 */
	private String weUseYn;

	/**
	 * 입력일
	 */
	private Date weInsDate;

	/**
	 * 입력자
	 */
	private String weInsUser;

	/**
	 * @return the weInsDate
	 */
	public Date getWeInsDate() {
		return weInsDate;
	}

	/**
	 * @param weInsDate the weInsDate to set
	 */
	public void setWeInsDate(Date weInsDate) {
		this.weInsDate = weInsDate;
	}

	/**
	 * @return the weAlarmIdx
	 */
	public Integer getWeAlarmIdx() {
		return weAlarmIdx;
	}

	/**
	 * @param weAlarmIdx the weAlarmIdx to set
	 */
	public void setWeAlarmIdx(Integer weAlarmIdx) {
		this.weAlarmIdx = weAlarmIdx;
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
	 * @return the weAlarmType
	 */
	public String getWeAlarmType() {
		return weAlarmType;
	}

	/**
	 * @param weAlarmType the weAlarmType to set
	 */
	public void setWeAlarmType(String weAlarmType) {
		this.weAlarmType = weAlarmType;
	}

	/**
	 * @return the weAlarmDomain
	 */
	public String getWeAlarmDomain() {
		return weAlarmDomain;
	}

	/**
	 * @param weAlarmDomain the weAlarmDomain to set
	 */
	public void setWeAlarmDomain(String weAlarmDomain) {
		this.weAlarmDomain = weAlarmDomain;
	}

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
	 * @return the weTargetUserIdx
	 */
	public Integer getWeTargetUserIdx() {
		return weTargetUserIdx;
	}

	/**
	 * @param weTargetUserIdx the weTargetUserIdx to set
	 */
	public void setWeTargetUserIdx(Integer weTargetUserIdx) {
		this.weTargetUserIdx = weTargetUserIdx;
	}

	/**
	 * @return the weAlarmText
	 */
	public String getWeAlarmText() {
		return weAlarmText;
	}

	/**
	 * @param weAlarmText the weAlarmText to set
	 */
	public void setWeAlarmText(String weAlarmText) {
		this.weAlarmText = weAlarmText;
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


}

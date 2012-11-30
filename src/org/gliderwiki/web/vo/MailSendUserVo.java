/**
 * @FileName  : MailSendUserVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 26. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

import java.util.Date;

import org.gliderwiki.framework.util.DateUtil;


/**
 * @author yion
 *
 */
public class MailSendUserVo extends BaseObjectBean {

	/**
	 * 사용자 순번
	 */
	private Integer weUserIdx;
	
	/**
	 * 사용자 아이디 
	 */
	private String weUserId;
	
	/**
	 * 사용자 닉네임
	 */
	private String weUserNick;
	
	/**
	 * 사용자 이름 
	 */
	private String weUserName;
	
	
	/**
	 * 사용자 이메일
	 */
	private String weUserEmail;
	
	/**
	 * 사용자 가입일
	 */
	private Date weUserJoinDate;
	
	/**
	 * 인증여부
	 */
	private String weUserAuthYn;
	
	/**
	 * 인증일
	 */
	private Date weUserAuthDate;
	
	/**
	 * 메일전송일
	 */
	private Date weInsDate;
	
	/**
	 * 전송 결과 상태
	 */
	private String weSendStatus;

	/**
	 * 최종방문일
	 */
	private Date weVisitDate;
	
	/**
	 * 회원 등급
	 */
	private Integer weGrade;
	
	
	/**
	 * 회원 포인트 
	 */
	private String wePoint;
	
	/**
	 * 전문사용자여부 
	 */
	private String weTechYn;

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
	 * @return the weUserId
	 */
	public String getWeUserId() {
		return weUserId;
	}

	/**
	 * @param weUserId the weUserId to set
	 */
	public void setWeUserId(String weUserId) {
		this.weUserId = weUserId;
	}

	/**
	 * @return the weUserNick
	 */
	public String getWeUserNick() {
		return weUserNick;
	}

	/**
	 * @param weUserNick the weUserNick to set
	 */
	public void setWeUserNick(String weUserNick) {
		this.weUserNick = weUserNick;
	}

	/**
	 * @return the weUserEmail
	 */
	public String getWeUserEmail() {
		return weUserEmail;
	}

	/**
	 * @param weUserEmail the weUserEmail to set
	 */
	public void setWeUserEmail(String weUserEmail) {
		this.weUserEmail = weUserEmail;
	}

	/**
	 * @return the weUserJoinDate
	 */
	public Date getWeUserJoinDate() {
		return weUserJoinDate;
	}

	/**
	 * @param weUserJoinDate the weUserJoinDate to set
	 */
	public void setWeUserJoinDate(Date weUserJoinDate) {
		this.weUserJoinDate = weUserJoinDate;
	}

	/**
	 * @return the weUserAuthYn
	 */
	public String getWeUserAuthYn() {
		return weUserAuthYn;
	}

	/**
	 * @param weUserAuthYn the weUserAuthYn to set
	 */
	public void setWeUserAuthYn(String weUserAuthYn) {
		this.weUserAuthYn = weUserAuthYn;
	}

	/**
	 * @return the weUserAuthDate
	 */
	public Date getWeUserAuthDate() {
		return weUserAuthDate;
	}

	/**
	 * @param weUserAuthDate the weUserAuthDate to set
	 */
	public void setWeUserAuthDate(Date weUserAuthDate) {
		this.weUserAuthDate = weUserAuthDate;
	}

	/**
	 * @return the weInsdate
	 */
	public Date getWeInsDate() {
		return weInsDate;
	}

	/**
	 * @param weInsdate the weInsdate to set
	 */
	public void setWeInsDate(Date weInsDate) {
		this.weInsDate = weInsDate;
	}

	/**
	 * @return the weSendStatus
	 */
	public String getWeSendStatus() {
		return weSendStatus;
	}

	/**
	 * @param weSendStatus the weSendStatus to set
	 */
	public void setWeSendStatus(String weSendStatus) {
		this.weSendStatus = weSendStatus;
	}

	/**
	 * @return the weVisitDate
	 */
	public Date getWeVisitDate() {
		return weVisitDate;
	}

	/**
	 * @param weVisitDate the weVisitDate to set
	 */
	public void setWeVisitDate(Date weVisitDate) {
		this.weVisitDate = weVisitDate;
	}

	/**
	 * @return the weGrade
	 */
	public Integer getWeGrade() {
		return weGrade;
	}

	/**
	 * @param weGrade the weGrade to set
	 */
	public void setWeGrade(Integer weGrade) {
		this.weGrade = weGrade;
	}

	/**
	 * @return the wePoint
	 */
	public String getWePoint() {
		return wePoint;
	}

	/**
	 * @param wePoint the wePoint to set
	 */
	public void setWePoint(String wePoint) {
		this.wePoint = wePoint;
	}

	/**
	 * @return the weTechYn
	 */
	public String getWeTechYn() {
		return weTechYn;
	}

	/**
	 * @param weTechYn the weTechYn to set
	 */
	public void setWeTechYn(String weTechYn) {
		this.weTechYn = weTechYn;
	}

	/**
	 * @return the weUserName
	 */
	public String getWeUserName() {
		return weUserName;
	}

	/**
	 * @param weUserName the weUserName to set
	 */
	public void setWeUserName(String weUserName) {
		this.weUserName = weUserName;
	}
	
	
}

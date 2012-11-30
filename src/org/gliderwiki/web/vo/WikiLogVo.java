/**
 * @FileName  : WikiLogVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 11. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;


/**
 * @author yion
 *
 */
public class WikiLogVo extends BaseObjectBean {

	/**
	 * 공간번호 
	 */
	private Integer weSpaceIdx;
	
	/**
	 * 공간명
	 */
	private String weSpaceName;
	
	/**
	 * 공간설명
	 */
	private String weSpaceDesc;
	
	/**
	 * 공간 개설자 
	 */
	private String weAdminDix;
	
	/**
	 * 위키 인덱스 
	 */
	private Integer weWikiIdx;
	
	
	/**
	 * 위키명
	 */
	private String weWikiTitle;
	
	/**
	 * 위키 공감 수 
	 */
	private Integer weWikiAgree;
	
	/**
	 * 위키 조회수 
	 */
	private Integer weWikiViewCnt;
	
	/**
	 * 위키 보호여부
	 */
	private String weWikiProtect;
	
	/**
	 * 위키 입력자
	 */
	private String weInsUser;
	
	/**
	 * 위키 입력자 명 
	 */
	private String weInsUserName;
	
	/**
	 * 위키 저장 상태 T:임시, S:저장
	 */
	private String weWikiStatus;
	
	/**
	 * 사용자 순번
	 */
	private Integer weUserIdx;
	
	/**
	 * 위키 버전
	 */
	private String weWikiRevision;
	
	/**
	 * 위키 작성 액션  I:작성, U:수정, D:삭제
	 */
	private String weWikiActionType;
	
	/**
	 * 위키로그 저장일 
	 */
	private String weInsDate;
	
	/**
	 * 위키 개설일 
	 */
	private String weCreDate;

	/**
	 * @return the weSpaceIdx
	 */
	public Integer getWeSpaceIdx() {
		return weSpaceIdx;
	}

	/**
	 * @param weSpaceIdx the weSpaceIdx to set
	 */
	public void setWeSpaceIdx(Integer weSpaceIdx) {
		this.weSpaceIdx = weSpaceIdx;
	}

	/**
	 * @return the weSpaceName
	 */
	public String getWeSpaceName() {
		return weSpaceName;
	}

	/**
	 * @param weSpaceName the weSpaceName to set
	 */
	public void setWeSpaceName(String weSpaceName) {
		this.weSpaceName = weSpaceName;
	}

	/**
	 * @return the weSpaceDesc
	 */
	public String getWeSpaceDesc() {
		return weSpaceDesc;
	}

	/**
	 * @param weSpaceDesc the weSpaceDesc to set
	 */
	public void setWeSpaceDesc(String weSpaceDesc) {
		this.weSpaceDesc = weSpaceDesc;
	}

	/**
	 * @return the weAdminDix
	 */
	public String getWeAdminDix() {
		return weAdminDix;
	}

	/**
	 * @param weAdminDix the weAdminDix to set
	 */
	public void setWeAdminDix(String weAdminDix) {
		this.weAdminDix = weAdminDix;
	}

	/**
	 * @return the weWikiIdx
	 */
	public Integer getWeWikiIdx() {
		return weWikiIdx;
	}

	/**
	 * @param weWikiIdx the weWikiIdx to set
	 */
	public void setWeWikiIdx(Integer weWikiIdx) {
		this.weWikiIdx = weWikiIdx;
	}

	/**
	 * @return the weWikiTitle
	 */
	public String getWeWikiTitle() {
		return weWikiTitle;
	}

	/**
	 * @param weWikiTitle the weWikiTitle to set
	 */
	public void setWeWikiTitle(String weWikiTitle) {
		this.weWikiTitle = weWikiTitle;
	}

	/**
	 * @return the weWikiAgree
	 */
	public Integer getWeWikiAgree() {
		return weWikiAgree;
	}

	/**
	 * @param weWikiAgree the weWikiAgree to set
	 */
	public void setWeWikiAgree(Integer weWikiAgree) {
		this.weWikiAgree = weWikiAgree;
	}

	/**
	 * @return the weWikiViewCnt
	 */
	public Integer getWeWikiViewCnt() {
		return weWikiViewCnt;
	}

	/**
	 * @param weWikiViewCnt the weWikiViewCnt to set
	 */
	public void setWeWikiViewCnt(Integer weWikiViewCnt) {
		this.weWikiViewCnt = weWikiViewCnt;
	}

	/**
	 * @return the weWikiProtect
	 */
	public String getWeWikiProtect() {
		return weWikiProtect;
	}

	/**
	 * @param weWikiProtect the weWikiProtect to set
	 */
	public void setWeWikiProtect(String weWikiProtect) {
		this.weWikiProtect = weWikiProtect;
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
	 * @return the weInsUserName
	 */
	public String getWeInsUserName() {
		return weInsUserName;
	}

	/**
	 * @param weInsUserName the weInsUserName to set
	 */
	public void setWeInsUserName(String weInsUserName) {
		this.weInsUserName = weInsUserName;
	}

	/**
	 * @return the weWikiStatus
	 */
	public String getWeWikiStatus() {
		return weWikiStatus;
	}

	/**
	 * @param weWikiStatus the weWikiStatus to set
	 */
	public void setWeWikiStatus(String weWikiStatus) {
		this.weWikiStatus = weWikiStatus;
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
	 * @return the weWikiRevision
	 */
	public String getWeWikiRevision() {
		return weWikiRevision;
	}

	/**
	 * @param weWikiRevision the weWikiRevision to set
	 */
	public void setWeWikiRevision(String weWikiRevision) {
		this.weWikiRevision = weWikiRevision;
	}

	/**
	 * @return the weWikiActionType
	 */
	public String getWeWikiActionType() {
		return weWikiActionType;
	}

	/**
	 * @param weWikiActionType the weWikiActionType to set
	 */
	public void setWeWikiActionType(String weWikiActionType) {
		this.weWikiActionType = weWikiActionType;
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
	 * @return the weCreDate
	 */
	public String getWeCreDate() {
		return weCreDate;
	}

	/**
	 * @param weCreDate the weCreDate to set
	 */
	public void setWeCreDate(String weCreDate) {
		this.weCreDate = weCreDate;
	}
	
	
	
}

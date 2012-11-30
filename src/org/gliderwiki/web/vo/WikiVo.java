/**
 * @FileName  : WikiVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 19. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

/**
 * @author yion
 *
 */
public class WikiVo extends BaseObjectBean {
	/**
	 * 위키 순번 
	 */
	private Integer weWikiIdx;
	
	/**
	 * 위키 공간 번호 
	 */
	private Integer weSpaceIdx;
	
	/**
	 * 부모 번호 
	 */
	private Integer weWikiPrentidx;
	
	/**
	 * 정렬 번호 
	 */
	private Integer weWikiOrderidx;
	
	/**
	 * 깊이 번호 
	 */
	private Integer weWikiDepthidx;
	
	/**
	 * 제목 
	 */
	private String weWikiTitle;
	
	/**
	 * 접근 제어 
	 */
	private String weWikiPrivacy;
	
	/**
	 * 위키 텍스트 
	 */
	private String weWikiText;
	
	/**
	 * 위키 마크업 
	 */
	private String weWikiMarkup;
	
	/**
	 * 위키 버전 정보 
	 */
	private String weWikiRevision;
	
	/**
	 * 위키 저장 상태 
	 */
	private String weWikiStatus;
	
	/**
	 * 피인용횟수
	 */
	private String weWikiQuota;
	
	/**
	 * 위키 주소 
	 */
	private String weWikiUrl;
	
	/**
	 * 위키 공감수 
	 */
	private Integer weWikiAgree;
	
	/**
	 * 조회수 
	 */
	private Integer weWikiViewCnt;
	
	/**
	 * 이전 참조 글 주소
	 */
	private String weWikiPrev;
	
	/**
	 * 이후 참조 글 주소 
	 */
	private String weWikiNext;
	
	/**
	 * 아이피 
	 */
	private String weUserIp;
	
	/**
	 * 보호여부
	 */
	private String weWikiProtect;
	
	/**
	 * 수정 이유
	 */
	private String weEditText;
	
	/**
	 * 사용여부
	 */
	private String weUseYn;
	
	/**
	 * 입력자
	 */
	private String weInsUser;
	
	/**
	 * 입력일
	 */
	private String weInsDate;
	
	/**
	 * 수정가능 여부 
	 */
	private String weEditYn;

	/**
	 * 수정자 
	 */
	private String weUpdUser;
	
	
	/**
	 * 수정일 
	 */
	private String weUpdDate;
	
	/**
	 * 등록자 
	 */
	private String weUserNick;
	
	


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
	 * @return the weSpaceidx
	 */
	public Integer getWeSpaceIdx() {
		return weSpaceIdx;
	}

	/**
	 * @param weSpaceidx the weSpaceidx to set
	 */
	public void setWeSpaceIdx(Integer weSpaceIdx) {
		this.weSpaceIdx = weSpaceIdx;
	}

	/**
	 * @return the weWikiPrentidx
	 */
	public Integer getWeWikiPrentidx() {
		return weWikiPrentidx;
	}

	/**
	 * @param weWikiPrentidx the weWikiPrentidx to set
	 */
	public void setWeWikiPrentidx(Integer weWikiPrentidx) {
		this.weWikiPrentidx = weWikiPrentidx;
	}

	/**
	 * @return the weWikiOrderidx
	 */
	public Integer getWeWikiOrderidx() {
		return weWikiOrderidx;
	}

	/**
	 * @param weWikiOrderidx the weWikiOrderidx to set
	 */
	public void setWeWikiOrderidx(Integer weWikiOrderidx) {
		this.weWikiOrderidx = weWikiOrderidx;
	}

	/**
	 * @return the weWikiDepthidx
	 */
	public Integer getWeWikiDepthidx() {
		return weWikiDepthidx;
	}

	/**
	 * @param weWikiDepthidx the weWikiDepthidx to set
	 */
	public void setWeWikiDepthidx(Integer weWikiDepthidx) {
		this.weWikiDepthidx = weWikiDepthidx;
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
	 * @return the weWikiPrivacy
	 */
	public String getWeWikiPrivacy() {
		return weWikiPrivacy;
	}

	/**
	 * @param weWikiPrivacy the weWikiPrivacy to set
	 */
	public void setWeWikiPrivacy(String weWikiPrivacy) {
		this.weWikiPrivacy = weWikiPrivacy;
	}

	/**
	 * @return the weWikiText
	 */
	public String getWeWikiText() {
		return weWikiText;
	}

	/**
	 * @param weWikiText the weWikiText to set
	 */
	public void setWeWikiText(String weWikiText) {
		this.weWikiText = weWikiText;
	}

	/**
	 * @return the weWikiMarkup
	 */
	public String getWeWikiMarkup() {
		return weWikiMarkup;
	}

	/**
	 * @param weWikiMarkup the weWikiMarkup to set
	 */
	public void setWeWikiMarkup(String weWikiMarkup) {
		this.weWikiMarkup = weWikiMarkup;
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
	 * @return the weWikiQuota
	 */
	public String getWeWikiQuota() {
		return weWikiQuota;
	}

	/**
	 * @param weWikiQuota the weWikiQuota to set
	 */
	public void setWeWikiQuota(String weWikiQuota) {
		this.weWikiQuota = weWikiQuota;
	}

	/**
	 * @return the weWikiUrl
	 */
	public String getWeWikiUrl() {
		return weWikiUrl;
	}

	/**
	 * @param weWikiUrl the weWikiUrl to set
	 */
	public void setWeWikiUrl(String weWikiUrl) {
		this.weWikiUrl = weWikiUrl;
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
	 * @return the weWikiViewcnt
	 */
	public Integer getWeWikiViewCnt() {
		return weWikiViewCnt;
	}

	/**
	 * @param weWikiViewcnt the weWikiViewcnt to set
	 */
	public void setWeWikiViewcnt(Integer weWikiViewCnt) {
		this.weWikiViewCnt = weWikiViewCnt;
	}

	/**
	 * @return the weWikiPrrv
	 */
	public String getWeWikiPrev() {
		return weWikiPrev;
	}

	/**
	 * @param weWikiPrrv the weWikiPrrv to set
	 */
	public void setWeWikiPrev(String weWikiPrev) {
		this.weWikiPrev = weWikiPrev;
	}

	/**
	 * @return the weWikiNext
	 */
	public String getWeWikiNext() {
		return weWikiNext;
	}

	/**
	 * @param weWikiNext the weWikiNext to set
	 */
	public void setWeWikiNext(String weWikiNext) {
		this.weWikiNext = weWikiNext;
	}

	/**
	 * @return the weUserIp
	 */
	public String getWeUserIp() {
		return weUserIp;
	}

	/**
	 * @param weUserIp the weUserIp to set
	 */
	public void setWeUserIp(String weUserIp) {
		this.weUserIp = weUserIp;
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
	 * @return the weEditText
	 */
	public String getWeEditText() {
		return weEditText;
	}

	/**
	 * @param weEditText the weEditText to set
	 */
	public void setWeEditText(String weEditText) {
		this.weEditText = weEditText;
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
	 * @return the weEditYn
	 */
	public String getWeEditYn() {
		return weEditYn;
	}

	/**
	 * @param weEditYn the weEditYn to set
	 */
	public void setWeEditYn(String weEditYn) {
		this.weEditYn = weEditYn;
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
	 * @return the we_user_nick
	 */
	public String getWeUserNick() {
		return weUserNick;
	}

	/**
	 * @param we_user_nick the we_user_nick to set
	 */
	public void setWeUserNick(String weUserNick) {
		this.weUserNick = weUserNick;
	}

}

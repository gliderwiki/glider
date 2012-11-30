/**
 * @FileName  : MemberSessionVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 13.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;


/**
 * @author yion
 *
 */
public class MemberSessionVo extends BaseObjectBean {

	/**
	 *
	 */
	private static final long serialVersionUID = 3016889534433630096L;

	public static final GuestUser GUEST_USER = new GuestUser();

	private Integer weUserIdx;

	/**
	 * 로그인 아이디
	 */
	private String weUserId;

	/**
	 * 회원 이름
	 */
	private String weUserName;

	/**
	 * 회원 비밀번호 키
	 */
	private String weUserKey;

	/**
	 * 회원 비밀번호
	 */
	private String weUserPwd;

	/**
	 * 회원 닉네임
	 */
	private String weUserNick;

	/**
	 * 회원 인증 여부
	 */
	private String weUserAuthYn;

	/**
	 * 회원 이메일
	 */
	private String weUserEmail;

	/**
	 * 회원블로그
	 */
	private String weUserSite;

	/**
	 * 회원썸네일경로
	 */
	private String weThumbPath;

	/**
	 * 회원썸네일명
	 */
	private String weThumbName;

	/**
	 * 회원등급
	 */
	private Integer weGrade;

	/**
	 * 전문가여부
	 */
	private String weTechYn;

	/**
	 * 회원포인트
	 */
	private Integer wePoint;

	public MemberSessionVo() {

	}

	public MemberSessionVo(String weUserId, String weUserName) {
		this.weUserId = weUserId;
		this.weUserName = weUserName;
	}
	
	public MemberSessionVo(Integer weUserIdx, String weUserId, String weUserName) {
		this.weUserIdx = weUserIdx;
		this.weUserId = weUserId;
		this.weUserName = weUserName;
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

	/**
	 * @return the weUserKey
	 */
	public String getWeUserKey() {
		return weUserKey;
	}

	/**
	 * @param weUserKey the weUserKey to set
	 */
	public void setWeUserKey(String weUserKey) {
		this.weUserKey = weUserKey;
	}

	/**
	 * @return the weUserPwd
	 */
	public String getWeUserPwd() {
		return weUserPwd;
	}

	/**
	 * @param weUserPwd the weUserPwd to set
	 */
	public void setWeUserPwd(String weUserPwd) {
		this.weUserPwd = weUserPwd;
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
	 * @return the weUserSite
	 */
	public String getWeUserSite() {
		return weUserSite;
	}

	/**
	 * @param weUserSite the weUserSite to set
	 */
	public void setWeUserSite(String weUserSite) {
		this.weUserSite = weUserSite;
	}

	/**
	 * @return the weThumbPath
	 */
	public String getWeThumbPath() {
		return weThumbPath;
	}

	/**
	 * @param weThumbPath the weThumbPath to set
	 */
	public void setWeThumbPath(String weThumbPath) {
		this.weThumbPath = weThumbPath;
	}

	/**
	 * @return the weThumbName
	 */
	public String getWeThumbName() {
		return weThumbName;
	}

	/**
	 * @param weThumbName the weThumbName to set
	 */
	public void setWeThumbName(String weThumbName) {
		this.weThumbName = weThumbName;
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
	 * @return the wePoint
	 */
	public Integer getWePoint() {
		return wePoint;
	}

	/**
	 * @param wePoint the wePoint to set
	 */
	public void setWePoint(Integer wePoint) {
		this.wePoint = wePoint;
	}

	public boolean isGuest() {
		return false;
	}

	public static class GuestUser extends MemberSessionVo {
		private static final long serialVersionUID = 6275403116070086438L;

		private GuestUser guestUser;

		GuestUser() {
			guestUser = new GuestUser(0, "GUEST","GUEST");
		}

		GuestUser(Integer weUserIdx, String weUserId, String weUserName) {
			super(weUserIdx, weUserId, weUserName);
		}

		@Override
		public boolean isGuest() {
			return true;
		}

		public GuestUser getGuestUser() {
			return guestUser;
		}
	}
}

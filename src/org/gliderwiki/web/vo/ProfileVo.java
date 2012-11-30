/**
 * @FileName  : ProfileVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 5. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 회원 프로필 수정 VO
 */
package org.gliderwiki.web.vo;

import java.io.File;


/**
 * @author yion
 *
 */
public class ProfileVo extends BaseObjectBean {
	
	/**
	 * WE_USER.we_user_idx
	 */
	private Integer weUserIdx;
	
	/**
	 * WE_USER.we_user_nick
	 */
	private String weUserNick;
	
	/**
	 * WE_USER.we_user_name
	 */
	private String weUserName;
	
	/**
	 * WE_USER.we_user_key
	 */
	private String weUserKey;
	
	/**
	 * WE_USER.we_user_pwd
	 */
	private String weUserPwd;
	
	/**
	 * WE_PROFILE.we_user_site
	 */
	private String weUserSite;

	/**
	 * WE_PROFILE.we_file_real_name
	 */
	private String weFileRealName;

	/**
	 * WE_PROFILE.we_file_save_name
	 */
	private String weFileSaveName;
	
	/**
	 * WE_PROFILE.we_file_save_path
	 */
	private String weFileSavePath;
	
	/**
	 * WE_PROFILE.we_thumb_path
	 */
	private String weThumbPath;
	
	/**
	 * WE_PROFILE.we_thumb_name
	 */
	private String weThumbName;

	/**
	 * WE_FILE.we_file_idx
	 */
	private Integer weFileIdx;
	
	/**
	 * as-is 사용자 패스워드 
	 */
	private String userPassword;
	
	/**
	 * 파일 정보가 존재하는지 구분 값 
	 */
	private String isUpload;
	
	/**
	 * 원본 temp 파일경로
	 */
	private File fromFilePath;
	
	/**
	 * 실제 저장 파일경로 
	 */
	private File toFilePath;
	
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
	 * @return the weFileRealName
	 */
	public String getWeFileRealName() {
		return weFileRealName;
	}

	/**
	 * @param weFileRealName the weFileRealName to set
	 */
	public void setWeFileRealName(String weFileRealName) {
		this.weFileRealName = weFileRealName;
	}

	/**
	 * @return the weFileSaveName
	 */
	public String getWeFileSaveName() {
		return weFileSaveName;
	}

	/**
	 * @param weFileSaveName the weFileSaveName to set
	 */
	public void setWeFileSaveName(String weFileSaveName) {
		this.weFileSaveName = weFileSaveName;
	}

	/**
	 * @return the weFileSavePath
	 */
	public String getWeFileSavePath() {
		return weFileSavePath;
	}

	/**
	 * @param weFileSavePath the weFileSavePath to set
	 */
	public void setWeFileSavePath(String weFileSavePath) {
		this.weFileSavePath = weFileSavePath;
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
	 * @return the weFileIdx
	 */
	public Integer getWeFileIdx() {
		return weFileIdx;
	}

	/**
	 * @param weFileIdx the weFileIdx to set
	 */
	public void setWeFileIdx(Integer weFileIdx) {
		this.weFileIdx = weFileIdx;
	}

	
	
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the isUpload
	 */
	public String getIsUpload() {
		return isUpload;
	}

	/**
	 * @param isUpload the isUpload to set
	 */
	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	/**
	 * @return the fromFilePath
	 */
	public File getFromFilePath() {
		return fromFilePath;
	}

	/**
	 * @param fromFilePath the fromFilePath to set
	 */
	public void setFromFilePath(File fromFilePath) {
		this.fromFilePath = fromFilePath;
	}

	/**
	 * @return the toFilePath
	 */
	public File getToFilePath() {
		return toFilePath;
	}

	/**
	 * @param toFilePath the toFilePath to set
	 */
	public void setToFilePath(File toFilePath) {
		this.toFilePath = toFilePath;
	}
	
	
}

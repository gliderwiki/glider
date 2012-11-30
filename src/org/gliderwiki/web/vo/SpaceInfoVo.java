/**
 * @FileName  : SpaceInfo.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 14.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.AuthorityType;


/**
 * @author bluepoet
 *
 */
public class SpaceInfoVo {
	private Integer spaceIdx;

	private String spaceName;

	private String spaceDesc;

	private String profileImg;

	private boolean exposed;

	private AuthorityType viewPrivacy;

	private AuthorityType editPrivacy;

	private Integer adminUserIdx;

	List<Map<String, Object>> viewParticipant;

	List<Map<String, Object>> editParticipant;

	public SpaceInfoVo() {

	}

	public Integer getSpaceIdx() {
		return spaceIdx;
	}

	public void setSpaceIdx(Integer spaceIdx) {
		this.spaceIdx = spaceIdx;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getSpaceDesc() {
		return spaceDesc;
	}

	public void setSpaceDesc(String spaceDesc) {
		this.spaceDesc = spaceDesc;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public AuthorityType getViewPrivacy() {
		return viewPrivacy;
	}

	public void setViewPrivacy(AuthorityType viewPrivacy) {
		this.viewPrivacy = viewPrivacy;
	}

	public AuthorityType getEditPrivacy() {
		return editPrivacy;
	}

	public void setEditPrivacy(AuthorityType editPrivacy) {
		this.editPrivacy = editPrivacy;
	}

	public List<Map<String, Object>> getViewParticipant() {
		return viewParticipant;
	}

	public void setViewParticipant(List<Map<String, Object>> viewParticipant) {
		this.viewParticipant = viewParticipant;
	}

	public List<Map<String, Object>> getEditParticipant() {
		return editParticipant;
	}

	public void setEditParticipant(List<Map<String, Object>> editParticipant) {
		this.editParticipant = editParticipant;
	}

	public Integer getAdminUserIdx() {
		return adminUserIdx;
	}

	public void setAdminUserIdx(Integer adminUserIdx) {
		this.adminUserIdx = adminUserIdx;
	}
}
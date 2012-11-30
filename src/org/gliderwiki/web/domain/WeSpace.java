/**
 * @FileName  : WeSpace.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 2.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * @author bluepoet
 *
 */
@Table("WE_SPACE")
public class WeSpace extends BaseObjectBean {

	private static final long serialVersionUID = 1699467698164836003L;

	@Column(name = "we_space_idx", primaryKey = true, autoIncrement = true)
	private Integer we_space_idx;

	@Column(name = "we_space_name")
	private String we_space_name;

	@Column(name = "we_space_desc")
	private String we_space_desc;

	@Column(name = "we_space_exposed")
	private String we_space_exposed;

	@Column(name = "we_view_privacy")
	private AuthorityType we_view_privacy;

	@Column(name = "we_edit_privacy")
	private AuthorityType we_edit_privacy;

	@Column(name = "we_admin_idx")
	private Integer we_admin_idx;

	@Column(name = "we_ins_user")
	private Integer we_ins_user;

	@Column(name = "we_ins_date")
	private Date we_ins_date;

	@Column(name = "we_upd_user")
	private Integer we_upd_user;

	@Column(name = "we_upd_date")
	private Date we_upd_date;

	/*
	 * 공간 사용여부-기본 Y, 삭제시 N
	 */
	@Column(name = "we_use_yn")
	private String we_use_yn;

	private String we_view_data;

	private String we_edit_data;

	private String we_view_name;

	private String we_edit_name;

	private String we_upload_imgName;

	/**
	 * 공간 개설자 닉네임
	 */
	private String we_space_admin_nick;

	public WeSpace() {

	}

	public WeSpace(Integer we_space_idx) {
		this.we_space_idx = we_space_idx;
	}

	public WeSpace(Integer we_space_idx, String we_space_name, String we_space_desc, String we_space_exposed,
			AuthorityType we_view_privacy, AuthorityType we_edit_privacy, Integer we_ins_user, Date we_ins_date,
			Integer we_admin_idx) {
		this.we_space_idx = we_space_idx;
		this.we_space_name = we_space_name;
		this.we_space_desc = we_space_desc;
		this.we_space_exposed = we_space_exposed;
		this.we_view_privacy = we_view_privacy;
		this.we_edit_privacy = we_edit_privacy;
		this.we_ins_user = we_ins_user;
		this.we_admin_idx = we_ins_user;
		this.we_ins_date = we_ins_date;
		this.we_upd_user = we_ins_user;
		this.we_upd_date = new Date();
		this.we_use_yn = "Y";
	}

	public WeSpace(String we_space_name, String we_space_desc, String we_space_exposed, AuthorityType we_view_privacy,
			AuthorityType we_edit_privacy, Integer we_admin_idx, Integer we_ins_user) {
		this.we_space_name = we_space_name;
		this.we_space_desc = we_space_desc;
		this.we_space_exposed = we_space_exposed;
		this.we_view_privacy = we_view_privacy;
		this.we_edit_privacy = we_edit_privacy;
		this.we_admin_idx = we_admin_idx;
		this.we_ins_user = we_ins_user;
		this.we_ins_date = new Date();
		this.we_use_yn = "Y";
	}

	/**
	 * @return the we_upload_imgName
	 */
	public String getWe_upload_imgName() {
		return we_upload_imgName;
	}

	/**
	 * @param we_upload_imgName
	 *            the we_upload_imgName to set
	 */
	public void setWe_upload_imgName(String we_upload_imgName) {
		this.we_upload_imgName = we_upload_imgName;
	}

	/**
	 * @return the we_view_data
	 */
	public String getWe_view_data() {
		return we_view_data;
	}

	/**
	 * @param we_view_data
	 *            the we_view_data to set
	 */
	public void setWe_view_data(String we_view_data) {
		this.we_view_data = we_view_data;
	}

	/**
	 * @return the we_edit_data
	 */
	public String getWe_edit_data() {
		return we_edit_data;
	}

	/**
	 * @param we_edit_data
	 *            the we_edit_data to set
	 */
	public void setWe_edit_data(String we_edit_data) {
		this.we_edit_data = we_edit_data;
	}

	public Integer getWe_space_idx() {
		return we_space_idx;
	}

	/**
	 * @return the we_space_exposed
	 */
	public String getWe_space_exposed() {
		return we_space_exposed;
	}

	/**
	 * @param we_space_exposed
	 *            the we_space_exposed to set
	 */
	public void setWe_space_exposed(String we_space_exposed) {
		this.we_space_exposed = we_space_exposed;
	}

	public void setWe_space_idx(Integer we_space_idx) {
		this.we_space_idx = we_space_idx;
	}

	public String getWe_space_name() {
		return we_space_name;
	}

	public void setWe_space_name(String we_space_name) {
		this.we_space_name = we_space_name;
	}

	public String getWe_space_desc() {
		return we_space_desc;
	}

	public void setWe_space_desc(String we_space_desc) {
		this.we_space_desc = we_space_desc;
	}

	public AuthorityType getWe_view_privacy() {
		return we_view_privacy;
	}

	public void setWe_view_privacy(AuthorityType we_view_privacy) {
		this.we_view_privacy = we_view_privacy;
	}

	public AuthorityType getWe_edit_privacy() {
		return we_edit_privacy;
	}

	public void setWe_edit_privacy(AuthorityType we_edit_privacy) {
		this.we_edit_privacy = we_edit_privacy;
	}

	public Integer getWe_admin_idx() {
		return we_admin_idx;
	}

	public void setWe_admin_idx(Integer we_admin_idx) {
		this.we_admin_idx = we_admin_idx;
	}

	/**
	 * @return the we_ins_user
	 */
	public Integer getWe_ins_user() {
		return we_ins_user;
	}

	/**
	 * @param we_ins_user
	 *            the we_ins_user to set
	 */
	public void setWe_ins_user(Integer we_ins_user) {
		this.we_ins_user = we_ins_user;
	}

	public Date getWe_ins_date() {
		return we_ins_date;
	}

	public void setWe_ins_date(Date we_ins_date) {
		this.we_ins_date = we_ins_date;
	}

	/**
	 * @return the we_upd_user
	 */
	public Integer getWe_upd_user() {
		return we_upd_user;
	}

	/**
	 * @param we_upd_user
	 *            the we_upd_user to set
	 */
	public void setWe_upd_user(Integer we_upd_user) {
		this.we_upd_user = we_upd_user;
	}

	public Date getWe_upd_date() {
		return we_upd_date;
	}

	public void setWe_upd_date(Date we_upd_date) {
		this.we_upd_date = we_upd_date;
	}

	/**
	 * @return the we_use_yn
	 */
	public String getWe_use_yn() {
		return we_use_yn;
	}

	/**
	 * @param we_use_yn
	 *            the we_use_yn to set
	 */
	public void setWe_use_yn(String we_use_yn) {
		this.we_use_yn = we_use_yn;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the we_view_name
	 */
	public String getWe_view_name() {
		return we_view_name;
	}

	/**
	 * @param we_view_name
	 *            the we_view_name to set
	 */
	public void setWe_view_name(String we_view_name) {
		this.we_view_name = we_view_name;
	}

	/**
	 * @return the we_edit_name
	 */
	public String getWe_edit_name() {
		return we_edit_name;
	}

	/**
	 * @param we_edit_name
	 *            the we_edit_name to set
	 */
	public void setWe_edit_name(String we_edit_name) {
		this.we_edit_name = we_edit_name;
	}

	/**
	 * @return the we_space_admin_nick
	 */
	public String getWe_space_admin_nick() {
		return we_space_admin_nick;
	}

	/**
	 * @param we_space_admin_nick
	 *            the we_space_admin_nick to set
	 */
	public void setWe_space_admin_nick(String we_space_admin_nick) {
		this.we_space_admin_nick = we_space_admin_nick;
	}

	public boolean isUploadImgName() {
		if (StringUtils.isEmpty(this.we_upload_imgName)) {
			return false;
		}

		return true;
	}

	public boolean isAllGroupViewPrivacy() {
		if (AuthorityType.ALLGROUP == this.we_view_privacy) {
			return false;
		}

		return true;
	}

	public boolean isAllGroupEditPrivacy() {
		if (AuthorityType.ALLGROUP == this.we_edit_privacy) {
			return false;
		}

		return true;
	}

	public boolean doUpdate() {
		if(this.we_upd_user != null) {
			return true;
		}

		return false;
	}

	public void setSpaceAdministrator(int userIdx) {
		this.we_admin_idx = userIdx;
		this.we_ins_user = userIdx;
	}

	public List<Integer> getSplitedAuthorityData(String authorityData) {
		List<Integer> splitedAuthorityData = Lists.newArrayList();

		Iterable<String> data = Splitter.on(',').trimResults().omitEmptyStrings().split(authorityData);
		Iterator<String> iter = data.iterator();

		while (iter.hasNext()) {
			int authorityUser = Integer.parseInt(iter.next());
		}

		return null;
	}

}
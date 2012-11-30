/**
 * @FileName  : WE_SPACE_GROUP.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 6.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;


/**
 * @author bluepoet
 *
 */
@Table("WE_SPACE_USER")
public class WeSpaceUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "we_space_idx")
	protected Integer we_space_idx;

	@Column(name = "we_user_idx")
	private Integer we_user_idx;

	@Column(name = "we_insert_permit")
	protected boolean we_insert_permit = false;

	@Column(name = "we_edit_permit")
	protected boolean we_edit_permit = false;

	@Column(name = "we_view_permit")
	protected boolean we_view_permit = false;

	@Column(name = "we_ins_date")
	protected Date we_ins_date;

	public WeSpaceUser() {

	}

	public WeSpaceUser(Integer we_space_idx, String type) {
		this.we_space_idx = we_space_idx;
		setAuthorityType(type);
	}

	public WeSpaceUser(int spaceIdx, int userIdx, String authorityType) {
		this.we_space_idx = spaceIdx;
		this.we_user_idx = userIdx;
		setAuthorityType(authorityType);
		this.we_ins_date = new Date();
	}

	public WeSpaceUser(Integer we_space_idx, Integer we_user_idx, boolean we_insert_permit, boolean we_edit_permit, boolean we_view_permit) {
		this.we_space_idx = we_space_idx;
		this.we_user_idx = we_user_idx;
		this.we_insert_permit = we_insert_permit;
		this.we_edit_permit = we_edit_permit;
		this.we_view_permit = we_view_permit;
		this.we_ins_date = new Date();
	}

	/**
	 * @return the we_space_idx
	 */
	public Integer getWe_space_idx() {
		return we_space_idx;
	}

	/**
	 * @param we_space_idx the we_space_idx to set
	 */
	public void setWe_space_idx(Integer we_space_idx) {
		this.we_space_idx = we_space_idx;
	}

	/**
	 * @return the we_user_idx
	 */
	public Integer getWe_user_idx() {
		return we_user_idx;
	}

	/**
	 * @param we_user_idx the we_user_idx to set
	 */
	public void setWe_user_idx(Integer we_user_idx) {
		this.we_user_idx = we_user_idx;
	}

	/**
	 * @return the we_insert_permit
	 */
	public boolean isWe_insert_permit() {
		return we_insert_permit;
	}

	/**
	 * @param we_insert_permit the we_insert_permit to set
	 */
	public void setWe_insert_permit(boolean we_insert_permit) {
		this.we_insert_permit = we_insert_permit;
	}

	/**
	 * @return the we_edit_permit
	 */
	public boolean isWe_edit_permit() {
		return we_edit_permit;
	}

	/**
	 * @param we_edit_permit the we_edit_permit to set
	 */
	public void setWe_edit_permit(boolean we_edit_permit) {
		this.we_edit_permit = we_edit_permit;
	}

	/**
	 * @return the we_view_permit
	 */
	public boolean isWe_view_permit() {
		return we_view_permit;
	}

	/**
	 * @param we_view_permit the we_view_permit to set
	 */
	public void setWe_view_permit(boolean we_view_permit) {
		this.we_view_permit = we_view_permit;
	}

	/**
	 * @return the we_ins_date
	 */
	public Date getWe_ins_date() {
		return we_ins_date;
	}

	/**
	 * @param we_ins_date the we_ins_date to set
	 */
	public void setWe_ins_date(Date we_ins_date) {
		this.we_ins_date = we_ins_date;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setAuthorityType(String type) {
		if(StringUtils.equals("view", type)) {
			setWe_view_permit(true);
		}else{
			setWe_insert_permit(true);
			setWe_edit_permit(true);
		}
	}
}
/**
 * @FileName  : WeSpaceAuthority.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 6.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.Serializable;
import java.util.Date;

import org.gliderwiki.framework.orm.sql.annotation.Column;


/**
 * @author bluepoet
 *
 */
public class WeSpaceAuthority implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "we_space_idx")
	protected Integer we_space_idx;

	@Column(name = "we_insert_permit")
	protected boolean we_insert_permit = false;

	@Column(name = "we_edit_permit")
	protected boolean we_edit_permit = false;

	@Column(name = "we_view_permit")
	protected boolean we_view_permit = false;

	@Column(name = "we_ins_date")
	protected Date we_ins_date;

	public Integer getWe_space_idx() {
		return we_space_idx;
	}

	public void setWe_space_idx(Integer we_space_idx) {
		this.we_space_idx = we_space_idx;
	}

	public boolean isWe_insert_permit() {
		return we_insert_permit;
	}

	public void setWe_insert_permit(boolean we_insert_permit) {
		this.we_insert_permit = we_insert_permit;
	}

	public boolean isWe_edit_permit() {
		return we_edit_permit;
	}

	public void setWe_edit_permit(boolean we_edit_permit) {
		this.we_edit_permit = we_edit_permit;
	}

	public boolean isWe_view_permit() {
		return we_view_permit;
	}

	public void setWe_view_permit(boolean we_view_permit) {
		this.we_view_permit = we_view_permit;
	}

	public Date getWe_ins_date() {
		return we_ins_date;
	}

	public void setWe_ins_date(Date we_ins_date) {
		this.we_ins_date = we_ins_date;
	}
}

/**
 * @FileName  : WeBbsComment.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 14.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.Serializable;
import java.util.Date;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;


/**
 * 공간게시판 댓글정보
 * @author bluepoet
 *
 */
@Table("WE_BBS_COMMENT")
public class WeBbsComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "we_bbs_comment_idx", primaryKey = true, autoIncrement = true)
	private Integer we_bbs_comment_idx;

	@Column(name = "we_bbs_idx")
	private Integer we_bbs_idx;

	@Column(name = "we_user_ip")
	private String we_user_ip;

	@Column(name = "we_bbs_text")
	private String we_bbs_text;

	@Column(name = "we_ins_user")
	private Integer we_ins_user;

	@Column(name = "we_ins_date")
	private Date we_ins_date;

	public WeBbsComment() {}

	/**
	 * @return the we_bbs_comment_idx
	 */
	public Integer getWe_bbs_comment_idx() {
		return we_bbs_comment_idx;
	}

	/**
	 * @param we_bbs_comment_idx the we_bbs_comment_idx to set
	 */
	public void setWe_bbs_comment_idx(Integer we_bbs_comment_idx) {
		this.we_bbs_comment_idx = we_bbs_comment_idx;
	}

	/**
	 * @return the we_bbs_idx
	 */
	public Integer getWe_bbs_idx() {
		return we_bbs_idx;
	}

	/**
	 * @param we_bbs_idx the we_bbs_idx to set
	 */
	public void setWe_bbs_idx(Integer we_bbs_idx) {
		this.we_bbs_idx = we_bbs_idx;
	}

	/**
	 * @return the we_user_ip
	 */
	public String getWe_user_ip() {
		return we_user_ip;
	}

	/**
	 * @param we_user_ip the we_user_ip to set
	 */
	public void setWe_user_ip(String we_user_ip) {
		this.we_user_ip = we_user_ip;
	}

	/**
	 * @return the we_bbs_text
	 */
	public String getWe_bbs_text() {
		return we_bbs_text;
	}

	/**
	 * @param we_bbs_text the we_bbs_text to set
	 */
	public void setWe_bbs_text(String we_bbs_text) {
		this.we_bbs_text = we_bbs_text;
	}

	/**
	 * @return the we_ins_user
	 */
	public Integer getWe_ins_user() {
		return we_ins_user;
	}

	/**
	 * @param we_ins_user the we_ins_user to set
	 */
	public void setWe_ins_user(Integer we_ins_user) {
		this.we_ins_user = we_ins_user;
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
}

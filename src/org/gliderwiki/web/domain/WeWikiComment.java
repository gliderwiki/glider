/**
 * @FileName  : WeWikiComment.java
 * @Project   : NightHawk
 * @Date      : 2012. 12. 10. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.Serializable;
import java.util.Date;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;

/**
 * @author yion
 *
 */

@Table("WE_WIKI_COMMENT")
public class WeWikiComment extends BaseObjectBean implements Serializable {

	private static final long serialVersionUID = 3964643123812251426L;

	@Column(name = "we_wiki_comment_idx", primaryKey = true, autoIncrement = true)
	private Integer we_wiki_comment_idx;

	@Column(name = "we_wiki_idx")
	private Integer we_wiki_idx;

	@Column(name = "we_user_ip")
	private String we_user_ip;

	@Column(name = "we_bbs_text")
	private String we_bbs_text;

	@Column(name = "we_ins_user")
	private Integer we_ins_user;

	@Column(name = "we_ins_date")
	private Date we_ins_date;

	
	@Column(name = "we_ins_name")
	private String we_ins_name;
	
	@Column(name = "we_use_yn")
	private String we_use_yn;

	/**
	 * @return the we_wiki_comment_idx
	 */
	public Integer getWe_wiki_comment_idx() {
		return we_wiki_comment_idx;
	}

	/**
	 * @param we_wiki_comment_idx the we_wiki_comment_idx to set
	 */
	public void setWe_wiki_comment_idx(Integer we_wiki_comment_idx) {
		this.we_wiki_comment_idx = we_wiki_comment_idx;
	}

	/**
	 * @return the we_wiki_idx
	 */
	public Integer getWe_wiki_idx() {
		return we_wiki_idx;
	}

	/**
	 * @param we_wiki_idx the we_wiki_idx to set
	 */
	public void setWe_wiki_idx(Integer we_wiki_idx) {
		this.we_wiki_idx = we_wiki_idx;
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

	/**
	 * @return the we_ins_name
	 */
	public String getWe_ins_name() {
		return we_ins_name;
	}

	/**
	 * @param we_ins_name the we_ins_name to set
	 */
	public void setWe_ins_name(String we_ins_name) {
		this.we_ins_name = we_ins_name;
	}

	/**
	 * @return the we_use_yn
	 */
	public String getWe_use_yn() {
		return we_use_yn;
	}

	/**
	 * @param we_use_yn the we_use_yn to set
	 */
	public void setWe_use_yn(String we_use_yn) {
		this.we_use_yn = we_use_yn;
	}

}

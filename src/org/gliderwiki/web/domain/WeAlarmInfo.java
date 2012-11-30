/**
 * @FileName  : WeAlarmInfo.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 16.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.Serializable;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;


/**
 * @author yion
 *
 */
@Table("WE_ALARM_INFO")
public class WeAlarmInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5422669440562154849L;

	@Column(primaryKey=true)
	private Integer we_alarm_idx;

	@Column(name="we_meta_idx")
	private Integer we_meta_idx;

	@Column(name="we_alarm_type")
	private String we_alarm_type;

	@Column(name="we_user_idx")
	private Integer we_user_idx;

	@Column(name="we_target_user_idx")
	private Integer we_target_user_idx;

	@Column(name="we_alarm_text")
	private String we_alarm_text;

	@Column(name="we_use_yn")
	private String we_use_yn;

	@Column(name="we_ins_date")
	private String we_ins_date;

	@Column(name="we_ins_user")
	private Integer we_ins_user;

	@Column(name="we_read_yn")
	private boolean we_read_yn;

	@Column(name="we_space_idx")
	private Integer we_space_idx;

	@Column(name="we_wiki_idx")
	private Integer we_wiki_idx;

	/**
	 * @return the we_alarm_idx
	 */
	public Integer getWe_alarm_idx() {
		return we_alarm_idx;
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
	 * @param we_alarm_idx the we_alarm_idx to set
	 */
	public void setWe_alarm_idx(Integer we_alarm_idx) {
		this.we_alarm_idx = we_alarm_idx;
	}

	/**
	 * @return the we_meta_idx
	 */
	public Integer getWe_meta_idx() {
		return we_meta_idx;
	}

	/**
	 * @param we_meta_idx the we_meta_idx to set
	 */
	public void setWe_meta_idx(Integer we_meta_idx) {
		this.we_meta_idx = we_meta_idx;
	}

	/**
	 * @return the we_alarm_type
	 */
	public String getWe_alarm_type() {
		return we_alarm_type;
	}

	/**
	 * @param we_alarm_type the we_alarm_type to set
	 */
	public void setWe_alarm_type(String we_alarm_type) {
		this.we_alarm_type = we_alarm_type;
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
	 * @return the we_target_user_idx
	 */
	public Integer getWe_target_user_idx() {
		return we_target_user_idx;
	}

	/**
	 * @param we_target_user_idx the we_target_user_idx to set
	 */
	public void setWe_target_user_idx(Integer we_target_user_idx) {
		this.we_target_user_idx = we_target_user_idx;
	}

	/**
	 * @return the we_alarm_text
	 */
	public String getWe_alarm_text() {
		return we_alarm_text;
	}

	/**
	 * @param we_alarm_text the we_alarm_text to set
	 */
	public void setWe_alarm_text(String we_alarm_text) {
		this.we_alarm_text = we_alarm_text;
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

	/**
	 * @return the we_ins_date
	 */
	public String getWe_ins_date() {
		return we_ins_date;
	}

	/**
	 * @param we_ins_date the we_ins_date to set
	 */
	public void setWe_ins_date(String we_ins_date) {
		this.we_ins_date = we_ins_date;
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
	 * @return the we_read_yn
	 */
	public boolean isWe_read_yn() {
		return we_read_yn;
	}

	/**
	 * @param we_read_yn the we_read_yn to set
	 */
	public void setWe_read_yn(boolean we_read_yn) {
		this.we_read_yn = we_read_yn;
	}

}

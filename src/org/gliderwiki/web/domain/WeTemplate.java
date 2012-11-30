/**
 * @FileName  : WeTemplate.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 23. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.util.Date;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;


/**
 * @author yion
 *
 */
@Table("WE_TEMPLATE") 
public class WeTemplate extends BaseObjectBean {

	/**
	 * 템플릿 순번 
	 */
	@Column(primaryKey=true, autoIncrement=true)
	private Integer we_template_idx;
	
	/**
	 * 템플릿타입 HTML, MARKUP 
	 */
	@Column(name="we_template_type") 
	private String we_template_type;
	
	
	/**
	 * 템플릿 명
	 */
	@Column(name="we_template_name") 
	private String we_template_name;
	
	/**
	 * 템플릿 내용 (TEXT)
	 */
	@Column(name="we_template_text") 
	private String we_template_text;
	
	
	/**
	 * 템플릿 내용 (MARKUP)
	 */
	@Column(name="we_template_markup") 
	private String we_template_markup;
	
	
	@Column(name="we_use_yn")
	private String we_use_yn;

	@Column(name="we_ins_user")
	private Integer we_ins_user;

	@Column(name="we_ins_date")
	private Date we_ins_date;

	@Column(name="we_upd_user")
	private Integer we_upd_user;

	@Column(name="we_upd_date")
	private Date we_upd_date;

	private String we_user_nick;
	
	

	/**
	 * @return the we_template_idx
	 */
	public Integer getWe_template_idx() {
		return we_template_idx;
	}

	/**
	 * @param we_template_idx the we_template_idx to set
	 */
	public void setWe_template_idx(Integer we_template_idx) {
		this.we_template_idx = we_template_idx;
	}

	/**
	 * @return the we_template_type
	 */
	public String getWe_template_type() {
		return we_template_type;
	}

	/**
	 * @param we_template_type the we_template_type to set
	 */
	public void setWe_template_type(String we_template_type) {
		this.we_template_type = we_template_type;
	}

	/**
	 * @return the we_template_name
	 */
	public String getWe_template_name() {
		return we_template_name;
	}

	/**
	 * @param we_template_name the we_template_name to set
	 */
	public void setWe_template_name(String we_template_name) {
		this.we_template_name = we_template_name;
	}

	/**
	 * @return the we_template_text
	 */
	public String getWe_template_text() {
		return we_template_text;
	}

	/**
	 * @param we_template_text the we_template_text to set
	 */
	public void setWe_template_text(String we_template_text) {
		this.we_template_text = we_template_text;
	}

	/**
	 * @return the we_template_martup
	 */
	public String getWe_template_markup() {
		return we_template_markup;
	}

	/**
	 * @param we_template_martup the we_template_martup to set
	 */
	public void setWe_template_markup(String we_template_markup) {
		this.we_template_markup = we_template_markup;
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
	 * @return the we_upd_user
	 */
	public Integer getWe_upd_user() {
		return we_upd_user;
	}

	/**
	 * @param we_upd_user the we_upd_user to set
	 */
	public void setWe_upd_user(Integer we_upd_user) {
		this.we_upd_user = we_upd_user;
	}

	/**
	 * @return the we_upd_date
	 */
	public Date getWe_upd_date() {
		return we_upd_date;
	}

	/**
	 * @param we_upd_date the we_upd_date to set
	 */
	public void setWe_upd_date(Date we_upd_date) {
		this.we_upd_date = we_upd_date;
	}

	
	/**
	 * @return the we_user_nick
	 */
	public String getWe_user_nick() {
		return we_user_nick;
	}

	/**
	 * @param we_user_nick the we_user_nick to set
	 */
	public void setWe_user_nick(String we_user_nick) {
		this.we_user_nick = we_user_nick;
	}
}

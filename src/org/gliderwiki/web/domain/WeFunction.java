/**
 * @FileName  : WeFunction.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 19. 
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
@Table("WE_FUNCTION")
public class WeFunction extends BaseObjectBean {

	@Column(primaryKey=true, autoIncrement=true)
	private Integer we_function_idx;
	
	@Column(name="we_function_name")
	private String we_function_name;
	
	@Column(name="we_function_desc")
	private String we_function_desc;
	
	@Column(name="we_function_code")
	private String we_function_code;
	
	@Column(name="we_function_ver")
	private String we_function_ver;
		
	/*
	 * 기능타입 : CLS-클래스, JSP-JSP, LIB-JAR, DBT-테이블, DBI-인서트, DBU-업데이트, LIC-라이센스
	 */
	@Column(name="we_function_type")
	private String we_function_type;
	
	@Column(name="we_ins_date")
	private Date we_ins_date;
	
	@Column(name="we_ins_user")
	private Integer we_ins_user;
	
	@Column(name="we_use_yn")
	private String we_use_yn;
	
	@Column(name="we_extend_yn")
	private String we_extend_yn;
	
	@Column(name="we_call_url")
	private String we_call_url;

	/**
	 * @return the we_function_idx
	 */
	public Integer getWe_function_idx() {
		return we_function_idx;
	}

	/**
	 * @param we_function_idx the we_function_idx to set
	 */
	public void setWe_function_idx(Integer we_function_idx) {
		this.we_function_idx = we_function_idx;
	}

	/**
	 * @return the we_function_name
	 */
	public String getWe_function_name() {
		return we_function_name;
	}

	/**
	 * @param we_function_name the we_function_name to set
	 */
	public void setWe_function_name(String we_function_name) {
		this.we_function_name = we_function_name;
	}

	/**
	 * @return the we_function_desc
	 */
	public String getWe_function_desc() {
		return we_function_desc;
	}

	/**
	 * @param we_function_desc the we_function_desc to set
	 */
	public void setWe_function_desc(String we_function_desc) {
		this.we_function_desc = we_function_desc;
	}

	/**
	 * @return the we_function_code
	 */
	public String getWe_function_code() {
		return we_function_code;
	}

	/**
	 * @param we_function_code the we_function_code to set
	 */
	public void setWe_function_code(String we_function_code) {
		this.we_function_code = we_function_code;
	}

	/**
	 * @return the we_function_ver
	 */
	public String getWe_function_ver() {
		return we_function_ver;
	}

	/**
	 * @param we_function_ver the we_function_ver to set
	 */
	public void setWe_function_ver(String we_function_ver) {
		this.we_function_ver = we_function_ver;
	}

	/**
	 * @return the we_function_type
	 */
	public String getWe_function_type() {
		return we_function_type;
	}

	/**
	 * @param we_function_type the we_function_type to set
	 */
	public void setWe_function_type(String we_function_type) {
		this.we_function_type = we_function_type;
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
	 * @return the we_extend_yn
	 */
	public String getWe_extend_yn() {
		return we_extend_yn;
	}

	/**
	 * @param we_extend_yn the we_extend_yn to set
	 */
	public void setWe_extend_yn(String we_extend_yn) {
		this.we_extend_yn = we_extend_yn;
	}

	/**
	 * @return the we_call_url
	 */
	public String getWe_call_url() {
		return we_call_url;
	}

	/**
	 * @param we_call_url the we_call_url to set
	 */
	public void setWe_call_url(String we_call_url) {
		this.we_call_url = we_call_url;
	}
	
	
	
}

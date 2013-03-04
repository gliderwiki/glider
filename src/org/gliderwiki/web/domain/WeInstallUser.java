/**
 * @FileName  : WeInstallUser.java
 * @Project   : glider
 * @Date      : 2013. 2. 26. 
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
@Table("WE_INSTALL_USER")
public class WeInstallUser extends BaseObjectBean {
	
	@Column(primaryKey=true, autoIncrement=true)
	private Integer we_install_idx;
	
	@Column(name="we_email")
	private String we_email;
	
	@Column(name="we_domain")
	private String we_domain;
	
	@Column(name="we_active_key")
	private String we_active_key;
	

	@Column(name="we_auth_yn")
	private String we_auth_yn;
	
	@Column(name="we_new_yn")
	private String we_new_yn;
	
	@Column(name="we_use_purpose")
	private String we_use_purpose;
	
	@Column(name="we_company")
	private String we_company;
	
	@Column(name="we_install_date")
	private Date we_install_date;
	
	@Column(name="we_auth_date")
	private Date we_auth_date;

	/**
	 * @return the we_install_idx
	 */
	public Integer getWe_install_idx() {
		return we_install_idx;
	}

	/**
	 * @param we_install_idx the we_install_idx to set
	 */
	public void setWe_install_idx(Integer we_install_idx) {
		this.we_install_idx = we_install_idx;
	}

	/**
	 * @return the we_email
	 */
	public String getWe_email() {
		return we_email;
	}

	/**
	 * @param we_email the we_email to set
	 */
	public void setWe_email(String we_email) {
		this.we_email = we_email;
	}

	/**
	 * @return the we_domain
	 */
	public String getWe_domain() {
		return we_domain;
	}

	/**
	 * @param we_domain the we_domain to set
	 */
	public void setWe_domain(String we_domain) {
		this.we_domain = we_domain;
	}

	/**
	 * @return the we_active_key
	 */
	public String getWe_active_key() {
		return we_active_key;
	}

	/**
	 * @param we_active_key the we_active_key to set
	 */
	public void setWe_active_key(String we_active_key) {
		this.we_active_key = we_active_key;
	}

	/**
	 * @return the we_auth_yn
	 */
	public String getWe_auth_yn() {
		return we_auth_yn;
	}

	/**
	 * @param we_auth_yn the we_auth_yn to set
	 */
	public void setWe_auth_yn(String we_auth_yn) {
		this.we_auth_yn = we_auth_yn;
	}

	/**
	 * @return the we_new_yn
	 */
	public String getWe_new_yn() {
		return we_new_yn;
	}

	/**
	 * @param we_new_yn the we_new_yn to set
	 */
	public void setWe_new_yn(String we_new_yn) {
		this.we_new_yn = we_new_yn;
	}

	/**
	 * @return the we_use_purpose
	 */
	public String getWe_use_purpose() {
		return we_use_purpose;
	}

	/**
	 * @param we_use_purpose the we_use_purpose to set
	 */
	public void setWe_use_purpose(String we_use_purpose) {
		this.we_use_purpose = we_use_purpose;
	}

	/**
	 * @return the we_company
	 */
	public String getWe_company() {
		return we_company;
	}

	/**
	 * @param we_company the we_company to set
	 */
	public void setWe_company(String we_company) {
		this.we_company = we_company;
	}

	/**
	 * @return the we_install_date
	 */
	public Date getWe_install_date() {
		return we_install_date;
	}

	/**
	 * @param we_install_date the we_install_date to set
	 */
	public void setWe_install_date(Date we_install_date) {
		this.we_install_date = we_install_date;
	}

	/**
	 * @return the we_auth_date
	 */
	public Date getWe_auth_date() {
		return we_auth_date;
	}

	/**
	 * @param we_auth_date the we_auth_date to set
	 */
	public void setWe_auth_date(Date we_auth_date) {
		this.we_auth_date = we_auth_date;
	}
	
	
	
}

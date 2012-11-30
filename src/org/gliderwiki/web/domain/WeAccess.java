/**
 * @FileName  : WeAccess.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 8.
 * @작성자      : @author inamhui

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.util.Date;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;


/**
 * 사용자접근제어 도메인
 * @author bluepoet
 *
 */
@Table("WE_ACCESS")
public class WeAccess extends BaseObjectBean {

	@Column(name="we_access_idx", primaryKey=true, autoIncrement=true)
	private Integer we_access_idx;

	@Column(name="we_target_ip")
	private String we_target_ip;

	@Column(name="we_ins_user")
	private Integer we_ins_user;

	@Column(name="we_ins_date")
	private Date we_ins_date;

	public WeAccess() {}

	public WeAccess(Integer we_access_idx) {
		this.we_access_idx = we_access_idx;
	}

	public WeAccess(String we_target_ip, Integer we_ins_user) {
		this.we_target_ip = we_target_ip;
		this.we_ins_user = we_ins_user;
		this.we_ins_date = new Date();
	}

	/**
	 * @return the we_access_idx
	 */
	public Integer getWe_access_idx() {
		return we_access_idx;
	}

	/**
	 * @param we_access_idx the we_access_idx to set
	 */
	public void setWe_access_idx(Integer we_access_idx) {
		this.we_access_idx = we_access_idx;
	}

	/**
	 * @return the we_target_ip
	 */
	public String getWe_target_ip() {
		return we_target_ip;
	}

	/**
	 * @param we_target_ip the we_target_ip to set
	 */
	public void setWe_target_ip(String we_target_ip) {
		this.we_target_ip = we_target_ip;
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

/**
 * @FileName  : WeGroupInfo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 22. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;


/**
 * @author yion
 *
 */
@Table("WE_GROUP_INFO")
@DataTransferObject
public class WeGroupInfo extends BaseObjectBean {

	/**
	 * 그룹 순번
	 */
	@Column(name="we_group_idx",primaryKey=true, autoIncrement=true)
	private Integer we_group_idx;

	
	/**
	 * 그룹 코드 
	 */
	@Column(name="we_group_code") 
	private String we_group_code;
	
	/**
	 * 그룹명
	 */
	@Column(name="we_group_name") 
	private String we_group_name;
	
	/**
	 * 그룹타입 0:조직, 1:사용자 생성그룹 
	 */
	@Column(name="we_group_type") 
	private String we_group_type;
	
	/**
	 * 그룹 오너 순번
	 */
	@Column(name="we_group_owner") 
	private String we_group_owner;
	
	
	/**
	 * 필수그룹여부 
	 */
	@Column(name="we_required_yn") 
	private String we_required_yn;
	
	/**
	 * 그룹 설명
	 */
	@Column(name="we_group_info") 
	private String we_group_info;
	
	@Column(name="we_use_yn") 
	private String we_use_yn;
	
	@Column(name="we_ins_user") 
	private String we_ins_user;
	
	@Column(name="we_ins_date") 
	private Date we_ins_date;
	
	@Column(name="we_upd_user") 
	private String we_upd_user;
	
	@Column(name="we_upd_date") 
	private Date we_upd_date;

	/**
	 * @return the we_group_idx
	 */
	public Integer getWe_group_idx() {
		return we_group_idx;
	}

	/**
	 * @param we_group_idx the we_group_idx to set
	 */
	public void setWe_group_idx(Integer we_group_idx) {
		this.we_group_idx = we_group_idx;
	}

	/**
	 * @return the we_group_code
	 */
	public String getWe_group_code() {
		return we_group_code;
	}

	/**
	 * @param we_group_code the we_group_code to set
	 */
	public void setWe_group_code(String we_group_code) {
		this.we_group_code = we_group_code;
	}

	/**
	 * @return the we_group_name
	 */
	public String getWe_group_name() {
		return we_group_name;
	}

	/**
	 * @param we_group_name the we_group_name to set
	 */
	public void setWe_group_name(String we_group_name) {
		this.we_group_name = we_group_name;
	}

	/**
	 * @return the we_group_type
	 */
	public String getWe_group_type() {
		return we_group_type;
	}

	/**
	 * @param we_group_type the we_group_type to set
	 */
	public void setWe_group_type(String we_group_type) {
		this.we_group_type = we_group_type;
	}

	/**
	 * @return the we_group_owner
	 */
	public String getWe_group_owner() {
		return we_group_owner;
	}

	/**
	 * @param we_group_owner the we_group_owner to set
	 */
	public void setWe_group_owner(String we_group_owner) {
		this.we_group_owner = we_group_owner;
	}

	/**
	 * @return the we_group_info
	 */
	public String getWe_group_info() {
		return we_group_info;
	}

	/**
	 * @param we_group_info the we_group_info to set
	 */
	public void setWe_group_info(String we_group_info) {
		this.we_group_info = we_group_info;
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
	public String getWe_ins_user() {
		return we_ins_user;
	}

	/**
	 * @param we_ins_user the we_ins_user to set
	 */
	public void setWe_ins_user(String we_ins_user) {
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
	public String getWe_upd_user() {
		return we_upd_user;
	}

	/**
	 * @param we_upd_user the we_upd_user to set
	 */
	public void setWe_upd_user(String we_upd_user) {
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
	 * @return the we_required_yn
	 */
	public String getWe_required_yn() {
		return we_required_yn;
	}

	/**
	 * @param we_required_yn the we_required_yn to set
	 */
	public void setWe_required_yn(String we_required_yn) {
		this.we_required_yn = we_required_yn;
	}
	
	
}

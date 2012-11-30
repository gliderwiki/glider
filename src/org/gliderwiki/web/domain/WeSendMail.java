/**
 * @FileName  : WeSendMail.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 26. 
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
@Table("WE_SEND_MAIL") 
public class WeSendMail extends BaseObjectBean {

	@Column(primaryKey=true, autoIncrement=true)
	private Integer we_mail_idx;
	
	@Column(name="we_user_idx") 
	private Integer we_user_idx;
	
	// 메일전송상태 0:기본 , 1:실패 , 2:관리자재전송 , 3:관리자재전송실패 , 9:성공 
	@Column(name="we_send_status") 
	private String we_send_status;
	
	
	@Column(name="we_ins_date") 
	private Date we_ins_date;
	
	
	@Column(name="we_upd_date") 
	private Date we_upd_date;
	
	
	@Column(name="we_meta_domain") 
	private String we_meta_domain;
	
	@Column(name="we_log_param") 
	private String we_log_param;

	public WeSendMail ()  {
		
	}
	
	public WeSendMail(Integer we_user_idx, String we_send_status, String we_meta_domain){
		this.we_user_idx = we_user_idx;
		this.we_send_status = we_send_status;
		this.we_meta_domain = we_meta_domain;
	}

	/**
	 * @return the we_mail_idx
	 */
	public Integer getWe_mail_idx() {
		return we_mail_idx;
	}

	/**
	 * @param we_mail_idx the we_mail_idx to set
	 */
	public void setWe_mail_idx(Integer we_mail_idx) {
		this.we_mail_idx = we_mail_idx;
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
	 * @return the we_send_status
	 */
	public String getWe_send_status() {
		return we_send_status;
	}

	/**
	 * @param we_send_status the we_send_status to set
	 */
	public void setWe_send_status(String we_send_status) {
		this.we_send_status = we_send_status;
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
	 * @return the we_meta_domain
	 */
	public String getWe_meta_domain() {
		return we_meta_domain;
	}

	/**
	 * @param we_meta_domain the we_meta_domain to set
	 */
	public void setWe_meta_domain(String we_meta_domain) {
		this.we_meta_domain = we_meta_domain;
	}

	/**
	 * @return the we_log_param
	 */
	public String getWe_log_param() {
		return we_log_param;
	}

	/**
	 * @param we_log_param the we_log_param to set
	 */
	public void setWe_log_param(String we_log_param) {
		this.we_log_param = we_log_param;
	}

}

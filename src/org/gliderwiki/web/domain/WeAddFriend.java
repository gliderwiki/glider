/**
 * @FileName  : WeAddFriend.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 27. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.Serializable;
import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;


/**
 * @author ganji
 *
 */
@Table("WE_ADD_FRIEND")
@DataTransferObject
public class WeAddFriend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3159761300963670525L;

	@Column(name = "we_user_idx")
	private Integer we_user_idx;

	@Column(name = "we_target_user_idx")
	private Integer we_target_user_idx;

	@Column(name = "we_add_date")
	private Date we_add_date;

	@Column(name = "we_del_date")
	private Date we_del_date;

	@Column(name = "we_use_yn")
	private String we_use_yn;
	

	public Integer getWe_user_idx() {
		return we_user_idx;
	}

	public void setWe_user_idx(Integer we_user_idx) {
		this.we_user_idx = we_user_idx;
	}

	public Integer getWe_target_user_idx() {
		return we_target_user_idx;
	}

	public void setWe_target_user_idx(Integer we_target_user_idx) {
		this.we_target_user_idx = we_target_user_idx;
	}

	public Date getWe_add_date() {
		return we_add_date;
	}

	public void setWe_add_date(Date we_add_date) {
		this.we_add_date = we_add_date;
	}

	public Date getWe_del_date() {
		return we_del_date;
	}

	public void setWe_del_date(Date we_del_date) {
		this.we_del_date = we_del_date;
	}

	public String getWe_use_yn() {
		return we_use_yn;
	}

	public void setWe_use_yn(String we_use_yn) {
		this.we_use_yn = we_use_yn;
	}
	
}

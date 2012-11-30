/**
 * @FileName  : WeUserInfo.java
 * @Project   : NightHawk
 * @Date      : 2011. 12. 9. 
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
@Table("WE_USER_INFO")
public class WeUserInfo extends BaseObjectBean {
	private static final long serialVersionUID = 426766516886165166L;

	@Column(primaryKey=true, autoIncrement=true)
	private Integer user_idx;
	
	@Column(name="user_id") 
	private String user_id;
	
	@Column
	private String user_name;
	
	@Column
	private Date join_date;
	

	public Integer getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(Integer user_idx) {
		this.user_idx = user_idx;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getJoin_date() {
		return join_date;
	}

	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}

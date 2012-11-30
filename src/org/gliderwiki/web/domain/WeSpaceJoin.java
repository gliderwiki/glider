/**
 * @FileName  : WeSpaceJoin.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 20.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.Serializable;
import java.util.Date;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.framework.util.DateUtil;


/**
 * @author bluepoet
 *
 */

@Table("WE_SPACE_JOIN")
public class WeSpaceJoin implements Serializable {
	@Column(name = "we_space_join_idx", primaryKey = true, autoIncrement = true)
	private Integer we_space_join_idx;

	@Column(name = "we_space_idx")
	private Integer we_space_idx;

	@Column(name = "we_join_type")
	private JoinType we_join_type;

	@Column(name = "we_user_idx")
	private Integer we_user_idx;

	@Column(name = "we_join_status")
	private JoinStatus we_join_status;

	@Column(name = "we_ins_date")
	private String we_ins_date;
	
	@Column(name = "we_ins_user")
	private Integer we_ins_user;

	public WeSpaceJoin() {

	}

	public WeSpaceJoin(Integer we_space_idx, JoinType we_join_type, Integer we_user_idx, JoinStatus we_join_status, Integer we_ins_user) {
		this.we_space_idx = we_space_idx;
		this.we_join_type = we_join_type;
		this.we_user_idx = we_user_idx;
		this.we_join_status = we_join_status;
		this.we_ins_date = DateUtil.getTodayTime();
		this.we_ins_user = we_ins_user;
	}

	public Integer getWe_space_join_idx() {
		return we_space_join_idx;
	}

	public void setWe_space_join_idx(Integer we_space_join_idx) {
		this.we_space_join_idx = we_space_join_idx;
	}

	public Integer getWe_space_idx() {
		return we_space_idx;
	}

	public void setWe_space_idx(Integer we_space_idx) {
		this.we_space_idx = we_space_idx;
	}

	public JoinType getWe_join_type() {
		return we_join_type;
	}

	public void setWe_join_type(JoinType we_join_type) {
		this.we_join_type = we_join_type;
	}

	public Integer getWe_user_idx() {
		return we_user_idx;
	}

	public void setWe_user_idx(Integer we_user_idx) {
		this.we_user_idx = we_user_idx;
	}

	public JoinStatus getWe_join_status() {
		return we_join_status;
	}

	public void setWe_join_status(JoinStatus we_join_status) {
		this.we_join_status = we_join_status;
	}

	public String getWe_ins_date() {
		return we_ins_date;
	}

	public void setWe_ins_date(String we_ins_date) {
		this.we_ins_date = we_ins_date;
	}

	
	public Integer getWe_ins_user() {
		return we_ins_user;
	}

	
	public void setWe_ins_user(Integer we_ins_user) {
		this.we_ins_user = we_ins_user;
	}

	
}

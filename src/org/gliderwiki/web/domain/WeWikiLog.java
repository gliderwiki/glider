/**
 * @FileName  : WeWikiLog.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 17. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.util.Date;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.web.vo.BaseObjectBean;


/**
 * @author yion
 *
 */
public class WeWikiLog extends BaseObjectBean {
	
	/**
	 * 위키 순번 
	 */
	@Column(name="we_wiki_idx")
	private Integer we_wiki_idx;
	
	/**
	 * 저장 상태 
	 */
	@Column(name="we_wiki_status")
	private String we_wiki_status;
	
	/**
	 * 액션 사용자 순번 
	 */
	@Column(name="we_user_idx")
	private Integer we_user_idx;

	/**
	 * 버전정보
	 */
	@Column(name="we_wiki_revision")
	private Integer we_wiki_revision;
	
	/**
	 * 액션 타입	I:작성, U:수정, D:삭제 
	 */
	@Column(name="we_wiki_action_type")
	private String we_wiki_action_type;
	
	@Column(name="we_ins_date")
	private Date we_ins_date;

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
	 * @return the we_wiki_status
	 */
	public String getWe_wiki_status() {
		return we_wiki_status;
	}

	/**
	 * @param we_wiki_status the we_wiki_status to set
	 */
	public void setWe_wiki_status(String we_wiki_status) {
		this.we_wiki_status = we_wiki_status;
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
	 * @return the we_wiki_revision
	 */
	public Integer getWe_wiki_revision() {
		return we_wiki_revision;
	}

	/**
	 * @param we_wiki_revision the we_wiki_revision to set
	 */
	public void setWe_wiki_revision(Integer we_wiki_revision) {
		this.we_wiki_revision = we_wiki_revision;
	}

	/**
	 * @return the we_wiki_action_type
	 */
	public String getWe_wiki_action_type() {
		return we_wiki_action_type;
	}

	/**
	 * @param we_wiki_action_type the we_wiki_action_type to set
	 */
	public void setWe_wiki_action_type(String we_wiki_action_type) {
		this.we_wiki_action_type = we_wiki_action_type;
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

	
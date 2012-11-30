/**
 * @FileName : WeWikiBak.java
 * @Project  : NightHawk
 * @Date     : 2012. 7. 3.
 * @작성자   : @author jaeger

 * @변경이력 :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.util.Date;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;


/**
 * WE_WIKI_BAK
 *
 * @author jaeger
 */
@Table("WE_WIKI_BAK")
public class WeWikiBak extends BaseObjectBean {

	private static final long serialVersionUID = -1709638901486471107L;

	@Column(name="we_bak_idx", primaryKey=true, autoIncrement=true)
	private Integer we_bak_idx;

	@Column(name="we_wiki_idx", primaryKey=true)
	private Integer we_wiki_idx;

	@Column(name="we_wiki_title")
	private String we_wiki_title;

	@Column(name="we_wiki_text")
	private String we_wiki_text;

	@Column(name="we_wiki_markup")
	private String we_wiki_markup;

	@Column(name="we_wiki_revision")
	private Integer we_wiki_revision;

	/**
	 * 위키 상태
	 *
	 * ('T' : 임시, 'S' : 저장)
	 */
	@Column(name="we_wiki_status")
	private String we_wiki_status;

	@Column(name="we_user_ip")
	private String we_user_ip;

	/**
	 * 위키 보호
	 *
	 * '0' : 기본    , '1' : 준보호, '2' : 보호    , '3' : 신고      , '4' : 삭제예정
	 * '6' : 이의제기, '7' : 토론  , '8' : 삭제복구, '9' : 관리자삭제
	 */
	@Column(name="we_wiki_protect")
	private String we_wiki_protect;

	@Column(name="we_edit_text")
	private String we_edit_text;

	/**
	 * 위키 사용 여부
	 */
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

	@Column(name="we_mov_date")
	private Date we_mov_date;

	private String we_text_br;

	private String we_user_nick;


	/**
	 * @return the we_text_br
	 */
	public String getWe_text_br() {
		return we_text_br;
	}

	/**
	 * @param we_text_br the we_text_br to set
	 */
	public void setWe_text_br(String we_text_br) {
		this.we_text_br = we_text_br;
	}

	/**
	 * @return the we_bak_idx
	 */
	public Integer getWe_bak_idx() {
		return we_bak_idx;
	}

	/**
	 * @param we_bak_idx the we_bak_idx to set
	 */
	public void setWe_bak_idx(Integer we_bak_idx) {
		this.we_bak_idx = we_bak_idx;
	}

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
	 * @return the we_wiki_title
	 */
	public String getWe_wiki_title() {
		return we_wiki_title;
	}

	/**
	 * @param we_wiki_title the we_wiki_title to set
	 */
	public void setWe_wiki_title(String we_wiki_title) {
		this.we_wiki_title = we_wiki_title;
	}

	/**
	 * @return the we_wiki_text
	 */
	public String getWe_wiki_text() {
		return we_wiki_text;
	}

	/**
	 * @param we_wiki_text the we_wiki_text to set
	 */
	public void setWe_wiki_text(String we_wiki_text) {
		this.we_wiki_text = we_wiki_text;
	}

	/**
	 * @return the we_wiki_markup
	 */
	public String getWe_wiki_markup() {
		return we_wiki_markup;
	}

	/**
	 * @param we_wiki_markup the we_wiki_markup to set
	 */
	public void setWe_wiki_markup(String we_wiki_markup) {
		this.we_wiki_markup = we_wiki_markup;
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
	 * @return the we_user_ip
	 */
	public String getWe_user_ip() {
		return we_user_ip;
	}

	/**
	 * @param we_user_ip the we_user_ip to set
	 */
	public void setWe_user_ip(String we_user_ip) {
		this.we_user_ip = we_user_ip;
	}

	/**
	 * @return the we_wiki_protect
	 */
	public String getWe_wiki_protect() {
		return we_wiki_protect;
	}

	/**
	 * @param we_wiki_protect the we_wiki_protect to set
	 */
	public void setWe_wiki_protect(String we_wiki_protect) {
		this.we_wiki_protect = we_wiki_protect;
	}

	/**
	 * @return the we_edit_text
	 */
	public String getWe_edit_text() {
		return we_edit_text;
	}

	/**
	 * @param we_edit_text the we_edit_text to set
	 */
	public void setWe_edit_text(String we_edit_text) {
		this.we_edit_text = we_edit_text;
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
	 * @return the we_mov_date
	 */
	public Date getWe_mov_date() {
		return we_mov_date;
	}

	/**
	 * @param we_mov_date the we_mov_date to set
	 */
	public void setWe_mov_date(Date we_mov_date) {
		this.we_mov_date = we_mov_date;
	}

	@Override
	public String toString() {
		return "WeWikiBak [we_bak_idx=" + we_bak_idx + ", we_wiki_idx="
				+ we_wiki_idx + ", we_wiki_title=" + we_wiki_title
				+ ", we_wiki_text=" + we_wiki_text + ", we_wiki_markup="
				+ we_wiki_markup + ", we_wiki_revision=" + we_wiki_revision
				+ ", we_wiki_status=" + we_wiki_status + ", we_user_ip="
				+ we_user_ip + ", we_wiki_protect=" + we_wiki_protect
				+ ", we_edit_text=" + we_edit_text + ", we_use_yn=" + we_use_yn
				+ ", we_ins_user=" + we_ins_user + ", we_ins_date="
				+ we_ins_date + ", we_upd_user=" + we_upd_user
				+ ", we_upd_date=" + we_upd_date + ", we_mov_date="
				+ we_mov_date + ", toString()=" + super.toString() + "]";
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

/**
 * @FileName  : WeWiki.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 3.
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
 * WE_WIKI
 *
 * @author yion
 */
@Table("WE_WIKI")
public class WeWiki extends BaseObjectBean {

	private static final long serialVersionUID = 8858688530827538562L;

	/**
	 * 위키 순번
	 */
	@Column(name="we_wiki_idx", primaryKey=true, autoIncrement=true)
	private Integer we_wiki_idx;

	/**
	 * 공간순번
	 */
	@Column(name="we_space_idx")
	private Integer we_space_idx;

	/**
	 * 부모번호
	 */
	@Column(name="we_wiki_parent_idx")
	private Integer we_wiki_parent_idx;

	/**
	 * 정렬순서
	 */
	@Column(name="we_wiki_order_idx")
	private Integer we_wiki_order_idx;

	/**
	 * 글 깊이
	 */
	@Column(name="we_wiki_depth_idx")
	private Integer we_wiki_depth_idx;

	/**
	 * 제목
	 */
	@Column(name="we_wiki_title")
	private String we_wiki_title;

	/**
	 * 내용 (html)
	 */
	@Column(name="we_wiki_text")
	private String we_wiki_text;

	/**
	 * 내용마크업
	 */
	@Column(name="we_wiki_markup")
	private String we_wiki_markup;

	/**
	 * 버전정보
	 */
	@Column(name="we_wiki_revision")
	private Integer we_wiki_revision;

	/**
	 * 저장상태 T:임시 S:저장
	 */
	@Column(name="we_wiki_status")
	private String we_wiki_status;

	/**
	 * 신고건수
	 */
	@Column(name="we_wiki_quota")
	private Integer we_wiki_quota;

	/**
	 * 위키 URL
	 */
	@Column(name="we_wiki_url")
	private String we_wiki_url;

	/**
	 * 공감수
	 */
	@Column(name="we_wiki_agree")
	private Integer we_wiki_agree;

	/**
	 * 조회수
	 */
	@Column(name="we_wiki_view_cnt")
	private Integer we_wiki_view_cnt;

	/**
	 * 이전 참고 주소
	 */
	@Column(name="we_wiki_prev")
	private String we_wiki_prev;

	/**
	 * 다음 참고 주소
	 */
	@Column(name="we_wiki_next")
	private String we_wiki_next;

	/**
	 * 사용자 아이피
	 */
	@Column(name="we_user_ip")
	private String we_user_ip;

	/**
	 * 보호 여부
	 *
	 * '0' : 기본    , '1' : 준보호, '2' : 보호    , '3' : 신고      , '4' : 삭제예정
	 * '6' : 이의제기, '7' : 토론  , '8' : 삭제복구, '9' : 관리자삭제
	 */
	@Column(name="we_wiki_protect")
	private String we_wiki_protect;

	/**
	 * 수정사유
	 */
	@Column(name="we_edit_text")
	private String we_edit_text;

	/**
	 * 사용여부
	 */
	@Column(name="we_use_yn")
	private String we_use_yn;

	/**
	 * 수정가능여부
	 */
	@Column(name="we_edit_yn")
	private String we_edit_yn;

	@Column(name="we_ins_user")
	private Integer we_ins_user;

	@Column(name="we_ins_date")
	private Date we_ins_date;

	@Column(name="we_upd_user")
	private Integer we_upd_user;

	@Column(name="we_upd_date")
	private Date we_upd_date;


	private String we_text_br;

	private Integer row_num;

	private String we_user_nick;




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

	/**
	 * @return the row_num
	 */
	public Integer getRow_num() {
		return row_num;
	}

	/**
	 * @param row_num the row_num to set
	 */
	public void setRow_num(Integer row_num) {
		this.row_num = row_num;
	}

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
	 * @return the we_space_idx
	 */
	public Integer getWe_space_idx() {
		return we_space_idx;
	}
	/**
	 * @param we_space_idx the we_space_idx to set
	 */
	public void setWe_space_idx(Integer we_space_idx) {
		this.we_space_idx = we_space_idx;
	}
	/**
	 * @return the we_wiki_parent_idx
	 */
	public Integer getWe_wiki_parent_idx() {
		return we_wiki_parent_idx;
	}
	/**
	 * @param we_wiki_parent_idx the we_wiki_parent_idx to set
	 */
	public void setWe_wiki_parent_idx(Integer we_wiki_parent_idx) {
		this.we_wiki_parent_idx = we_wiki_parent_idx;
	}
	/**
	 * @return the we_wiki_order_idx
	 */
	public Integer getWe_wiki_order_idx() {
		return we_wiki_order_idx;
	}
	/**
	 * @param we_wiki_order_idx the we_wiki_order_idx to set
	 */
	public void setWe_wiki_order_idx(Integer we_wiki_order_idx) {
		this.we_wiki_order_idx = we_wiki_order_idx;
	}
	/**
	 * @return the we_wiki_depth_idx
	 */
	public Integer getWe_wiki_depth_idx() {
		return we_wiki_depth_idx;
	}
	/**
	 * @param we_wiki_depth_idx the we_wiki_depth_idx to set
	 */
	public void setWe_wiki_depth_idx(Integer we_wiki_depth_idx) {
		this.we_wiki_depth_idx = we_wiki_depth_idx;
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
	 * @param i the we_wiki_revision to set
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
	 * @return the we_wiki_quota
	 */
	public Integer getWe_wiki_quota() {
		return we_wiki_quota;
	}
	/**
	 * @param we_wiki_quota the we_wiki_quota to set
	 */
	public void setWe_wiki_quota(Integer we_wiki_quota) {
		this.we_wiki_quota = we_wiki_quota;
	}
	/**
	 * @return the we_wiki_url
	 */
	public String getWe_wiki_url() {
		return we_wiki_url;
	}
	/**
	 * @param we_wiki_url the we_wiki_url to set
	 */
	public void setWe_wiki_url(String we_wiki_url) {
		this.we_wiki_url = we_wiki_url;
	}
	/**
	 * @return the we_wiki_agree
	 */
	public Integer getWe_wiki_agree() {
		return we_wiki_agree;
	}
	/**
	 * @param we_wiki_agree the we_wiki_agree to set
	 */
	public void setWe_wiki_agree(Integer we_wiki_agree) {
		this.we_wiki_agree = we_wiki_agree;
	}
	/**
	 * @return the we_wiki_view_cnt
	 */
	public Integer getWe_wiki_view_cnt() {
		return we_wiki_view_cnt;
	}
	/**
	 * @param we_wiki_view_cnt the we_wiki_view_cnt to set
	 */
	public void setWe_wiki_view_cnt(Integer we_wiki_view_cnt) {
		this.we_wiki_view_cnt = we_wiki_view_cnt;
	}
	/**
	 * @return the we_wiki_prev
	 */
	public String getWe_wiki_prev() {
		return we_wiki_prev;
	}
	/**
	 * @param we_wiki_prev the we_wiki_prev to set
	 */
	public void setWe_wiki_prev(String we_wiki_prev) {
		this.we_wiki_prev = we_wiki_prev;
	}
	/**
	 * @return the we_wiki_next
	 */
	public String getWe_wiki_next() {
		return we_wiki_next;
	}
	/**
	 * @param we_wiki_next the we_wiki_next to set
	 */
	public void setWe_wiki_next(String we_wiki_next) {
		this.we_wiki_next = we_wiki_next;
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
	 * @return the we_edit_yn
	 */
	public String getWe_edit_yn() {
		return we_edit_yn;
	}
	/**
	 * @param we_edit_yn the we_edit_yn to set
	 */
	public void setWe_edit_yn(String we_edit_yn) {
		this.we_edit_yn = we_edit_yn;
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
	 * 위키 최초생성시 기본 세팅
	 * @param usrIdx
	 * @param saveType
	 * @param usrIp
	 */
	public void newBasicSetting(Integer usrIdx,String saveType, String usrIp){
		setWe_use_yn("Y");
		setWe_ins_user(usrIdx);
		setWe_wiki_revision(1);
		setWe_wiki_status(saveType);		// 저장상태
		setWe_user_ip(usrIp);
		setWe_wiki_agree(0);
		setWe_wiki_view_cnt(0);
		setWe_wiki_protect("0");
		setWe_wiki_order_idx(0);	// 정렬순서
		setWe_wiki_depth_idx(0);	// 글 깊이
		setWe_edit_yn("Y");		// 수정 가능 상태
		setWe_ins_date(new Date());
	}


	public WeWiki(){}
}

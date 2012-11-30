/**
 * @FileName  : WeUser.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 2. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 회원 정보 기본 테이블 
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
@Table("WE_USER") 
public class WeUser extends BaseObjectBean {
	
	 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2040477000818559062L;

	/**
	 * 회원 순번 
	 */
	@Column(primaryKey=true, autoIncrement=true)
	private Integer we_user_idx;
	
	/**
	 * 로그인 아이디 
	 */
	@Column(name="we_user_id") 
	private String we_user_id;
	
	/**
	 * 회원 이름 
	 */
	@Column(name="we_user_name")
	private String we_user_name;
	
	/**
	 * 회원 닉네임
	 */
	@Column(name="we_user_nick")
	private String we_user_nick;
	
	/**
	 * 비밀번호 생성 키 
	 */
	@Column(name="we_user_key")
	private String we_user_key;
	
	/**
	 * 회원 비밀번호 
	 */
	@Column(name="we_user_pwd")
	private String we_user_pwd;
	
	/**
	 * 회원 가입일 
	 */
	@Column(name="we_user_join_date")
	private Date we_user_join_date;
	
	/**
	 * 회원 인증 여부 
	 */
	@Column(name="we_user_auth_yn")
	private String we_user_auth_yn;
	
	@Column(name="we_user_auth")
	private String we_user_auth;
	
	
	


	/**
	 * 회원 인증일 
	 */
	@Column(name="we_user_auth_date")
	private Date we_user_auth_date;

		
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
	 * @return the we_user_id
	 */
	public String getWe_user_id() {
		return we_user_id;
	}

	/**
	 * @param we_user_id the we_user_id to set
	 */
	public void setWe_user_id(String we_user_id) {
		this.we_user_id = we_user_id;
	}

	/**
	 * @return the we_user_name
	 */
	public String getWe_user_name() {
		return we_user_name;
	}

	/**
	 * @param we_user_name the we_user_name to set
	 */
	public void setWe_user_name(String we_user_name) {
		this.we_user_name = we_user_name;
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

	/**
	 * @return the we_user_key
	 */
	public String getWe_user_key() {
		return we_user_key;
	}

	/**
	 * @param we_user_key the we_user_key to set
	 */
	public void setWe_user_key(String we_user_key) {
		this.we_user_key = we_user_key;
	}

	/**
	 * @return the we_user_pwd
	 */
	public String getWe_user_pwd() {
		return we_user_pwd;
	}

	/**
	 * @param we_user_pwd the we_user_pwd to set
	 */
	public void setWe_user_pwd(String we_user_pwd) {
		this.we_user_pwd = we_user_pwd;
	}

	/**
	 * @return the we_user_join_date
	 */
	public Date getWe_user_join_date() {
		return we_user_join_date;
	}

	/**
	 * @param we_user_join_date the we_user_join_date to set
	 */
	public void setWe_user_join_date(Date we_user_join_date) {
		this.we_user_join_date = we_user_join_date;
	}

	/**
	 * @return the we_user_auth_yn
	 */
	public String getWe_user_auth_yn() {
		return we_user_auth_yn;
	}

	/**
	 * @param we_user_auth_yn the we_user_auth_yn to set
	 */
	public void setWe_user_auth_yn(String we_user_auth_yn) {
		this.we_user_auth_yn = we_user_auth_yn;
	}

	/**
	 * @return the we_user_auth_date
	 */
	public Date getWe_user_auth_date() {
		return we_user_auth_date;
	}

	/**
	 * @param we_user_auth_date the we_user_auth_date to set
	 */
	public void setWe_user_auth_date(Date we_user_auth_date) {
		this.we_user_auth_date = we_user_auth_date;
	}
	
	public WeUser() {
		
	}
	
	public WeUser(String we_user_id, String we_user_pwd) {
		this.we_user_id = we_user_id;
		this.we_user_pwd = we_user_pwd;
	}
	

	public WeUser(Integer we_user_idx, String we_user_auth_yn) {
		this.we_user_idx = we_user_idx;
		this.we_user_auth_yn = we_user_auth_yn;
	}
	

	/**
	 * @return the we_user_auth
	 */
	public String getWe_user_auth() {
		return we_user_auth;
	}

	/**
	 * @param we_user_auth the we_user_auth to set
	 */
	public void setWe_user_auth(String we_user_auth) {
		this.we_user_auth = we_user_auth;
	}
	
}
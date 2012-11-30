/**
 * @FileName  : WeProfile.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 13.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 회원 확장 정보
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
@Table("WE_PROFILE")
@DataTransferObject
public class WeProfile extends BaseObjectBean {

	/**
	 *
	 */
	private static final long serialVersionUID = 7515195031463866669L;

	@Column(primaryKey=true, autoIncrement=false)
	private Integer we_user_idx;

	/**
	 * 회원 이메일
	 */
	@Column(name="we_user_email")
	private String we_user_email;

	/**
	 * 회원 블로그
	 */
	@Column(name="we_user_site")
	private String we_user_site;

	/**
	 * 회원연락처1
	 */
	@Column(name="we_cell_num1")
	private String we_cell_num1;

	/**
	 * 회원연락처2
	 */
	@Column(name="we_cell_num2")
	private String we_cell_num2;

	/**
	 * 회원연락처3
	 */
	@Column(name="we_cell_num3")
	private String we_cell_num3;

	/**
	 * 회원 이미지 저장명
	 */
	@Column(name="we_file_save_name")
	private String we_file_save_name;

	/**
	 * 회원 이미지 실제 파일명
	 */
	@Column(name="we_file_real_name")
	private String we_file_real_name;

	/**
	 * 회원이미지파일저장경로
	 */
	@Column(name="we_file_save_path")
	private String we_file_save_path;

	/**
	 * 회원이미지 썸네일경로
	 */
	@Column(name="we_thumb_path")
	private String we_thumb_path;

	/**
	 * 회원이미지 썸네일 명
	 */
	@Column(name="we_thumb_name")
	private String we_thumb_name;

	/**
	 * 탈퇴여부
	 */
	@Column(name="we_away_yn")
	private String we_away_yn = "N";

	/**
	 * 등급
	 */
	@Column(name="we_grade")
	private Integer we_grade;

	/**
	 * 전문가여부
	 */
	@Column(name="we_tech_yn")
	private String we_tech_yn = "N";

	/**
	 * 포인트
	 */
	@Column(name="we_point")
	private Integer we_point = 0;

	/**
	 * 최종방문일
	 */
	@Column(name="we_visit_date")
	private Date we_visit_date;

	/**
	 * 저장일
	 */
	@Column(name="we_ins_date")
	private Date we_ins_date;

	/**
	 * 수정일
	 */
	@Column(name="we_upd_date")
	private Date we_upd_date;

	/**
	 * 실시간알림 체크여부
	 */
	@Column(name="we_noti_checked")
	private String we_noti_checked;

	// 조회용 생성자
	public WeProfile(String we_tech_yn, Integer we_point) {
		this.we_tech_yn = we_tech_yn;
		this.we_point = we_point;
	}

	public WeProfile() {

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
	 * @return the we_user_email
	 */
	public String getWe_user_email() {
		return we_user_email;
	}

	/**
	 * @param we_user_email the we_user_email to set
	 */
	public void setWe_user_email(String we_user_email) {
		this.we_user_email = we_user_email;
	}

	/**
	 * @return the we_user_site
	 */
	public String getWe_user_site() {
		return we_user_site;
	}

	/**
	 * @param we_user_site the we_user_site to set
	 */
	public void setWe_user_site(String we_user_site) {
		this.we_user_site = we_user_site;
	}

	/**
	 * @return the we_cell_num1
	 */
	public String getWe_cell_num1() {
		return we_cell_num1;
	}

	/**
	 * @param we_cell_num1 the we_cell_num1 to set
	 */
	public void setWe_cell_num1(String we_cell_num1) {
		this.we_cell_num1 = we_cell_num1;
	}

	/**
	 * @return the we_cell_num2
	 */
	public String getWe_cell_num2() {
		return we_cell_num2;
	}

	/**
	 * @param we_cell_num2 the we_cell_num2 to set
	 */
	public void setWe_cell_num2(String we_cell_num2) {
		this.we_cell_num2 = we_cell_num2;
	}

	/**
	 * @return the we_cell_num3
	 */
	public String getWe_cell_num3() {
		return we_cell_num3;
	}

	/**
	 * @param we_cell_num3 the we_cell_num3 to set
	 */
	public void setWe_cell_num3(String we_cell_num3) {
		this.we_cell_num3 = we_cell_num3;
	}

	/**
	 * @return the we_file_save_name
	 */
	public String getWe_file_save_name() {
		return we_file_save_name;
	}

	/**
	 * @param we_file_save_name the we_file_save_name to set
	 */
	public void setWe_file_save_name(String we_file_save_name) {
		this.we_file_save_name = we_file_save_name;
	}

	/**
	 * @return the we_file_real_name
	 */
	public String getWe_file_real_name() {
		return we_file_real_name;
	}

	/**
	 * @param we_file_real_name the we_file_real_name to set
	 */
	public void setWe_file_real_name(String we_file_real_name) {
		this.we_file_real_name = we_file_real_name;
	}

	/**
	 * @return the we_file_save_path
	 */
	public String getWe_file_save_path() {
		return we_file_save_path;
	}

	/**
	 * @param we_file_save_path the we_file_save_path to set
	 */
	public void setWe_file_save_path(String we_file_save_path) {
		this.we_file_save_path = we_file_save_path;
	}

	/**
	 * @return the we_thumb_path
	 */
	public String getWe_thumb_path() {
		return we_thumb_path;
	}

	/**
	 * @param we_thumb_path the we_thumb_path to set
	 */
	public void setWe_thumb_path(String we_thumb_path) {
		this.we_thumb_path = we_thumb_path;
	}

	/**
	 * @return the we_thumb_name
	 */
	public String getWe_thumb_name() {
		return we_thumb_name;
	}

	/**
	 * @param we_thumb_name the we_thumb_name to set
	 */
	public void setWe_thumb_name(String we_thumb_name) {
		this.we_thumb_name = we_thumb_name;
	}

	/**
	 * @return the we_away_yn
	 */
	public String getWe_away_yn() {
		return we_away_yn;
	}

	/**
	 * @param we_away_yn the we_away_yn to set
	 */
	public void setWe_away_yn(String we_away_yn) {
		this.we_away_yn = we_away_yn;
	}

	/**
	 * @return the we_grade
	 */
	public Integer getWe_grade() {
		return we_grade;
	}

	/**
	 * @param we_grade the we_grade to set
	 */
	public void setWe_grade(Integer we_grade) {
		this.we_grade = we_grade;
	}

	/**
	 * @return the we_tech_yn
	 */
	public String getWe_tech_yn() {
		return we_tech_yn;
	}

	/**
	 * @param we_tech_yn the we_tech_yn to set
	 */
	public void setWe_tech_yn(String we_tech_yn) {
		this.we_tech_yn = we_tech_yn;
	}

	/**
	 * @return the we_point
	 */
	public Integer getWe_point() {
		return we_point;
	}

	/**
	 * @param we_point the we_point to set
	 */
	public void setWe_point(Integer we_point) {
		this.we_point = we_point;
	}

	/**
	 * @return the we_visit_date
	 */
	public Date getWe_visit_date() {
		return we_visit_date;
	}

	/**
	 * @param we_visit_date the we_visit_date to set
	 */
	public void setWe_visit_date(Date we_visit_date) {
		this.we_visit_date = we_visit_date;
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
	 * @return the we_noti_checked
	 */
	public String getWe_noti_checked() {
		return we_noti_checked;
	}

	/**
	 * @param we_noti_checked the we_noti_checked to set
	 */
	public void setWe_noti_checked(String we_noti_checked) {
		this.we_noti_checked = we_noti_checked;
	}
}

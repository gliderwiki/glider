/**
 * @FileName  : WeCategory.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 21. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;

/**
 * @author yion
 *
 */
@Table("WE_CATEGORY")
public class WeCategory extends BaseObjectBean {

	/**
	 * 카테고리 순번
	 */
	@Column(name="we_category_idx",primaryKey=true, autoIncrement=true)
	private Integer we_category_idx;
	
	/**
	 * 카테고리 종료 1:대분류, 2:중분류 
	 */
	@Column(name="we_category_kind") 
	private String we_category_kind;
	
	/**
	 * 카테고리 명 
	 */
	@Column(name="we_category_name") 
	private String we_category_name;
	
	/**
	 * 카테고리 부모순번
	 */
	@Column(name="we_parent_cate_id") 
	private Integer we_parent_cate_id;
	
	/**
	 * 카테고리 정렬순서 
	 */
	@Column(name="we_category_sort") 
	private Integer we_category_sort;
	
	/**
	 * 공간 순번 
	 */
	@Column(name="we_space_idx") 
	private Integer we_space_idx;
	
	/**
	 * 사용여부 
	 */
	@Column(name="we_use_yn") 
	private String we_use_yn;

	/**
	 * @return the we_category_idx
	 */
	public Integer getWe_category_idx() {
		return we_category_idx;
	}

	/**
	 * @param we_category_idx the we_category_idx to set
	 */
	public void setWe_category_idx(Integer we_category_idx) {
		this.we_category_idx = we_category_idx;
	}

	/**
	 * @return the we_category_kind
	 */
	public String getWe_category_kind() {
		return we_category_kind;
	}

	/**
	 * @param we_category_kind the we_category_kind to set
	 */
	public void setWe_category_kind(String we_category_kind) {
		this.we_category_kind = we_category_kind;
	}

	/**
	 * @return the we_category_name
	 */
	public String getWe_category_name() {
		return we_category_name;
	}

	/**
	 * @param we_category_name the we_category_name to set
	 */
	public void setWe_category_name(String we_category_name) {
		this.we_category_name = we_category_name;
	}

	/**
	 * @return the we_parent_cate_id
	 */
	public Integer getWe_parent_cate_id() {
		return we_parent_cate_id;
	}

	/**
	 * @param we_parent_cate_id the we_parent_cate_id to set
	 */
	public void setWe_parent_cate_id(Integer we_parent_cate_id) {
		this.we_parent_cate_id = we_parent_cate_id;
	}

	/**
	 * @return the we_category_sort
	 */
	public Integer getWe_category_sort() {
		return we_category_sort;
	}

	/**
	 * @param we_category_sort the we_category_sort to set
	 */
	public void setWe_category_sort(Integer we_category_sort) {
		this.we_category_sort = we_category_sort;
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

	
}

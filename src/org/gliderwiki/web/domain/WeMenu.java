/**
 * @FileName  : WeMenu.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 메뉴관리 
 */
package org.gliderwiki.web.domain;

import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;





/**
 * @author yion
 *
 */
@Table("WE_MENU")
@DataTransferObject
public class WeMenu extends BaseObjectBean {

	@Column(name="we_menu_idx",primaryKey=true, autoIncrement=true)
	private Integer we_menu_idx;
	
	@Column(name="we_menu_name") 
	private String  we_menu_name;

	@Column(name="we_menu_order_idx") 
	private Integer we_menu_order_idx;
	
	@Column(name="we_menu_parent_idx") 
	private Integer we_menu_parent_idx;
	
	@Column(name="we_menu_depth") 
	private Integer we_menu_depth;
	
	@Column(name="we_menu_url") 
	private String we_menu_url;
		
	@Column(name="we_use_yn") 
	private String we_use_yn;
		
	@Column(name="we_menu_type") 
	private String we_menu_type;
	
	
	@Column(name="we_access_level") 
	private String we_access_level;
	
	@Column(name="we_menu_group") 
	private String we_menu_group;

	


	/**
	 * @return the we_menu_idx
	 */
	public Integer getWe_menu_idx() {
		return we_menu_idx;
	}

	/**
	 * @param we_menu_idx the we_menu_idx to set
	 */
	public void setWe_menu_idx(Integer we_menu_idx) {
		this.we_menu_idx = we_menu_idx;
	}

	/**
	 * @return the we_menu_name
	 */
	public String getWe_menu_name() {
		return we_menu_name;
	}

	/**
	 * @param we_menu_name the we_menu_name to set
	 */
	public void setWe_menu_name(String we_menu_name) {
		this.we_menu_name = we_menu_name;
	}

	/**
	 * @return the we_menu_order_idx
	 */
	public Integer getWe_menu_order_idx() {
		return we_menu_order_idx;
	}

	/**
	 * @param we_menu_order_idx the we_menu_order_idx to set
	 */
	public void setWe_menu_order_idx(Integer we_menu_order_idx) {
		this.we_menu_order_idx = we_menu_order_idx;
	}

	/**
	 * @return the we_menu_parent_idx
	 */
	public Integer getWe_menu_parent_idx() {
		return we_menu_parent_idx;
	}

	/**
	 * @param we_menu_parent_idx the we_menu_parent_idx to set
	 */
	public void setWe_menu_parent_idx(Integer we_menu_parent_idx) {
		this.we_menu_parent_idx = we_menu_parent_idx;
	}

	/**
	 * @return the we_menu_depth
	 */
	public Integer getWe_menu_depth() {
		return we_menu_depth;
	}

	/**
	 * @param we_menu_depth the we_menu_depth to set
	 */
	public void setWe_menu_depth(Integer we_menu_depth) {
		this.we_menu_depth = we_menu_depth;
	}

	/**
	 * @return the we_menu_url
	 */
	public String getWe_menu_url() {
		return we_menu_url;
	}

	/**
	 * @param we_menu_url the we_menu_url to set
	 */
	public void setWe_menu_url(String we_menu_url) {
		this.we_menu_url = we_menu_url;
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
	 * @return the we_menu_type
	 */
	public String getWe_menu_type() {
		return we_menu_type;
	}

	/**
	 * @param we_menu_type the we_menu_type to set
	 */
	public void setWe_menu_type(String we_menu_type) {
		this.we_menu_type = we_menu_type;
	}
	

	/**
	 * @return the we_access_level
	 */
	public String getWe_access_level() {
		return we_access_level;
	}

	/**
	 * @param we_access_level the we_access_level to set
	 */
	public void setWe_access_level(String we_access_level) {
		this.we_access_level = we_access_level;
	}

	
	/**
	 * @return the we_menu_group
	 */
	public String getWe_menu_group() {
		return we_menu_group;
	}

	/**
	 * @param we_menu_group the we_menu_group to set
	 */
	public void setWe_menu_group(String we_menu_group) {
		this.we_menu_group = we_menu_group;
	}
}	

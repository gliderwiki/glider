/**
 * @FileName  : WePatch.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 22. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.util.Date;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author yion
 *
 */
@Table("WE_PATCH")
public class WePatch extends BaseObjectBean {

	@Column(primaryKey=true, autoIncrement=true)
	private Integer we_patch_idx;
	
	
	@Column(name="we_function_name")
	private Integer we_function_idx;
	
	@Column(name="we_file_name")
	private String we_file_name;
	
	@Column(name="we_file_path")
	private String we_file_path;
	
	@Column(name="we_patch_type")
	private String we_patch_type;
	
	@Column(name="we_patch_path")
	private String we_patch_path;
	
	@Column(name="we_ins_date")
	private Date we_ins_date;
	
	
	private MultipartFile file; 	// 파일 객체


	/**
	 * @return the we_patch_idx
	 */
	public Integer getWe_patch_idx() {
		return we_patch_idx;
	}


	/**
	 * @param we_patch_idx the we_patch_idx to set
	 */
	public void setWe_patch_idx(Integer we_patch_idx) {
		this.we_patch_idx = we_patch_idx;
	}


	/**
	 * @return the we_function_idx
	 */
	public Integer getWe_function_idx() {
		return we_function_idx;
	}


	/**
	 * @param we_function_idx the we_function_idx to set
	 */
	public void setWe_function_idx(Integer we_function_idx) {
		this.we_function_idx = we_function_idx;
	}


	/**
	 * @return the we_file_name
	 */
	public String getWe_file_name() {
		return we_file_name;
	}


	/**
	 * @param we_file_name the we_file_name to set
	 */
	public void setWe_file_name(String we_file_name) {
		this.we_file_name = we_file_name;
	}


	/**
	 * @return the we_file_path
	 */
	public String getWe_file_path() {
		return we_file_path;
	}


	/**
	 * @param we_file_path the we_file_path to set
	 */
	public void setWe_file_path(String we_file_path) {
		this.we_file_path = we_file_path;
	}


	/**
	 * @return the we_patch_type
	 */
	public String getWe_patch_type() {
		return we_patch_type;
	}


	/**
	 * @param we_patch_type the we_patch_type to set
	 */
	public void setWe_patch_type(String we_patch_type) {
		this.we_patch_type = we_patch_type;
	}


	/**
	 * @return the we_patch_path
	 */
	public String getWe_patch_path() {
		return we_patch_path;
	}


	/**
	 * @param we_patch_path the we_patch_path to set
	 */
	public void setWe_patch_path(String we_patch_path) {
		this.we_patch_path = we_patch_path;
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
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}


	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
	
}

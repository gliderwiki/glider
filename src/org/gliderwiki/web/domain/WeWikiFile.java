/**
 * @FileName  : WeWikiFile.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 3. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 위키 파일 업로드 정보 
 */
package org.gliderwiki.web.domain;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;

/**
 * @author yion
 *
 */
@Table("WE_WIKI_FILE")
public class WeWikiFile  extends BaseObjectBean {

	@Column(name="we_file_idx", primaryKey=true, autoIncrement=true)
	private Integer we_file_idx;
	
	/**
	 * 위키순번 
	 */
	@Column(name="we_wiki_idx") 
	private Integer we_wiki_idx;
	
	@Column(name="we_file_real_name")
	private String we_file_real_name;

	@Column(name="we_file_save_name")
	private String we_file_save_name;
	
	@Column(name="we_file_save_path")
	private String we_file_save_path;
	
	@Column(name="we_file_type")
	private String we_file_type;
	
	@Column(name="we_thumb_path")
	private String we_thumb_path;
	
	@Column(name="we_thumb_name")
	private String we_thumb_name;
	
		
	@Column(name="we_file_size")
	private Long we_file_size;
	
	/**
	 * 위키리비전 
	 */
	@Column(name="we_wiki_revision") 
	private Integer we_wiki_revision;
	
	
	@Column(name="we_file_down")
	private Integer we_file_down;

	@Column(name="we_use_yn") 
	private String we_use_yn;

	/**
	 * @return the we_file_idx
	 */
	public Integer getWe_file_idx() {
		return we_file_idx;
	}

	/**
	 * @param we_file_idx the we_file_idx to set
	 */
	public void setWe_file_idx(Integer we_file_idx) {
		this.we_file_idx = we_file_idx;
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
	 * @return the we_file_type
	 */
	public String getWe_file_type() {
		return we_file_type;
	}

	/**
	 * @param we_file_type the we_file_type to set
	 */
	public void setWe_file_type(String we_file_type) {
		this.we_file_type = we_file_type;
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
	 * @return the we_file_size
	 */
	public Long getWe_file_size() {
		return we_file_size;
	}

	/**
	 * @param we_file_size the we_file_size to set
	 */
	public void setWe_file_size(Long we_file_size) {
		this.we_file_size = we_file_size;
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
	 * @return the we_file_down
	 */
	public Integer getWe_file_down() {
		return we_file_down;
	}

	/**
	 * @param we_file_down the we_file_down to set
	 */
	public void setWe_file_down(Integer we_file_down) {
		this.we_file_down = we_file_down;
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

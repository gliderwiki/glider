/**
 * @FileName  : WeWikiNote.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 3. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 위키 각주 
 */
package org.gliderwiki.web.domain;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;

/**
 * @author yion
 *
 */

@Table("WE_WIKI_NOTE")
public class WeWikiNote extends BaseObjectBean {


	/**
	 * 각주 순번  
	 */
	@Column(name="we_wiki_note_num", primaryKey=true, autoIncrement=true)
	private Integer we_wiki_note_num;
	

	/**
	 * 각주 순번  
	 */
	@Column(name="we_wiki_note_name") 
	private String we_wiki_note_name;
	

	/**
	 * 각주 순번  
	 */
	@Column(name="we_wiki_note_desc") 
	private String we_wiki_note_desc;
	
	
	
	/**
	 * 위키순번 
	 */
	@Column(name="we_wiki_idx") 
	private Integer we_wiki_idx;
	
	/**
	 * 위키리비전 
	 */
	@Column(name="we_wiki_revision") 
	private Integer we_wiki_revision;

	

	@Column(name="we_use_yn") 
	private String we_use_yn;

	
	
	/**
	 * @return the we_wiki_note_num
	 */
	public Integer getWe_wiki_note_num() {
		return we_wiki_note_num;
	}

	/**
	 * @param we_wiki_note_num the we_wiki_note_num to set
	 */
	public void setWe_wiki_note_num(Integer we_wiki_note_num) {
		this.we_wiki_note_num = we_wiki_note_num;
	}

	/**
	 * @return the we_wiki_note_name
	 */
	public String getWe_wiki_note_name() {
		return we_wiki_note_name;
	}

	/**
	 * @param we_wiki_note_name the we_wiki_note_name to set
	 */
	public void setWe_wiki_note_name(String we_wiki_note_name) {
		this.we_wiki_note_name = we_wiki_note_name;
	}

	/**
	 * @return the we_wiki_note_desc
	 */
	public String getWe_wiki_note_desc() {
		return we_wiki_note_desc;
	}

	/**
	 * @param we_wiki_note_desc the we_wiki_note_desc to set
	 */
	public void setWe_wiki_note_desc(String we_wiki_note_desc) {
		this.we_wiki_note_desc = we_wiki_note_desc;
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
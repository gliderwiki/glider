/**
 * @FileName  : WeWikiTag.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 5. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
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
@SuppressWarnings("serial")
@Table("WE_WIKI_TAG")
@DataTransferObject
public class WeWikiTag extends BaseObjectBean {
	

	/**
	 * 태그순번 
	 */
	@Column(name="we_wiki_tag_idx", primaryKey=true, autoIncrement=true)
	private Integer we_wiki_tag_idx;
	
	
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
	
	/**
	 * 태그명 
	 */
	@Column(name="we_tag") 
	private String we_tag;

	/**
	 * 입력일
	 */
	@Column(name="we_ins_date") 
	private String we_ins_date;
	
	/**
	 * 사용여부
	 */
	@Column(name="we_use_yn") 
	private String we_use_yn;

	/**
	 * @return the we_tag_idx
	 */
	public Integer getWe_wiki_tag_idx() {
		return we_wiki_tag_idx;
	}

	/**
	 * @param we_tag_idx the we_tag_idx to set
	 */
	public void setWe_wiki_tag_idx(Integer we_wiki_tag_idx) {
		this.we_wiki_tag_idx = we_wiki_tag_idx;
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
	 * @return the we_tag
	 */
	public String getWe_tag() {
		return we_tag;
	}

	/**
	 * @param we_tag the we_tag to set
	 */
	public void setWe_tag(String we_tag) {
		this.we_tag = we_tag;
	}

	/**
	 * @return the we_ins_date
	 */
	public String getWe_ins_date() {
		return we_ins_date;
	}

	/**
	 * @param we_ins_date the we_ins_date to set
	 */
	public void setWe_ins_date(String we_ins_date) {
		this.we_ins_date = we_ins_date;
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

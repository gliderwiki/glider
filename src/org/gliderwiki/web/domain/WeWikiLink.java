/**
 * @FileName  : WeWikiLink.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 3. 
 * @작성자      : @author inamhui

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
@Table("WE_WIKI_LINK")
public class WeWikiLink extends BaseObjectBean {


	/**
	 * 링크순번 
	 */
	@Column(name="we_link_idx", primaryKey=true, autoIncrement=true)
	private Integer we_link_idx;
	
	/**
	 * 링크주소 
	 */
	@Column(name="we_link_url") 
	private String we_link_url;
	
	/**
	 * 링크
	 */
	@Column(name="we_link_title") 
	private String we_link_title;
	
	
	/**
	 * 링크텍스트 
	 */
	@Column(name="we_link_text") 
	private String we_link_text;
	
	
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
	 * @return the we_link_idx
	 */
	public Integer getWe_link_idx() {
		return we_link_idx;
	}


	/**
	 * @param we_link_idx the we_link_idx to set
	 */
	public void setWe_link_idx(Integer we_link_idx) {
		this.we_link_idx = we_link_idx;
	}


	/**
	 * @return the we_link_url
	 */
	public String getWe_link_url() {
		return we_link_url;
	}


	/**
	 * @param we_link_url the we_link_url to set
	 */
	public void setWe_link_url(String we_link_url) {
		this.we_link_url = we_link_url;
	}


	/**
	 * @return the we_link_title
	 */
	public String getWe_link_title() {
		return we_link_title;
	}


	/**
	 * @param we_link_title the we_link_title to set
	 */
	public void setWe_link_title(String we_link_title) {
		this.we_link_title = we_link_title;
	}


	/**
	 * @return the we_link_text
	 */
	public String getWe_link_text() {
		return we_link_text;
	}


	/**
	 * @param we_link_text the we_link_text to set
	 */
	public void setWe_link_text(String we_link_text) {
		this.we_link_text = we_link_text;
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


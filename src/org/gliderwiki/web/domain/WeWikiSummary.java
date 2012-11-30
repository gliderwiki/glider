/**
 * @FileName  : WeWikiSummary.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 3. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 위키 헤드라인 섬머리 
 */
package org.gliderwiki.web.domain;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;

/**
 * @author yion
 * 
 */

@Table("WE_WIKI_SUMMARY")
public class WeWikiSummary extends BaseObjectBean {

	/**
	 * 섬머리 순번
	 */
	@Column(name = "we_summary_idx", primaryKey = true, autoIncrement = true)
	private Integer we_summary_idx;

	/**
	 * 위키순번
	 */
	@Column(name = "we_wiki_idx")
	private Integer we_wiki_idx;

	/**
	 * 위키리비전
	 */
	@Column(name = "we_wiki_revision")
	private Integer we_wiki_revision;

	/**
	 * 섬머리 타이틀
	 */
	@Column(name = "we_summary_title")
	private String we_summary_title;

	@Column(name = "we_summary_tag")
	private String we_summary_tag;

	@Column(name = "we_use_yn")
	private String we_use_yn;

	/**
	 * @return the we_summary_idx
	 */
	public Integer getWe_summary_idx() {
		return we_summary_idx;
	}

	/**
	 * @param we_summary_idx the we_summary_idx to set
	 */
	public void setWe_summary_idx(Integer we_summary_idx) {
		this.we_summary_idx = we_summary_idx;
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
	 * @return the we_summary_title
	 */
	public String getWe_summary_title() {
		return we_summary_title;
	}

	/**
	 * @param we_summary_title the we_summary_title to set
	 */
	public void setWe_summary_title(String we_summary_title) {
		this.we_summary_title = we_summary_title;
	}

	/**
	 * @return the we_summary_tag
	 */
	public String getWe_summary_tag() {
		return we_summary_tag;
	}

	/**
	 * @param we_summary_tag the we_summary_tag to set
	 */
	public void setWe_summary_tag(String we_summary_tag) {
		this.we_summary_tag = we_summary_tag;
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
/**
 * @FileName  : WeWikiGraph.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 15. 
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;


/**
 * @author ganji
 *
 */
@Table("WE_WIKI_GRAPH")
@DataTransferObject
public class WeWikiGraph extends BaseObjectBean {

	private static final long serialVersionUID = 1701670437274928359L;
	
	/**
	 * 섬머리 순번
	 */
	@Column(name = "we_graph_idx", primaryKey = true, autoIncrement = true)
	private Integer we_graph_idx;
	
	/**
	 * 위키순번
	 */
	@Column(name="we_wiki_idx") 
	private Integer we_wiki_idx;

	/**
	 * 위키리비전
	 */
	@Column(name = "we_wiki_revision")
	private Integer we_wiki_revision;
	
	/**
	 * 그래프 갯수
	 */
	@Column(name="we_graph_cnt") 
	private Integer we_graph_cnt;

	@Column(name="we_use_yn") 
	private String we_use_yn;
	
	public Integer getWe_wiki_idx() {
		return we_wiki_idx;
	}

	public void setWe_wiki_idx(Integer we_wiki_idx) {
		this.we_wiki_idx = we_wiki_idx;
	}

	public Integer getWe_graph_cnt() {
		return we_graph_cnt;
	}

	public void setWe_graph_cnt(Integer we_graph_cnt) {
		this.we_graph_cnt = we_graph_cnt;
	}

	public Integer getWe_graph_idx() {
		return we_graph_idx;
	}

	public void setWe_graph_idx(Integer we_graph_idx) {
		this.we_graph_idx = we_graph_idx;
	}

	public Integer getWe_wiki_revision() {
		return we_wiki_revision;
	}

	public void setWe_wiki_revision(Integer we_wiki_revision) {
		this.we_wiki_revision = we_wiki_revision;
	}

	public String getWe_use_yn() {
		return we_use_yn;
	}

	public void setWe_use_yn(String we_use_yn) {
		this.we_use_yn = we_use_yn;
	}	
	
}

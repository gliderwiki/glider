/**
 * @FileName  : WeFavorite.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 17.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.domain;

import java.io.Serializable;
import java.util.Date;

import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.framework.util.DateUtil;


/**
 * @author bluepoet
 *
 */
@Table("WE_FAVORITE")
@DataTransferObject
public class WeFavorite implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1222752497063273097L;

	@Column(name = "we_user_idx")
	private Integer we_user_idx;

	@Column(name = "we_favorite_type")
	private FavorityType we_favorite_type;

	@Column(name = "we_space_idx")
	private Integer we_space_idx;

	@Column(name = "we_wiki_idx")
	private Integer we_wiki_idx;

	@Column(name = "we_use_yn")
	private String we_use_yn;

	@Column(name = "we_add_date")
	private Date we_add_date;

	@Column(name = "we_del_date")
	private Date we_del_date;

	public WeFavorite() {

	}

	public WeFavorite(Integer we_user_idx, FavorityType we_favorite_type, Integer we_space_idx, Integer we_wiki_idx) throws Exception {
		this.we_user_idx = we_user_idx;
		this.we_favorite_type = we_favorite_type;
		this.we_space_idx = we_space_idx;
		this.we_wiki_idx = we_wiki_idx;
		this.we_use_yn = "Y";
		this.we_add_date = new Date();
	}
	
	public WeFavorite(Integer we_user_idx, FavorityType we_favorite_type, Integer we_space_idx, Integer we_wiki_idx, Date we_add_date) throws Exception {
		this.we_user_idx = we_user_idx;
		this.we_favorite_type = we_favorite_type;
		this.we_space_idx = we_space_idx;
		this.we_wiki_idx = we_wiki_idx;
		this.we_add_date = we_add_date;
	}
	
	/**
	 * @return the we_user_idx
	 */
	public Integer getWe_user_idx() {
		return we_user_idx;
	}

	/**
	 * @param we_user_idx
	 *            the we_user_idx to set
	 */
	public void setWe_user_idx(Integer we_user_idx) {
		this.we_user_idx = we_user_idx;
	}

	/**
	 * @return the we_space_idx
	 */
	public Integer getWe_space_idx() {
		return we_space_idx;
	}

	/**
	 * @param we_space_idx
	 *            the we_space_idx to set
	 */
	public void setWe_space_idx(Integer we_space_idx) {
		this.we_space_idx = we_space_idx;
	}

	/**
	 * @return the we_wiki_idx
	 */
	public Integer getWe_wiki_idx() {
		return we_wiki_idx;
	}

	/**
	 * @param we_wiki_idx
	 *            the we_wiki_idx to set
	 */
	public void setWe_wiki_idx(Integer we_wiki_idx) {
		this.we_wiki_idx = we_wiki_idx;
	}

	/**
	 * @return the we_use_yn
	 */
	public String getWe_use_yn() {
		return we_use_yn;
	}

	/**
	 * @param we_use_yn
	 *            the we_use_yn to set
	 */
	public void setWe_use_yn(String we_use_yn) {
		this.we_use_yn = we_use_yn;
	}

	/**
	 * @return the we_ins_date
	 */
	public Date getWe_add_date() {
		return we_add_date;
	}

	/**
	 * @param we_ins_date
	 *            the we_ins_date to set
	 */
	public void setWe_add_date(Date we_add_date) {
		this.we_add_date = we_add_date;
	}

	/**
	 * @return the we_del_date
	 */
	public Date getWe_del_date() {
		return we_del_date;
	}

	/**
	 * @param we_del_date
	 *            the we_del_date to set
	 */
	public void setWe_del_date(Date we_del_date) {
		this.we_del_date = we_del_date;
	}

	public FavorityType getWe_favorite_type() {
		return we_favorite_type;
	}

	public void setWe_favorite_type(FavorityType we_favorite_type) {
		this.we_favorite_type = we_favorite_type;
	}
	
	
}
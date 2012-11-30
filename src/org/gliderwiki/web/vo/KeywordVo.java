/**
 * @FileName  : KeywordVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

import java.util.Date;

/**
 * @author yion
 *
 */
public class KeywordVo extends BaseObjectBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 583794306853252101L;

	private Integer weWikiTagIdx;
	
	private String weWikiTitle;
	
	private Integer weWikiIdx;
	
	private String weTag;
	
	private Integer weWikiRevision;
	
	private Date weInsDate;
	
	
	private int startRow;
	
	private int endRow;

	/**
	 * @return the weWikiTagIdx
	 */
	public Integer getWeWikiTagIdx() {
		return weWikiTagIdx;
	}

	/**
	 * @param weWikiTagIdx the weWikiTagIdx to set
	 */
	public void setWeWikiTagIdx(Integer weWikiTagIdx) {
		this.weWikiTagIdx = weWikiTagIdx;
	}

	/**
	 * @return the weWikiTitle
	 */
	public String getWeWikiTitle() {
		return weWikiTitle;
	}

	/**
	 * @param weWikiTitle the weWikiTitle to set
	 */
	public void setWeWikiTitle(String weWikiTitle) {
		this.weWikiTitle = weWikiTitle;
	}

	/**
	 * @return the weWikiIdx
	 */
	public Integer getWeWikiIdx() {
		return weWikiIdx;
	}

	/**
	 * @param weWikiIdx the weWikiIdx to set
	 */
	public void setWeWikiIdx(Integer weWikiIdx) {
		this.weWikiIdx = weWikiIdx;
	}

	/**
	 * @return the weTag
	 */
	public String getWeTag() {
		return weTag;
	}

	/**
	 * @param weTag the weTag to set
	 */
	public void setWeTag(String weTag) {
		this.weTag = weTag;
	}

	/**
	 * @return the weWikiRevision
	 */
	public Integer getWeWikiRevision() {
		return weWikiRevision;
	}

	/**
	 * @param weWikiRevision the weWikiRevision to set
	 */
	public void setWeWikiRevision(Integer weWikiRevision) {
		this.weWikiRevision = weWikiRevision;
	}

	/**
	 * @return the weInsDate
	 */
	public Date getWeInsDate() {
		return weInsDate;
	}

	/**
	 * @param weInsDate the weInsDate to set
	 */
	public void setWeInsDate(Date weInsDate) {
		this.weInsDate = weInsDate;
	}

	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the endRow
	 */
	public int getEndRow() {
		return endRow;
	}

	/**
	 * @param endRow the endRow to set
	 */
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	
	
	
}

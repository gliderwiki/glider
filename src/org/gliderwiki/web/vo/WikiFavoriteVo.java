/**
 * @FileName  : WikiFavoriteVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 즐겨찾기 내역, 공간 및 위키 
 */
package org.gliderwiki.web.vo;

import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.web.domain.FavorityType;

/**
 * @author yion
 *
 */
public class WikiFavoriteVo extends BaseObjectBean {
	
	/**
	 * 공간명
	 */
	private String weSpaceName;
	
	/**
	 * 공간설명
	 */
	private String weSpaceDesc;
	
	
	/**
	 * 위키 타이틀 
	 */
	private String weWikiTitle;
	
	/**
	 * 위키 버전 
	 */
	private String weWikiRevision;
	
	/**
	 * 위키 저장 상태 
	 */
	private String weWikiStatus;
	
	/**
	 * 위키 어드민 	
	 */
	private Integer weAdminIdx;
	
	/**
	 * 사용자 순번
	 */
	private Integer weUserIdx;
	
	/**
	 * 즐겨찾기 타입 wiki, space
	 */
	@Column(name = "we_favorite_type")
	private FavorityType weFavoriteType;
	
	/**
	 * 즐겨찾기 공간 순번 
	 */
	private Integer weSpaceIdx;
	
	/**
	 * 즐겨찾기 위키순번 
	 */
	private Integer weWikiIdx;
	
	/**
	 * 사용여부
	 */
	private String weUseYn;
	
	/**
	 * 추가일
	 */
	private String weAddDate;
	
	/**
	 * 삭제일 
	 */
	private String weDelDate;

	/**
	 * @return the weSpaceName
	 */
	public String getWeSpaceName() {
		return weSpaceName;
	}

	/**
	 * @param weSpaceName the weSpaceName to set
	 */
	public void setWeSpaceName(String weSpaceName) {
		this.weSpaceName = weSpaceName;
	}

	/**
	 * @return the weSpaceDesc
	 */
	public String getWeSpaceDesc() {
		return weSpaceDesc;
	}

	/**
	 * @param weSpaceDesc the weSpaceDesc to set
	 */
	public void setWeSpaceDesc(String weSpaceDesc) {
		this.weSpaceDesc = weSpaceDesc;
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
	 * @return the weWikiRevision
	 */
	public String getWeWikiRevision() {
		return weWikiRevision;
	}

	/**
	 * @param weWikiRevision the weWikiRevision to set
	 */
	public void setWeWikiRevision(String weWikiRevision) {
		this.weWikiRevision = weWikiRevision;
	}

	/**
	 * @return the weWikiStatus
	 */
	public String getWeWikiStatus() {
		return weWikiStatus;
	}

	/**
	 * @param weWikiStatus the weWikiStatus to set
	 */
	public void setWeWikiStatus(String weWikiStatus) {
		this.weWikiStatus = weWikiStatus;
	}

	/**
	 * @return the weAdminIdx
	 */
	public Integer getWeAdminIdx() {
		return weAdminIdx;
	}

	/**
	 * @param weAdminIdx the weAdminIdx to set
	 */
	public void setWeAdminIdx(Integer weAdminIdx) {
		this.weAdminIdx = weAdminIdx;
	}

	/**
	 * @return the weUserIdx
	 */
	public Integer getWeUserIdx() {
		return weUserIdx;
	}

	/**
	 * @param weUserIdx the weUserIdx to set
	 */
	public void setWeUserIdx(Integer weUserIdx) {
		this.weUserIdx = weUserIdx;
	}

	/**
	 * @return the weWikiTypeCd
	 */
	public FavorityType getWeFavoriteType() {
		return weFavoriteType;
	}

	/**
	 * @param weWikiTypeCd the weWikiTypeCd to set
	 */
	public void setWeFavoriteType(FavorityType weFavoriteType) {
		this.weFavoriteType = weFavoriteType;
	}

	/**
	 * @return the weSpaceIdx
	 */
	public Integer getWeSpaceIdx() {
		return weSpaceIdx;
	}

	/**
	 * @param weSpaceIdx the weSpaceIdx to set
	 */
	public void setWeSpaceIdx(Integer weSpaceIdx) {
		this.weSpaceIdx = weSpaceIdx;
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
	 * @return the weUseYn
	 */
	public String getWeUseYn() {
		return weUseYn;
	}

	/**
	 * @param weUseYn the weUseYn to set
	 */
	public void setWeUseYn(String weUseYn) {
		this.weUseYn = weUseYn;
	}

	/**
	 * @return the weAddDate
	 */
	public String getWeAddDate() {
		return weAddDate;
	}

	/**
	 * @param weAddDate the weAddDate to set
	 */
	public void setWeAddDate(String weAddDate) {
		this.weAddDate = weAddDate;
	}

	/**
	 * @return the weDelDate
	 */
	public String getWeDelDate() {
		return weDelDate;
	}

	/**
	 * @param weDelDate the weDelDate to set
	 */
	public void setWeDelDate(String weDelDate) {
		this.weDelDate = weDelDate;
	}
	
	
	
}

/**
 * @FileName  : AdminSpaceService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 21. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.service;

import java.util.Date;
import java.util.List;

import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeSpace;


/**
 * @author yion
 *
 */
public interface AdminSpaceService {

	/**
	 * 최근 개설된 공간 리스트 (1달)
	 * @param space
	 * @param month
	 * @return
	 */
	List<WeSpace> getSpaceListMonth(WeSpace space, Date month) throws Throwable;

	/**
	 * 공간 리스트 검색 (관리자 or 공간명)
	 * @param weSpace
	 * @return
	 */
	List<WeSpace> getSpaceSearchList(WeSpace weSpace) throws Throwable;
	
	/**
	 * 공간 상세 정보 
	 * @param weSpaceIdx
	 * @param attrId
	 * @return
	 * @throws Throwable
	 */
	Object[] getSpaceDetailInfo(Integer weSpaceIdx, String attrId) throws Throwable;

	
	int updateSpaceByAdmin(Integer spaceIdx, String spaceName, String spaceDesc, Integer spaceAdminIdx, String spaceExposed, Integer weUserIdx) throws Throwable;
	
	Object[] deleteSpaceInfo(Integer weSpaceIdx,  Integer weUserIdx) throws Throwable;

	/**
	 * 게시판 검색 
	 * @param weBbs
	 * @return
	 * @throws Throwable
	 */
	List<WeBbs> getBbsSearchList(WeBbs weBbs) throws Throwable;
}

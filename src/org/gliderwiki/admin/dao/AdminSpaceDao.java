/**
 * @FileName  : AdminSpaceDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 10. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.Date;
import java.util.List;

import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;


/**
 * @author yion
 *
 */
public interface AdminSpaceDao {

	/**
	 * @param space
	 * @param month
	 * @return
	 */
	List<WeSpace> getSpaceListMonth(WeSpace space, Date month) throws Throwable;

	/**
	 * @param weSpace
	 * @return
	 */
	List<WeSpace> getSpaceSearchList(WeSpace weSpace) throws Throwable;

	/**
	 * @param weSpaceIdx
	 * @return
	 */
	WeSpace getSpaceDetailInfo(Integer weSpaceIdx) throws Throwable;

	/**
	 * @param weSpaceIdx
	 * @return
	 */
	List<WeWiki> getWikiListInSpace(Integer weSpaceIdx) throws Throwable;

	/**
	 * @param space
	 * @return
	 */
	int updateSpaceByAdmin(WeSpace space) throws Throwable;

	/**
	 * @param weBbs
	 * @return
	 */
	List<WeBbs> getBbsSearchList(WeBbs weBbs) throws Throwable;


}

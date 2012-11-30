/**
 * @FileName  : AdminWikiDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 11. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.Date;
import java.util.List;

import org.gliderwiki.web.domain.WeWiki;


/**
 * @author yion
 *
 */
public interface AdminWikiDao {

	/**
	 * @param weUserNick
	 * @param weWikiTitle
	 * @param weWikiText
	 * @param weSpaceName
	 * @return
	 */
	List<WeWiki> getWikiSearchList(String weUserNick, String weWikiTitle, String weWikiText, String weSpaceName) throws Throwable;

	/**
	 * @param weWikiIdx
	 * @return
	 */
	WeWiki getWikiDetailInfo(Integer weWikiIdx) throws Throwable;

	/**
	 * @param domain
	 * @return
	 */
	int updateWeWikiByAdmin(WeWiki domain) throws Throwable;

	/**
	 * @param month
	 * @return
	 */
	List<WeWiki> getWikiListMonth(Date month) throws Throwable;

}

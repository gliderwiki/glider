/**
 * @FileName  : WikiFunctionDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 16. 
 * @작성자      : @author inamhui

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.dao;

import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiAgree;

/**
 * @author inamhui
 *
 */
public interface WikiFunctionDao {

	/**
	 * @param weWiki
	 * @return
	 */
	int updateWikiByUser(WeWiki weWiki) throws Throwable;

	/**
	 * @param wewikiagree
	 * @return
	 */
	int insertWeAgree(WeWikiAgree wewikiagree) throws Throwable;

}

/**
 * @FileName  : WikiFunctionDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 16. 
 * @작성자      : @author inamhui

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.dao;

import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiAgree;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author inamhui
 *
 */
@Repository("wikiFunctionDao")
public class WikiFunctionDaoImpl extends SqlSessionDaoSupport implements WikiFunctionDao  {

	@Override
	public int updateWikiByUser(WeWiki weWiki) throws Throwable {
		return getSqlSession().update("WikiManage.updateWikiByUser", weWiki);
	}

	@Override
	public int insertWeAgree(WeWikiAgree wewikiagree) throws Throwable {
		return getSqlSession().update("WikiManage.insertWeAgree", wewikiagree);
	}
	
}

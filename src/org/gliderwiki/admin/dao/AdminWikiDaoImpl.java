/**
 * @FileName  : AdminWikiDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 11. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("adminWikiDao")
public class AdminWikiDaoImpl extends SqlSessionDaoSupport implements AdminWikiDao{


	@Override
	public List<WeWiki> getWikiSearchList(String weUserNick, String weWikiTitle, String weWikiText, String weSpaceName) throws Throwable {
		Map<String, String> mapper = new HashMap<String, String>();
		mapper.put("weUserNick", weUserNick);
		mapper.put("weWikiTitle", weWikiTitle);
		mapper.put("weWikiText", weWikiText);
		mapper.put("weSpaceName", weSpaceName);
		
		List<WeWiki> list = null;
		
		list = (List<WeWiki>) getSqlSession().selectList("AdminManage.getWikiSearchList", mapper);
		return list;
	}

	
	@Override
	public WeWiki getWikiDetailInfo(Integer weWikiIdx) throws Throwable {
		return (WeWiki) getSqlSession().selectOne("AdminManage.getWikiDetailInfo" , weWikiIdx);
	}


	@Override
	public int updateWeWikiByAdmin(WeWiki domain) throws Throwable {
		int result = getSqlSession().update("AdminManage.updateWeWikiByAdmin", domain);
		return result;
	}


	@Override
	public List<WeWiki> getWikiListMonth(Date month) throws Throwable {
		List<WeWiki> list = null;
		
		list = (List<WeWiki>) getSqlSession().selectList("AdminManage.getWikiListMonth", month);
		return list;
	}

}

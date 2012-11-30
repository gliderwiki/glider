/**
 * @FileName  : AdminKeywordDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.List;

import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.vo.KeywordVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("adminKeywordDao")
public class AdminKeywordDaoImpl extends SqlSessionDaoSupport implements AdminKeywordDao {

	
	@Override
	public List<KeywordVo> getKeywordList(KeywordVo keyword) throws Throwable {
		List<KeywordVo>  list = null;
		list = (List<KeywordVo>) getSqlSession().selectList("AdminManage.getKeywordList", keyword);
		return list;
	}

	@Override
	public Integer updateKeywordWiki(WeWikiTag weWikiTag) throws Throwable {
		// TODO Auto-generated method stub
		return getSqlSession().update("AdminManage.updateKeywordWiki", weWikiTag);
	}

	

}

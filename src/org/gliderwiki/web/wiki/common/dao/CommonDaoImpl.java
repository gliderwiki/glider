/**
 * @FileName  : CommonDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 10.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeAddFriend;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.vo.WikiVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

/**
 * @author yion
 *
 */
@Repository("commonDao")
public class CommonDaoImpl extends SqlSessionDaoSupport implements  CommonDao {


	@Override
	public int delFavorite(WeFavorite weFavorite) throws Throwable {
		return getSqlSession().update("CommonManage.delFavorite", weFavorite);
	}


	@Override
	public int delRelationDWR(WeAddFriend weAddFriend) throws Throwable {
		return getSqlSession().update("CommonManage.delRelationDWR", weAddFriend);
	}

	@Override
	public void notiAllRead(int userIdx) throws Throwable {
		getSqlSession().update("CommonManage.updateAllRead", userIdx);

	}

	@Override
	public String realNotiView(int userIdx) throws Throwable {
		return (String) getSqlSession().selectOne("CommonManage.realNotiView", userIdx);
	}

	@Override
	public void changeRealTimeView(int userIdx, String isView) throws Throwable {
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("userIdx", userIdx);
		mapper.put("isView", isView);

		getSqlSession().update("CommonManage.changeRealTimeView", mapper);
	}

	@Override
	public int updateFavorite(WeFavorite weFavorite) throws Throwable {
		return getSqlSession().update("CommonManage.updateFavorite", weFavorite);
	}


	@Override
	public void updateUserPoint(WeProfile domain) throws Throwable {
		getSqlSession().update("CommonManage.updateUserPoint", domain);
	}


	
	@Override
	public List<WikiVo> getWikiSearchList(String wiki_text) throws Throwable {
		Map<String, String> mapper = new HashMap<String, String>();
		mapper.put("wiki_text", wiki_text);
		
		return getSqlSession().selectList("CommonManage.getWikiSearchList", mapper);
	}

}

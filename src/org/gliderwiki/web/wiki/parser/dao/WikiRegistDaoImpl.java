/**
 * @FileName  : WikiRegistDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 12.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.dao;

import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.vo.WikiLogVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

/**
 * @author yion
 *
 */
@Repository("wikiRegistDao")
public class WikiRegistDaoImpl extends SqlSessionDaoSupport implements WikiRegistDao {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String getMinDepthIdx(WeWiki weWiki) throws Throwable {
		return (String) getSqlSession().selectOne("WikiManage.getMinDepthIdx", weWiki);
	}

	@Override
	public String getMaxDepthIdx(WeWiki weWiki) throws Throwable {
		return (String) getSqlSession().selectOne("WikiManage.getMaxDepthIdx", weWiki);
	}

	@Override
	public int updateParentDepthIdx(WeWiki weWiki, String minDepthIdx) throws Throwable {
		weWiki.setWe_wiki_depth_idx(Integer.parseInt(minDepthIdx));
		return getSqlSession().update("WikiManage.updateParentDepthIdx", weWiki);
	}

	@Override
	public int getNextWikiIdx() throws Throwable {
		return (Integer) getSqlSession().selectOne("WikiManage.getNextWikiIdx");
	}

	@Override
	public int insertArrayFileList(String[] weFileIdx, int currIdx, int weWikiRevision) throws Throwable {
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("weWikiIdx", currIdx);
		mapper.put("weWikiRevision", weWikiRevision);
		mapper.put("list", weFileIdx);

		int result = getSqlSession().update("WikiManage.insertArrayFileList", mapper);
		return result;
	}

}

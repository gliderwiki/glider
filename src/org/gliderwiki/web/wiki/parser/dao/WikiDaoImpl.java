/**
 * @FileName  : WikiDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 28. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.dao;

import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.domain.WeWikiLog;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

/**
 * @author yion
 *
 */
@Repository("wikiDao")
public class WikiDaoImpl extends SqlSessionDaoSupport implements WikiDao {

	
	@Override
	public List<WeWiki> getWikiList(WeWiki searchWiki) throws Throwable {
		return (List<WeWiki>) getSqlSession().selectList("WikiManage.getWikiList", searchWiki);
	}

	
	@Override
	public int delWeWikiFile(Integer weFileIdx) throws Throwable {
		return getSqlSession().update("WikiManage.delWeWikiFile", weFileIdx);
	}


	@Override
	public int insertSelectedWikiBak(WeWikiBak wikiBak) throws Throwable {
		return getSqlSession().update("WikiManage.insertSelectedWikiBak", wikiBak);
	}


	@Override
	public int delWeWikiLink(Integer weWikiIdx, int weWikiRevision,	String weUseYn) throws Throwable {
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("weWikiIdx", weWikiIdx);
		mapper.put("weWikiRevision", weWikiRevision);
		mapper.put("weUseYn", weUseYn);

		int result = getSqlSession().update("WikiManage.delWeWikiLink", mapper);
		return result;
	}


	@Override
	public int delWeWikiSummary(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable {
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("weWikiIdx", weWikiIdx);
		mapper.put("weWikiRevision", weWikiRevision);
		mapper.put("weUseYn", weUseYn);

		int result = getSqlSession().update("WikiManage.delWeWikiSummary", mapper);
		return result;
	}


	@Override
	public int delWeWikiNote(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable {
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("weWikiIdx", weWikiIdx);
		mapper.put("weWikiRevision", weWikiRevision);
		mapper.put("weUseYn", weUseYn);

		int result = getSqlSession().update("WikiManage.delWeWikiNote", mapper);
		return result;
	}


	@Override
	public int delWeWikiTag(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable {
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("weWikiIdx", weWikiIdx);
		mapper.put("weWikiRevision", weWikiRevision);
		mapper.put("weUseYn", weUseYn);

		int result = getSqlSession().update("WikiManage.delWeWikiTag", mapper);
		return result;
	}


	@Override
	public int updateWikiFile(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable {
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("weWikiIdx", weWikiIdx);
		mapper.put("weWikiRevision", weWikiRevision);
		mapper.put("weUseYn", weUseYn);

		int result = getSqlSession().update("WikiManage.updateWikiFile", mapper);
		return result;
	}


	@Override
	public int delWeWikiGraph(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable {
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("weWikiIdx", weWikiIdx);
		mapper.put("weWikiRevision", weWikiRevision);
		mapper.put("weUseYn", weUseYn);

		int result = getSqlSession().update("WikiManage.delWeWikiGraph", mapper);
		return result;
	}


	@Override
	public int insertWikiLog(WeWikiLog wikiLog) throws Throwable {
		int result = getSqlSession().update("WikiManage.insertWikiLog", wikiLog);
		return result;
	}


	@Override
	public List<WeTemplate> getWeTemplateList(WeTemplate temp) throws Throwable {
		return (List<WeTemplate>) getSqlSession().selectList("WikiManage.getWeTemplateList", temp);
	}

	
	@Override
	public List<WeTemplate> getWeTemplateIdx(WeTemplate temp) throws Throwable {
		return (List<WeTemplate>) getSqlSession().selectList("WikiManage.getWeTemplateIdx", temp);
	}
	


}

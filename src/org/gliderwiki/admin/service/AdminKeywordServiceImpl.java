/**
 * @FileName  : AdminKeywordServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.admin.dao.AdminKeywordDao;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.KeywordVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yion
 *
 */
@Service("adminKeywordService")
@RemoteProxy(name="AdminKeywordService")
public class AdminKeywordServiceImpl implements AdminKeywordService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityService entityService;
		
	@Autowired
	private AdminKeywordDao adminKeywordDao;
	
	@Override
	public List<KeywordVo> getKeywordList(KeywordVo keyword) throws Throwable {
		return adminKeywordDao.getKeywordList(keyword);
	}

	@RemoteMethod
	public List<KeywordVo> getMoreKeyword(Integer startRow, Integer endRow) throws Throwable {
		logger.debug("#startRow : " + startRow);
		logger.debug("#endRow : " + endRow);
		
		KeywordVo keyword = new KeywordVo();
		
		keyword.setStartRow(startRow);	// 잘라올 시작점 
		keyword.setEndRow(endRow);		// 21개
		
		return this.getKeywordList(keyword);
	}

	@Override
	@RemoteMethod
	public int deleteKeyword(Integer weWikiTagIdx, Integer weUserIdx) throws Throwable {
		WeWikiTag domain = new WeWikiTag();
		domain.setWe_wiki_tag_idx(weWikiTagIdx);
		WeWikiTag tag = (WeWikiTag) entityService.getRowEntity(domain);	
		tag.setWe_use_yn("N");
		int result = entityService.updateEntity(tag);
		return result;
	}

	@Override
	public int deleteKeywordWiki(Integer wikiIdx, Integer weWikiRevisionIdx, Integer weUserIdx) throws Throwable {
		WeWikiTag tag = new WeWikiTag();
		tag.setWe_wiki_idx(wikiIdx);
		tag.setWe_wiki_revision(weWikiRevisionIdx);
		tag.setWe_use_yn("N");

		int result = adminKeywordDao.updateKeywordWiki(tag);
		return result;
	}
	
	
}

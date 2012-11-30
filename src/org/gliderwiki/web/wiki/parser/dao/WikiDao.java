/**
 * @FileName  : WikiDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 28. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.dao;

import java.util.List;

import org.gliderwiki.web.domain.WeTemplate;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiBak;
import org.gliderwiki.web.domain.WeWikiLog;


/**
 * @author yion
 *
 */
public interface WikiDao {

	/**
	 * @param searchWiki
	 * @return
	 */
	public List<WeWiki> getWikiList(WeWiki searchWiki) throws Throwable;

	/**
	 * @param weFileIdx
	 * @return
	 */
	public int delWeWikiFile(Integer weFileIdx) throws Throwable;

	/**
	 * 위키 수정시 원본 위키를 백업 테이블에 저장한다. 
	 * @param wikiBak
	 * @return
	 */
	public int insertSelectedWikiBak(WeWikiBak wikiBak) throws Throwable;

	
	/**
	 * 위키 수정시 기존 링크들을 제거한다. 
	 * @param weWikiIdx
	 * @param weWikiRevision
	 * @param weUseYn
	 * @return
	 * @throws Throwable
	 */
	public int delWeWikiLink(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable;
	
	
	/**
	 * 위키 수정시 기존 섬머리들을 제거한다.
	 * @param weWikiIdx
	 * @param weWikiRevision
	 * @param weUseYn
	 * @return
	 * @throws Throwable
	 */
	public int delWeWikiSummary(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable;
	
	/**
	 * 위키 수정시 기존 주석들을 제거한다.
	 * @param weWikiIdx
	 * @param weWikiRevision
	 * @param weUseYn
	 * @return
	 * @throws Throwable
	 */
	public int delWeWikiNote(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable;
	
	/**
	 * 위키 수정시 기존 태그들을 제거한다.
	 * @param weWikiIdx
	 * @param weWikiRevision
	 * @param weUseYn
	 * @return
	 * @throws Throwable
	 */
	public int delWeWikiTag(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable;
	
	/**
	 * 위키 파일 목록을 버전 업 한다 
	 * @param weWikiIdx
	 * @param weWikiRevision
	 * @param weUseYn
	 * @return
	 * @throws Throwable
	 */
	public int updateWikiFile(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable;
	
	/**
	 * 위키 수정시 기존 그래프들을 제거한다.
	 * @param weWikiIdx
	 * @param weWikiRevision
	 * @return
	 * @throws Throwable
	 */
	public int delWeWikiGraph(Integer weWikiIdx, int weWikiRevision, String weUseYn) throws Throwable;

	/**
	 * @param wikiLog
	 * @return
	 */
	public int insertWikiLog(WeWikiLog wikiLog) throws Throwable;

	/**
	 * @param temp
	 * @return
	 */
	public List<WeTemplate> getWeTemplateList(WeTemplate temp) throws Throwable;
	
	/**
	 * @param temp
	 * @return
	 */
	public List<WeTemplate> getWeTemplateIdx(WeTemplate temp) throws Throwable;

	
}

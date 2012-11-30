/**
 * @FileName  : AdminWikiService.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 11. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;


/**
 * @author yion
 *
 */
public interface AdminWikiService {

	/**
	 * @param weUserNick
	 * @param weWikiTitle
	 * @param weWikiText
	 * @param weSpaceName
	 * @return
	 */
	List<WeWiki> getWikiSearchList(String weUserNick, String weWikiTitle, String weWikiText, String weSpaceName) throws Throwable;

	/**
	 * 위키 상세 내용 조회 
	 * @param weWikiIdx
	 * @param attrId
	 * @return
	 * @throws Throwable
	 */
	Object[] getWikiDetailInfo(Integer weWikiIdx, String attrId) throws Throwable;
	
	/**
	 * 선택된 위키 삭제 업데이트 
	 * @param wikiIdx
	 * @param weUserIdx
	 * @return
	 * @throws Throwable
	 */
	int arrayDeleteWiki(String wikiIdx, Integer weUserIdx) throws Throwable;
	
	/**
	 * 단일 위키 삭제 업데이트(상세화면에서 사용) 
	 * @param wikiIdx
	 * @param weUserIdx
	 * @return
	 * @throws Throwable
	 */
	Map<String, Object> entityDeleteWiki(Integer wikiIdx, Integer wikiRevision, Integer weUserIdx) throws Throwable;
	
	/**
	 * 위키 상태 업데이트 
	 * @param wikiIdx
	 * @param weUserIdx
	 * @return
	 * @throws Throwable
	 */
	int updateWikiStatus(Integer wikiIdx, Integer weUserIdx, String status) throws Throwable;
	
	/**
	 * 최근 개설된 위키 리스트 (1달)
	 * @param space
	 * @param month
	 * @return
	 */
	List<WeWiki> getWikiListMonth(Date month) throws Throwable;
}

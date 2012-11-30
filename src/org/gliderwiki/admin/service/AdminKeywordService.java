/**
 * @FileName  : AdminKeywordService.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.service;

import java.util.List;

import org.gliderwiki.web.vo.KeywordVo;


/**
 * @author yion
 *
 */
public interface AdminKeywordService {

	/**
	 * @param keyword
	 * @return
	 */
	List<KeywordVo> getKeywordList(KeywordVo keyword) throws Throwable;
	
	List<KeywordVo> getMoreKeyword(Integer startRow, Integer endRow) throws Throwable;
	
	/**
	 * 키워드(태그) 삭제( 위키태그정보를 가지고 삭제시 )
	 * @param weWikiTagIdx
	 * @param weUserIdx
	 * @return
	 * @throws Throwable
	 */
	int deleteKeyword(Integer weWikiTagIdx, Integer weUserIdx) throws Throwable;

	/**
	 * 키워드(태그) 삭제(위키idx 와 위키리비전정보를 가지고 삭제)
	 * @param wikiIdx
	 * @param wikiRevision
	 * @param weUserIdx
	 * @return
	 * @throws Throwable
	 */
	int deleteKeywordWiki(Integer wikiIdx, Integer wikiRevision, Integer weUserIdx) throws Throwable;
}

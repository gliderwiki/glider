/**
 * @FileName  : AdminKeywordDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.List;

import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.vo.KeywordVo;


/**
 * @author yion
 *
 */
public interface AdminKeywordDao {

	/**
	 * @param keyword
	 * @return
	 */
	List<KeywordVo> getKeywordList(KeywordVo keyword) throws Throwable;
	
	/**
	 * 위키번호, 위키리비전 정보를 가지고 위키태그(키워드)삭제
	 * @param weWikiTag
	 * @return
	 * @throws Throwable
	 */
	Integer updateKeywordWiki(WeWikiTag weWikiTag) throws Throwable;
	 
}

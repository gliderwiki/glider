/**
 * @FileName  : TagDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 19.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.keyword.dao;

import java.util.Date;
import java.util.List;

import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.vo.WikiTagVo;
import org.gliderwiki.web.vo.WikiVo;


/**
 * @author yion
 *
 */
public interface TagDao {

	/**
	 * 태그 클라우드 목록을 출력한다.
	 * @return
	 * @throws Throwable
	 */
	List<WikiTagVo> getTagList(Date month, Integer maxLimit) throws Throwable;

	/**
	 * @param tagName
	 * @return
	 */
	List<Integer> getTagNameList(String tagName) throws Throwable;

	/**
	 * @param wikiListIdx
	 * @return
	 */
	List<WikiVo> getWikiListByTagIdx(List<Integer> wikiListIdx) throws Throwable;

	/**
	 * @param wikiListIdx
	 * @return
	 */
	List<WeSpace> getSpaceIdxByWikiIdx(List<Integer> wikiListIdx) throws Throwable;

	/**
	 * @param wikiListIdx
	 * @return
	 */
	List<WeSpace> getSpaceInfoBySpaceIdx(List<Integer> spaceListIdx) throws Throwable;


}

/**
 * @FileName  : TagService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 19.
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.keyword.service;

import java.util.List;

import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.vo.WikiTagVo;
import org.gliderwiki.web.vo.WikiVo;


/**
 * @author yion
 *
 */
public interface TagService {


	/**
	 * 태그 클라우드 목록을 출력한다.
	 * @return
	 * @throws Throwable
	 */
	List<WikiTagVo> getTagList() throws Throwable;

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

	/**
	 * 태그를 포함한 위키리스트 중에 중복을 제거한 리스트
	 * @param tagName
	 * @return
	 * @throws Throwable
	 */
	List<WikiVo> createWikiListWithTag(String tagName) throws Throwable;

	/**
	 * 태그를 포함한 위키가 포함된 공간 리스트
	 * @param tagName
	 * @return
	 * @throws Throwable
	 */
	List<WeSpace> createSpaceListWithTag(List<WikiVo> wikiInfoList) throws Throwable;
}

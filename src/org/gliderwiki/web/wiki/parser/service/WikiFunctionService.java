/**
 * @FileName  : WikiFunctionService.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 14.
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.service;

import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiAgree;

/**
 * @author ganji
 *
 */
public interface WikiFunctionService {

	/**
	 * 현재 위치한 위키 공감 숫자를 증가후 사용자 목록 추가한다.
	 * @param WikiId 위키 ID
	 * @return
	 * @throws Exception
	 */
	int updateAgreeUserInsert(Integer weWikiIdx, Integer weUserIdx) throws Exception;

	/**
	 * 관심인맥 추가
	 * @param Id 대상 회원 ID
	 * @return
	 * @throws Exception
	 */
	String addUserFriend(Integer loginUserIdx, Integer Id) throws Exception ;


	/**
	 * Wiki 즐겨찾기
	 * @param WikiId 위키ID
	 * @return
	 */
	String addWikiFavorite(Integer weUserIdx, Integer WikiId, Integer SpaceId) throws Exception ;

	/**
	 * 현재 위치한 위키를 신고횟수를 추가한다.
	 * @param WikiId 위키번호
	 * @return
	 * @throws Exception
	 */
	String addWikiQuota(Integer WikiId) throws Exception;
	
	/**
	 * 현재 위치한 위키를 잠금상태로 한다.
	 * @param WikiId 위키번호
	 * @return
	 * @throws Exception
	 */
	String updateWikiProdectStatus(Integer WikiId) throws Exception;


}

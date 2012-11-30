/**
 * @FileName  : UserActionService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 11. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 활동 내역 
 */
package org.gliderwiki.web.user.service;

import java.util.List;

import org.gliderwiki.web.domain.JoinStatus;
import org.gliderwiki.web.domain.WeSpaceJoin;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiLogVo;


/**
 * @author yion
 *
 */
public interface UserActionService {

	/**
	 * 내 활동 내역을 조회한다.
	 * @param memberSessionVo
	 * @return
	 * @throws Throwable
	 */
	List<WikiLogVo> getMyWikiLogAction(MemberSessionVo memberSessionVo) throws Throwable;

	/**
	 * 내 활동 내역의 공간 정보를 조회한다. 
	 * @param wikiSpaceidx
	 * @return
	 * @throws Throwable
	 */
	List<WikiLogVo> getSpaceInfoByIdx(List<Integer> wikiSpaceIdxList) throws Throwable;

	/**
	 * 내가 가입 신청한 내역, 나를 초대한 내역을 조회한다. 
	 * @param joinDomain
	 * @return
	 * @throws Throwable
	 */
	List<WeSpaceJoin> getUserSpaceJoinList(WeSpaceJoin joinDomain, int type) throws Throwable;

	/**
	 * 가입신청한 공간에 가입을 취소처리 한다. 
	 * @param we_user_idx
	 * @return
	 * @throws Throwable
	 */
	String cancelJoinToSpaceDWR(Integer we_user_idx, Integer we_space_join_idx, Integer we_space_idx, String joinStatus) throws Throwable;
	
}

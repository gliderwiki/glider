/**
 * @FileName  : UserProfileDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 5. 30. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.dao;

import java.util.List;

import org.gliderwiki.web.domain.WeSpaceJoin;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiLogVo;


/**
 * @author yion
 *
 */
public interface UserActionDao {


	/**
	 * 내 활동 내역을 조회한다.
	 * @param memberSessionVo
	 * @return
	 * @throws Throwable
	 */
	List<WikiLogVo> getMyWikiLogAction(MemberSessionVo memberSessionVo) throws Throwable;

	/**
	 * 내 활동 내역의 공간정보를 조회한다. 
	 * @param wikiSpaceidx
	 * @return
	 * @throws Throwable
	 */
	List<WikiLogVo> getSpaceInfoByIdx(List<Integer> wikiSpaceIdxList) throws Throwable;

	/**
	 * 내가 가입 신청한 내역을 조회한다. 
	 * @param joinDomain
	 * @return
	 * @throws Throwable
	 */
	List<WeSpaceJoin> getUserSpaceJoinList(WeSpaceJoin joinDomain, int type) throws Throwable;

}

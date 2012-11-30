/**
 * @FileName  : UserConnectionService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 11. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 사용자 인맥 
 */
package org.gliderwiki.web.user.service;

import java.util.List;

import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.MemberSessionVo;


/**
 * @author yion
 *
 */
public interface UserConnectionService {

	/**
	 * 내가 추가한 인맥 정보를 조회한다.
	 * @param memberSessionVo
	 * @return
	 * @throws Throwable
	 */
	List<AddFriendVo> getMyConnection(MemberSessionVo memberSessionVo) throws Throwable;
	
	/**
	 * 나를 추가한 인맥 정보를 조회한다.
	 * @param memberSessionVo
	 * @return
	 * @throws Throwable
	 */
	List<AddFriendVo> getConnectionToMe(MemberSessionVo memberSessionVo) throws Throwable;
	
	
	/**
	 * 타겟 사용자의 인맥 정보를 조회한다. 
	 * @param weUserIdx
	 * @return
	 * @throws Throwable
	 */
	List<AddFriendVo> getConnectionToMe(Integer weUserIdx) throws Throwable;

	/**
	 * 내 인맥에 사용자를 추가한다. 
	 * @param arrayCheckId
	 * @param weUserIdx
	 * @return
	 */
	int addWeFriends(String arrayCheckId, Integer weUserIdx) throws Throwable;
}

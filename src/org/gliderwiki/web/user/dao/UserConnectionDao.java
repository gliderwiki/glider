/**
 * @FileName  : UserConnectionDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.dao;

import java.util.List;

import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.MemberSessionVo;


/**
 * @author yion
 *
 */
public interface UserConnectionDao {

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
}

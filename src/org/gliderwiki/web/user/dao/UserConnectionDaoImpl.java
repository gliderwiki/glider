/**
 * @FileName  : UserConnectionDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 내가 추가한 인맥, 나를 추가한 인맥, 인맥추가, 인맥 삭제 
 */
package org.gliderwiki.web.user.dao;

import java.util.List;

import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("userConnectionDao")
public class UserConnectionDaoImpl extends SqlSessionDaoSupport implements UserConnectionDao {

	@Override
	public List<AddFriendVo> getMyConnection(MemberSessionVo memberSessionVo) throws Throwable {
		List<AddFriendVo> addFriendVoList = null;
		addFriendVoList = (List<AddFriendVo>) getSqlSession().selectList("MemberManage.getMyConnection" , memberSessionVo); 
		
		return addFriendVoList;
	}

	
	@Override
	public List<AddFriendVo> getConnectionToMe(MemberSessionVo memberSessionVo) throws Throwable {
		List<AddFriendVo> addFriendVoList = null;
		addFriendVoList = (List<AddFriendVo>) getSqlSession().selectList("MemberManage.getConnectionToMe" , memberSessionVo); 
		
		return addFriendVoList;
	}
}

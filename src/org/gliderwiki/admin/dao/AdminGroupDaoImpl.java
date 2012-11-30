/**
 * @FileName  : AdminGroupDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 22. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.List;

import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("adminGroupDao")
public class AdminGroupDaoImpl extends SqlSessionDaoSupport implements AdminGroupDao {

	
	@Override
	public List<WeGroupInfo> getGroupInfoList() throws Throwable {
		List<WeGroupInfo> groupList = null;
		groupList = (List<WeGroupInfo>) getSqlSession().selectList("AdminManage.getGroupInfoList");
		return groupList;
	}

	
	@Override
	public List<GroupUserVo> getGroupUserList(GroupUserVo groupUser) throws Throwable {
		List<GroupUserVo> userList = null;
		userList = (List<GroupUserVo>) getSqlSession().selectList("AdminManage.getGroupUserList" , groupUser);
		return userList;
	}


	@Override
	public List<WeUser> getSearchAdminList() throws Throwable {
		List<WeUser> userList = null;
		userList = (List<WeUser>) getSqlSession().selectList("AdminManage.getSearchAdminList");
		return userList;
	}

	@Override
	public int deleteGroupInfoDWR(WeGroupInfo domain) throws Throwable {
		int result = getSqlSession().update("AdminManage.deleteGroupInfoDWR", domain);
		return result;
	}


	@Override
	public int deleteUserInGroupDWR(WeGroupUser groupUser) throws Throwable {
		int result = getSqlSession().update("AdminManage.deleteUserInGroupDWR", groupUser);
		return result;
		
	}


	@Override
	public int deleteGroupUserDWR(WeGroupUser domain) throws Throwable {
		int result = getSqlSession().update("AdminManage.deleteGroupUserDWR", domain);
		return result;
		
	}


	@Override
	public List<WeGroupInfo> getUserJoinGroupList(Integer weUserIdx) throws Throwable {
		List<WeGroupInfo> groupList = getSqlSession().selectList("AdminManage.getUserJoinGroupList", weUserIdx);
		return groupList;
	}

	@Override
	public int insertGroupInfo(WeGroupInfo weGroupInfo) throws Throwable {
		return getSqlSession().update("AdminManage.insertGroupInfo", weGroupInfo);
	}
	
	@Override
	public int insertGroupUser(WeGroupUser weGroupUser) throws Throwable {
		return getSqlSession().update("AdminManage.insertGroupUser", weGroupUser);
	}
}

/**
 * @FileName  : AdminGroupDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 22. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.mockito.internal.stubbing.answers.ThrowsException;


/**
 * @author yion
 *
 */
public interface AdminGroupDao {

	List<WeGroupInfo> getGroupInfoList() throws Throwable;

	/**
	 * @param weGroupUser
	 * @return
	 */
	List<GroupUserVo> getGroupUserList(GroupUserVo groupUser) throws Throwable;

	/**
	 * @return
	 */
	List<WeUser> getSearchAdminList() throws Throwable;

	/**
	 * @param domain
	 * @return
	 */
	int deleteGroupInfoDWR(WeGroupInfo domain) throws Throwable;

	/**
	 * @param groupUser
	 */
	int deleteUserInGroupDWR(WeGroupUser groupUser) throws Throwable;

	/**
	 * @param domain
	 * @return
	 */
	int deleteGroupUserDWR(WeGroupUser domain) throws Throwable;

	/**
	 * @param weUserIdx
	 * @return
	 */
	List<WeGroupInfo> getUserJoinGroupList(Integer weUserIdx) throws Throwable;

	/**
	 * @param maps
	 * @return
	 */
	int insertGroupInfo(WeGroupInfo weGroupInfo) throws Throwable;
	
	/**
	 * @param maps
	 * @return
	 */
	int insertGroupUser(WeGroupUser weGroupUser) throws Throwable;
	
}

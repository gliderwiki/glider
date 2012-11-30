/**
 * @FileName  : AdminGroupService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 22. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.service;

import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;


/**
 * @author yion
 *
 */
public interface AdminGroupService {
	
	/**
	 * 그룹 정보를 저장한다. (어드민)
	 * @param groupName
	 * @param groupType
	 * @param groupInfo
	 * @param adminIdx
	 * @param userIdx
	 * @return
	 * @throws Throwable
	 */
	String insertGroupInfoDWR(String groupName, String groupType, String groupInfo, String adminIdx, String userIdx) throws Throwable;
	
	/**
	 * 그룹 정보를 수정한다 
	 * @param groupName
	 * @param groupType
	 * @param groupInfo
	 * @param adminIdx
	 * @param userIdx
	 * @return
	 * @throws Throwable
	 */
	String updateGroupInfoDWR(Integer groupIdx, String groupName, String groupType, String groupInfo, String adminIdx, String userIdx) throws Throwable;

	/**
	 * 그룹 목록을 조회한다. 
	 * @return
	 * @throws Throwable
	 */
	List<WeGroupInfo> getGroupInfoList() throws Throwable;
	
	
	/**
	 * 그룹에 속한 사용자를 조회한다. 
	 * @param groupIdx
	 * @return
	 * @throws Throwable
	 */
	List<GroupUserVo> getGroupUserList(Integer groupIdx) throws Throwable;
	
	/**
	 * 그룹에 사용자를 추가하기 위해서 회원 정보를 조회한다. 
	 * @param userNick
	 * @param userEmail
	 * @param userName
	 * @return
	 */
	List<WeUser> getWeUserList(Integer loginUserIdx, String userNick, String userEmail, String userName);
	
	List<WeUser> getWeAdminList();

	/**
	 * @param arrayCheckId
	 * @return
	 */
	int arrayInsertGroupUser(String arrayCheckId, String checkGroupIdx) throws Throwable;
	
	/**
	 * 그룹 정보를 조회한다. 
	 * @param weGroupIdx
	 * @return
	 * @throws Throwable
	 */
	Object[] getGroupInfo(Integer weGroupIdx) throws Throwable;
	
	/**
	 * 그룹정보 삭제 
	 * @param weGroupIdx
	 * @return
	 * @throws Throwable
	 */
	String deleteGroupInfoDWR(Integer weGroupIdx, Integer userIdx) throws Throwable;
	
	String deleteGroupInUserDWR(Integer weGroupIdx, String checkUsersId, Integer userIdx) throws Throwable;
	
	/**
	 * 사용자 전체 정보 조회 
	 * @param weUserIdx
	 * @param attrId
	 * @return
	 * @throws Throwable
	 */
	Object[] getUserProfileInfo(Integer weUserIdx, String attrId) throws Throwable;
	
	/**
	 * 회원 정보 업데이트 
	 * @param weUserIdx
	 * @param weGrade
	 * @param weTechYn
	 * @param wePoint
	 * @return
	 * @throws Throwable
	 */
	int updateUserProfileInfo(Integer weUserIdx, Integer weGrade, String weTechYn, Integer wePoint) throws Throwable;
	
	
	/**
	 * 그룹 정보 입력
	 * @param Map
	 * @return
	 * @throws Throwable
	 */	
	int insertGroupInfo(Map <Integer, Map> maps) throws Throwable;
	
	/**
	 * 그룹 회원 입력
	 * @param Map
	 * @return
	 * @throws Throwable
	 */	
	int insertGroupUser(Map <Integer, Map> maps) throws Throwable;
	
	
}

/**
 * @FileName  : AdminUserDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 26. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.vo.MailSendUserVo;


/**
 * @author yion
 *
 */
public interface AdminUserDao {

	/**
	 * @param mailUserVo
	 * @return
	 */
	List<MailSendUserVo> getUserListMailInfo(WeUser weUser) throws Throwable;
	
	/**
	 * 탈퇴 회원 보기 
	 * @param weUser
	 * @return
	 * @throws Throwable
	 */
	List<MailSendUserVo> getUserAwayList(WeUser weUser) throws Throwable;

	/**
	 * @param domain
	 * @return
	 */
	List<MailSendUserVo> getUserListByGrade(WeProfile domain) throws Throwable;

	/**
	 * @param groupUser
	 * @return
	 */
	List<MailSendUserVo> getUserListByGroup(GroupUserVo groupUser) throws Throwable;

	/**
	 * @param 
	 * @return
	 */	
	
	int insertUser(WeUser weUser) throws Throwable;

	
	/**
	 * @param 
	 * @return
	 */	
	int getUserMaxIdx()throws Throwable;
	
	/**
	 * @param 
	 * @return
	 */	
	int insertProfile(Integer weUserIdx) throws Throwable;
	
}

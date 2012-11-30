/**
 * @FileName  : AdminUserService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 26. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.service;

import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.MailSendUserVo;


/**
 * @author yion
 *
 */
public interface AdminUserService {

	/**
	 * @param weUser
	 * @param weGroupIdx
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
	 * 등급별 사용자 리스트 조회 
	 * @param weGrade
	 * @return
	 * @throws Throwable
	 */
	List<MailSendUserVo> getUserListByGrade(Integer weGrade) throws Throwable;
	
	
	/**
	 * 그룹별 사용자 리스트 조회 
	 * @param weGroupIdx
	 * @return
	 * @throws Throwable
	 */
	List<MailSendUserVo> getUserListByGroup(Integer weGroupIdx) throws Throwable;
	
	
	@SuppressWarnings("rawtypes")
	int insertUser( Map <Integer, Map> map) throws Throwable;
	
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

/**
 * @FileName  : UserAlarmService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 11. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 사용자 알람관리
 */
package org.gliderwiki.web.user.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.web.vo.AlarmInfoVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.MetaInfoVo;


/**
 * @author yion
 *
 */

public interface UserAlarmService {

	/**
	 * 메타 알람 정보 조회 (사용자가 선택한 내역까지 조회한다)
	 * @param memberSessionVo
	 * @return
	 * @throws Throwable
	 */
	List<MetaInfoVo> getUserMetaInfoList(MemberSessionVo memberSessionVo) throws Throwable;

	/**
	 * 사용자에게 전송된 알람 내역을 조회한다.
	 * @param memberSessionVo
	 * @return
	 * @throws Throwable
	 */
	List<AlarmInfoVo> getUserAlarmList(MemberSessionVo memberSessionVo) throws Throwable;
	
	/**
	 * 내가 선택한 알람을 저장한다. from UserAlarm.jsp
	 * @param arrValue
	 * @return
	 * @throws Throwable
	 */
	String addCheckUserAlarmDWR(String arrValue, int userIdx) throws Throwable;
	
	
}

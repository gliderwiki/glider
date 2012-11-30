/**
 * @FileName  : UserAlarmDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 13. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.dao;

import java.util.List;

import org.gliderwiki.web.vo.AlarmInfoVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.MetaInfoVo;


/**
 * @author yion
 *
 */
public interface UserAlarmDao {

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

}

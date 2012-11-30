/**
 * @FileName  : NotifiationService.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 23.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.notification.service;

import javax.annotation.Resource;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.web.domain.WeAlarmInfo;
import org.springframework.stereotype.Service;


/**
 * @author bluepoet
 *
 */
@Service("notifiationService")
public class NotifiationService {

	@Resource(name = "entityService")
	EntityService entityService;

	/**
	 * 아직 읽지 않은 알림갯수를 가져온다
	 * @param userIdx
	 * @return
	 * @throws Throwable
	 */
	public int getNewNotiCount(int userIdx) throws Throwable {
		WeAlarmInfo weAlarmInfo = new WeAlarmInfo();
		weAlarmInfo.setWe_target_user_idx(userIdx);
		weAlarmInfo.setWe_read_yn(false);
		weAlarmInfo.setWe_use_yn("Y");

		return entityService.getCountEntity(weAlarmInfo);
	}
}

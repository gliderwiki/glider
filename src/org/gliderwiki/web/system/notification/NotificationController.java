/**
 * @FileName  : NotificationController.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 23.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.notification;

import javax.annotation.Resource;

import org.gliderwiki.web.space.controller.SpaceController;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.system.notification.service.NotifiationService;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author bluepoet
 *
 */
@Controller
@RequestMapping("/noti")
public class NotificationController {
	Logger logger = LoggerFactory.getLogger(NotificationController.class);

	@Resource(name = "notifiationService")
	private NotifiationService notifiationService;

	@Resource(name = "commonService")
	private CommonService commonService;

	@RequestMapping(value = "/newNotiCount", method = RequestMethod.GET)
	@ResponseBody
	public int getNewNotiCount(@LoginUser MemberSessionVo loginUser) throws Throwable {
		return notifiationService.getNewNotiCount(loginUser.getWeUserIdx());
	}

	@RequestMapping(value = "/view/update", method = RequestMethod.POST)
	@ResponseBody
	public void realTimeViewUpadte(@LoginUser MemberSessionVo loginUser, String isChecked) throws Throwable {
		logger.debug("isChecked : {}" + isChecked);

		commonService.changeRealTimeView(loginUser.getWeUserIdx(), isChecked);
	}

	@RequestMapping(value = "/view/check", method = RequestMethod.GET)
	@ResponseBody
	public String realTimeViewCheck(@LoginUser MemberSessionVo loginUser) {
		logger.debug("realTimeViewCheck : {}", commonService.realNotiView(loginUser.getWeUserIdx()));
		return commonService.realNotiView(loginUser.getWeUserIdx());
	}
}

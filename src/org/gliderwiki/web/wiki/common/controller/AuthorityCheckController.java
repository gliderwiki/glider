/**
 * @FileName  : AuthorityCheckController.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.common.controller;

import javax.annotation.Resource;

import org.gliderwiki.web.domain.WebConstant;
import org.gliderwiki.web.system.argumentresolver.LoginUser;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.AuthorityCheckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author bluepoet
 *
 */
@Controller
public class AuthorityCheckController {

	@Resource(name="authorityCheckService")
	private AuthorityCheckService authorityCheckService;

	@RequestMapping(value = "/api/authority/wiki", method = RequestMethod.GET)
	@ResponseBody
	public WebConstant accessCheckAuthorityWikiPage(@LoginUser MemberSessionVo loginUser, @RequestParam(value = "spaceIdx") int spaceIdx) {
		return authorityCheckService.accessCheckAuthorityWikiPage(loginUser.getWeUserIdx(), spaceIdx);
	}
}

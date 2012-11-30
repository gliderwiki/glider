/**
 * @FileName  : AuthorityCheckService.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.common.service;

import javax.annotation.Resource;

import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WebConstant;
import org.gliderwiki.web.space.service.SpaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bluepoet
 *
 */
@Service("authorityCheckService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class AuthorityCheckService {

	@Resource(name = "spaceService")
	private SpaceService spaceService;

	public WebConstant accessCheckAuthorityWikiPage(int loginIdx, int spaceIdx) {

		try {
			WeSpace weSpace = spaceService.getWeSpaceInfo(spaceIdx);

			if (!weSpace.isAllGroupEditPrivacy()) {
				return WebConstant.SUCCESS;
			} else {
				return spaceService.checkSearchSpaceInfo(weSpace.getWe_edit_privacy(), spaceIdx, loginIdx, "edit");
			}
		} catch (Throwable e) {
			throw new GliderwikiException(e);
		}
	}
} 
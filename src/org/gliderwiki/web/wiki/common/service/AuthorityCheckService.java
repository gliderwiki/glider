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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "spaceService")
	private SpaceService spaceService;

	public WebConstant accessCheckAuthorityWikiPage(int loginIdx, int spaceIdx) {
		logger.debug("##loginIdx : " + loginIdx);
		logger.debug("##spaceIdx : " + spaceIdx);
		try {
			WeSpace weSpace = spaceService.getWeSpaceInfo(spaceIdx);

			if (!weSpace.isAllGroupEditPrivacy()) {
				return WebConstant.SUCCESS;
			} else {
				// 개설자, 어드민일 경우 패스 
				if(weSpace.getWe_admin_idx() == loginIdx || loginIdx == 1) { 
					return WebConstant.SUCCESS;
				} else {
					return spaceService.checkSearchSpaceInfo(weSpace.getWe_edit_privacy(), spaceIdx, loginIdx, "edit");
				}
			}
		} catch (Throwable e) {
			throw new GliderwikiException(e);
		}
	}
} 
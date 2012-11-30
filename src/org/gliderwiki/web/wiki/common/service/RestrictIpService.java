/**
 * @FileName  : RestrictIpService.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 12.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.web.domain.WeAccess;

import com.google.common.collect.Lists;


/**
 * 차단할 ip목록을 가져오는 빈, 시큐리티에서 사용함
 * @author bluepoet
 *
 */
public class RestrictIpService {

	@Resource(name = "entityService")
	EntityService entityService;

	public List<WeAccess> getRestrictIpList() {
		List<WeAccess> restrictIpList = Lists.newArrayList();

		try {
			restrictIpList = entityService.getListEntity(new WeAccess());
		} catch (Throwable e) {
			throw new GliderwikiException("차단IP목록 가져오는 도중 에러 발생함!", e);
		}

		return restrictIpList;
	}
}

/**
 * @FileName  : PatchServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 19. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.rest.service;

import java.util.List;

import org.gliderwiki.rest.dao.PatchServiceDao;
import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WePatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yion
 *
 */
@Service("patchService")
public class PatchServiceImpl implements PatchService {

	@Autowired
	private PatchServiceDao patchServiceDao;
	
	@Override
	public WeFunction[] getCurrentFunctionList(String version, String extendYn) throws Throwable {

		return patchServiceDao.getCurrentFunctionList(version, extendYn);
	}

	@Override
	public WePatch getWePatchInfo(WePatch wePatchModel) throws Throwable {
		return patchServiceDao.getWePatchInfo(wePatchModel);
	}

}

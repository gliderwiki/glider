/**
 * @FileName  : PatchService.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 19. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.rest.service;

import java.util.List;

import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WePatch;


/**
 * @author yion
 *
 */
public interface PatchService {

	
	WeFunction[] getCurrentFunctionList(String version, String extendYn) throws Throwable;

	/**
	 * @param wePatchModel
	 * @return
	 */
	WePatch getWePatchInfo(WePatch wePatchModel) throws Throwable;
}

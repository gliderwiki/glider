/**
 * @FileName  : CommonInfoService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 7. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.common.service;

import java.util.List;

import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeUser;


/**
 * @author yion
 *
 */
public interface CommonInfoService {
	
	
	/**
	 * 공통 메뉴에서 서브메뉴 리스트를 조회한다. 
	 * @param weMenuIdx
	 * @param subMenuId
	 * @return
	 * @throws Throwable
	 */
	public List<WeMenu> getHeaderMenuListAjax(Integer weMenuIdx, Integer weUserIdx) throws Throwable;

	/**
	 * 하위 서브 메뉴 리스트를 조회한다. 
	 * @param menuEntity
	 * @param weUserIdx
	 * @return
	 */
	public List<WeMenu> getTitleMenuByAuth(WeMenu menuEntity, Integer weUserIdx) throws Throwable;


}

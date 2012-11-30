/**
 * @FileName  : CommonInfoDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.common.dao;

import java.util.List;

import org.gliderwiki.web.domain.WeMenu;


/**
 * @author yion
 *
 */
public interface CommonInfoDao {

	/**
	 * @param weMenuIdx
	 * @param weUserIdx
	 * @return
	 */
	List<WeMenu> getSubMenuByAuth(Integer weMenuIdx, Integer weUserIdx, Integer weGrade) throws Throwable;

	/**
	 * @param menuEntity
	 * @param weUserIdx
	 * @param we_grade
	 * @return
	 */
	List<WeMenu> getTitleMenuByAuth(WeMenu menuEntity, Integer weUserIdx, Integer we_grade) throws Throwable;

}

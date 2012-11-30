/**
 * @FileName  : UserProfileDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 17. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.dao;

import java.util.Map;

/**
 * @author yion
 *
 */
public interface UserProfileDao {

	
	
	/**
	 * WE_USER 리셋 
	 * @param weUserIdx
	 * @return
	 */
	int updateAwayUser(Integer weUserIdx) throws Throwable;
	
	
	

	/**
	 * WE_PROFILE 리셋 
	 * @param weUserIdx
	 * @return
	 * @throws Throwable
	 */
	int updateAwayProfile(Integer weUserIdx)  throws Throwable;
	
}

/**
 * @FileName  : UserProfileDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 17. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.dao;

import java.util.Map;

import org.gliderwiki.web.domain.WeUser;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("userProfileDao")
public class UserProfileDaoImpl extends SqlSessionDaoSupport implements UserProfileDao {

	
	@Override
	public int updateAwayUser(Integer weUserIdx) throws Throwable {
		return getSqlSession().update("MemberManage.updateAwayUser", weUserIdx);
	}
	
	@Override
	public int updateAwayProfile(Integer weUserIdx) throws Throwable {
		return getSqlSession().update("MemberManage.updateAwayProfile", weUserIdx);
	}

}

/**
 * @FileName  : LoginDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 12. 
 * @작성자      : @author inamhui

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.login.dao;

import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


/**
 * @author inamhui
 *
 */
@Repository("loginDao")
public class LoginDaoImpl extends SqlSessionDaoSupport implements LoginDao {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public MemberSessionVo getRowWeUserById(WeUser weUser) throws Throwable {
		MemberSessionVo sessionVo = null;
		sessionVo = (MemberSessionVo) getSqlSession().selectOne("MemberManage.getRowWeUserById" , weUser); 
		return sessionVo;
	}


	@Override
	public int updateUserAuth(WeUser weUser) throws Throwable {
		return getSqlSession().update("MemberManage.updateUserAuth", weUser);
	}


	@Override
	public int getCurrentMailIdx() throws Throwable {
		return (Integer) getSqlSession().selectOne("MemberManage.getCurrentMailIdx");
	}

	
}

/**
 * @FileName  : AdminUserDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 26. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.vo.MailSendUserVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

/**
 * @author yion
 *
 */
@Repository("adminUserDao")
public class AdminUserDaoImpl extends SqlSessionDaoSupport implements AdminUserDao {

	
	@Override
	public List<MailSendUserVo> getUserListMailInfo(WeUser weUser) throws Throwable {
		
		List<MailSendUserVo> mailSendUserVo = null;
		mailSendUserVo = (List<MailSendUserVo>) getSqlSession().selectList("AdminManage.getUserListMailInfo", weUser);
		return mailSendUserVo;
	}
	
	@Override
	public List<MailSendUserVo> getUserAwayList(WeUser weUser) throws Throwable {
		
		List<MailSendUserVo> mailSendUserVo = null;
		mailSendUserVo = (List<MailSendUserVo>) getSqlSession().selectList("AdminManage.getUserListMailInfo", weUser);
		return mailSendUserVo;
	}


	@Override
	public List<MailSendUserVo> getUserListByGrade(WeProfile domain) throws Throwable {
		List<MailSendUserVo> mailSendUserVo = null;
		mailSendUserVo = (List<MailSendUserVo>) getSqlSession().selectList("AdminManage.getUserAwayList", domain);
		return mailSendUserVo;
	}


	@Override
	public List<MailSendUserVo> getUserListByGroup(GroupUserVo groupUser) throws Throwable {
		List<MailSendUserVo> mailSendUserVo = null;
		mailSendUserVo = (List<MailSendUserVo>) getSqlSession().selectList("AdminManage.getUserListByGroup", groupUser);
		return mailSendUserVo;
	}

	@Override
	public int insertUser(WeUser weUser) throws Throwable {
		return  getSqlSession().update("MemberManage.insertUser", weUser);
	}
	@Override
	public int getUserMaxIdx() throws Throwable {
		return (Integer) getSqlSession().selectOne("MemberManage.getUserMaxIdx");
	}
	
	@Override
	public int insertProfile(Integer weUserIdx) throws Throwable {
		return getSqlSession().update("MemberManage.insertProfile", weUserIdx);
	}
	
	
}

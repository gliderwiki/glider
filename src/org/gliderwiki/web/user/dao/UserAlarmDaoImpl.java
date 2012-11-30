/**
 * @FileName  : UserAlarmDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 13. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.dao;

import java.util.List;

import org.gliderwiki.web.vo.AlarmInfoVo;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.MetaInfoVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("userAlarmDao")
public class UserAlarmDaoImpl extends SqlSessionDaoSupport implements UserAlarmDao {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<MetaInfoVo> getUserMetaInfoList(MemberSessionVo memberSessionVo) throws Throwable {
		List<MetaInfoVo> metaList = (List<MetaInfoVo>) getSqlSession().selectList("MemberManage.getUserMetaInfoList" , memberSessionVo); 
		
		return metaList;	
	}

	
	@Override
	public List<AlarmInfoVo> getUserAlarmList(MemberSessionVo memberSessionVo) throws Throwable {
		List<AlarmInfoVo> metaList = (List<AlarmInfoVo>) getSqlSession().selectList("MemberManage.getUserAlarmList" , memberSessionVo); 
		
		return metaList;	
	}
	
	
}

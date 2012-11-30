/**
 * @FileName  : UserProfileDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 5. 30. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.web.domain.WeSpaceJoin;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("userActionDao")
public class UserActionDaoImpl extends SqlSessionDaoSupport implements UserActionDao {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Override
	public List<WikiLogVo> getMyWikiLogAction(MemberSessionVo memberSessionVo) throws Throwable {
		List<WikiLogVo> wikiLogList = null;
		wikiLogList = (List<WikiLogVo>) getSqlSession().selectList("MemberManage.getMyWikiLogAction" , memberSessionVo); 
		
		return wikiLogList;
	}


	@Override
	public List<WikiLogVo> getSpaceInfoByIdx(List<Integer> wikiSpaceIdxList) throws Throwable {
		List<WikiLogVo> wikiLogList = null;
				
		wikiLogList = (List<WikiLogVo>) getSqlSession().selectList("MemberManage.getSpaceInfoByIdx" , wikiSpaceIdxList); 
		
		return wikiLogList;
	}


	
	@Override
	public List<WeSpaceJoin> getUserSpaceJoinList(WeSpaceJoin joinDomain, int type) throws Throwable {
		List<WeSpaceJoin> joinList = null;
		
		// 조회 기간은 1달로 제한 
		if(type == 1) {
			String month = DateUtil.getOffsetDate(SystemConst.SEACH_MONTH);
			joinDomain.setWe_ins_date(month);
		} else {
			joinDomain.setWe_ins_date(null);
		}
		
		
		joinList = (List<WeSpaceJoin>) getSqlSession().selectList("MemberManage.getUserSpaceJoinList" , joinDomain); 
		
		return joinList;
	}
}

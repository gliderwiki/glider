/**
 * @FileName  : AdminSpaceDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 9. 10. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeBbs;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.vo.WikiTagVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("adminSpaceDao")
public class AdminSpaceDaoImpl extends SqlSessionDaoSupport implements AdminSpaceDao {
	
	
	@Override
	public List<WeSpace> getSpaceListMonth(WeSpace space, Date month) throws Throwable {
		Map<String, Object> mapper = new HashMap<String, Object>();
		mapper.put("month", month);
		mapper.put("space", space);
		
		List<WeSpace> list = null;
		
		list = (List<WeSpace>) getSqlSession().selectList("AdminManage.getSpaceListMonth", mapper);
		return list;
	}

	@Override
	public List<WeSpace> getSpaceSearchList(WeSpace weSpace) throws Throwable {
		List<WeSpace> list = null;
		
		list = (List<WeSpace>) getSqlSession().selectList("AdminManage.getSpaceSearchList", weSpace);
		return list;
	}

	
	@Override
	public WeSpace getSpaceDetailInfo(Integer weSpaceIdx) throws Throwable {
		return (WeSpace) getSqlSession().selectOne("AdminManage.getSpaceDetailInfo" , weSpaceIdx);
	}

	@Override
	public List<WeWiki> getWikiListInSpace(Integer weSpaceIdx) throws Throwable {
		List<WeWiki> list = null;
		
		list = (List<WeWiki>) getSqlSession().selectList("AdminManage.getWikiListInSpace", weSpaceIdx);
		return list;
	}

	@Override
	public int updateSpaceByAdmin(WeSpace space) throws Throwable {
		
		return getSqlSession().update("AdminManage.updateSpaceByAdmin", space);
	}

	@Override
	public List<WeBbs> getBbsSearchList(WeBbs weBbs) throws Throwable {
		List<WeBbs> list = null;
		
		list = (List<WeBbs>) getSqlSession().selectList("AdminManage.getBbsSearchList", weBbs);
		return list;
	}

}

/**
 * @FileName  : SpaceDao.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 10.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.space.dao;

import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeBbs;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

/**
 * @author bluepoet
 *
 */
@Repository("boardDao")
public class BoardDao extends SqlSessionDaoSupport {

	@SuppressWarnings("unchecked")
	public List<WeBbs> getList(int spaceIdx, int startRow, int blockList) {
		List<WeBbs> list = null;
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("listType", "all");
		mapper.put("spaceIdx", spaceIdx);
		mapper.put("startRow", startRow);
		mapper.put("blockList", blockList);
		list = (List<WeBbs>) getSqlSession().selectList("BoardManage.getList", mapper);

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<WeBbs> getRecentList(int spaceIdx) {
		List<WeBbs> list = null;
		Map<String, Object> mapper = Maps.newHashMap();
		mapper.put("listType", "recent");
		mapper.put("spaceIdx", spaceIdx);
		list = (List<WeBbs>) getSqlSession().selectList("BoardManage.getList", mapper);

		return list;
	}


}
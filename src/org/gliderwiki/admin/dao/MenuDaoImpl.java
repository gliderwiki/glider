/**
 * @FileName  : MenuDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 24. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeMenu;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("menuDao")
public class MenuDaoImpl extends SqlSessionDaoSupport implements MenuDao {

	/*
	@SuppressWarnings("unchecked")
	@Override
	public List<WeMenu> getSubMenuByAuth(Integer weMenuIdx, Integer weUserIdx, Integer weGrade) throws Throwable {
		Map<String, Integer> mapper = new HashMap<String, Integer>();
		mapper.put("weMenuIdx", weMenuIdx);
		mapper.put("weUserIdx", weUserIdx);
		mapper.put("weGrade", weGrade);
		
		return (List<WeMenu>) getSqlSession().selectList("CommonManage.getSubMenuByAuth", mapper);
	}
	*/
	
}

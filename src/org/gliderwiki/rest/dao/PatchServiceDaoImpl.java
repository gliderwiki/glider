/**
 * @FileName  : PatchServiceDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 19. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.rest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gliderwiki.web.domain.WeFunction;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WePatch;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("patchServiceDao")
public class PatchServiceDaoImpl extends SqlSessionDaoSupport implements PatchServiceDao {

	@Override
	public WeFunction[] getCurrentFunctionList(String version, String extendYn) throws Throwable {
		Map<String, String> mapper = new HashMap<String, String>();
		mapper.put("version", version);
		mapper.put("we_extend_yn", extendYn);
		
		List<WeFunction> selectList = getSqlSession().selectList("AdminManage.getCurrentFunctionList", mapper);
		WeFunction[] list = selectList.toArray(new WeFunction[selectList.size()] );
		return list;
	}

	@Override
	public WePatch getWePatchInfo(WePatch wePatchModel) throws Throwable {
		
		return (WePatch) getSqlSession().selectOne("AdminManage.getWePatchInfo", wePatchModel);
	}

}

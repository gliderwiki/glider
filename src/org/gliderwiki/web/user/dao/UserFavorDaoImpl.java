/**
 * @FileName  : UserFavorDaoImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 12. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.dao;

import java.util.List;

import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiFavoriteVo;
import org.gliderwiki.web.vo.WikiLogVo;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


/**
 * @author yion
 *
 */
@Repository("userFavorDao")
public class UserFavorDaoImpl extends SqlSessionDaoSupport implements UserFavorDao {

	@Override
	public List<WikiFavoriteVo> getMyFavoriteSpaceList(Integer weUserIdx)  throws Throwable {
		List<WikiFavoriteVo> wikiFavoriteVo = null;
		wikiFavoriteVo = (List<WikiFavoriteVo>) getSqlSession().selectList("MemberManage.getMyFavoriteSpaceList" , weUserIdx); 
		
		return wikiFavoriteVo;
	}
	
	
	@Override
	public List<WikiFavoriteVo> getMyFavoriteWikiList(Integer weUserIdx)  throws Throwable {
		List<WikiFavoriteVo> wikiFavoriteVo = null;
		wikiFavoriteVo = (List<WikiFavoriteVo>) getSqlSession().selectList("MemberManage.getMyFavoriteWikiList" , weUserIdx); 
		
		return wikiFavoriteVo;
	}
	
	
}

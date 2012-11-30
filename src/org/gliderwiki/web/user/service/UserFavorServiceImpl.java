/**
 * @FileName  : UserFavorService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 11. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 사용자 즐겨찾기 
 */
package org.gliderwiki.web.user.service;

import java.util.List;

import org.gliderwiki.web.user.dao.UserFavorDao;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.WikiFavoriteVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yion
 *
 */
@Service("userFavorService")
public class UserFavorServiceImpl implements UserFavorService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserFavorDao userFavorDao;
	
	@Override
	public List<WikiFavoriteVo> getMyFavoriteSpaceList(Integer weUserIdx) throws Throwable {
		
		return userFavorDao.getMyFavoriteSpaceList(weUserIdx);
	}
	
	
	@Override
	public List<WikiFavoriteVo> getMyFavoriteWikiList(Integer weUserIdx) throws Throwable {
		
		return userFavorDao.getMyFavoriteWikiList(weUserIdx);
	}
}

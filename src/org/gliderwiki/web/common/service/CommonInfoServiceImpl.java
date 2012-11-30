/**
 * @FileName  : CommonInfoServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 7. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 각 클래스에서 조회용 공통 모듈을 서비스한다. 
 * Admin 영역과 Front 영역에서 쓰이는 모든 키 기반 단일 테이블 조회를 중복 없이 재사용하도록 모아서 서비스한다. 
 * DAO가 없으며, 모든 DB Access는 EntityService를 통해 제공한다. 
 */
package org.gliderwiki.web.common.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.web.common.dao.CommonInfoDao;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("commonInfoService")
@RemoteProxy(name="CommonInfoService")
public class CommonInfoServiceImpl implements CommonInfoService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private CommonInfoDao commonInfoDao;
	
	
	@Override
	public List<WeMenu> getHeaderMenuListAjax(Integer weMenuIdx, Integer weUserIdx) throws Throwable {	
		logger.debug("###getHeaderMenuListDWR-weUserIdx : "  + weUserIdx);
		WeProfile domain = new WeProfile();
		WeProfile rtnObj = null;
		domain.setWe_user_idx(weUserIdx);
		domain.setWe_tech_yn(null);
		domain.setWe_point(null);
		List<WeMenu> menuList = null;
		
		if(weUserIdx != null && weUserIdx != 0) {
			// 현재 사용자의 등급을 구해온다. 
			rtnObj =(WeProfile) entityService.getRowEntity(domain);
			
			menuList = commonInfoDao.getSubMenuByAuth(weMenuIdx, weUserIdx, rtnObj.getWe_grade());
		} else {
			menuList = commonInfoDao.getSubMenuByAuth(weMenuIdx, weUserIdx, 0);
		}
				
		return menuList;

	}


	@Override
	public List<WeMenu> getTitleMenuByAuth(WeMenu menuEntity, Integer weUserIdx) throws Throwable {
		logger.debug("###getHeaderMenuListDWR-weUserIdx : "  + weUserIdx);
		WeProfile domain = new WeProfile(null, null);
		WeProfile rtnObj = null;
		domain.setWe_user_idx(weUserIdx);
		
		List<WeMenu> menuList = null;
		
		if(weUserIdx != null && weUserIdx != 0) {
			// 현재 사용자의 등급을 구해온다. 
			rtnObj =(WeProfile) entityService.getRowEntity(domain);
			
			menuList = commonInfoDao.getTitleMenuByAuth(menuEntity, weUserIdx, rtnObj.getWe_grade());
		} else {
			menuList = commonInfoDao.getTitleMenuByAuth(menuEntity, weUserIdx, 0);
		}
				
		return menuList;
	}
	

}

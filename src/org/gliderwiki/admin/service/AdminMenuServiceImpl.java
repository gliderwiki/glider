/**
 * @FileName  : MenuServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.admin.dao.MenuDao;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeMenu;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yion
 *
 */
@Service("adminMenuService")
@RemoteProxy(name="AdminMenuService")
public class AdminMenuServiceImpl implements AdminMenuService{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private CommonService commonService;
	
	
	/**
	 * 메뉴의 타입에 따른 메뉴 목록을 조회한다. 
	 */
	@Override
	@RemoteMethod
	public List<WeMenu> getWeMenuTypeDWR(String menuType) throws Throwable {
		WeMenu menuEntity = new WeMenu();
		menuEntity.setWe_use_yn("Y");
		menuEntity.setWe_menu_type(menuType);			// 시스템 메뉴만 조회
		
		List<WeMenu> menuList = entityService.getListEntityOrdered(menuEntity, "we_menu_order_idx");
		
		return menuList;
	}

	/**
	 * 서브 메뉴 조회 
	 */
	@Override
	@RemoteMethod
	public List<WeMenu> getSubMenuListDWR(Integer weMenuIdx) throws Throwable {
		WeMenu menuEntity = new WeMenu();
		menuEntity.setWe_use_yn("Y");
		menuEntity.setWe_menu_parent_idx(weMenuIdx);
		
		List<WeMenu> menuList = entityService.getListEntityOrdered(menuEntity, "we_menu_order_idx");
		
		return menuList;
	}


	@Override
	@RemoteMethod
	public String insertMenuDWR(String weMenuName, String weMenuType,
			String weMenuUrl, String weMenuOrderIdx, String weAccessLevel) throws Throwable {
		
		WeMenu weMenu = new WeMenu();
		weMenu.setWe_menu_name(weMenuName);
		weMenu.setWe_menu_type(weMenuType);
		weMenu.setWe_menu_url(weMenuUrl);
		weMenu.setWe_menu_order_idx(Integer.parseInt(weMenuOrderIdx));
		weMenu.setWe_access_level(weAccessLevel);
		weMenu.setWe_use_yn("Y");
		weMenu.setWe_menu_parent_idx(0);
		weMenu.setWe_menu_group(null);
		weMenu.setWe_menu_depth(1);
		
		int result = 0;
		
		JSONObject jsonObj  = null;
		BaseObjectBean resultBean = new BaseObjectBean();
		
		try { 
			result =  entityService.insertEntity(weMenu);
			
			resultBean.setRtnMsg("처리 되었습니다.");
			resultBean.setRtnResult(1);							
			resultBean.setRtnStatus(SystemConst.CALL_SUCCESS);	
		} catch (Throwable e) {
			resultBean.setRtnMsg(e.getCause().toString());
			resultBean.setRtnResult(0);
			resultBean.setRtnStatus(SystemConst.CALL_FAIL);	 
		}
		
		jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultBean));
		
		return jsonObj.toString();
	}

	@Override
	@RemoteMethod
	public WeMenu getMenuInfo(Integer weMenuIdx) throws Throwable {
		WeMenu domain = new WeMenu();
		domain.setWe_menu_idx(weMenuIdx);
		domain.setWe_use_yn("Y");
			
		WeMenu resultInfo = new WeMenu();
		try { 
			resultInfo = (WeMenu) entityService.getRowEntity(domain);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return resultInfo;
	}

	@Override
	@RemoteMethod
	public String deleteMenuInfoDWR(Integer weMenuIdx) throws Throwable {
		WeMenu domain = new WeMenu();
		
		domain = commonService.getMenuInfo(weMenuIdx);
		domain.setWe_use_yn("N");
		
		
		int result = 0;
		
		JSONObject jsonObj  = null;
		BaseObjectBean resultBean = new BaseObjectBean();
		
		try { 
			
			result =  entityService.updateEntity(domain);
			
			if(result == 1) {
				resultBean.setRtnMsg("처리 되었습니다.");
				resultBean.setRtnResult(1);		// 생성된 그룹 인덱스를 넘겨준다.  
				resultBean.setRtnStatus(SystemConst.CALL_SUCCESS);	// 서버 에러
			} else {
				resultBean.setRtnMsg("수정이 되지 않았습니다");
				resultBean.setRtnResult(0);
				resultBean.setRtnStatus(SystemConst.CALL_FAIL);	// 서버 에러 
			}
			
		} catch (Throwable e) {
			resultBean.setRtnMsg(e.getCause().toString());
			resultBean.setRtnResult(-1);
			resultBean.setRtnStatus(SystemConst.CALL_FAIL);	// 서버 에러 
		}
		
		jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultBean));
		
		return jsonObj.toString();
	}


	@Override
	@RemoteMethod
	public String updateMenuDWR(Integer weMenuIdx, String weMenuName, String weMenuType, String weMenuUrl, String weMenuOrderIdx, String weAccessLevel)
			throws Throwable {
		
		WeMenu weMenu = new WeMenu();
		weMenu.setWe_menu_idx(weMenuIdx);
		weMenu.setWe_menu_name(weMenuName);
		weMenu.setWe_menu_type(weMenuType);
		weMenu.setWe_menu_url(weMenuUrl);
		weMenu.setWe_menu_order_idx(Integer.parseInt(weMenuOrderIdx));
		weMenu.setWe_access_level(weAccessLevel);
		weMenu.setWe_use_yn("Y");
		weMenu.setWe_menu_parent_idx(0);
		weMenu.setWe_menu_group(null);
		weMenu.setWe_menu_depth(1);
		
		int result = 0;
		
		JSONObject jsonObj  = null;
		BaseObjectBean resultBean = new BaseObjectBean();
		
		try { 
			result =  entityService.updateEntity(weMenu);
			
			resultBean.setRtnMsg("처리 되었습니다.");
			resultBean.setRtnResult(1);							
			resultBean.setRtnStatus(SystemConst.CALL_SUCCESS);	
		} catch (Throwable e) {
			resultBean.setRtnMsg(e.getCause().toString());
			resultBean.setRtnResult(0);
			resultBean.setRtnStatus(SystemConst.CALL_FAIL);	 
		}
		
		jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultBean));
		
		return jsonObj.toString();
	}
}

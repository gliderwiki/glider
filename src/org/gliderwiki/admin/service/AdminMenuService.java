/**
 * @FileName  : MenuService.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 25. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.service;

import java.util.List;

import org.gliderwiki.web.domain.WeMenu;


/**
 * @author yion
 *
 */
public interface AdminMenuService {
	
	/**
	 * 메뉴 타입에 따른 목록을 조회한다. S:시스템, M:메인메뉴, T:타이틀메뉴, C:서브메뉴
	 * @param menuType
	 * @return
	 * @throws Throwable
	 */
	public List<WeMenu> getWeMenuTypeDWR(String menuType) throws Throwable;
	
	/**
	 * 서브 메뉴 리스트를 조회한다. 
	 * @param weMenuIdx
	 * @return
	 * @throws Throwable
	 */
	public List<WeMenu> getSubMenuListDWR(Integer weMenuIdx) throws Throwable;
	
	
	/**
	 * 메인 메뉴를 저장한다. 
	 * @param weMenuName
	 * @param weMenuType
	 * @param weMenuUrl
	 * @param weMenuOrderIdx
	 * @param weAccessLevel
	 * @return
	 * @throws Throwable
	 */
	public String insertMenuDWR(String weMenuName, String weMenuType, String weMenuUrl, String weMenuOrderIdx, String weAccessLevel) throws Throwable;
	
	

	/**
	 * 선택한 메뉴 정보를 조회한다. 
	 * @param weMenuIdx
	 * @return
	 * @throws Throwable
	 */
	public WeMenu getMenuInfo(Integer weMenuIdx) throws Throwable;
	
	
	/**
	 * 선택한 메뉴 정보를 삭제 한다. 
	 * @param weMenuIdx
	 * @return
	 * @throws Throwable
	 */
	public String deleteMenuInfoDWR(Integer weMenuIdx) throws Throwable;

	/**
	 * 메뉴 정보 수정 
	 * @param weMenuName
	 * @param weMenuType
	 * @param weMenuUrl
	 * @param weMenuOrderIdx
	 * @param weAccessLevel
	 * @return
	 * @throws Throwable
	 */
	public String updateMenuDWR(Integer weMenuIdx, String weMenuName, String weMenuType, String weMenuUrl, String weMenuOrderIdx, String weAccessLevel) throws Throwable;
}

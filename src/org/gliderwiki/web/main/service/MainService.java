/**
 * @FileName  : MainService.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 11.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.main.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.util.RequestManager;
import org.gliderwiki.web.domain.VisitCounter;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiTag;
import org.gliderwiki.web.main.dao.MainDao;
import org.gliderwiki.web.system.SystemConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author bluepoet
 *
 */
@Service("mainService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class MainService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "entityService")
	private EntityService entityService;

	@Resource(name = "mainDao")
	private MainDao mainDao;

	@Resource(name = "requestManager")
	private RequestManager requestManager;

	public List getAllTagList() throws Throwable {
		WeWikiTag tags = new WeWikiTag();
		return entityService.getListEntity(tags);
	}

	/**
	 * 대시보드 내 활동 내역 - 사용안함 
	 * @param userIdx
	 * @return
	 
	public List<Map<String, String>> getMyActionList(Integer userIdx) {
		return mainDao.getMyActionList(userIdx);
	}
	*/

	/**
	 * 대시보드 기본 위키 리스트 조회 
	 * @param userIdx
	 * @param listType
	 * @return
	 */
	public List<Map<String, String>> getWikiList(Integer userIdx, String listType) {
		int startRow = 0;
		int endRow = 0;
		if(listType.equals("recent")) {
			startRow = 0;
			endRow = 5;
		} else {
			startRow = 0;
			endRow = 20;
		}
		return this.getMoreWikiList(userIdx, listType, startRow, endRow);
	}
	
	/**
	 * 대시보드 위키목록 펼치기 
	 * @param userIdx
	 * @param listType
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<Map<String, String>> getMoreWikiList(Integer userIdx, String listType, Integer startRow, Integer endRow){
		logger.debug("##userIdx : " + userIdx);
		logger.debug("##listType : " + listType);
		logger.debug("##startRow : " + startRow);
		logger.debug("##endRow : " + endRow);
		
		return mainDao.getWikiList(userIdx, listType, startRow, endRow);
	}

	public int getAllSpaceCount() throws Throwable {
		WeSpace weSpace = new WeSpace();
		weSpace.setWe_use_yn("Y");

		return entityService.getCountEntity(weSpace);
	}

	public int getAllWikiCount() throws Throwable {
		WeWiki weWiki = new WeWiki();
		weWiki.setWe_use_yn("Y");

		return entityService.getCountEntity(weWiki);
	}

	/**
	 * 최근 업데이트 목록 - 대시 보드 
	 * @param startRow
	 * @param endRow
	 * @return
	 */
	public List<Map<String, String>> getUpdatedList(Integer startRow, Integer endRow) {
		return mainDao.getUpdatedList(startRow, endRow);
	}

	public List<Map<String, String>> getFavorList() {
		return mainDao.getFavorList();
	}

	public List<Map<String, String>> getAgreeList() {
		return mainDao.getAgreeList();
	}

	public List<Map<String, String>> getUserPointList() {
		return mainDao.getUserPointList();
	}

	public List<Map<String, String>> getHomeWikiList(String listType) {
		return mainDao.getHomeWikiList(listType);
	}

	public int getWikiCount() {
		return mainDao.getWikiCount();
	}

	public int getTagCount() {
		return mainDao.getTagCount();
	}

	public int getMyWikiCount(int userIdx) {
		return mainDao.getMyWikiCount(userIdx);
	}

	public int getMyPeopleCount(int userIdx) {
		return mainDao.getMyPeopleCount(userIdx);
	}

	public int getToMePeopleCount(int userIdx) {
		return mainDao.getToMePeopleCount(userIdx);
	}

	/**
	 * 나의 오늘방문카운트와 토탈카운트수를 구해온다.
	 * @return List<Integer>
	 */
	public Map<String, Integer> getVisitCount(HttpServletRequest request) {
		VisitCounter visitCounter = new VisitCounter(request);

		return visitCounter.getAllVisitCountInfo();
	}

	/**
	 * @param weUserIdx
	 * @return
	 */
	public List<Map<String, String>> getMyNotiList(Integer weUserIdx, Integer startRow, Integer endRow) {
		// 최근 한달로 조정해야 할 경우 아래 코드 추가 
		// String month = DateUtil.getOffsetDate(SystemConst.SEACH_MONTH);
		return mainDao.getMyNotiList(weUserIdx, startRow, endRow);
	}
}
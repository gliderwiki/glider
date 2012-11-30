/**
 * @FileName  : WikiFunctionServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 14.
 * @작성자      : @author ganji

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.parser.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.WeAddFriend;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.domain.WePoint;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.domain.WeWikiAgree;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.wiki.common.dao.CommonDao;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.parser.dao.WikiFunctionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ganji
 *
 */
@Service
@RemoteProxy(name = "WikiFunctionService")
public class WikiFunctionServiceImpl implements WikiFunctionService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntityService entityService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private WikiFunctionDao wikiFunctionDao;

	@Resource(name = "commonDao")
	private CommonDao commonDao;

	@SuppressWarnings("unchecked")
	@Override
	@RemoteMethod
	public int updateAgreeUserInsert(Integer weWikiIdx, Integer weUserIdx) throws Exception {
		int result = 0; // SQL 처리 결과
		int agreeCount = 0; // 공감한 갯수 가져오기

		WeWiki wewiki = null;

		WeWikiAgree wewikiagree = null;
		try {
			// 1. 위키번호로 현재 위치한 위키를 찾은후 공감수를 확인한다.
			// 2. 위키번호로 위키공감사용자에 해당 회원이 이미 공감을 하였는지 확인한다.
			wewiki = commonService.getWikiInfo(weWikiIdx);

			wewikiagree = new WeWikiAgree();
			wewikiagree.setWe_wiki_idx(weWikiIdx);
			wewikiagree.setWe_user_idx(weUserIdx);

			wewikiagree = (WeWikiAgree) entityService.getRowEntity(wewikiagree);
			logger.debug("공감 추가전 wewiki : " + wewiki.toString());

			// 현재 위치한 위키에 회원이 공감을 하지 않았다면 공감을 카운터 하고 공감사용자에 저장한다.
			if (wewikiagree == null) {
				
				// 본인 글에는 공감하기를 클릭할 수 없다. 
				if(wewiki.getWe_ins_user() == weUserIdx) {
					result = -3; 
				} else {
					// 가져온 위키에 공감수 +1 를 한후 수정한다.
					agreeCount = wewiki.getWe_wiki_agree() + 1;
					wewiki.setWe_wiki_agree(agreeCount);
					result = entityService.updateEntity(wewiki);

					// 공감사용자에 저장한다.
					wewikiagree = new WeWikiAgree();
					wewikiagree.setWe_wiki_idx(weWikiIdx);
					wewikiagree.setWe_user_idx(weUserIdx);
					wewikiagree.setWe_view_Date(new Date());

					logger.debug("###  wewikiagree ### : " + wewikiagree.toString());

					result = wikiFunctionDao.insertWeAgree(wewikiagree);
					logger.debug("#공감하기 result : " + result);
					
					
					
					if (result == 1) {
						result = agreeCount;
						// 회원 포인트 추가 - 공감 
						commonService.updateUserPoint(wewiki.getWe_ins_user(), WePoint.AGREE_WIKI.point); 
					}

					// 알림추가
					WeWiki weWiki = commonService.getWikiInfo(weWikiIdx);
					WeUser weUser = commonService.getUserInfo(weUserIdx);
					commonService.requestAlarmInfo(weUserIdx, weUser.getWe_user_nick(), SystemConst.WIKI_AGREE,	weWiki.getWe_ins_user(), weWiki.getWe_wiki_idx(), weWiki.getWe_space_idx());
				}
				
			} else {
				logger.debug("# else # ");

				result = -1; // 이미 공감한 사용자이다.
			}
		} catch (Throwable e) {
			result = -2;
			logger.debug("#공감하기 에러 : " + e.getMessage());
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@RemoteMethod
	public String addUserFriend(Integer loginUserIdx, Integer friendId) throws Exception {
		// TODO Auto-generated method stub
		String result = "";

		WeAddFriend weAddFriend = new WeAddFriend();
		weAddFriend.setWe_user_idx(loginUserIdx);
		weAddFriend.setWe_target_user_idx(friendId);

		try {
			// 관심인맥으로 등록되어있는지 확인한다.
			
			if(loginUserIdx == friendId) {
				result = "본인을 관심인맥으로 추가할 수 없습니다.";
			} else {
				weAddFriend = (WeAddFriend) entityService.getRowEntity(weAddFriend);
				
				// 관심인맥으로 등록되어있지않으면 추가한다.
				if (weAddFriend == null) {
					weAddFriend = new WeAddFriend();
					weAddFriend.setWe_user_idx(loginUserIdx);
					weAddFriend.setWe_target_user_idx(friendId);
					weAddFriend.setWe_add_date(new Date());
					weAddFriend.setWe_use_yn("Y");

					logger.debug("###  weAddFriend ### : " + weAddFriend.toString());
					entityService.insertEntity(weAddFriend);
					result = "관심인맥으로 등록했습니다.";

					// 회원 포인트 추가 - 관심인맥 
					commonService.updateUserPoint(friendId, WePoint.ADD_FRIEND.point);
					
					// 알림추가
					WeUser weUser = commonService.getUserInfo(loginUserIdx);
					commonService.requestAlarmInfo(loginUserIdx, weUser.getWe_user_nick(), SystemConst.WIKI_ADD_FRIEND, friendId, null, null);
				} else {
					result = "이미 관심인맥으로 등록되어 있습니다.";					
				}
			}

		} catch (Throwable e) {
			e.getCause();
			result = "관리자에게 문의하세요.";
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@RemoteMethod
	public String addWikiFavorite(Integer weUserIdx, Integer WikiId, Integer SpaceId) throws Exception {
		String result = "";

		WeFavorite weFavorite = new WeFavorite();
		weFavorite.setWe_user_idx(weUserIdx);
		weFavorite.setWe_favorite_type(FavorityType.WIKI);
		weFavorite.setWe_space_idx(SpaceId);
		weFavorite.setWe_wiki_idx(WikiId);

		try {
			// 즐겨찾기를 하기전에 해당 유저가 공간과 위키에 해당하는 정보를 이미 즐겨찾기는 확인한다.
			weFavorite = (WeFavorite) entityService.getRowEntity(weFavorite);

			// 즐겨찾기에 등록되어 있지않으면 추가한다.
			if (weFavorite == null) {
				weFavorite = new WeFavorite();
				weFavorite.setWe_user_idx(weUserIdx);
				weFavorite.setWe_favorite_type(FavorityType.WIKI);
				weFavorite.setWe_space_idx(SpaceId);
				weFavorite.setWe_wiki_idx(WikiId);
				weFavorite.setWe_use_yn("Y");
				weFavorite.setWe_add_date(new Date());

				logger.debug("###  weAddFriend ### : " + weFavorite.toString());
				entityService.insertEntity(weFavorite);
				
				result = "즐겨찾기를 추가했습니다.";
				
				
				WeWiki weWiki = commonService.getWikiInfo(WikiId);
				WeUser weUser = commonService.getUserInfo(weUserIdx);
				
				// 회원 포인트 추가 - 즐겨찾기 
				commonService.updateUserPoint(weWiki.getWe_ins_user(), WePoint.ADD_FAVORITE.point);
				
				// 알림추가
				commonService.requestAlarmInfo(weUserIdx, weUser.getWe_user_nick(), SystemConst.WIKI_FAVORITE, weWiki.getWe_ins_user(), weWiki.getWe_wiki_idx(), weWiki.getWe_space_idx());
			} else {
				return "이미 즐겨찾기가 된 위키입니다.";
			}

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.getCause();
			result = "관리자에게 문의하세요.";
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@RemoteMethod
	public String addWikiQuota(Integer WikiId) throws Exception {
		// TODO Auto-generated method stub
		String result = "";

		WeWiki wewiki = new WeWiki();
		wewiki.setWe_wiki_idx(WikiId);

		try {

			wewiki = (WeWiki) entityService.getRowEntity(wewiki);
			wewiki.setWe_wiki_quota(StringUtil.intNull(wewiki.getWe_wiki_quota()) + 1);
			entityService.updateEntity(wewiki);
			
			// 회원 포인트 추가 - 위키 신고  
			commonService.updateUserPoint(wewiki.getWe_ins_user(), WePoint.REPORT_WIKI.point);
			

			result = "위키 신고가 접수되었습니다.";
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.getCause();
			result = "관리자에게 문의하세요.";
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@RemoteMethod
	public String updateWikiProdectStatus(Integer WikiId) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		WeWiki domain = new WeWiki();
		domain.setWe_wiki_idx(WikiId);
		
		try{
			domain = (WeWiki) entityService.getRowEntity(domain);
			domain.setWe_wiki_protect("2");
			
			entityService.updateEntity(domain);
			result = "잠금상태로 변경했습니다..";
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.getCause();
			result = "관리자에게 문의하세요.";
		}
		return result;
	}

}
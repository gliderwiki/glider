/**
 * @FileName  : AdminGroupServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 6. 22. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.admin.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.GroupDefinitionException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.admin.dao.AdminGroupDao;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.GroupUserVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


/**
 * @author yion
 *
 */
@Service("adminGroupService")
@RemoteProxy(name="AdminGroupService")
public class AdminGroupServiceImpl implements AdminGroupService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 트랜잭션 처리 선언
	@Inject
	private PlatformTransactionManager tx;
	
	@Autowired
	private EntityService entityService;
		
	@Autowired
	private AdminGroupDao adminGroupDao;
	
	@Autowired
	private CommonService commonService;
	
	
	
	@Override
	@RemoteMethod
	public String insertGroupInfoDWR(String groupName, String groupType, String groupInfo, String adminIdx, String userIdx) throws Throwable {
		logger.debug("adminIdx : " + adminIdx);
		
		WeGroupInfo entityGroupInfo = new WeGroupInfo();
		
		entityGroupInfo.setWe_group_name(groupName);
		entityGroupInfo.setWe_group_type(groupType);
		entityGroupInfo.setWe_group_info(groupInfo);
		entityGroupInfo.setWe_group_owner(adminIdx);
		entityGroupInfo.setWe_ins_user(userIdx);
		entityGroupInfo.setWe_use_yn("Y");
		entityGroupInfo.setWe_ins_date(new Date());
		
		int result = 0;
		
		JSONObject jsonObj  = null;
		BaseObjectBean resultBean = new BaseObjectBean();
		
		WeGroupInfo resultInfo = null;
		try { 
			result =  entityService.insertEntity(entityGroupInfo);
			
			if(result == 1) {
				entityGroupInfo.setWe_ins_date(null);
				resultInfo = (WeGroupInfo) entityService.getRowEntity(entityGroupInfo);
			}
			resultBean.setRtnMsg("처리 되었습니다.");
			resultBean.setRtnResult(resultInfo.getWe_group_idx());		// 생성된 그룹 인덱스를 넘겨준다.  
			resultBean.setRtnStatus(SystemConst.CALL_SUCCESS);	// 서버 에러 
		} catch (Throwable e) {
			resultBean.setRtnMsg(e.getCause().toString());
			resultBean.setRtnResult(0);
			resultBean.setRtnStatus(SystemConst.CALL_FAIL);	// 서버 에러 
		}
		
		jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultBean));
		
		return jsonObj.toString();
	}

	
	@RemoteMethod
	public String updateGroupInfoDWR(Integer groupIdx, String groupName, String groupType, String groupInfo, String adminIdx, String userIdx) throws Throwable {
		logger.debug("adminIdx : " + adminIdx);
		WeGroupInfo domain = new WeGroupInfo();
		
		domain = commonService.getWeGroupInfo(groupIdx);
		
		domain.setWe_group_name(groupName);
		domain.setWe_group_type(groupType);
		domain.setWe_group_info(groupInfo);
		domain.setWe_group_owner(adminIdx);
		domain.setWe_upd_user(userIdx);
		domain.setWe_upd_date(new Date());
		
		int result = 0;
		
		JSONObject jsonObj  = null;
		BaseObjectBean resultBean = new BaseObjectBean();
		
		WeGroupInfo resultInfo = null;
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
	public List<WeGroupInfo> getGroupInfoList() throws Throwable {
		return adminGroupDao.getGroupInfoList();
	}

	@Override
	@RemoteMethod
	public List<GroupUserVo> getGroupUserList(Integer groupIdx) throws Throwable {
		GroupUserVo groupUser = new GroupUserVo();
		groupUser.setWeGroupIdx(groupIdx);
		return adminGroupDao.getGroupUserList(groupUser);
	}

	
	/**
	 * TODO 추후 회원 정보가 10000 건이 되었을 시 테스트를 해본다. 
	 */
	@Override
	@RemoteMethod
	public List<WeUser> getWeUserList(Integer loginUserIdx, String userNick, String userEmail, String userName) {
		List<WeUser> userList = null;
		try {
			userList = (List<WeUser>) commonService.getWeUserList(loginUserIdx, userNick, userEmail, userName);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return userList;
	}

	@RemoteMethod
	public List<WeUser> getWeAdminList() {
		List<WeUser> userList = null;
		try {
			userList = (List<WeUser>) adminGroupDao.getSearchAdminList();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;
	}
	

	/**
	 * 선택한 회원 정보를 그룹 유저로 저장한다. 
	 */
	@Override
	public int arrayInsertGroupUser(String arrayCheckId, String checkGroupIdx) throws Throwable {
		
		String[] arrayUser = arrayCheckId.split(",");
		
		int size = arrayUser.length;
		int result = 0;
		for(int i = 0; i < size; i++ ) {
			WeGroupUser domain = new WeGroupUser();
			domain.setWe_group_idx(checkGroupIdx);
			domain.setWe_user_idx(arrayUser[i].trim());
			domain.setWe_use_yn("Y");
			domain.setWe_ins_date(new Date());
			domain.setWe_ins_user(1);
			
			try {
				result = entityService.insertEntity(domain);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}


	@Override
	@RemoteMethod
	public Object[] getGroupInfo(Integer weGroupIdx) throws Throwable {
		WeGroupInfo domain = new WeGroupInfo();
		domain.setWe_group_idx(weGroupIdx);
		domain.setWe_use_yn("Y");
		
		
		WeGroupInfo resultInfo = null;
		WeUser  resultUser = null;
		try { 
			resultInfo = (WeGroupInfo) entityService.getRowEntity(domain);
			
			resultUser = commonService.getUserInfo(Integer.parseInt(resultInfo.getWe_group_owner()));
			
		} catch (Throwable e) {

		}
		
		Object[] returnArrayObject = new Object[2];
		
		returnArrayObject[0] = resultInfo;	// 그룹 정보 
		returnArrayObject[1] = resultUser;		// 사용자 정보 
		return returnArrayObject;		

	}


	@Override
	@RemoteMethod
	public String deleteGroupInfoDWR(Integer weGroupIdx, Integer userIdx) throws Throwable {

		WeGroupInfo domain = new WeGroupInfo();
		
		domain = commonService.getWeGroupInfo(weGroupIdx);
		
		
		domain.setWe_use_yn("N");
		domain.setWe_upd_user(userIdx+"");
		
		int result = 0;
		
		JSONObject jsonObj  = null;
		BaseObjectBean resultBean = new BaseObjectBean();
		
		WeGroupInfo resultInfo = null;
		try { 
			WeGroupUser groupUser = new WeGroupUser();
			groupUser.setWe_group_idx(weGroupIdx+"");
			groupUser.setWe_use_yn("Y");
			
			int count = entityService.getCountEntity(groupUser);
			if(count > 0) {
				// 그룹에 속한 사용자가 있을 경우 그룹 사용자 리스트 N 으로 업데이트 후 그룹 삭제 함 
				groupUser.setWe_use_yn("N");
				groupUser.setWe_ins_user(userIdx);
				int rowCount = adminGroupDao.deleteUserInGroupDWR(groupUser);
			}
			result =  adminGroupDao.deleteGroupInfoDWR(domain);
			
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
	public String deleteGroupInUserDWR(Integer weGroupIdx, String checkUsersId, Integer userIdx) throws Throwable {
		String[] arrayUser = checkUsersId.split(",");
		
		int size = arrayUser.length;
		int result = 0;
		
		JSONObject jsonObj  = null;
		BaseObjectBean resultBean = new BaseObjectBean();
		
		for(int i = 0; i < size; i++ ) {
			WeGroupUser domain = new WeGroupUser();
			domain.setWe_group_idx(weGroupIdx+"");
			domain.setWe_user_idx(arrayUser[i].trim());
			domain.setWe_use_yn("N");
			domain.setWe_ins_user(userIdx);
			
			try {
				result = adminGroupDao.deleteGroupUserDWR(domain);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
		if(result > 0) {
			resultBean.setRtnMsg("처리 되었습니다.");
			resultBean.setRtnResult(1);		// 생성된 그룹 인덱스를 넘겨준다.  
			resultBean.setRtnStatus(SystemConst.CALL_SUCCESS);	// 서버 에러
		} else {
			resultBean.setRtnMsg("수정이 되지 않았습니다");
			resultBean.setRtnResult(0);
			resultBean.setRtnStatus(SystemConst.CALL_FAIL);	// 서버 에러 
		}
		
		jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultBean));
		return jsonObj.toString();
		
	}


	@Override
	@RemoteMethod
	public Object[] getUserProfileInfo(Integer weUserIdx, String attrId) throws Throwable {
		WeUser weUser = commonService.getUserInfo(weUserIdx);
		WeProfile weProfile = commonService.getUserProfileInfo(weUserIdx);
		List<WeGroupInfo> weGroupInfoList = adminGroupDao.getUserJoinGroupList(weUserIdx);
		
		Object[] returnArrayObject = new Object[4];
		
		returnArrayObject[0] = weUser;		// 사용자 정보 
		returnArrayObject[1] = weProfile;	// 프로필 정보  
		returnArrayObject[2] = attrId;		// 프로필 정보  
		returnArrayObject[3] = weGroupInfoList;		// 프로필 정보  
		return returnArrayObject;		
	}



	@Override
	@RemoteMethod
	public int updateUserProfileInfo(Integer weUserIdx, Integer weGrade, String weTechYn, Integer wePoint) throws Throwable {
		WeProfile domain = commonService.getUserProfileInfo(weUserIdx);
		domain.setWe_grade(weGrade);
		domain.setWe_tech_yn(weTechYn);
		domain.setWe_point(wePoint);
		domain.setWe_upd_date(new Date());
		
		int result = 0;
		
		try {
			result = entityService.updateEntity(domain);
		} catch (Exception e) {
			result = -1;
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public int insertGroupInfo(Map <Integer, Map> maps) throws Throwable {
		int result = 0;
		
		TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
		try{
			// 기본테이블 리셋
			logger.debug ("UserProfileService :::: insertUser(Map <Integer, Map> map)");
			logger.debug ("UserProfileService :::: insertUser ::: map " + maps.size());
			
			for(int i = 1; i < maps.size();i++) {
				
				Map <Integer, String> map = maps.get(i);
				
				WeGroupInfo weGroupInfo = new WeGroupInfo();
				weGroupInfo.setWe_group_code(map.get(1));
				weGroupInfo.setWe_group_name(map.get(2));
				weGroupInfo.setWe_group_info(map.get(3));
				weGroupInfo.setWe_group_owner(map.get(4));
				
				result += adminGroupDao.insertGroupInfo(weGroupInfo);
			
			}
			
			tx.commit(status);
		} catch (Exception e) {
			result = -1;
			logger.error("***그룹 일괄 처리중  Error " + e.getCause());
			tx.rollback(status);
			throw new DBHandleException("그룹일괄 처리 중 에러가 발생하였습니다. 관리자에게 문의하세요.", null, result);
		}
		
		
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public int insertGroupUser(Map <Integer, Map> maps) throws Throwable {
		int result = 0;
		
		TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
		try{
			// 기본테이블 리셋
			logger.debug ("UserProfileService :::: insertUser(Map <Integer, Map> map)");
			logger.debug ("UserProfileService :::: insertUser ::: map " + maps.size());
			
			for(int i = 1; i < maps.size();i++) {
				
				Map <Integer, String> map = maps.get(i);
				
				WeGroupUser weGroupUser = new WeGroupUser();
				weGroupUser.setWe_group_idx(map.get(0));
				weGroupUser.setWe_user_idx(map.get(3));
				
				result += adminGroupDao.insertGroupUser(weGroupUser);
			
			}
			
			tx.commit(status);
		} catch (Exception e) {
			result = -1;
			logger.error("***그룹 유저일괄 처리중  Error " + e.getCause());
			tx.rollback(status);
			throw new DBHandleException("그룹일괄 처리 중 에러가 발생하였습니다. 관리자에게 문의하세요.", null, result);
		}
		
		
		return result;
	}
}


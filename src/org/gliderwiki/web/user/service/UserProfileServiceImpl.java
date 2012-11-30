/**
 * @FileName  : UserProfileServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 5. 30. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.user.service;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.web.domain.WeFile;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.login.service.LoginService;
import org.gliderwiki.web.user.dao.UserProfileDao;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.ProfileVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;


/**
 * @author yion
 *
 */
@Service("userProfileService")
public class UserProfileServiceImpl implements UserProfileService {
	Logger logger = LoggerFactory.getLogger(this.getClass());


	// 트랜잭션 처리 선언
	@Inject
	private PlatformTransactionManager tx;
	
	@Autowired
	private EntityService entityService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private UserProfileDao userProfileDao;
	

	
	
	@Override
	public BaseObjectBean updateUserProfile(ProfileVo profileVo, HttpServletRequest request) throws Throwable {
		// WE_USER, WE_PROFILE 업데이트 Transation 
		TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
		
		// 첨부파일이 있는 경우 첨부파일 정보를 조회한다. 
		WeFile weFile = null;			// 파일 정보 
		WeUser weUser = null;			// 사용자 기본정보
		WeProfile weProfile = null;		// 사용자 프로필 정보 
		int result = 0;
		// 결과값 리턴 Object 
		BaseObjectBean rtnObject = new BaseObjectBean();
		weUser = commonService.getUserInfo(profileVo.getWeUserIdx());		// 회원 기본 정보 조회
		weProfile = commonService.getUserProfileInfo(profileVo.getWeUserIdx());	// 회원 프로파일 정보 조회 
		
		logger.debug("profileVo.getIsUpload() : " + profileVo.getIsUpload());
		
		logger.debug("### profileVo.getUserPassword() : " + profileVo.getUserPassword());
		// 비밀번호가 새로 입력 된 경우 비밀번호까지 암호화 해야 한다. 
		if(!profileVo.getUserPassword().equals("") && !profileVo.getWeUserPwd().equals("")){
			// 현재 넘어온 키로 현재 비밀번호를 암호화 한다. 
			String passKey = weUser.getWe_user_key();
			String password = loginService.getEncryptPassword(passKey, profileVo.getUserPassword());
			
			logger.debug("현재 가져온 PASSWORD : " + password);
			logger.debug("DB의 getWe_user_pwd() : " + weUser.getWe_user_pwd());
			
			// 사용자가 입력한 현재 비밀번호와 DB에서 가져온 값과 같은가 판단한다.
			if(password.equals(weUser.getWe_user_pwd())) {
				// 넘어온 새 비밀번호로 암호화 한다. 
				logger.debug("비밀번호 새로 세팅");
				String newPassword = loginService.getEncryptPassword(passKey, profileVo.getWeUserPwd());			
				logger.debug("newPassword : " + newPassword);
				weUser.setWe_user_pwd(newPassword);
			} else {
				logger.error("***회원 프로필 비밀번호 입력 에러");
				rtnObject.setRtnResult(-3);
				rtnObject.setRtnMsg("입력하신 비밀번호가 올바르지 않습니다. 현재 비밀번호를 정확히 넣어주세요");
				return rtnObject;
			}
		}
		
		try {
			// 회원 닉네임, 이름 정보 저장 
			weUser.setWe_user_name(profileVo.getWeUserName());
			weUser.setWe_user_nick(profileVo.getWeUserNick());
			
			
			logger.debug("weUser : " + weUser.toString());
			
			result = entityService.updateEntity(weUser);				
			
			if(result < 1) {
				logger.error("*** entityService.updateEntity(weUser) 에러 ***");			
				throw new DBHandleException("회원 정보 수정시 에러가 발생하였습니다. 다시 시도하세요.", null, -1);
			}
			
			logger.debug("**회원정보 수정 result : " + result);
			
			if(profileVo.getWeFileIdx() != null && !profileVo.getWeFileIdx().equals("") ) {
				weFile = commonService.getUserFileInfo(profileVo.getWeFileIdx());
				weProfile.setWe_file_real_name(weFile.getWe_file_real_name());
				weProfile.setWe_file_save_name(weFile.getWe_file_save_name());
				weProfile.setWe_file_save_path(weFile.getWe_file_save_path());
				weProfile.setWe_thumb_name(weFile.getWe_thumb_name());
				weProfile.setWe_thumb_path(weFile.getWe_thumb_path());
			}
			
			weProfile.setWe_user_site(profileVo.getWeUserSite());		// 사용자 홈피
			weProfile.setWe_upd_date(new Date());			// 수정일 
			
			result = entityService.updateEntity(weProfile);				// 파일 정보가 있을 경우 파일정보까지 수정됨.				
			
			if(result < 1) {
				logger.error("***entityService.updateEntity(weProfile)***");
				throw new DBHandleException("회원 프로필 수정시 에러가 발생하였습니다. 다시 시도하세요.", null, -1);
			}
			
			logger.debug("**회원프로필 수정 result : " + result);
			
			boolean fileRemove = false;
			if(result == 1) {
				//파일 카피 및 이동 후 관련 DB 삭제 
				if(profileVo.getIsUpload().equals("1")) {
					// 파일 정보 조회 후 프로필에 필드 업데이트 
					
					// 섬네일이 없을 경우 일반 파일 업로드는 에러가 난다. (강제로 thumb 폴더를 뒤지기 때문)
					// 따라서 thumb 가 있는지 여부를 다시 판단하는 로직을 capyTo에 추가하도록 한다. 
					
					// 첨부파일이 존재할 경우 파일 remove 해야 한다. 
				
					String fromFilePath = request.getSession().getServletContext().getRealPath("/resource/temp");
					String toFilePath = request.getSession().getServletContext().getRealPath("/resource/real");
					File fromFile = new File(fromFilePath+weFile.getWe_file_save_path());
					File toFile = new File(toFilePath+weFile.getWe_file_save_path());
					
					profileVo.setFromFilePath(fromFile);
					profileVo.setToFilePath(toFile);
					
					fileRemove = FileUploader.copyTo(profileVo.getFromFilePath(), profileVo.getToFilePath());
					
					if(fileRemove) {
						WeFile delWeFile = new WeFile();
						delWeFile.setWe_file_idx(profileVo.getWeFileIdx());								
						result = entityService.deleteEntity(delWeFile);
						logger.debug("**회원 파일 수정 result : " + result);
					}		
					
				}
				
				rtnObject.setRtnResult(result);
				rtnObject.setRtnMsg("처리되었습니다.");
			} else {
				result = 0;
				logger.error("회원정보수정 Error : updateUserProfile Transation이 제대로 수행되지 않았습니다");
				tx.rollback(status);
				rtnObject.setRtnResult(result);
				rtnObject.setRtnMsg("회원정보수정 트랜잭션 실패! 관리자에게 문의하세요.");			
			}
			
			tx.commit(status);
			
		} catch (Exception e) {
			result = -1;
			logger.error("***회원정보수정 Error " + e.getCause());
			tx.rollback(status);
			throw new DBHandleException("회원정보수정  실패! 관리자에게 문의하세요.", null, result);
		}
		
		
		return rtnObject;
	}

	@Override
	public int updateAwayUser(Integer weUserIdx) throws Throwable {
		int result = 0;
		
		TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());
		
		try{
			// 기본테이블 리셋
			result = userProfileDao.updateAwayUser(weUserIdx);
			
			// 프로필 테이블 리셋 
			result = userProfileDao.updateAwayProfile(weUserIdx);
			
			tx.commit(status);
		} catch (Exception e) {
			result = -1;
			logger.error("***회원 탈퇴 처리중  Error " + e.getCause());
			tx.rollback(status);
			throw new DBHandleException("회원탈퇴 처리 중 에러가 발생하였습니다. 관리자에게 문의하세요.", null, result);
		}
		
		
		return result;
	}

}

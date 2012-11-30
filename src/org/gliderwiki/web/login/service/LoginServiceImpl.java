/**
 * @FileName  : LoginServiceImpl.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 12.
 * @작성자      : @author inamhui

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.login.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.DBHandleException;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.exception.UserNotFoundException;
import org.gliderwiki.framework.util.Base64Coder;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.util.PropertyUtil;
import org.gliderwiki.util.SendMailSMTP;
import org.gliderwiki.web.domain.WeGroupInfo;
import org.gliderwiki.web.domain.WeGroupUser;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeSendMail;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.login.dao.LoginDao;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.BaseObjectBean;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;


/**
 * @author inamhui
 *
 */

@Service("loginService")
@RemoteProxy(name="LoginService")
public class LoginServiceImpl implements LoginService {

	Logger logger = LoggerFactory.getLogger(this.getClass());


	// 트랜잭션 처리 선언
	@Inject
	private PlatformTransactionManager tx;

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private EntityService entityService;

	@Autowired
	private CommonService commonService;

	@Override
	public MemberSessionVo getRowWeUserById(String id) throws Throwable{
		WeUser weUser = new WeUser();
		weUser.setWe_user_id(id);
		return loginDao.getRowWeUserById(weUser);
	}



	/**
	 * 시스템 키로 특정 문자열을 암호화 한다.  -- 추후 메소드 이동시킴
	 * @param passKey
	 * @param passVal
	 * @return
	 * @throws Exception
	 */
	public String getEncryptPassword(String passKey, String passVal) {
		String password = "";
		try {
			SecretKeyPBECipher.initiate(passKey);
			byte[] data;
			data = SecretKeyPBECipher.encrypt(passVal);
			password = Base64Coder.encodeString(data);
		} catch (Exception e) {
			logger.error("문자열 암호화 에러 ", e.getCause());
			e.printStackTrace();
		}

		// 넘어온 비밀번호를 Key로 암호화
		return password;
	}

	/**
	 * 회원 정보 저장 후 아이디로 메일 전송 - 회원 인증
	 * 스팸 유저 방지용 인증 메일을 전송한다.
	 */
	public int sendMailAuth(WeUser weUser, HttpServletRequest request) throws Throwable {
		WeUser retUser = new WeUser();
		int result = 0;

		// 프로퍼티 정보를 가져온다.
		// 변경된 정보를 읽어오기 위해서 새 프로퍼티를 선언하여 호출한다.
		String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
		Properties props = PropertyUtil.getMailPropertyInfo(svcPath);

		String mailUserId 		= props.getProperty("mail.user.id");
		String mailUserPassword = props.getProperty("mail.user.password");
		String mailUserKey 		= props.getProperty("mail.user.key");
		String smtpHostName 	= props.getProperty("smtp.host.name");
		String smtpServerPort   = props.getProperty("smtp.server.port");
		String mailCharset 		= props.getProperty("mail.charset");
		String siteDomain	 	= props.getProperty("mail.site.domain");
		String siteOwner	 	= props.getProperty("mail.site.owner");

		WeSendMail sendMail = null;

		TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());

		try {
			result = entityService.insertEntity(weUser);

			WeUser currentUser = (WeUser)entityService.getRowEntity(weUser);


			//TODOLIST 트랜잭션 시작 이전에 WE_SEND_MAIL 테이블에 적재한다.
			sendMail = new WeSendMail(weUser.getWe_user_idx(), "0", "WE_USER_JOIN_AUTH_MAIL");
			sendMail.setWe_log_param(weUser.toString());
			sendMail.setWe_ins_date(new Date());

			entityService.insertEntity(sendMail);


			logger.info("새 사용자 순번 : " + currentUser.toString());
			String sendKey = currentUser.getWe_user_idx()+"-"+weUser.getWe_user_auth();

			if(result == 1) {
				WeProfile weProfile = new WeProfile();

				weProfile.setWe_user_idx(currentUser.getWe_user_idx());
				weProfile.setWe_user_email(currentUser.getWe_user_id());		// 사용자 아이디가 이메일임
				weProfile.setWe_ins_date(new Date());
				weProfile.setWe_grade(SystemConst.NEW_USER_GRADE);				// 기본 등급 세팅
				weProfile.setWe_noti_checked("Y");

				result = entityService.insertEntity(weProfile);

				if(result == 1) {
					//String code = getEncryptPassword(mailKey, currentUser.getWe_user_idx().toString());
					String targetUrl = siteDomain + "/authUser/"+sendKey;
					String targetID = currentUser.getWe_user_id();

					String emailMsgTxt = "안녕하세요. " + siteOwner +" 입니다. <br><br>" +
							"자동 가입 방지를 위한 인증 페이지주소를 보내 드립니다. <br><br>" +
							"아래의 인증 페이지 링크를 클릭하세요. <br><br>" +
							"주소 : <a href='"+targetUrl+"' target='_blank'>" + targetUrl + "</a>"; 	// 내용

					String emailTitle = siteOwner + " Wiki 가입 인증 안내입니다.";		// 제목
					try {
						SendMailSMTP sender = new SendMailSMTP();
						result = sender.sendSimpleMail(targetID, emailTitle, emailMsgTxt, request);

						logger.debug("###result : " + result);
						if(result > 0){
							result = 1;

							int weMailIdx = loginDao.getCurrentMailIdx();

							sendMail.setWe_mail_idx(weMailIdx);
							sendMail.setWe_send_status("9");
							sendMail.setWe_upd_date(new Date());

							logger.debug("###### 메일 전송 테이블 정상 START ######");
							entityService.updateEntity(sendMail);
							logger.debug("###### 메일 전송 테이블 정상 END ######");

							tx.commit(status);
						} else {
							result = -1;
							tx.rollback(status);
						}
					} catch (UnsupportedEncodingException e) {
						result = -3;
						logger.debug("### UnsupportedEncodingException : " + e.getCause());
						logger.info("***회원가입 Exception " + e.getCause());
						tx.rollback(status);
					} catch (MessagingException e) {
						result = -4;
						logger.debug("### MessagingException : " + e.getCause());
						logger.info("***회원가입 Exception " + e.getCause());
						tx.rollback(status);
					}

				} else {
					result = 0;
					logger.info("회원가입 Error - WE_PROFILE 테이블 저장 실패  (result=" + result+"}");
					tx.rollback(status);
				}
			} else {
				result = -1;
				logger.info("회원가입 Error - WE_USER 테이블 저장 실패  (result=" + result+"}");
				tx.rollback(status);
			}


		} catch (DBHandleException e) {
			result = -2;
			// 익셉션 처리 로직 확인
			logger.info("***회원가입 Exception " + e.getCause());
			tx.rollback(status);
		}



		logger.debug("###result : "+ result);
		return result;
	}



	/**
	 * 회원 가입 후 인증 페이지로 들어왔을 경우 인증여부 필드를 업데이트 한다.
	 */
	@Override
	public int updateUserAuth(String code) throws Throwable{
		int result = 0;
		logger.info("code : " + code);

		TransactionStatus status = tx.getTransaction(new DefaultTransactionDefinition());

		try {
			String[] arrCode = code.split("-");


			WeUser weUser = new WeUser();
			weUser.setWe_user_idx(Integer.parseInt(arrCode[0]));
			weUser.setWe_user_auth(arrCode[1]);

			if(arrCode[0] == null || arrCode[1] == null) {
				throw new UserNotFoundException("사용자 인증정보가 잘못되었습니다.");
			}

			int userCount = entityService.getCountEntity(weUser);

			if(userCount == 1) {
				// 회원 정보 수정
				result = loginDao.updateUserAuth(weUser);
			} else {
				throw new UserNotFoundException("사용자 인증정보가 올바르지 않습니다.");

			}

			WeProfile weProfile = new WeProfile();
			weProfile = commonService.getUserProfileInfo(Integer.parseInt(arrCode[0]));
			weProfile.setWe_grade(1);			// 기본 등급은 1
			weProfile.setWe_visit_date(new Date());
			weProfile.setWe_point(1);		// 최초 로그인 시 1점

			// 프로필 정보 수정
			result = entityService.updateEntity(weProfile);

			// 인증 시점에 기본 그룹에 담아준다.
			WeGroupUser groupUser = new WeGroupUser(1, Integer.parseInt(arrCode[0]));

			result  = entityService.insertEntity(groupUser);

			tx.commit(status);

			return result;
		} catch (Exception e1) {
			tx.rollback(status);
			result = -1;
			e1.printStackTrace();
		}
		return result;
	}


	@Override
	public BaseObjectBean findUserPassword(String userId, String userNick, HttpServletRequest request) throws Throwable {
		logger.debug("###userId : "+ userId);
		logger.debug("###userNick : "+ userNick);
		// TODOLIST we_user테이블에 we_user_nick, we_user_auth_yn 값으로 인덱스 추가 해야 함
		WeUser domain = new WeUser();
		domain.setWe_user_auth_yn("Y");			
		domain.setWe_user_id(userId);
		domain.setWe_user_nick(userNick);
		WeUser searchUser = null;

		// 프로퍼티 정보를 가져온다.
		// 변경된 정보를 읽어오기 위해서 새 프로퍼티를 선언하여 호출한다.
		String svcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
		Properties props = PropertyUtil.getMailPropertyInfo(svcPath);

		String mailUserId 		= props.getProperty("mail.user.id");
		String mailUserPassword = props.getProperty("mail.user.password");
		String mailUserKey 		= props.getProperty("mail.user.key");
		String smtpHostName 	= props.getProperty("smtp.host.name");
		String smtpServerPort   = props.getProperty("smtp.server.port");
		String mailCharset 		= props.getProperty("mail.charset");
		String siteDomain	 	= props.getProperty("mail.site.domain");
		String siteOwner	 	= props.getProperty("mail.site.owner");

		int result = 0;

		BaseObjectBean resultBean = new BaseObjectBean();

		try {
			searchUser = (WeUser) entityService.getRowEntity(domain);

			if(searchUser != null ) {
				// 키와 패스워드로 복호화 한다.
				String userPass = SecretKeyPBECipher.getUserPassword(searchUser.getWe_user_pwd(), searchUser.getWe_user_key());

				// 메일 전송
				String targetID = userId;

				String emailMsgTxt = "안녕하세요. " + siteOwner +" Wiki 입니다. <br><br>" +
						"요청한 정보는 아래와 같습니다.<br><br>" +
						"비밀번호 : " + userPass;

				String emailTitle = siteOwner + " Wiki 정보 확인 안내입니다.";		// 제목

				try {
					SendMailSMTP sender = new SendMailSMTP();
					result = sender.sendSimpleMail(targetID, emailTitle, emailMsgTxt, request);
					resultBean.setRtnMsg("* 비밀번호가  정상적으로 전송 되었습니다.");
					resultBean.setRtnResult(1);
					return resultBean;
				} catch (UnsupportedEncodingException e) {
					resultBean.setRtnMsg("* 메일 전송이 처리 되지 않았습니다. <br />다시 시도하세요.");
					resultBean.setRtnResult(-3);
					logger.debug("### UnsupportedEncodingException : " + e.getCause());
					logger.info("***회원가입 Exception " + e.getCause());
					return resultBean;
				} catch (MessagingException e) {
					resultBean.setRtnMsg("* 메일 전송이 처리 되지 않았습니다. <br />다시 시도하세요.");
					resultBean.setRtnResult(-4);
					logger.debug("### MessagingException : " + e.getCause());
					logger.info("***회원가입 Exception " + e.getCause());
					return resultBean;

				}
			} else {
				resultBean.setRtnMsg("* 회원정보가 존재하지 않습니다.<br />다시 시도하세요.");
				resultBean.setRtnResult(-1);
				return resultBean;
			}
		} catch (Exception e) {
			resultBean.setRtnMsg("* 입력된 정보에 해당하는 아이디가 없습니다. <br />다시 시도하세요.");
			resultBean.setRtnResult(-2);
			e.printStackTrace();
			return resultBean;
		}

	}


	@Override
	@RemoteMethod
	public String findUserIdDWR(String userPassword, String userNick) throws Throwable {
		// TODOLIST we_user테이블에 we_user_nick, we_user_auth_yn 값으로 인덱스 추가 해야 함
		WeUser domain = new WeUser();
		domain.setWe_user_nick(userNick);		// 사용자 닉네임으로  조회 (중복되면 안됨)
		domain.setWe_user_auth_yn("Y");			
		WeUser searchUser = null;

		JSONObject jsonObj  = null;
		BaseObjectBean resultBean = new BaseObjectBean();

		try {
			searchUser = (WeUser) entityService.getRowEntity(domain);

			if(searchUser != null ) {
				// 키 정보로 비밀번호가 같은지를 비교한다.
				logger.debug("#######넘어온 userPassword : " + userPassword);
				String PassValue = getEncryptPassword(searchUser.getWe_user_key(), userPassword);

				// 시스템 키로 현재 문자열을 암호화 하여 디비에 입력된 값과 같은지 비교한다.
				if(PassValue.equals(searchUser.getWe_user_pwd())) {
					String[] startId = searchUser.getWe_user_id().split("@");
					int idLen = startId[0].length();
					String userId = "";
					if(idLen < 8) {
						userId = startId[0].substring(0, idLen-3) + "***@" + startId[1];
					} else {
						userId = startId[0].substring(0, idLen-4) + "****@" + startId[1];
					}

					resultBean.setRtnMsg("* 가입한 ID는 <span class=\"keyword\">" + userId + "</span> 입니다.");
					resultBean.setRtnResult(1);
				} else {
					resultBean.setRtnMsg("* 비밀번호가 올바르지 않습니다.<br />다시 시도하세요.");
					resultBean.setRtnResult(0);
				}
			} else {
				resultBean.setRtnMsg("* 입력된 정보에 해당하는 아이디가  없습니다. <br />다시 시도하세요.");
				resultBean.setRtnResult(-1);
			}
		} catch (Exception e) {
			resultBean.setRtnMsg("* 입력된 정보에 해당하는 아이디가  없습니다. <br />다시 시도하세요.");
			resultBean.setRtnResult(-2);
			e.printStackTrace();
		}

		jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(resultBean));

		return jsonObj.toString();
	}

	@Override
	public void updateLastVisitDate(int userIdx) {
		WeProfile searchUser = new WeProfile(null, null);

		try {
			searchUser.setWe_user_idx(userIdx);
			WeProfile user = (WeProfile) entityService.getRowEntity(searchUser);
			user.setWe_visit_date(new Date());

			entityService.updateEntity(user);
		} catch (Throwable e) {
			new GliderwikiException("최종방문날짜 업데이트 중 오류발생", e);
		}
	}
}
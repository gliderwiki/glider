/**
 * @FileName  : AdimnLoginController.java
 * @Project   : NightHawk
 * @Date      : 2012. 4. 2. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 : 위키  서비스를 인스톨 한다. 
 */
package org.gliderwiki.install;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.FileUploader;
import org.gliderwiki.framework.util.SecretKeyPBECipher;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.util.SendMailSMTP;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.TempUploadVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



/**
 * @author yion
 *
 */
@Controller
public class InstallController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private String jdbcUrl;
	
	private String jdbcId;
	
	private String jdbcPassword;
	
	private String jdbcSchema;	//DB명 
	
	
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	
	public String getJdbcId() {
		return jdbcId;
	}
	
	public void setJdbcId(String jdbcId) {
		this.jdbcId = jdbcId;
	}
	
	public String getJdbcPassword() {
		return jdbcPassword;
	}
	
	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}
	

	public String getJdbcSchema() {
		return jdbcSchema;
	}

	public void setJdbcSchema(String jdbcSchema) {
		this.jdbcSchema = jdbcSchema;
	}



	/**
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/install", method = RequestMethod.GET)
	public ModelAndView installMain(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### installMain "); // = Step1
		
		String domain = getServerDomain(request);
		
		
		/*시스템 정보를 가져옵니다.*/
		String rtnCode = ShellCommands.execute("uname");		
		
		logger.debug("#### rtnCode : " +rtnCode);
		
		modelAndView.addObject("rtnCode", rtnCode.trim());
		modelAndView.addObject("domain", domain);
		modelAndView.setViewName("admin/install/install");
		return modelAndView;
	}

	/**
	 * @param request
	 * @return
	 */
	public String getServerDomain(HttpServletRequest request) {
		String path = request.getContextPath();
		logger.debug("path : " + path);
		String getProtocol = request.getScheme();
		logger.debug("getProtocol : " + getProtocol);
		String getDomain = request.getServerName();
		logger.debug("getDomain : " + getDomain);
		String getPort = Integer.toString(request.getServerPort());		
		String domain = getProtocol + "://" + getDomain + ":" + getPort + path	+ "/";
		logger.debug("domain : " + domain );
		return domain;
	}
	

	/**
	 * 사용자가 입력한 properties 생성 합니다.
	 * 1. Properties를  생성 하는 로직 입니다.
	 * 2. DATABASE 접속 후 SELECT 1; 정상처리 하는지 확인 까지
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/install/jdbcConnection", method = RequestMethod.GET)
	public ModelAndView jdbcConnection(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### installStep2 ");
		
		String jdbc_url = request.getParameter("jdbc_url");  //JDBC URL
		String jdbc_id  = request.getParameter("jdbc_id");   //JDBC ID
		String jdbc_pw  = request.getParameter("jdbc_pw");   //JDBC PW
		
		logger.debug("### jdbc_url " + jdbc_url);
		logger.debug("### jdbc_id " + jdbc_id);
		logger.debug("### jdbc_pw " + jdbc_pw);
		
    	/* 생성된 DB Information Properties의 정보로 DB Select를 해온다.*/
    	int result = 0;
    	
    	SingleDatasourceDao ds = new SingleDatasourceDao();
		result = ds.selectInfoJDBC(jdbc_url, jdbc_id, jdbc_pw);

    	logger.debug("***********************");
    	logger.debug("JDBC Connection Result   : "  + result);
    	logger.debug("***********************");
     
    	Map<String, Object> param = new HashMap<String, Object>();
    	
    	
    	if (1 == result) {
    		int startSchema =  jdbc_url.lastIndexOf("/");
    		
    		int endSchema = jdbc_url.indexOf("?");
    		if (endSchema < 1) {
    			endSchema = jdbc_url.length();
    		}
    		
    		logger.debug("###startSchema : " +startSchema);
    		logger.debug("###endSchema : " +endSchema);
    		
    		
    		// DB스키마 명을 세팅한다. 
    		String schema = jdbc_url.substring(startSchema+1, endSchema);
    		
    		logger.debug("###schema : " +schema);
    		
    		this.setJdbcUrl(jdbc_url);
    		this.setJdbcId(jdbc_id);
    		this.setJdbcPassword(jdbc_pw);
    		this.setJdbcSchema(schema);
    		
    		// /WEB-INF/spring/properties/jdbc 디렉토리 하위에 생성하기 위해 경로를 세팅한다. 
    		String jdbcPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "jdbc");
    		
    		logger.debug("jdbcPath : " + jdbcPath);
    		logger.debug("##root path : " + request.getServletContext().getRealPath("/"));

    		
    		InstallPropertyUtil property = new InstallPropertyUtil();
    		
    		// DB Information Properties 쓰기 (jdbc.properties)
    		result = property.CreateJDBCProperties(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), jdbcPath);
    		
    		if( result == 1) {
    			param.put("result", "SUCCESS"); //OK
    			param.put("status", result); //1
    		} else {
    			param.put("result", "FAIL"); //OK
    			param.put("status", result); //-1
    		}

    	} else {
    		param.put("result", "FAIL"); //OK
			param.put("status", result); 	 //-2
    	}
    	
		modelAndView.setViewName("admin/install/install");
		return new ModelAndView("json_").addObject("param", param);
	}
	
	/**
	 * 기본 테이블 생성 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/install/createTables", method = RequestMethod.GET)
	public ModelAndView createTables(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		
		String charType = request.getParameter("charType");  //CREATE할 테이블 타입 
		logger.debug("charType : " + charType);
		String strKor =  request.getParameter("strKor");  //strKor
		//String temp =  request.getParameter("strKor");  //strKor
		//String strKor = new String( temp.getBytes("ISO-8859-1"),"UTF-8");
				
		logger.debug("strKor : " + strKor);
		
		int result = 0;
		
		String tableInitPath = request.getSession().getServletContext().getRealPath(SystemConst.MYSQL_DB_PATH);

		// 테이블 이름 로드 - 드랍할 경우 대비   
		LoadTableData crateTable = new LoadTableData(tableInitPath);
		logger.debug("###### 테이블 가져온다 : " + crateTable.getAllTables());
		
		SingleDatasourceDao singleDao = new SingleDatasourceDao();
		
		try {
			// 캐릭터 셋 타입에 따라 다른 테이블형태를 create 한다. 
			result = singleDao.createTables(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), this.getJdbcSchema(), charType, crateTable.getAllTables(), tableInitPath);
		} catch (Exception e) {
			result = -1;
		}
		
		
		logger.debug("###테이블생성 result : " + result);
		String resultStr = "";
		if(result != -1) {
			// 테이블에 테스트 데이터를 인서트 한 후 정상적으로 출력되는지 확인한다.
			singleDao.insertLogToKor(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), this.getJdbcSchema(), strKor);
			resultStr = singleDao.selectKorLog(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword());			
		}
		
		int tableSize = 0;
		
		tableSize = crateTable.getAllTables().size();
		
		
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		
		param.put("result", result);
		param.put("resultStr", resultStr);
		param.put("tableSize", tableSize);
		
		modelAndView.setViewName("admin/install/install");
		return new ModelAndView("json_").addObject("param", param);
	}
	
	/**
	 * 테이블 정보를 초기화 한다. 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/install/dropTables", method = RequestMethod.GET)
	public ModelAndView dropTables(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### dropTables ");
		Map<String, Object> param = new HashMap<String, Object>();
		
		String tableInitPath = request.getSession().getServletContext().getRealPath(SystemConst.MYSQL_DB_PATH);
		// 테이블 이름 로드 하기  
		LoadTableData crateTable = new LoadTableData(tableInitPath);
		logger.debug("###### 테이블 가져온다 : " + crateTable.getAllTables());
		
		
		SingleDatasourceDao singleDao = new SingleDatasourceDao();
		
		
		// 드랍 버튼 클릭시 스키마에 존재하는 생성 테이블들을 삭제해준다. 
		int result = singleDao.dropTables(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), this.getJdbcSchema(), crateTable.getAllTables());		

		int tableSize = crateTable.getAllTables().size();
		
		logger.debug("### 리턴 result : " + result);
		logger.debug("### 리턴 tableSize : " + tableSize);
		
		if(result > 0){
			param.put("result", result);
			param.put("tableSize", tableSize);
		} else {
			param.put("result", result);
			param.put("tableSize", tableSize);
		}
		
		modelAndView.setViewName("admin/install/install");
		return new ModelAndView("json_").addObject("param", param);
	}
	
	
	
	/**
	 * 관리자 정보를 저장한다. 
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/install/loadData", method = RequestMethod.POST)
	public ModelAndView loadData(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		
		String adminMailId  = request.getParameter("adminMailId"); // 관리자 로그인 메일주소(ID)
		String adminpass 	= request.getParameter("adminpass");   // 관리자 로그인 비밀번
		String adminSite 	= request.getParameter("adminSite");   // 시스템 도메인 주
		String userMail 	= request.getParameter("userMail");    // 정보를 전달 받을 메일주
		String activeKey 	= request.getParameter("activeKey");   // 활성화를 위한 라이센스 
		
		logger.debug("adminMailId : " + adminMailId);
		logger.debug("adminpass : " + adminpass);
		logger.debug("adminSite : " + adminSite);
		logger.debug("userMail : " + userMail);
		logger.debug("activeKey :" + activeKey);
		
		String tableInitPath = request.getSession().getServletContext().getRealPath(SystemConst.MYSQL_DB_PATH);
		
		
		//TODO activeKey를 Gliderwiki.org 에 Rest로 전송해야 한다. 
		
		int result = 0;
		
		try {
			// 액티브 키로 어드민 암호화 한다.
			String passwd = SecretKeyPBECipher.setUserPassword(adminpass, activeKey);
			
			WeUser weUser = new WeUser();
			weUser.setWe_user_id(adminMailId);
			weUser.setWe_user_pwd(passwd);
			weUser.setWe_user_nick("ADMIN");
			weUser.setWe_user_name("어드민");
			weUser.setWe_user_key(activeKey);
			
			WeProfile weProfile = new WeProfile();
			weProfile.setWe_user_email(adminMailId);
			weProfile.setWe_user_site(adminSite);
			
						
			SingleDatasourceDao singleDao = new SingleDatasourceDao();
			
			// 어드민 사용자와 기본 데이터들을 저장한다. 
			result = singleDao.insertInitTableData(this.getJdbcUrl(), this.getJdbcId(), this.getJdbcPassword(), weUser, weProfile, tableInitPath); 
			
		} catch(Exception e) {
			result = -1;
			e.getMessage();
			e.printStackTrace();
		}
		

		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("result", result+"");

		
		modelAndView.setViewName("admin/install/install");
		return new ModelAndView("json_").addObject("param", param);
	}
	
	
	/**
	 * 이미지 업로드 테스트 및 퍼미션 조절 
	 * @param fileVo
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value = "/admin/installUpload", method = RequestMethod.POST)
	public ModelAndView profileUpload(@ModelAttribute("frmFile") TempUploadVo fileVo,
			HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView)  throws Throwable {
		logger.debug("##root path : " + request.getServletContext().getRealPath("/"));
		logger.debug("##request.getSession() : " + request.getSession().getServletContext().getRealPath("/resource/temp/"));
		String svcPath = request.getSession().getServletContext().getRealPath("/resource/temp/");
		String svcRealPath = request.getSession().getServletContext().getRealPath("/resource/real/");
		String svcDataPath = request.getSession().getServletContext().getRealPath("/resource/data/");
		
		
		String rtnCode = ShellCommands.execute("uname");		
		
		logger.debug("### System uname : " + rtnCode);
		logger.debug("### startsWith uname : " + rtnCode.startsWith("Linux"));
		
		if(rtnCode.startsWith("Linux") || rtnCode.equalsIgnoreCase("linux")) {
			logger.debug("############ Linux 퍼미션 조정 ############");
			ShellCommands.execute("chmod -R 755 " + svcPath);
			ShellCommands.execute("chmod -R 755 " + svcRealPath);
			ShellCommands.execute("chmod -R 755 " + svcDataPath);
		}
		

		//업로드 날짜 및 파일명 구성
		String today = DateUtil.getToday();

		double maxSize = 10d;

		// 파일 객체, 사용자 아이디, 오늘날짜, 파일 업로드 사이즈
		TempUploadVo tempFile = FileUploader.uploadTempFile(fileVo.getFile(), svcPath, "1", today, maxSize);

		logger.debug("tempFile : " + tempFile.toString());


		Map<String, String> param = new HashMap<String, String>();
		if(tempFile.isUploaded()) {
			// 임시 파일이기 때문에 DB 인서트는 하지 않음 
			param.put("result", "1");
	    	param.put("msg", "성공");
	    	param.put("realFileName", tempFile.getFileRealName());
	    	param.put("saveFileName", tempFile.getFileSaveName());
	    	param.put("thumbPath", tempFile.getThumbPath());
	    	param.put("thumbName", tempFile.getThumbName());
	    	param.put("filePath", tempFile.getFilePath());
	    	param.put("fileSize", tempFile.getFileSize()+"");
	    	param.put("tmpsrc", svcPath);
		} else {
			param.put("result", "-1");
	    	param.put("msg", "파일 업로드에 실패 하였습니다.");
	    	param.put("realFileName", tempFile.getFileRealName());
		}

		logger.debug("param : "  + param.toString());

		return new ModelAndView("json_").addObject("param", param);
	}

	
	/**
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 * @throws Throwable
	 */
	@RequestMapping(value="/admin/install/mailSend", method = RequestMethod.POST)
	public ModelAndView mailSend(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		logger.debug("### installStep5 ");
		String testUserMail	= StringUtil.strNull(request.getParameter("testUserMail"));
		String mailUserId 		= StringUtil.strNull(request.getParameter("mailUserId"));
		String mailUserPassword = StringUtil.strNull(request.getParameter("mailUserPassword"));
		String mailUserKey 		= StringUtil.strNull(request.getParameter("mailUserKey"));
		String smtpHostName 	= StringUtil.strNull(request.getParameter("smtpHostName"));
		String smtpServerPort   = StringUtil.strNull(request.getParameter("smtpServerPort"));
		String mailCharset 		= StringUtil.strNull(request.getParameter("mailCharset"));
		String siteDomain 		= StringUtil.strNull(request.getParameter("siteDomain"));
		String siteOwner 		= StringUtil.strNull(request.getParameter("siteOwner"));
		
		
		// /WEB-INF/spring/properties/config 하위 디렉토리에 mail.properties 파일을 저장한다. 
		String configPath = request.getSession().getServletContext().getRealPath(SystemConst.PROPERTY_FULL_PATH + "config");
		
		logger.debug("### 원래 패스워드 :" + mailUserPassword);
		logger.debug("### testUserMail :" + testUserMail);
		logger.debug("### mailUserId :" + mailUserId);
		logger.debug("### smtpHostName :" + smtpHostName);
		logger.debug("### smtpServerPort :" + smtpServerPort);
		logger.debug("### mailCharset :" + mailCharset);
		logger.debug("### siteDomain :" + siteDomain);
		logger.debug("### siteOwner :" + siteOwner);
		
		// 패스워드 암호화 
		String passwd = SecretKeyPBECipher.setUserPassword(mailUserPassword, mailUserKey);
		logger.debug("### 변환 패스워드 :" + passwd);
		
		InstallPropertyUtil property = new InstallPropertyUtil();
		
		int result = property.CreateMailProperties(mailUserId, passwd, mailUserKey, smtpHostName, smtpServerPort, mailCharset, siteDomain, siteOwner, configPath);
		
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		String domain = getServerDomain(request);
		
		String adminUrl =  domain+"admin/wikiadminlogin";
				
		if(result == 1 ) {
			
			// 메일을 전송한다. 
			String emailMsgTxt = "안녕하세요. " + siteOwner +" 입니다. <br><br>" +
					"관리자 모드에서 보내는 테스트 메일 입니다.<br><br>" +
					"어드민 모드는 " + adminUrl + "을 통해서 입력한 계정으로 로그인 할 수 있습니다.";
			

			String emailTitle = siteOwner + " Wiki 안내 메일입니다.";		// 제목
						
			try {
				SendMailSMTP sender = new SendMailSMTP();
				result = sender.sendSimpleMail(testUserMail, emailTitle, emailMsgTxt, request);

				logger.debug("###result : " + result);
				
				if(result > 0) {
					
					// config 프로퍼티를 생성한다. 
					property.CreateCionfigProperties(mailUserId, mailUserKey, siteDomain, configPath);
					
					param.put("result", "SUCCESS");
					param.put("status", SystemConst.CALL_SUCCESS);
					param.put("rtnResult", result);
					
				} else {
					param.put("result", "FAIL");
					param.put("status", SystemConst.CALL_FAIL);
					param.put("rtnResult", -1);
				}
				
				
			} catch (UnsupportedEncodingException e) {
				param.put("result", "FAIL");
				param.put("status", SystemConst.CALL_FAIL);
				param.put("rtnResult", -2);
			}

		} else { 
			// Properties 생성 중 에러 발생 
			param.put("result", "FAIL");
			param.put("status", SystemConst.CALL_FAIL);
			param.put("rtnResult", -3);
		}
				
		return new ModelAndView("json_").addObject("param", param);
	}
}

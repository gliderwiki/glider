package org.gliderwiki.web.wiki.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.util.GliderFileUtils;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.domain.Attachment;
import org.gliderwiki.web.domain.AttachmentCategory;
import org.gliderwiki.web.domain.AttachmentType;
import org.gliderwiki.web.domain.WeProfile;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.user.service.UserConnectionService;
import org.gliderwiki.web.vo.AddFriendVo;
import org.gliderwiki.web.vo.UploadFileVo;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


/*
 * 컨트롤러 메소드 prefix 명명규칙
 * 단일건 오브젝트 조회 : getRow + 테이블명 (업무명) +  키조건
 *                   : select + 테이블명 (업무명) +  키조건
 *                   : selectOne + 테이블명 (업무명) +  키조건
 * ex : userInfoVo = loginService.getRowUserInfoById();
 *      userInfoVo = loginService.selectUserInfoById();
 *      userInfoVo = loginService.selectOneUserInfoById();
 *
 * 다건 오브젝트 조회 : getList + 테이블명 (업무명) +  키조건
 * ex : userInfoBeanList = loginService.getListUserInfoById();
 *
 * update, delete, insert 구문은 prefix + 테이블명(업무명) + 키조건으로 작성한다.
 * ex : int result = loginService.insertNewMember();
 *      int result = loginService.updateUserInfoByCustNo()
 *
 * Transaction 처리를 해야 할 경우 prefix 를 tx 로 주고 처리하면 된다.
 *
 * Service 명명 규칙 : 업무명 + Service,  업무명 + ServiceImpl 의 형태로 작성한다.
 * Dao 명명 규칙 : 업무명 + Dao,  업무명 + DaoImpl 의 형태로 작성한다.
 * //target = new String( target.getBytes("ISO-8859-1"),"UTF-8");
 */

@Controller
public class CommonController {

	Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private EntityService entityService;

	// 사용자 프로필 조회용
	@Autowired
	private UserConnectionService userConnectionService;


	@Autowired
	private CommonService commonService;


//	@Resource(name = "redisTemplate")
//	private RedisTemplate<String, String> redisTemplate;
//
//	@Resource(name = "redisTemplate")
//	private RedisTemplate<String, List<WeSpace>> spoaceRedisTemplate;

	@Value("#{config['file.maxSize']}")
	String maxSize;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelAndView modelAndView, ModelMap model) {

		// 페이지 이동
		modelAndView.setViewName("list");
		return modelAndView;
	}


	@RequestMapping(value = "/sysinfo", method = RequestMethod.GET)
	public ModelAndView sysinfo(ModelAndView modelAndView) throws Exception {
		logger.debug("##### sysinfo");

		String jvm = System.getProperty("java.version");						// 자바 버전
		String vendor = System.getProperty("java.vendor");						// 자바 공급자
		String url = System.getProperty("java.vendor.url");						// 공급자 주소
		String home  = System.getProperty("java.home");							// 자바 설치 디렉토리
		String classVersion  = System.getProperty("java.class.version");		// 자바 클래스 버전

		String osName = System.getProperty("os.name");							// os 이름
		String osArch = System.getProperty("os.arch");							// os 아키텍쳐
		String osVersion = System.getProperty("os.version");					// os 버전 정보

		String fileSeparator = System.getProperty("file.separator");			// 파일구분자
		String pathSeparator = System.getProperty("path.separator");			// 경로 구분자
		String lineSeparator = System.getProperty("line.separator");			// 행 구분자
		String userDir = System.getProperty("user.dir");						// 현재 작업 디렉토리
		String userHome = System.getProperty("user.home");						// 사용자홈 디렉토리
		String userName = System.getProperty("user.name");						// 사용자계정
		String fileEncoding = System.getProperty("file.encoding");				// 파일인코딩
		String tmpDir = System.getProperty("java.io.tmpdir");					// 임시경로
		Locale loc = Locale.getDefault();										// 로케일

		logger.debug("#loc:" + loc);

		String enc = new java.io.OutputStreamWriter(System.out).getEncoding();
		logger.debug("=======================================");
		logger.debug("#default encoding = " + enc);
		logger.debug("#fileEncoding : "  + fileEncoding);
		logger.debug("#tmpDir : "  + tmpDir);

		logger.debug("#jvm : "  + jvm);
		logger.debug("#vendor : "  + vendor);
		logger.debug("#url : "  + url);
		logger.debug("#home : "  + home);
		logger.debug("#classVersion : "  + classVersion);
		logger.debug("#osName : "  + osName);
		logger.debug("#osArch : "  + osArch);
		logger.debug("#osVersion : "  + osVersion);
		logger.debug("#fileSeparator : "  + fileSeparator);
		logger.debug("#pathSeparator : "  + pathSeparator);
		logger.debug("#lineSeparator : "  + lineSeparator);
		logger.debug("#userDir : "  + userDir);
		logger.debug("#userHome : "  + userHome);
		logger.debug("#userName : "  + userName);



		modelAndView.addObject("enc", enc);
		modelAndView.addObject("fileEncoding", fileEncoding);
		modelAndView.addObject("tmpDir",tmpDir);
		modelAndView.addObject("jvm", jvm);
		modelAndView.addObject("vendor", vendor);
		modelAndView.addObject("url", url);
		modelAndView.addObject("home", home);
		modelAndView.addObject("classVersion", classVersion);
		modelAndView.addObject("osName", osName);
		modelAndView.addObject("osArch", osArch);
		modelAndView.addObject("osVersion", osVersion);
		modelAndView.addObject("fileSeparator", fileSeparator);
		modelAndView.addObject("pathSeparator", pathSeparator);
		modelAndView.addObject("lineSeparator", lineSeparator);
		modelAndView.addObject("userDir", userDir);
		modelAndView.addObject("userHome", userHome);
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("loc", loc);

		// 페이지 이동
		modelAndView.setViewName("sysinfo");
		return modelAndView;
	}

	/**
	 * 파일업로드 페이지 이동
	 *
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/UploadFile", method = RequestMethod.GET)
	public ModelAndView uploadFileView(ModelAndView modelAndView) {

		modelAndView.setViewName("temp/fileUpload");

		return modelAndView;
	}

	/**
	 * 파일업로드
	 *
	 * @param uploadFileVo
	 * @param result
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/UploadFile", method = RequestMethod.POST)
	public ModelAndView uploadFile(@ModelAttribute("concat") UploadFileVo uploadFileVo, BindingResult result,
			ModelAndView modelAndView) {

		MultipartFile file = uploadFileVo.getFileData();


		File f = new File(
				"D:\\develop2\\01.NightHawk\\NightHawk-workspace\\NightHawk\\WebContent\\resource\\sample\\temp\\"
						+ file.getOriginalFilename());

		try {
			file.transferTo(f);
			System.out.println("파일업로드 완료");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.setViewName("temp/fileUpload");

		return modelAndView;
	}

	/**
	 * 파일업로드
	 *
	 * @param uploadFileVo
	 * @param result
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/multiUploadFile", method = RequestMethod.POST)
	public void multiUploadFile(@ModelAttribute("concat") UploadFileVo uploadFileVo, BindingResult result) {

		System.out.println("1 :" + uploadFileVo.getFileData());
		System.out.println("2 :" + uploadFileVo.getFile_upload());
		System.out.println("3 :" + uploadFileVo.getFileNm());
		/*
		 * MultipartFile file = uploadFileVo.getFileData();
		 *
		 * System.out.println("파일이름 :"+file.getName());
		 * System.out.println("파일크기 :"+file.getSize());
		 * System.out.println("파일존재 :"+file.isEmpty());
		 * System.out.println("파일이름 :"+file.getOriginalFilename());
		 *
		 * File f = new File(
		 * "D:\\develop2\\01.NightHawk\\NightHawk-workspace\\NightHawk\\WebContent\\resource\\sample\\temp\\"
		 * +file.getOriginalFilename());
		 *
		 * try{ file.transferTo(f); System.out.println("파일업로드 완료"); }catch
		 * (IllegalStateException e) { e.printStackTrace(); }catch (Exception e)
		 * { e.printStackTrace(); }
		 */
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String uploadForm(ModelMap map) {
		return "upload/uploadForm";
	}

	/**
	 * 멀티파일 업로드 메서드
	 *
	 * @author bluepoet
	 * @param request
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadFile(HttpServletRequest request) throws Throwable {

		logger.debug("uploadController 진입!");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		int result = 0;
		List<MultipartFile> files = multipartRequest.getFiles("uploadFile");

		int uploadTotalSize = files.size();

		logger.debug("파일 사이즈 : {}", uploadTotalSize);

		for (int i = 0; i < uploadTotalSize; i++) {
			MultipartFile file = files.get(i);

			Attachment attachment = new Attachment();
			String attFileName = file.getOriginalFilename();

			attachment.setCategory(AttachmentCategory.PUBLIC);
			attachment.setUser_id("bluepoet");
			attachment.setBoard_no(2);
			attachment.setFile_name(attFileName.substring(0, attFileName.lastIndexOf(".")));
			attachment.setExtension(attFileName.substring(attFileName.lastIndexOf(".") + 1, attFileName.length()));
			attachment.setCreated_date(new Date());

			File destFile = GliderFileUtils.getDestFile(attachment.getFilePath());

			try {
				GliderFileUtils.forceMKParentDir(destFile);
				file.transferTo(destFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// DB 데이터 저장
			result = entityService.insertEntity(attachment);
		}

		logger.debug("업로드 후 결과 : {} ", result);

		return "upload/uploadForm";
	}

	/**
	 * 파일다운로드, 파일 패스는 자기 로컬패스에 있는 파일로 지정해서 테스트하세요
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
		logger.debug("다운로드 파일 이름 : " + fileName);
		GliderFileUtils.downloadFile(request, response,
				new File(request.getSession().getServletContext().getRealPath("/resource/temp/" + fileName)),
				AttachmentType.DOWNLOAD);
	}

	/**
	 * 이미지 업로드 파일 미리보기용
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/attachments/{fileName}", method = RequestMethod.GET)
	public void imgPreView(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName, @RequestParam("ext") String ext) throws IOException {
		GliderFileUtils.downloadFile(request, response, new File(request.getSession().getServletContext().getRealPath("/resource/temp/" + fileName+"."+ext)),
				AttachmentType.VIEW);
	}

	@RequestMapping(value = "/ajax/preUpload", method = RequestMethod.POST)
	public void preImgUpload(HttpServletRequest request, Model model) throws IOException {
		logger.debug("root path : " + request.getServletContext().getRealPath("/"));
		String rootPath = request.getServletContext().getRealPath("/");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		String userAgent = request.getHeader("User-Agent");

		logger.debug("userAgent : " + userAgent);

		List<MultipartFile> files = multipartRequest.getFiles("uploadFile");
		MultipartFile tempUploadFile = null;

		if (userAgent.indexOf("MSIE") > -1) { // MS IE (보통은 6.x 이상 가정)
			tempUploadFile = files.get(files.size() - 2);

			logger.debug("tempUploadFile : " + tempUploadFile);
		} else { // 모질라나 오페라,크롬
			tempUploadFile = files.get(files.size() - 1);
		}
		logger.debug("미리보기 파일 이름 : " + tempUploadFile.getOriginalFilename());

		// File targetTempFile =
		// GliderFileUtils.getRootDestFile("/temp/"+tempUploadFile.getOriginalFilename());
		File targetTempFile = new File(rootPath + "/resource/temp/" + tempUploadFile.getOriginalFilename());

		try {
			tempUploadFile.transferTo(targetTempFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/temp/dwrtest", method = RequestMethod.GET)
	public String dwr(ModelMap map) {
		return "temp/dwr/dwrtest";
	}

	/**
	 * 파일업로드 페이지 이동
	 *
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping(value = "/syntax", method = RequestMethod.GET)
	public ModelAndView syntax(ModelAndView modelAndView) {

		modelAndView.setViewName("temp/syntax");

		return modelAndView;
	}

	/**
	 * 에디터 히스토리
	 *
	 * @return
	 */
	@RequestMapping(value = "/editor", method = { RequestMethod.GET, RequestMethod.POST })
	public String editorHistoryList() {
		logger.debug("welcome @ /editorHistory");
		return "temp/editor/editorHistory";
	}

	/**
	 * 에디터 버젼
	 *
	 * @return
	 */
	@RequestMapping(value = "/editor/{version}", method = { RequestMethod.GET, RequestMethod.POST })
	public String editorV01(@PathVariable("version") int version) {
		String versionName = "v";
		if(version < 10){
			versionName +="0";
		}
		return "temp/editor/"+versionName+ version;
	}

	@RequestMapping(value = "/calendar", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView calendar(ModelAndView modelAndView) {
		modelAndView.setViewName("calendar/calendar");
		return modelAndView;
	}


	@RequestMapping(value = "/common/userinfo", method = RequestMethod.GET )
	public ModelAndView userinfo(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		Integer weUserIdx = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("userIdx"), "0"));
		logger.debug("#### weUserIdx : " + weUserIdx);

		if(weUserIdx == 0) {
			throw new GliderwikiException("사용자 전달값이 올바르지  않습니다.");
		}

		Map<String, Object> param = new HashMap<String, Object>();


		try {
			WeUser userInfo = commonService.getUserInfo(weUserIdx);

			param.put("result", "SUCCESS");
			param.put("status", SystemConst.CALL_SUCCESS);
			param.put("userId", userInfo.getWe_user_id());
			param.put("nick", userInfo.getWe_user_nick());
			param.put("weUserIdx", weUserIdx+"");

		} catch (Exception e) {
			param.put("result", "FAIL");
			param.put("status", SystemConst.CALL_FAIL);
			param.put("userId", "");
			param.put("nick", "");
			param.put("weUserIdx", weUserIdx+"");
		}

		logger.debug("## param : " + param.toString());

		return new ModelAndView("json_").addObject("param", param);

	}

	@RequestMapping(value="/user/viewProfile", method = RequestMethod.GET)
	public ModelAndView userProfile(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) throws Throwable {
		Integer weUserIdx = Integer.parseInt(StringUtil.strNullToSpace(request.getParameter("weUserIdx"), "0"));

		logger.debug("### weUserIdx : " + weUserIdx);

		Map<String, Object> param = new HashMap<String, Object>();

		// 해당 사용자의 프로필 정보 조회
		List<AddFriendVo> myConnectionList = null;


		try {
			myConnectionList = userConnectionService.getConnectionToMe(weUserIdx);

			WeUser weUser = null;			// 타겟 사용자 기본정보
			WeProfile weProfile = null;		// 타겟 사용자 프로필 정보

			weUser = commonService.getUserInfo(weUserIdx);					// 회원 기본 정보 조회
			weProfile = commonService.getUserProfileInfo(weUserIdx);		// 회원 프로파일 정보 조회
			String lastVisitDate = weProfile.getWe_visit_date().toString();
			
			logger.debug("### lastVisitDate : " + lastVisitDate);
			
			
			param.put("result", "SUCCESS");
			param.put("status", SystemConst.CALL_SUCCESS);
			param.put("connectionListSize", myConnectionList.size()+"");
			param.put("weProfile", weProfile);
			param.put("weUser", weUser);
			param.put("lastVisitDate", lastVisitDate);
			param.put("weUserIdx", weUserIdx+"");

		} catch (Exception e) {
			param.put("result", "FAIL");
			param.put("status", SystemConst.CALL_FAIL);
			param.put("connectionListSize", "");
			param.put("weProfile", null);
			param.put("weUser", null);
			param.put("weUserIdx", weUserIdx+"");
		}

		return new ModelAndView("json_").addObject("param", param);
	}


} 
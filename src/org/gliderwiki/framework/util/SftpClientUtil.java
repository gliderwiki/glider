package org.gliderwiki.framework.util;

import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.gliderwiki.framework.util.constant.FtpConstant;


import com.sshtools.j2ssh.SftpClient;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.transport.HostKeyVerification;
import com.sshtools.j2ssh.transport.TransportProtocolException;
import com.sshtools.j2ssh.transport.publickey.SshPublicKey;

public class SftpClientUtil {

	Logger logger = Logger.getLogger(this.getClass());
	
	private SshClient client = null;
	private PasswordAuthenticationClient auth = null;
	private SftpClient sftp = null;
	
	/**
	 * ftr 서버 config 
	 */
	private String ftpIP = "111.111.111.111";
	private int  ftpPort = 1229;
	private String id = "root";
	private String pw = "pass";
	private String path = "";
	
	public SftpClientUtil(){
		
	}

	
	/**
	 * 커넥션 맺고 파일 업로드 한 후 결과값 리턴 기본 
	 * @param uploadBean
	 * @param saveFileName
	 * @return
	 * @throws Exception
	 */
	
	/*
	public boolean ftpFileUpload(BlogBean uploadBean, String saveFileName) throws Exception {
		logger.debug("connect() 들어옵니다 \n\n\n\n\n\n");
		boolean isConnect = false;
		
		try {
			client = new SshClient();
			client.setSocketTimeout(70000);
			client.connect(ftpIP, ftpPort, new HostKeyVerification() {
				@Override
				public boolean verifyHost(String s, SshPublicKey sshpublickey) throws TransportProtocolException {
					return true;
				}
			});
			auth = new PasswordAuthenticationClient();
			auth.setUsername(id);
			auth.setPassword(pw);
			int result = client.authenticate(auth);
			if (result != AuthenticationProtocolState.COMPLETE) {
				throw new Exception("### Login to " + ftpIP + ":"+ftpPort +"\n" + id + "/" + pw + " failed");
			}
			
			sftp = client.openSftpClient();
			sftp.cd(path);
			
			logger.debug("#### pwd() : " + pwd());
			
			FileInputStream fis = (FileInputStream) uploadBean.getFile().getInputStream();		// 업로드할 File 생성
			
			
			
			sftp.put(fis, saveFileName);
			isConnect = true;
		} catch (Exception e) {
			isConnect = false;
			logger.debug(e);
			throw e;
		} finally {
			logout();
		}
		return isConnect;
	}
	*/
	
	/**
	 * 블로그 파일  업로드시에 쓰는 sftp 파일 업로드 
	 * @param blogBean
	 * @param saveFileName
	 * @param realFileName
	 * @param extendname
	 * @return
	 * @throws Exception
	 */
	 
	/*
	public TempUploadBean ftpFileUpload(BlogBean blogBean, String saveFileName, String realFileName, String extendname) throws Exception {
		logger.debug("connect() 들어옵니다 \n\n\n\n\n\n");
		TempUploadBean tempUploadBean = new TempUploadBean();
		
		
		boolean isResult = false;
		
		try {
			
			// SshClient 생성 
			client = new SshClient();
			
			client.setSocketTimeout(70000);
			client.connect(ftpIP, ftpPort, new HostKeyVerification() {
				@Override
				public boolean verifyHost(String s, SshPublicKey sshpublickey) throws TransportProtocolException {
					return true;
				}
			});
			auth = new PasswordAuthenticationClient();
			auth.setUsername(id);
			auth.setPassword(pw);
			
			int result = client.authenticate(auth);
			
			logger.debug("################ SFTP 인증 결과 : " + result + "###################");
			if (result != AuthenticationProtocolState.COMPLETE) {
				isResult = false;
				throw new Exception("### Login to " + ftpIP + ":"+ftpPort +"\n" + id + "/" + pw + " failed");
			}
			
			String ftpPath = path; // 현재 이동할 경로 
			
			String moveSrc = "";		// 파일 생성시 이동할 경로
			
			sftp = client.openSftpClient();
			// 경로로 이동 
			sftp.cd(ftpPath);
			
			logger.debug("#### 현재 경로  : " + pwd());
			
			
			 // 디렉토리 존재 여부 판별. 즉, 최종의 디렉토리 구조는 group / 날짜:2011-08-09 / user_id:cafeciel@xxx.com / temp / 
			 // 만약 확장자가 이미지이면 temp 하위에 thumb 까지 만든다.  
			 
			boolean isRootPath = this.cd(FtpConstant.FTP_BLOG_PATH); // 사용자 정의 디렉토리 구조 (현재 ../2011-08-10  가 생성되었는지) 
			logger.debug("### isRootPath : " + isRootPath);
			
			// 01. group 디렉토리 검사 
			if(!isRootPath) {
				// 경로가 존재하지 않을 경우 경로를 만들어 준다. 하지만 최초 한번 만들면 그 이후부터는 만들지 않는다.
				logger.debug("************************* group root 경로를 만듭니다 *****************************");
				sftp.mkdir(FtpConstant.FTP_BLOG_PATH);				
			}
			
			// 블로그 기본 경로로 이동 
			sftp.cd(ftpPath+"/"+FtpConstant.FTP_BLOG_PATH);
			
			logger.debug("#### group 디렉토리 현재 경로  : " + pwd());
			
			String nowDate = DateUtil.getToday();
			logger.debug("nowDate : " + nowDate);
			boolean isDatePath = this.cd(nowDate);
			logger.debug("### isDatePath : " + isDatePath);
			
			
			// 02. 오늘 날짜  디렉토리 검사 
			if(!isDatePath) {
				// 경로가 존재하지 않을 경우 경로를 만들어 준다. 하지만 일자별로 최초 한번 만들면 그 이후부터는 만들지 않는다.
				logger.debug("************************* 오늘 날짜의 경로를 만듭니다 *****************************");
				sftp.mkdir(nowDate);				
			}
			
			// 날짜 경로로 이동
			sftp.cd(ftpPath+"/"+FtpConstant.FTP_BLOG_PATH+"/"+nowDate);
			logger.debug("#### nowDate 디렉토리 현재 경로  : " + pwd());
			
			String userId = blogBean.getUserId();
			boolean isUserIdPath = this.cd(userId); // 사용자 아이디  디렉토리 구조 (현재 ../user_id 가 생성되었는지) 
			logger.debug("### isUserIdPath : " + isUserIdPath);
			
			// 03. 사용자 아이디  디렉토리 검사 
			if(!isUserIdPath) {
				// 경로가 존재하지 않을 경우 경로를 만들어 준다. 하지만 일자별, 사용자별 최초 한번 만들면 그 이후부터는 만들지 않는다.
				logger.debug("************************* 사용자 아이디로 경로를 만듭니다 *****************************");
				sftp.mkdir(userId);				
			}
			
			// 사용자아이디 경로로 이동
			sftp.cd(ftpPath+"/"+FtpConstant.FTP_BLOG_PATH+"/"+nowDate+"/"+userId);
			logger.debug("#### 사용자 아이디의 디렉토리 현재 경로  : " + pwd());
			
			
			String temp = FtpConstant.FTP_TEMP_PATH;
			boolean isTempPath = this.cd(temp); // 임시  디렉토리 구조 (현재 ../temp 가 생성되었는지) 
			logger.debug("### isTempPath : " + isTempPath);
			
			// 04. 임시  디렉토리 검사 
			if(!isTempPath) {
				// 경로가 존재하지 않을 경우 경로를 만들어 준다. 하지만 일자별, 사용자별, temp 폴더를 최초 한번 만들면 그 이후부터는 만들지 않는다.
				logger.debug("************************* 사용자 아이디로 경로를 만듭니다 *****************************");
				sftp.mkdir(temp);				
			}
			
			// 사용자아이디 경로로 이동
			sftp.cd(ftpPath+"/"+FtpConstant.FTP_BLOG_PATH+"/"+nowDate+"/"+userId+"/"+temp);
			logger.debug("#### 임시 디렉토리 현재 경로  : " + pwd());
						
			
			
			FileInputStream fis = (FileInputStream) blogBean.getFile().getInputStream();		// 업로드할 File 생성
			
			sftp.put(fis, saveFileName);
			
			
			String thumbnailYn = StringUtil.getThumbnailYn(extendname);
			logger.debug("FTP 업로드 파일 thumbnailYn 여부 : " + thumbnailYn);
			
			if(thumbnailYn.equals("Y")) {
				// 섬네일 폴더가 있는지 먼저 검사 
				String thumb = FtpConstant.FTP_THUMB_PATH;
				boolean isThumbPath = this.cd(thumb); // 임시  디렉토리 구조 (현재 ../temp 가 생성되었는지) 
				logger.debug("### isThumbPath : " + isThumbPath);
				
				// 04. 임시  디렉토리 검사 
				if(!isThumbPath) {
					// 경로가 존재하지 않을 경우 경로를 만들어 준다. 하지만 일자별, 사용자별, temp , thumb 폴더를 최초 한번 만들면 그 이후부터는 만들지 않는다.
					logger.debug("************************* 섬네일대상 일 경우  경로를 만듭니다 *****************************");
					sftp.mkdir(thumb);				
				}
				
				// 사용자아이디 경로로 이동
				sftp.cd(ftpPath+"/"+FtpConstant.FTP_BLOG_PATH+"/"+nowDate+"/"+userId+"/"+temp+"/"+thumb);
				logger.debug("#### 임시 디렉토리 현재 경로  : " + pwd());
							
				
				
				FileInputStream thumbFis = (FileInputStream) blogBean.getFile().getInputStream();		// 업로드할 File 생성
				
				sftp.put(thumbFis, saveFileName);
			}
			
			
			isResult = true;
			
			tempUploadBean.setUserId(userId);
			tempUploadBean.setUploadType(FtpConstant.FTP_BLOG_PATH);
			tempUploadBean.setTempSaveName(saveFileName);
			tempUploadBean.setTempRealName(realFileName);
			tempUploadBean.setTempPath(ftpPath+"/"+FtpConstant.FTP_BLOG_PATH+"/"+nowDate+"/"+userId+"/"+temp);  // temp 파일 절대경로 
			tempUploadBean.setTempExtName(extendname);
			tempUploadBean.setThumbYn(thumbnailYn);	// 섬네일 유무 
			tempUploadBean.setIsUploaded(isResult);
			
		} catch (Exception e) {
			isResult = false;
			tempUploadBean.setIsUploaded(isResult);
			logger.debug(e);
			throw e;
		} finally {
			logout();		// 로그아웃 
		}
		
		
		return tempUploadBean;
		
	}

	 */

	
	
	/**
	 * 프로필 사진 업로드에 쓰는 sftp 파일 업로드  uploadType (user: 사용자 프로필 이미지 , rep: 그룹 대표 이미지)
	 * @param uploadBean
	 * @param saveFileName
	 * @param realFileName
	 * @param extendname
	 * @param uploadType
	 * @return
	 * @throws Exception
	 */
	
	
	/**
	 * 이미지 업로드 경로
	 * 
	 * 사용자 프로필
	 * user / 사용자아이디(minroom@naver.com)/ temp(target) / 1.jpg 
	 *                                                   / thumb / 1_thumb.jpg
	 * 
	 * 그룹대표 이미지
	 * rep / 그룹인덱스 / temp(target) / 2.jpg
	 *                               / thumb / 2_thumb.jpg 
	 * 
	 * temp 임시 폴더를 실제 업로드 완료후 target으로 폴도명 변경
	 *          
	 */
	
	/*
	public TempUploadBean ftpFileUpload(UploadBean uploadBean, String saveFileName, String realFileName, String extendname , String uploadType) throws Exception {
		
		

		logger.debug("connect() 들어옵니다 \n\n\n\n\n\n");
		TempUploadBean tempUploadBean = new TempUploadBean();
		
		boolean isResult = false;
		
		try {
			
			// SshClient 생성 
			client = new SshClient();
			
			client.setSocketTimeout(70000);
			client.connect(ftpIP, ftpPort, new HostKeyVerification() {
				@Override
				public boolean verifyHost(String s, SshPublicKey sshpublickey) throws TransportProtocolException {
					return true;
				}
			});
			auth = new PasswordAuthenticationClient();
			auth.setUsername(id);
			auth.setPassword(pw);
			
			int result = client.authenticate(auth);
			
			logger.debug("################ SFTP 인증 결과 : " + result + "###################");
			if (result != AuthenticationProtocolState.COMPLETE) {
				isResult = false;
				throw new Exception("### Login to " + ftpIP + ":"+ftpPort +"\n" + id + "/" + pw + " failed");
			}
			
			String ftpPath = path;		// 현재 이동할 경로 
			
			String moveSrc = "";		// 파일 생성시 이동할 경로
			
			sftp = client.openSftpClient();
			// 경로로 이동 
			sftp.cd(ftpPath);
			
			logger.debug("#### 현재 경로  : " + pwd());
			
			boolean isRootPath;
			
			
			
			 // 사용자 프로필인가 그룹 대표이미지인가 구분
			 
			if (uploadType.equals("user")) {
				
				isRootPath = this.cd(FtpConstant.FTP_USER_PATH); // 사용자 정의 디렉토리 구조 (현재 ../user  가 생성되었는지) 

				// 폴더 생성여부 확인 없으면 생성
				if(!isRootPath) {
					logger.debug("************************* user root 경로를 만듭니다 *****************************");
					sftp.mkdir(FtpConstant.FTP_USER_PATH);				
				}
				
				// 사용자의 기본 경로로 이동 
				sftp.cd(ftpPath+"/"+FtpConstant.FTP_USER_PATH);
				
				String userId = uploadBean.getUserID();
				boolean isUserIdPath = this.cd(userId); // 사용자 아이디  디렉토리 구조 (현재 ../user_id 가 생성되었는지) 
				
				// 사용자 아이디 디렉토리 검사 
				if(!isUserIdPath) {
					logger.debug("************************* 사용자 아이디로 경로를 만듭니다 *****************************");
					sftp.mkdir(userId);				
				}
				
				// 사용자 아이디 경로로 이동
				sftp.cd(ftpPath+"/"+FtpConstant.FTP_USER_PATH+"/"+userId);
				logger.debug("#### 사용자 아이디의 디렉토리 현재 경로  : " + pwd());
				
				
				String temp = FtpConstant.FTP_TEMP_PATH;
				boolean isTempPath = this.cd(temp); // 임시  디렉토리 구조 (현재 ../temp 가 생성되었는지) 
				// 임시 디렉토리 검사 
				if(!isTempPath) {
					// 경로가 존재하지 않을 경우 경로를 만들어 준다. 하지만 일자별, 사용자별, temp 폴더를 최초 한번 만들면 그 이후부터는 만들지 않는다.
					logger.debug("************************* 임시 경로를 만듭니다 *****************************");
					sftp.mkdir(temp);				
				}
				
				// 임시 디렉토리  경로로 이동
				sftp.cd(ftpPath+"/"+FtpConstant.FTP_USER_PATH+"/"+userId+"/"+temp);
				logger.debug("#### 임시 디렉토리 현재 경로  : " + pwd());
				
				// 파일 업로드 
				FileInputStream fis = (FileInputStream) uploadBean.getFile().getInputStream();
				sftp.put(fis, saveFileName);
				
				String thumbnailYn = StringUtil.getThumbnailYn(extendname);
				logger.debug("#### 현재 경로  : " + pwd());
				
				if(thumbnailYn.equals("Y")) {
					
					// 섬네일 폴더가 있는지 먼저 검사 
					String thumb = FtpConstant.FTP_THUMB_PATH;
					boolean isThumbPath = this.cd(thumb); // 임시  디렉토리 구조 (현재 ../temp 가 생성되었는지) 
					logger.debug("### isThumbPath : " + isThumbPath);
					
					//썸네일 디렉토리 생성
					if(!isThumbPath) {
						logger.debug("************************* 섬네일대상 일 경우  경로를 만듭니다 *****************************");
						sftp.mkdir(thumb);				
					}
					
					// 사용자아이디 경로로 이동
					sftp.cd(ftpPath+"/"+FtpConstant.FTP_USER_PATH+"/"+userId+"/"+temp+"/"+thumb);
					logger.debug("#### 임시 디렉토리 현재 경로  : " + pwd());
					
					// 섬네일 업로드 
					FileInputStream thumbFis = (FileInputStream) uploadBean.getFile().getInputStream();		// 업로드할 File 생성
					sftp.put(thumbFis, saveFileName);
				}
				
				isResult = true;
				
				tempUploadBean.setUserId(userId);
				tempUploadBean.setUploadType(FtpConstant.FTP_USER_PATH);
				tempUploadBean.setTempSaveName(saveFileName);
				tempUploadBean.setTempRealName(realFileName);
				tempUploadBean.setTempPath(ftpPath+"/"+FtpConstant.FTP_USER_PATH+"/"+userId+"/"+temp);  // temp 파일 절대경로 
				tempUploadBean.setTempExtName(extendname);
				tempUploadBean.setThumbYn(thumbnailYn);	// 섬네일 유무 
				tempUploadBean.setIsUploaded(isResult);
				
				
			
			// 그룹 대표이미지 일 경우 
			} else {

				
				isRootPath = this.cd(FtpConstant.FTP_GROUP_PATH); // 그룹대표이미지 정의 디렉토리 구조 (현재 ../rep  가 생성되었는지) 

				// 폴더 생성여부 확인 없으면 생성
				if(!isRootPath) {
					logger.debug("************************* 그룹대표인덱스 root 경로를 만듭니다 *****************************");
					sftp.mkdir(FtpConstant.FTP_USER_PATH);				
				}
				
				// 그룹대표이미지 기본 경로로 이동(rep) 
				sftp.cd(ftpPath+"/"+FtpConstant.FTP_GROUP_PATH);
				
				String groupIndex = uploadBean.getGroupIndex();
				boolean isGroupIndexPath = this.cd(groupIndex); // 그룹인덱스 디렉토리 구조 (현재 ../groupIndex 가 생성되었는지) 
				
				// 그룹인덱스 디렉토리 검사 
				if(!isGroupIndexPath) {
					logger.debug("************************* 그룹 인덱로 경로를 만듭니다 *****************************");
					sftp.mkdir(groupIndex);				
				}
				
				// 그룹인덱스 경로로 이동
				sftp.cd(ftpPath+"/"+FtpConstant.FTP_GROUP_PATH+"/"+groupIndex);
				logger.debug("#### 그룹 인덱의 디렉토리 현재 경로  : " + pwd());
				
				
				String temp = FtpConstant.FTP_TEMP_PATH;
				boolean isTempPath = this.cd(temp); // 임시  디렉토리 구조 (현재 ../temp 가 생성되었는지) 
				// 임시 디렉토리 검사 
				if(!isTempPath) {
					// 경로가 존재하지 않을 경우 경로를 만들어 준다. 하지만 일자별, 사용자별, temp 폴더를 최초 한번 만들면 그 이후부터는 만들지 않는다.
					logger.debug("************************* 임시 경로를 만듭니다 *****************************");
					sftp.mkdir(temp);				
				}
				
				// 임시 디렉토리  경로로 이동
				sftp.cd(ftpPath+"/"+FtpConstant.FTP_GROUP_PATH+"/"+groupIndex+"/"+temp);
				logger.debug("#### 임시 디렉토리 현재 경로  : " + pwd());
				
				// 파일 업로드 
				FileInputStream fis = (FileInputStream) uploadBean.getFile().getInputStream();
				sftp.put(fis, saveFileName);
				
				String thumbnailYn = StringUtil.getThumbnailYn(extendname);
				logger.debug("#### 현재 경로  : " + pwd());
				
				if(thumbnailYn.equals("Y")) {
					
					// 섬네일 폴더가 있는지 먼저 검사 
					String thumb = FtpConstant.FTP_THUMB_PATH;
					boolean isThumbPath = this.cd(thumb); // 임시  디렉토리 구조 (현재 ../temp 가 생성되었는지) 
					logger.debug("### isThumbPath : " + isThumbPath);
					
					//썸네일 디렉토리 생성
					if(!isThumbPath) {
						logger.debug("************************* 섬네일대상 일 경우  경로를 만듭니다 *****************************");
						sftp.mkdir(thumb);				
					}
					
					// 사용자아이디 경로로 이동
					sftp.cd(ftpPath+"/"+FtpConstant.FTP_USER_PATH+"/"+groupIndex+"/"+temp+"/"+thumb);
					logger.debug("#### 임시 디렉토리 현재 경로  : " + pwd());
					
					// 섬네일 업로드 
					FileInputStream thumbFis = (FileInputStream) uploadBean.getFile().getInputStream();		// 업로드할 File 생성
					sftp.put(thumbFis, saveFileName);
				}
				
				isResult = true;
				
				tempUploadBean.setUserId(uploadBean.getUserID());
				tempUploadBean.setUploadType(FtpConstant.FTP_USER_PATH);
				tempUploadBean.setTempSaveName(saveFileName);
				tempUploadBean.setTempRealName(realFileName);
				tempUploadBean.setTempPath(ftpPath+"/"+FtpConstant.FTP_USER_PATH+"/"+groupIndex+"/"+temp);  // temp 파일 절대경로 
				tempUploadBean.setTempExtName(extendname);
				tempUploadBean.setThumbYn(thumbnailYn);	// 섬네일 유무 
				tempUploadBean.setIsUploaded(isResult);
				
			}
			

			
		} catch (Exception e) {
			
			isResult = false;
			tempUploadBean.setIsUploaded(isResult);
			logger.debug(e);
			
			throw e;
			
		} finally {
			
			logout();		// 로그아웃
			
		}
		
		
		return tempUploadBean;
	}
	
	*/
	
	public SftpClientUtil(String server, int port, String user, String pwd)
			throws Exception {
		try {
			if (server == null || user == null || pwd == null) {
				logger.debug("Parameter is null!");
			}
			client = new SshClient();
			client.setSocketTimeout(70000);
			client.connect(server, port, new HostKeyVerification() {
				@Override
				public boolean verifyHost(String s, SshPublicKey sshpublickey) throws TransportProtocolException {
					return true;
				}
			});
			auth = new PasswordAuthenticationClient();
			auth.setUsername(user);
			auth.setPassword(pwd);
			int result = client.authenticate(auth);
			if (result != AuthenticationProtocolState.COMPLETE) {
				throw new Exception("Login to " + server + ":22" + user + "/" + pwd + " failed");
			}
			sftp = client.openSftpClient();
		} catch (Exception e) {
			logger.debug(e);
			throw e;
		}
	}

	public boolean put(String path) throws Exception {
		boolean rtn = false;
		try {
			if (sftp != null) {
				sftp.put(path);
				rtn = true;
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return rtn;
	}

	public boolean get(String srcFile, String destFile) throws Exception {
		boolean rtn = false;
		try {
			if (sftp != null) {
				if (destFile == null)
					sftp.get(srcFile);
				else
					sftp.get(srcFile, destFile);
				rtn = true;
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return rtn;
	}

	public boolean lcd(String path) throws Exception {
		boolean rtn = false;
		try {
			if (sftp != null) {
				sftp.lcd(path);
				rtn = true;
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return rtn;
	}

	public boolean cd(String path) throws Exception {
		boolean rtn = false;
		try {
			if (sftp != null) {
				sftp.cd(path);
				rtn = true;
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return rtn;
	}

	public String pwd() throws Exception {
		String rtnStr = null;
		try {
			if (sftp != null) {
				rtnStr = sftp.pwd();
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return rtnStr;
	}

	public boolean chmod(int permissions, String path) throws Exception {
		boolean rtn = false;
		try {
			if (sftp != null) {
				sftp.chmod(permissions, path);
				rtn = true;
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return rtn;
	}

	public boolean isClosed() throws Exception {
		boolean rtn = false;
		try {
			if (sftp != null)
				rtn = sftp.isClosed();
		} catch (Exception e) {
			logger.debug(e);
		}
		return rtn;
	}

	public boolean logout() throws Exception {
		boolean rtn = false;
		try {
			if (sftp != null)
				sftp.quit();
			if (client != null)
				client.disconnect();
			rtn = true;
		} catch (Exception e) {
			logger.debug(e);
		}
		return rtn;
	}

	/*
	public static void main(String[] args) {
		try {
			SftpClientUtil jsftp = new SftpClientUtil("121.78.127.29", 1229,
					"root", "sns123!@#");
			boolean test = jsftp.cd("/usr/local/talkon-web/");
			logger.debug(jsftp.pwd());
			boolean test1 = jsftp.lcd("C:/");
			boolean test2 = jsftp.get("NOTICE.txt", "NOTICE.txt");
			boolean isClosed = jsftp.isClosed();
			boolean test3 = jsftp.logout();
			logger.debug(test + " " + test1 + " " + test2 + " "
					+ isClosed + " " + test3);
		} catch (Exception e) {
			logger.debug(e);
		}
	}
	*/

}
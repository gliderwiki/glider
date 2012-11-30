package org.gliderwiki.framework.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.gliderwiki.framework.exception.FtpException;



public class FtpFileUploader {
	
	
	/* 리사이즈 너비, 높이
	private final static double SCALE_WIDTH = 200d; 
	private final static double SCALE_HEIGHT = 200d; 
	 */
	
	/* ftp 정보 */
	private String ftpIP = "111.111.111.111";
	private int  ftpPort = 1229;
	private String id = "root";
	private String pw = "pass";
	private String path = "";
		
	private FTPClient ftpClient = null; 
	
	
	public FtpFileUploader() {
		ftpClient = new FTPClient();
		FTPClientConfig config = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		config.setServerLanguageCode("ko");
		ftpClient.configure(config);
		
	}

	
	public boolean connect()  throws FtpException, IOException {
		//System.out.println("connect() 들어옵니다 \n\n\n\n\n\n");
		boolean isConnect = false;
		
		
	
		ftpClient.setControlEncoding("euc-kr"); // 한글파일명 때문에 디폴트 인코딩을 euc-kr로 합니다.
		
		//System.out.println("한글처리");
		
		ftpClient.connect(ftpIP, ftpPort); // 서버에 접속합니다
		
		//System.out.println("커넥션");

		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		
		//System.out.println("바이너리 타입 ");

		int reply = ftpClient.getReplyCode(); // 응답코드가 비정상이면 종료합니다
		
		//System.out.println("응답 코드 : " + reply);
		
		
		if (!FTPReply.isPositiveCompletion(reply)) {
			//System.out.println("######## FTP Client 응답코드가 비정상임 : " + reply);
			ftpClient.disconnect();
		} else {
			ftpClient.setSoTimeout(10000); // 현재 커넥션 timeout을 millisecond 값으로
			// 입력합니다
			ftpClient.login(id, pw); // 로그인 유저명과 비밀번호를 입력 합니다

			ftpClient.enterLocalPassiveMode(); // 패시브모드로 설정
			isConnect = true;
		}
		
		return isConnect;
	}
	
	/**
	 * ftp 파일 업로드
	 * @param uploadBean
	 * @return
	 
	public boolean ftpFileUpload(UploadBean uploadBean, String saveName){
		
		FileInputStream fis = null;
		boolean uploadResult = false;
		
		try {
			
			ftpClient = new FTPClient();						// FTP Client 객체 생성
			ftpClient.setControlEncoding("UTF-8");				// 문자 코드를 UTF-8로 인코딩
			ftpClient.connect(ftpIP, ftpPort);					// 서버접속 " "안에 서버 주소 입력 또는 "서버주소", 포트번호
			ftpClient.login(id, pw);							// FTP 로그인 ID, PASSWORLD 입력
			ftpClient.enterLocalPassiveMode();					// Passive Mode 접속일때
			ftpClient.changeWorkingDirectory(path);				// 작업 디렉토리 변경
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);		// 업로드 파일 타입 셋팅
			ftpClient.setSoTimeout(100000);
			ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
			
			try {
				
				fis = (FileInputStream) uploadBean.getFile().getInputStream();		// 업로드할 File 생성
				uploadResult = ftpClient.storeFile(saveName, fis);	// File 업로드

			} finally {
				
				if (fis != null) {
					
					try {
						
						fis.close();        // Stream 닫기
						
					} catch (Exception e) {
						
					}
				}
			}
			
			

		} catch (SocketException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
			
		}finally{
			
			try {
				
				ftpClient.logout();			// FTP Log Out
				ftpClient.disconnect();		// 접속 끊기
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
			
		return uploadResult;
	} 
	*/
	
	

	/**
	 * 이미지 리사이즈 사이즈 (200*200)
	 * @param uploadImage
	 * @return BufferedImage
	
	private BufferedImage scaleToSize(BufferedImage uploadImage) {
		
        AffineTransform atx = new AffineTransform();
        atx.scale(SCALE_WIDTH / uploadImage.getWidth(), SCALE_HEIGHT / uploadImage.getHeight());
        AffineTransformOp afop = new AffineTransformOp(atx, AffineTransformOp.TYPE_BILINEAR);
        uploadImage = afop.filter(uploadImage, null);
        
        return uploadImage;
    }
    */
	
}

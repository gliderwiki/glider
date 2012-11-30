/**
 * @FileName  : TempUploadVo.java
 * @Project   : NightHawk
 * @Date      : 2012. 2. 9. 
 * @작성자      : @author yion

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author yion
 *
 */

public class TempUploadVo extends BaseObjectBean { 
	private String fileSaveName; // 파일 저장명
	private String fileRealName; // 원본 파일 명
	private String filePath;     // 파일 저장 경로
	private double fileSize;     // 파일 사이즈 
	private String fileType;     // 파일 확장자
	private String thumbYn;		 // 섬네일 유무
	private String thumbPath;	 // 섬네일 패스 
	private String thumbName;	 // 섬네일 파일 명
	private boolean isUploaded;	 // 업로드 성공 여부 
	
	private MultipartFile file; 	// 파일 객체 
	
	
	
	
	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	/**
	 * @return the fileSaveName
	 */
	public String getFileSaveName() {
		return fileSaveName;
	}
	/**
	 * @param fileSaveName the fileSaveName to set
	 */
	public void setFileSaveName(String fileSaveName) {
		this.fileSaveName = fileSaveName;
	}
	/**
	 * @return the fileRealName
	 */
	public String getFileRealName() {
		return fileRealName;
	}
	/**
	 * @param fileRealName the fileRealName to set
	 */
	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * @return the fileSize
	 */
	public double getFileSize() {
		return fileSize;
	}
	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @return the thumbYn
	 */
	public String getThumbYn() {
		return thumbYn;
	}
	/**
	 * @param thumbYn the thumbYn to set
	 */
	public void setThumbYn(String thumbYn) {
		this.thumbYn = thumbYn;
	}
	/**
	 * @return the thumbPath
	 */
	public String getThumbPath() {
		return thumbPath;
	}
	/**
	 * @param thumbPath the thumbPath to set
	 */
	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}
	/**
	 * @return the thumbName
	 */
	public String getThumbName() {
		return thumbName;
	}
	/**
	 * @param thumbName the thumbName to set
	 */
	public void setThumbName(String thumbName) {
		this.thumbName = thumbName;
	}

	/**
	 * @return the isUploaded
	 */
	public boolean isUploaded() {
		return isUploaded;
	}
	/**
	 * @param isUploaded the isUploaded to set
	 */
	public void setIsUploaded(boolean isUploaded) {
		this.isUploaded = isUploaded;
	}
	
}
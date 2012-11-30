package org.gliderwiki.web.vo;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileVo {
	private String fileNm;
	private MultipartFile fileData;
	private MultipartFile file_upload;
	
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public MultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(MultipartFile fileData) {
		this.fileData = fileData;
	}
	public MultipartFile getFile_upload() {
		return file_upload;
	}
	public void setFile_upload(MultipartFile file_upload) {
		this.file_upload = file_upload;
	}
	
	
}

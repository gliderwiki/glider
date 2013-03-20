package org.gliderwiki.framework.util.constant;

public class FtpConstant {

	/**
	 * 임시 저장 기본 패스 
	 */
	public static final String FTP_TEMP_PATH = "temp";
	
	/**
	 * 실 저장 기본 패스 
	 */
	public static final String FTP_TARGET_PATH = "target";
	
	/**
	 * 섬네일 기본 패스 
	 */
	public static final String FTP_THUMB_PATH = "thumb";
	
	
	/**
	 * 확장자 체크
	 */
	public static final String[] PERMITTED_EXTS = new String[] {
		"DOC", "XLS", "XLSX", "PPT", "HWP", "TXT", "PDF", "DOCX", "PPTX", "PPS" ,
		"ZIP", "ALZ", "JAR",  "AI", "PSD",
		"AVI", "MOV", "MPG", "MPEG", "RM", "ASF", "WMV", "MP3", "MP4",
		"BMP", "JPG", "JPEG", "GIF", "PNG", "FLA", "SWF", "HTM", "HTML" , "HTML", 
		"JAVA", "JS","JSP", "XML", "WAR", "JAR", "TAR.GZ", "GZ", "DMG", "GDOC", "GSHEET", "GSLIDES"
		};

	
	/**
	 * 파일 확장자가 허용범위 인지 판별 	
	 * @param fileName
	 * @return
	 */
	public static boolean isPermittedExt(String fileName) {
        boolean permittedExt = false;

        int dotIndex = fileName.lastIndexOf('.');
        String ext = fileName.substring(dotIndex + 1);

        for (int i = 0; i < PERMITTED_EXTS.length; i++) {
            if (ext.equalsIgnoreCase(PERMITTED_EXTS[i])) {
                permittedExt = true;
                break;
            }
        }

        return permittedExt;
    }
}

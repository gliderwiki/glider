/**
 * @FileName  : FileUtils.java
 * @Project   : NightHawk
 * @Date      : 2012. 1. 10.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.gliderwiki.web.domain.AttachmentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;


/**
 * 파일관련된 부분을 처리함
 *
 * @author bluepoet
 *
 */
public class GliderFileUtils extends FileUtils {
	public static Logger log = LoggerFactory.getLogger(GliderFileUtils.class);

	public static String attachment_root_dir = System.getProperty("java.io.tmpdir");

	public static final String CHARSET = "UTF-8";

	public static final long EXPIRE_MILLIS = 31556926000L; // 1year

	public static final int FAIL_RESULT = -1;

	/**
	 * 현재 파일의 부모 디렉토리 File 객체확보
	 *
	 * @param filePath
	 * @return
	 */
	public static File getParentDir(File file) {
		String parentDirPath = FilenameUtils.getFullPathNoEndSeparator(file.getAbsolutePath());
		File parentDir = new File(parentDirPath);
		return parentDir;
	}

	/**
	 * 현재 파일의 부모디렉토리를 모두 생성함
	 *
	 * @param filePath
	 * @return
	 */
	public static File forceMKParentDir(File file) throws IOException {
		File parentDir = getParentDir(file);
		forceMkdir(parentDir);
		return parentDir;
	}

	/**
	 * 파일 업로드전 미리보기를 위한 temp파일 경로얻기, 폴더가 없다면 생성함
	 *
	 * @return
	 * @throws IOException
	 */
	public static File getRootDestFile(String fileName) throws IOException {
		File tempFile = new File(attachment_root_dir + "/glider/attachments" + fileName);

		log.debug("attachment_root_dir : " + attachment_root_dir);
		log.debug("/glider/attachments" + fileName);

		File parentDir = getParentDir(tempFile);
		forceMkdir(parentDir);
		return tempFile;
	}

	/**
	 * 카피의 대상이 되는 파일을 만듦
	 *
	 * @param file
	 * @return
	 */
	public static File getDestFile(String filePath) throws IOException {
		return new File(attachment_root_dir + "/glider/attachments" + filePath);
	}

	/**
	 * 해당 파일을 다운로드함
	 *
	 * @param request
	 * @param response
	 * @param file
	 */
	public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file,
			AttachmentType type) throws IOException {
		BufferedInputStream bis = null;

		if (file == null || !file.exists() || file.length() < 0 || file.isDirectory()) {
			log.debug("### 파일이 존재하지 않음");

			setStatusAsNotFound(response);
			return;
		}

		// response 관련 설정
		setHeader(request, response, file, type);

		// 실제 파일다운로드 부분
		try {
			response.setBufferSize((int) file.length());
			bis = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(bis, response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException("### Fail of download, msg : " + e.getMessage(), e);
		} finally {
			response.getOutputStream().flush();
			response.getOutputStream().close();
			IOUtils.closeQuietly(bis);
		}
	}

	/**
	 * 실제 파일을 다운로드 혹은 보기를 실행함
	 *
	 * @param request
	 * @param response
	 * @param file
	 * @param type
	 * @throws IOException
	 */
	public static void setHeader(HttpServletRequest request, HttpServletResponse response, File file,
			AttachmentType type) throws IOException {
		String mimetype = request.getSession().getServletContext().getMimeType(file.getName());
		String isViewer = "";

		if (type == AttachmentType.DOWNLOAD) {
			isViewer = "attachment";
		}

		if (mimetype == null || mimetype.length() == 0) {
			mimetype = "application/octet-stream;";
		}

		response.setContentType(mimetype + "; charset=" + CHARSET);
		log.debug("### minetype :: {}", mimetype);

		String userAgent = request.getHeader("User-Agent");

		if (userAgent != null && userAgent.indexOf("MSIE 5.5") > -1) { // MS IE
																		// 5.5
																		// 이하
			response.setHeader("Content-Disposition",
					isViewer + ";filename=" + URLEncoder.encode(file.getName(), "UTF-8") + ";");
		} else if (userAgent != null && userAgent.indexOf("MSIE") > -1) { // MS
																			// IE
																			// (보통은
																			// 6.x
																			// 이상
																			// 가정)
			response.setHeader("Content-Disposition",
					isViewer + ";filename=" + URLEncoder.encode(file.getName(), "UTF-8") + ";");
		} else { // 모질라나 오페라
			response.setHeader("Content-Disposition",
					isViewer + ";filename=" + new String(file.getName().getBytes(CHARSET), "latin1") + ";");
		}

		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		// response.addDateHeader("Expires", System.currentTimeMillis() +
		// EXPIRE_MILLIS);
	}

	/**
	 * 해당하는 파일이 없을시 404에러 뱉고 클로즈
	 *
	 * @param response
	 * @throws IOException
	 */
	public static void setStatusAsNotFound(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		response.getWriter().close();
	}

	/**
	 * 해당 도메인 HTML 페이지를 PDF로 EXPORT하여 다운로드 실행
	 *
	 * @param request
	 * @param response
	 * @param url
	 */
	public void htmlToPdfExport(HttpServletRequest request, HttpServletResponse response, String wikiPageNum) {
		int exitCode = FAIL_RESULT;
		Runtime run = Runtime.getRuntime();
		Process p = null;

		try {
			p = run.exec(makePdfCommand(request.getScheme()+"://"+request.getServerName(), wikiPageNum));

			StreamLogger in = new StreamLogger(p.getInputStream());
			StreamLogger err = new StreamLogger(p.getErrorStream());
			in.start();
			err.start();

			exitCode = p.waitFor();

			log.info("exitCode : " + exitCode);

			// 프로그램이 에러없이 실행되었을 경우 pdf 파일 다운로드 함
			if (exitCode > FAIL_RESULT) {
				downloadFile(request, response, new File("/home/user/instance/nighthawk/webapps/ROOT/resource/pdf/"
						+ wikiPageNum + ".pdf"), AttachmentType.DOWNLOAD);
			}
		} catch (Exception e) {
			log.debug("pdf export시 exception 발생! : {}", e.getMessage());
		} finally {
			if (p != null) {
				p.destroy();
			}
		}
	}

	private String makePdfCommand(String domainName, String wikiPageNum) {
		StringBuilder sb = new StringBuilder();
		sb.append("/usr/bin/xvfb-run --auto-servernum --server-num=99 wkhtmltopdf --dpi 300 --page-size A4 ");
		sb.append(domainName+"/wiki/pdf/"+wikiPageNum + "?pdfView=ok ");
		sb.append("/home/user/instance/nighthawk/webapps/ROOT/resource/pdf/" + wikiPageNum + ".pdf");
		log.info("pdf export command :: {}", sb.toString());

		return sb.toString();
	}

	/**
	 * Process 버퍼비우기용 내부 클래스
	 *
	 * @author bluepoet
	 *
	 */
	class StreamLogger extends Thread {
		private InputStream mInputStream;

		public StreamLogger(InputStream is) {
			this.mInputStream = is;
		}

		public void run() {
			InputStreamReader isr = null;
			BufferedReader br = null;

			try {
				isr = new InputStreamReader(mInputStream);
				br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(isr);
				IOUtils.closeQuietly(br);
			}
		}
	}
}
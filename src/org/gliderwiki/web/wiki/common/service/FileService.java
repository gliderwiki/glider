/**
 * @FileName  : FileService.java
 * @Project   : NightHawk
 * @Date      : 2012. 11. 5.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.common.service;

import java.io.File;
import java.io.IOException;

/**
 * 파일과 관련된 서비스를 제공하는 인터페이스
 * @author bluepoet
 *
 */
public interface FileService {
	void copyFile(File oringinFile, File destFile) throws IOException;
}

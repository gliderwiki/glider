/**
 * @FileName  : ApacheFileUtilService.java
 * @Project   : NightHawk
 * @Date      : 2012. 11. 6.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.wiki.common.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

/**
 * @author bluepoet
 *
 */
@Service("apacheFileUtilService")
public class ApacheFileUtilService implements FileService {

	@Override
	public void copyFile(File oringinFile, File destFile) throws IOException {
		FileUtils.copyFile(oringinFile, destFile);
	}
}

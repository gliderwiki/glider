/**
 * @FileName  : LogPrintTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 2. 1.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.common.etc;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bluepoet
 *
 */
public class LogPrintTest {
	Logger logger = LoggerFactory.getLogger(LogPrintTest.class);

	@Test
	public void logLevelPrintTest() {
		logger.debug("[DEBUG] message!");
		logger.info("[INFO] message!");
		logger.warn("[WARN] message!");
		logger.error("[ERROR] message!");
	}
}

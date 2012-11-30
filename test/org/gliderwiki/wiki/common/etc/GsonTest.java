/**
 * @FileName  : GsonTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 1. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.common.etc;

import org.apache.log4j.Logger;
import org.gliderwiki.web.domain.Temp;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author bluepoet
 *
 */
public class GsonTest {
	Logger logger = Logger.getLogger(this.getClass());

	private Gson gson;

	@Before
	public void setup() {
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}

	@Test
	public void gsonExcludedField() {
		Temp temp = new Temp();
		temp.setId(1);
		temp.setName("bluepoet");

		String resultJson = gson.toJson(temp);
		logger.debug("## 결과 json String : " + resultJson);
	}
}

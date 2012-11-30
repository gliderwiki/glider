/**
 * @FileName  : GliderWikiFunctions.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 10.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.tag;

import static com.ReflectionInjectorUtils.injector;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.gliderwiki.framework.util.DateUtil;
import org.gliderwiki.framework.util.StringUtil;
import org.gliderwiki.web.space.controller.SpaceController;
import org.junit.*;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author bluepoet
 *
 */
public class GliderWikiFunctionsTest {

	Logger logger = LoggerFactory.getLogger(GliderWikiFunctionsTest.class);

	@Test
	public void changeDateFormat() {
		String changeDate = DateUtil.getDate(new Date(), "yyyy.MM.dd");
		System.out.println("changeDate : " + changeDate);
		logger.debug("changeDate : {}", changeDate);
	}

	@Test
	public void cutAndRemoveTag() {
		String beforeStr = "<div class='c-F' role='tooltip' style='display: none;'><div class='c-F-RE'>프로필 보기</div><div class='c-F-Ia'><div class='c-F-ja'></div><div class='c-F-na'></div></div></div>";
		String afterStr = StringUtil.cutAndRemoveTag(beforeStr, 200);
		System.out.println("afterStr : " + afterStr);

		assertThat(afterStr, is("프로필 보기"));

	}
}

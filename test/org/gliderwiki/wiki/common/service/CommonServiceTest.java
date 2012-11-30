/**
 * @FileName  : CommonServiceTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 17.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.common.service;

import static com.ReflectionInjectorUtils.injector;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.web.domain.FavorityType;
import org.gliderwiki.web.domain.WeFavorite;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.common.service.CommonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author bluepoet
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CommonServiceTest {

	@Mock
	private EntityService<WeFavorite> entityService;

	@Spy
	private CommonService commonService = new CommonServiceImpl();

	@Before
	public void setup() {
		injector(commonService).set(entityService);
	}

	@Test
	public void addFavoriteTest() throws Throwable {
		int weUserIdx = 2;
		FavorityType type = FavorityType.SPACE;
		int weSpaceIdx= 1;

		WeFavorite weFavorite = new WeFavorite(weUserIdx, type, weSpaceIdx, null);

//		doReturn(1).when(entityService).insertEntity(weFavorite);

		//int result = commonService.addFavorite(weUserIdx, type, weSpaceIdx);

//		verify(entityService).insertEntity(weFavorite);
//
//		assertThat(result, is(1));

	}

	@Test
	public void 날짜비교테스트() throws ParseException {
		String firstDate = "2012/10/17";
		String secondDate = "2012/10/16";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		assertTrue(sdf.parse(firstDate).after(sdf.parse(secondDate)));

		System.out.println("날짜 : " + sdf.format(new Date()));
	}
}

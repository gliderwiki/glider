/**
 * @FileName  : WikiControllerTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 14. 
 * @작성자      : @author silverDragon

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.temp;

import static com.ReflectionInjectorUtils.injector;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.system.SystemConst;
import org.gliderwiki.web.vo.JsonResponse;
import org.gliderwiki.web.vo.MemberSessionVo;
import org.gliderwiki.web.vo.JsonResponse.ResponseStatus;
import org.gliderwiki.web.wiki.common.service.CommonService;
import org.gliderwiki.web.wiki.parser.controller.WikiController;
import org.gliderwiki.web.wiki.parser.service.WikiServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;


/**
 * @author silverDragon
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WikiControllerTest {
	
	@Mock
	WikiServiceImpl wikiService;
	
	@SuppressWarnings("rawtypes")
	@Mock
	EntityService entityService;
	
	@Mock
	CommonService commonService;
	
	@InjectMocks
	WikiController controller = new WikiController();
	
	@Mock
	Model model;
	
	@Mock
	BindingResult result;
	
	@Mock
	SessionStatus status;
	
	@Mock
	MemberSessionVo loginUser;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	WeSpace mockSpace;
	
	String weTag;
	
	
	@Before
	public void setUp(){
		injector(controller).set(wikiService).set(entityService).set(commonService);
		
		weTag = "1,2,3,4";
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test위키생성() throws Throwable{
		WeWiki wiki = newWeWiki();
		
		when(entityService.getRowEntity(mockSpace)).thenReturn(mockSpace);
		when(commonService.requestAlarmInfo(loginUser.getWeUserIdx(), loginUser.getWeUserNick(), SystemConst.SPACE_NEW_POST, 2, 1, 1)).thenReturn(0);
		when(wikiService.addWikiAllContents(wiki, mockSpace, weTag)).thenReturn(1);
		
		
//		JsonResponse res = controller.regist(loginUser, weTag, wiki, request);
		
//		assertThat(res.getStatus(), is(ResponseStatus.SUCCESS));
//		assertThat(res.getResponse().getRedirectUrl(), is("/wiki/1"));
		
	}
	
	
	@Test
	public void test수정_페이지_가져오기() throws Throwable{
		WeWiki wiki = newWeWiki();
		
//		when(wikiService.getWikiForEdit(wiki)).thenReturn(wiki);
		
//		String viewNm = controller.editWikiForm(wiki.getWe_wiki_idx(), model);
//		
//		assertThat(viewNm, is("/wiki/edit-form"));
	}
	
	@Test
	public void test위키수정() throws Throwable{
		WeWiki wiki = newWeWiki();
		
//		String weTag = "1,2,3,4";
//		
//		when(wikiService.modifiedWikiAndSaveRevision(wiki, weTag)).thenReturn(wiki);
//		
//		String viewNm = controller.edit(wiki,weTag, result, loginUser, status, model);
		
//		assertThat(viewNm, is("wiki/show"));
	}
	
	private WeWiki newWeWiki(){
		WeWiki wiki = new WeWiki();
		wiki.setWe_wiki_idx(1);
		wiki.setWe_wiki_title("타이틀");
		wiki.setWe_wiki_text("텍스트");
		wiki.setWe_edit_yn("Y");
		
		return wiki;
	}
}

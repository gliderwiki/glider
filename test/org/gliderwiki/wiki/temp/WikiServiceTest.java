/**
 * @FileName  : WikiServiceImplTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 10. 
 * @작성자      : @author silverDragon

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.temp;

import static com.ReflectionInjectorUtils.injector;

import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.web.domain.WeWiki;
import org.gliderwiki.web.wiki.parser.service.WikiServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author silverDragon
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WikiServiceTest {
	
	@SuppressWarnings("rawtypes")
	@Mock
	EntityService entityService;
	
	@InjectMocks
	WikiServiceImpl wikiService = new WikiServiceImpl();
	
	@Before
	public void setup(){
		injector(wikiService).set(entityService);
	}
	
	@Test
	public void test수정() throws Throwable{
		WeWiki wiki = newWeWiki();
		wiki.setWe_edit_yn("N");
		String weTag = "12,3,4,123";
//		WeWiki compare = wikiService.modifiedWikiAndSaveRevision(wiki, weTag);
//		
//		assertEquals("Y", compare.getWe_edit_yn());
//		assertEquals("2",String.valueOf(compare.getWe_wiki_revision()));
	}
	
	private WeWiki newWeWiki(){
		WeWiki wiki = new WeWiki();
		wiki.setWe_space_idx(1);
		wiki.setWe_wiki_title("타이틀");
		wiki.setWe_wiki_text("텍스트 내용");
		wiki.setWe_wiki_revision(1);
		wiki.setWe_wiki_status("S");
		wiki.setWe_wiki_protect("0");
		return wiki;
	}
}

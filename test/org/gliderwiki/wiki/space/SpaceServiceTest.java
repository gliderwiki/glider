/**
 * @FileName  : SpaceServiceTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 16.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.wiki.space;

import static com.ReflectionInjectorUtils.injector;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.gliderwiki.framework.entity.dao.EntityDao;
import org.gliderwiki.framework.entity.service.EntityService;
import org.gliderwiki.web.domain.AuthorityType;
import org.gliderwiki.web.domain.ImageInfo;
import org.gliderwiki.web.domain.WeSpace;
import org.gliderwiki.web.space.dao.SpaceDao;
import org.gliderwiki.web.space.service.SpaceService;
import org.gliderwiki.web.wiki.common.service.FileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author bluepoet
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SpaceServiceTest {

	@Mock
	private EntityService entityService;

	@Mock
	private EntityDao entityDao;

	@Mock
	private FileService fileService;

	@Mock
	SpaceDao spaceDao;

	@Spy
	private SpaceService spaceService = new SpaceService();

	@Captor
	private ArgumentCaptor<WeSpace> weSpaceCaptor;

	private WeSpace weSpace;

	@Before
	public void setup() {
		weSpace = new WeSpace("테스트공간", "공간설명", "Y", AuthorityType.ALLGROUP, AuthorityType.USER, 1, 1);
		injector(spaceService).set(entityService).set(spaceDao).set(fileService);
	}

	@Test
	public void create() throws Throwable {
		// 공간정보를 DB에 저장한다
		ImageInfo imageInfo = new ImageInfo("/temp", "/real");
		WeSpace savedSpace = new WeSpace();
		savedSpace.setWe_space_idx(1);
		weSpace.setWe_view_data("");
		weSpace.setWe_edit_data("1,2");
		weSpace.setWe_upload_imgName("/upload/test.jpg");

		when(entityService.getRowEntity(weSpaceCaptor.capture())).thenReturn(savedSpace);

		spaceService.create(weSpace, 1, imageInfo);

		verify(entityService).insertEntity(weSpaceCaptor.getValue());

		// 조회와 수정정책에 따라 관련된 데이터를 DB에 저장한다
		verify(spaceService).processSpaceAuthorityPolicy(savedSpace);

		// 공간이미지파일을 지정된 위치에 복사하고, 이미지 정보를 DB에 저장한다
		verify(spaceService).processSpaceImage(savedSpace, imageInfo);

		WeSpace ws = weSpaceCaptor.getValue();

		assertThat(ws.getWe_space_name(), is("테스트공간"));
		assertThat(ws.getWe_space_desc(), is("공간설명"));
		assertThat(savedSpace.getWe_edit_data(), is("1,2"));
		assertThat(savedSpace.getWe_upload_imgName(), is("/upload/test.jpg"));
	}
}
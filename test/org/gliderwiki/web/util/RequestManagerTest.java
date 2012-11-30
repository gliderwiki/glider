/**
 * @FileName  : RequestManagerTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 10. 29.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.util;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;

import javax.servlet.http.HttpServletRequest;

import org.gliderwiki.util.RequestManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author bluepoet
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class RequestManagerTest {

	private RequestManager requestManager;

	@Mock
	private HttpServletRequest request;

	@Before
	public void setUp() {
		requestManager = new RequestManager();
	}

	@Test
	public void getRemoteAddress() {
		when(requestManager.getRemoteAddress(request)).thenReturn("127.0.0.1");

		assertThat("127.0.0.1", is(requestManager.getRemoteAddress(request)));
	}
}

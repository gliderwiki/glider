/**
 * @FileName  : GliderwikiHandlerExceptionResolverTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 16.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.exception;

import static com.ReflectionInjectorUtils.injector;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.exception.GliderwikiHandlerExceptionResolver;
import org.gliderwiki.framework.exception.GliderwikiHandlerExceptionView;
import org.gliderwiki.framework.exception.ResourceNotFoundException;
import org.gliderwiki.framework.exception.GliderwikiException.LogLevel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

/**
 * @author bluepoet
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GliderwikiHandlerExceptionResolverTest {

	@InjectMocks
	@Spy
	private GliderwikiHandlerExceptionResolver resolver = new GliderwikiHandlerExceptionResolver();

	@Mock
	Logger log;

	@Mock
	MessageSourceAccessor messageSourceAccessor;

	@Mock
	private HttpServletRequest request;

	@Mock
	private HttpServletResponse response;

	@Before
	public void setup() {
		GliderwikiHandlerExceptionView view1 = new GliderwikiHandlerExceptionView();
		List<String> uriPatterns1 = Lists.newArrayList("^/space(.*)");

		view1.setPrefix("/error");
		view1.setViewName("/space");
		view1.setUriPatterns(uriPatterns1);

		List<GliderwikiHandlerExceptionView> viewList = Lists.newArrayList(view1);

		injector(resolver).set(viewList);
	}

	@Test
	public void redefineException_다른예외() {
		IllegalArgumentException exception = new IllegalArgumentException("GliderWikiException과는 다른 에러다!");

		Exception redefineException = resolver.refineException(exception);
		assertThat(redefineException, instanceOf(GliderwikiException.class));
		assertThat(redefineException.getCause(), sameInstance((Throwable) exception));
		assertThat(redefineException.getMessage(), is(exception.getMessage()));
	}

	@Test
	public void redefineException_GliderwikiException() {
		GliderwikiException ex = new ResourceNotFoundException(String.class, "id");

		Exception redeineException = resolver.refineException(ex);
		assertThat(redeineException, instanceOf(GliderwikiException.class));
		assertThat(redeineException, sameInstance((Exception) ex));
	}

	@Test
	public void logException_error() {
		GliderwikiException ge = new GliderwikiException("예외!");
		ge.setLogLevel(LogLevel.ERROR);

		resolver.logException(ge);
		verify(log).error(ge.getMessage(), ge);
	}

	@Test
	public void generateView() {
		GliderwikiException ex = new GliderwikiException("예외");

		doReturn("/error").when(resolver).parseViewName(request, ex);

		String message = "messsage!";

		when(messageSourceAccessor.getMessage(ex.getMessageCode(), ex.getMessageArgs())).thenReturn(message);

		ModelAndView mav = resolver.generateView(request, ex);

		assertThat((String) mav.getModel().get("message"), is(message));
	}

	@Test
	public void parseView() {
		GliderwikiException ex = new GliderwikiException("예외!");

		String expectedViewName = "/error/space";

		when(request.getRequestURI()).thenReturn("/space/form");

		String viewName = resolver.parseViewName(request, ex);

		assertThat(expectedViewName, is(viewName));
	}

	@Test
	public void setHttpResponseCode() {
		GliderwikiException ge = new GliderwikiException();

		resolver.setHttpResponseStatus(response, ge);

		verify(response).setStatus(ge.getHttpStatusCode());

		assertThat(HttpServletResponse.SC_BAD_REQUEST, is(ge.getHttpStatusCode()));
	}
}

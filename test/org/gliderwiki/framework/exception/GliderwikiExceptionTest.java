/**
 * @FileName  : GliderwikiExceptionTest.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 5.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.exception;

import static com.ReflectionInjectorUtils.injector;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.gliderwiki.framework.exception.GliderwikiException;
import org.gliderwiki.framework.exception.GliderwikiException.LogLevel;
import org.junit.*;
import org.springframework.web.multipart.MultipartException;


/**
 * @author bluepoet
 *
 */
public class GliderwikiExceptionTest {

	@Test
	public void causeLogLevel_지정시() {
		MultipartException exception = new MultipartException("예외상황발생!");

		GliderwikiException ae = new GliderwikiException(exception);

		assertThat(ae.getLogLevel(), is(LogLevel.WARN));
	}

	@Test
	public void causeLogLevel_미지정시() {
		NullPointerException exception = new NullPointerException();

		GliderwikiException ae = new GliderwikiException(exception);

		assertThat(ae.getLogLevel(), is(GliderwikiException.DEFAULT_LOGLEVEL));
	}
}
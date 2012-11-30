/**
 * @FileName  : GliderwikiHandlerExceptionResolver.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 5.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.framework.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

/**
 * 모든 예외를 잡아 처리하는 ExceptionResolver를 재정의함
 *
 * @author bluepoet
 *
 */
public class GliderwikiHandlerExceptionResolver implements HandlerExceptionResolver {

	Logger log = LoggerFactory.getLogger(GliderwikiHandlerExceptionResolver.class);

	private List<GliderwikiHandlerExceptionView> viewList = Lists.newArrayList();

	private MessageSourceAccessor messageSourceAccessor;

	public void setViewList(List<GliderwikiHandlerExceptionView> viewList) {
		this.viewList = viewList;
	}

	public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
		this.messageSourceAccessor = messageSourceAccessor;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		GliderwikiException gliderwikiException = refineException(ex);
		logException(gliderwikiException);
		ModelAndView mav = generateView(request, gliderwikiException);
		setHttpResponseStatus(response, gliderwikiException);

		return mav;
	}

	protected GliderwikiException refineException(Exception ex) {
		if (ex instanceof GliderwikiException) {
			return (GliderwikiException) ex;
		}

		return new GliderwikiException(ex.getMessage(), ex);
	}

	protected void logException(GliderwikiException ex) {
		switch (ex.getLogLevel()) {
		case TRACE:
			log.trace(ex.getMessage(), ex);
			break;
		case DEBUG:
			log.debug(ex.getMessage(), ex);
			break;
		case INFO:
			log.info(ex.getMessage(), ex);
			break;
		case WARN:
			log.warn(ex.getMessage(), ex);
			break;
		default:
			log.error(ex.getMessage(), ex);
			break;
		}
	}

	protected ModelAndView generateView(HttpServletRequest request, GliderwikiException gliderwikiException) {
		String viewName = parseViewName(request, gliderwikiException);
		ModelAndView mav = new ModelAndView(viewName);
		String message = messageSourceAccessor.getMessage(gliderwikiException.getMessageCode(),
				gliderwikiException.getMessageArgs());

		mav.addObject("message", message);

		return mav;
	}

	protected String parseViewName(HttpServletRequest request, GliderwikiException gliderwikiException) {
		String prefix = "";
		String viewName = gliderwikiException.getViewName();

		for (GliderwikiHandlerExceptionView v : viewList) {
			if (v.isDefaultView()) {
				prefix = v.getPrefix();
			}

			if (v.match(request)) {
				prefix = v.getPrefix();
				if (v.getViewName() != null) {
					viewName = v.getViewName();
				}
				break;
			}
		}

		return prefix + viewName;
	}

	protected void setHttpResponseStatus(HttpServletResponse response, GliderwikiException gliderwikiException) {
		response.setStatus(gliderwikiException.getHttpStatusCode());
	}
}
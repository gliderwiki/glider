/**
 * @FileName  : JavascriptScriptsTag.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 11.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.tags;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * 이 태그라이브러리의 body에서 Javascript를 선언하는데 이를 실제 웹페이지에 바로 출력하지 않고 request 속성으로 저장만
 * 한다. 실제 출력은 {@link JavascriptLoadTag}에서 이뤄진다.
 *
 * 이 태그라이브러리는 일반 JSP페이지에서 사용한다. 실제 출력은 Sitemesh Decorator에서
 * {@link JavascriptLoadTag}를 통해 이뤄진다.
 *
 * @author bluepoet
 *
 */
public class JavascriptScriptsTag extends SimpleTagSupport {
	Logger logger = LoggerFactory.getLogger(JavascriptScriptsTag.class);

	public static final String JAVASCRIPT_SCRIPTS_ATTR_NAME = "___$$$@!JAVASCRIPT_SCRIPTS!@$$$___";

	public static final int JAVASCRIPT_SCRIPTS_SCOPE = PageContext.REQUEST_SCOPE;

	@Override
	public void doTag() throws IOException, JspException {
		List<String> scripts = getScripts();

		String bodyString = getBodyAsString();
		scripts.add(bodyString);
	}

	protected List<String> getScripts() {
		JspContext jspContext = getJspContext();

		@SuppressWarnings("unchecked")
		List<String> scripts = (List<String>) jspContext.findAttribute(JAVASCRIPT_SCRIPTS_ATTR_NAME);

		if (scripts == null) {
			scripts = Lists.newArrayList();
			jspContext.setAttribute(JAVASCRIPT_SCRIPTS_ATTR_NAME, scripts, JAVASCRIPT_SCRIPTS_SCOPE);
		}

		return scripts;
	}

	protected String getBodyAsString() throws IOException, JspException {
		StringWriter sw = new StringWriter();
		getJspBody().invoke(sw);
		return sw.toString();
	}
}
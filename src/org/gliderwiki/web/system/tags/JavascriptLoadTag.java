/**
 * @FileName  : JavascriptLoadTag.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 11.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.tags;

import static org.gliderwiki.web.system.tags.JavascriptScriptsTag.JAVASCRIPT_SCRIPTS_ATTR_NAME;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.google.common.base.Preconditions;

/**
 * {@link JavascriptScriptsTag}에서 저장한 스크립트를 실제로 출력한다.
 *
 * 이 태그라이브러리는 보통 Sitemesh Decorator 쪽에서 사용한다.
 *
 * @author bluepoet
 *
 */
public class JavascriptLoadTag extends SimpleTagSupport {

	@Override
	public void doTag() throws IOException, JspException {
		List<String> scripts = getScripts();

		if (scripts == null) {
			return;
		}

		writeOut(scripts);
		scripts.clear();
	}

	@SuppressWarnings("unchecked")
	protected List<String> getScripts() {
		return (List<String>) getJspContext().findAttribute(JAVASCRIPT_SCRIPTS_ATTR_NAME);
	}

	protected void writeOut(List<String> scripts) throws IOException {
		Preconditions.checkNotNull(scripts);

		for (String script : scripts) {
			getJspContext().getOut().append(script + "\n");
		}
	}
}
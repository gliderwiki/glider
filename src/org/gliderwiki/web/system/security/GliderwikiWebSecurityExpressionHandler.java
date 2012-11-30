/**
 * @FileName  : GliderwikiWebSecurityExpressionHandler.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import org.gliderwiki.util.RequestManager;
import org.gliderwiki.web.system.SessionService;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


/**
 * @author bluepoet
 *
 */
public class GliderwikiWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {
	private SessionService sessionService;

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@Override
	public EvaluationContext createEvaluationContext(Authentication authentication, FilterInvocation fi) {
		StandardEvaluationContext ctx = (StandardEvaluationContext) super.createEvaluationContext(authentication, fi);

		SecurityExpressionRoot root = new GliderwikiSecurityExpressionRoot(authentication, fi, sessionService);
		ctx.setRootObject(root);
		return ctx;
	}
}

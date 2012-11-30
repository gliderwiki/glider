/**
 * @FileName  : IPRestrictFilter.java
 * @Project   : NightHawk
 * @Date      : 2012. 8. 27.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.gliderwiki.util.RequestManager;
import org.gliderwiki.web.domain.WeAccess;
import org.gliderwiki.web.wiki.common.service.RestrictIpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * 특정 IP의 사용자의 접근을 금지하는 필터임
 *
 * @author bluepoet
 *
 */
public class IPRestrictFilter extends OncePerRequestFilter {
	Logger logger = LoggerFactory.getLogger(IPRestrictFilter.class);

	private RequestManager requestManager;
	private RestrictIpService restrictIpService;
	private String targetRole;

	public void setRequestManager(RequestManager requestManager) {
		this.requestManager = requestManager;
	}

	public void setRestrictIpService(RestrictIpService restrictIpService) {
		this.restrictIpService = restrictIpService;
	}

	public void setTargetRole(String targetRole) {
		this.targetRole = targetRole;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		// 사용자의 역할을 가져온다.
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && targetRole != null) {
			boolean isAllowedRole = false;

			// 로그인유저인지를 확인한다.
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				if (authority.getAuthority().equals(targetRole)) {
					isAllowedRole = true;
					break;
				}
			}

			if (isAllowedRole) {
				// DB에 저장되어 있는 차단목록을 가져온다
				if (!CollectionUtils.isEmpty(restrictIpService.getRestrictIpList())) {
					for (WeAccess weAccess : restrictIpService.getRestrictIpList()) {
						String restrictIp = weAccess.getWe_target_ip();
						String currentIp = requestManager.getRemoteAddress(request);

						if (StringUtils.equals(restrictIp, currentIp)) {
							logger.debug("restrictIp, currentIp : {}{}", restrictIp, currentIp);
							throw new AccessDeniedException(currentIp + "는 접근이 허용되지 않는 IP 주소입니다.");
						}
					}
				}
			}
		} else {
			throw new AccessDeniedException("인증되지 않은 사용자입니다.");
		}

		chain.doFilter(request, response);
	}
}
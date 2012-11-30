/**
 * @FileName  : GliderwikiUserDetails.java
 * @Project   : NightHawk
 * @Date      : 2012. 7. 30.
 * @작성자      : @author bluepoet

 * @변경이력    :
 * @프로그램 설명 :
 */
package org.gliderwiki.web.system.security;

import java.util.Collection;

import org.gliderwiki.web.vo.MemberSessionVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


/**
 * @author bluepoet
 *
 */
public class GliderwikiUserDetails extends User {

	private static final long serialVersionUID = 1L;
	private MemberSessionVo memberSessionVo;

	public GliderwikiUserDetails(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities,
			MemberSessionVo memberSessionVo) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.memberSessionVo = memberSessionVo;
	}

	public MemberSessionVo getMemberSessionVo() {
		return memberSessionVo;
	}
}

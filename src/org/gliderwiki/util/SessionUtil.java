/**
 * @FileName  : SessionUtil.java
 * @Project   : GLider Wiki
 * @Date      : 2012-04-02
 * @작성자      : @author Yion

 * @변경이력    : 
 * @프로그램 설명 : Session 정보를 관리한다. 
 */

package org.gliderwiki.util;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.gliderwiki.web.domain.WeUser;
import org.gliderwiki.web.vo.MemberSessionVo;

public class SessionUtil {

	Logger logger = Logger.getLogger(this.getClass());

	HttpSession session = null;

	private static final int MAXSESSIONTIME = 60 * 60 * 3;

	public SessionUtil() {

	}

	public SessionUtil(HttpServletRequest req, HttpServletResponse res) {
		session = req.getSession();
		session.setMaxInactiveInterval(MAXSESSIONTIME);
	}

	public SessionUtil(HttpServletRequest req) {
		session = req.getSession();
		session.setMaxInactiveInterval(MAXSESSIONTIME);
	}

	public SessionUtil(HttpServletRequest req, MemberSessionVo sessionUserVo) {
		session = req.getSession();
		session.setMaxInactiveInterval(MAXSESSIONTIME);

//		create("userIdx", sessionUserVo.getWeUserIdx());
//		create("userId", sessionUserVo.getWeUserId());
//		create("userNick", sessionUserVo.getWeUserName());
//		create("userEmail", sessionUserVo.getWeUserEmail());
//		create("userSite", sessionUserVo.getWeUserSite());
//		create("userThumbPath", sessionUserVo.getWeThumbPath());
//		create("userThumbName", sessionUserVo.getWeThumbName());
//		create("userGrade", sessionUserVo.getWeGrade());
//		create("userTechYn", sessionUserVo.getWeTechYn());
//		create("userPoint", sessionUserVo.getWePoint());

		create("loginUser", sessionUserVo);

		logger.info("viewSession : " + viewSession());

	}

	public MemberSessionVo getLoginUser() {
		return (MemberSessionVo) get("loginUser");
	}

	public void create(String key, Object value) {
		session.setAttribute(key, value);

	}

	public Object get(String key) {
		return session.getAttribute(key);
	}

	public void remove(String key) {
		session.removeAttribute(key);
	}

	public void invalidate() {

		session.invalidate();
		session.setMaxInactiveInterval(0);
		this.session = null;
	}

	public HttpSession getSession() {
		return session;
	}

	public boolean isLogin() {
		if (get("weUserId") == null || "".equals(get("weUserId"))) {
			return false;
		}
		return true;
	}

	public boolean isAdmin() {
		// userAdmin 필드 값이 1인가 판단
		return false;
	}

	public String viewSession() {
		StringBuffer sessionAll = new StringBuffer();
		Enumeration enumer = null;
		try {
			enumer = session.getAttributeNames();
			sessionAll.append("\n=================  session Start ===================");
			while (enumer.hasMoreElements()) {
				String key = enumer.nextElement().toString();
				sessionAll.append("\n " + key + " : " + session.getAttribute(key).toString());
			}
			sessionAll.append("\n=================  session End ===================");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionAll.toString();
	}

}
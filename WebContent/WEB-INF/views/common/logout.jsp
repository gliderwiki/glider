<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String userAgent = request.getHeader("user-agent");

	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	
	if (userAgent.indexOf("MSIE") > -1) { // internet explorer
		response.setHeader("Cache-Control", "no-cache");
	} else if (userAgent.indexOf("Firefox") > -1) { // firefox
		response.setHeader("Cache-Control", "no-store");
	} else if (userAgent.indexOf("Chrome") > -1) { // chrome
		response.setHeader("Cache-Control", "no-store");
	} else { // other browser
		response.setHeader("Cache-Control", "no-cache");
	}

	response.sendRedirect("/index");
%>

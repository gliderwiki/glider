<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page import="javax.servlet.*" %>
<!doctype html>
<!--[if lt IE 8]> <html class="lt-ie8" lang="ko"> <![endif]-->
<!--[if gt IE 7]><!--> <html lang="ko"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width">
<title>::Enterprise OpenSource Wiki – GLiDER™</title>
<link rel="stylesheet" href="/resource/glider/front/css/common.css" />


<!--[if IE]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
</head>

<body>
<!--[if lt IE 8]>
<p style="padding:1em;border-bottom:10px dashed #fff;background-color:#efefef;">
	<strong>낡은 브라우저</strong>를 사용하고 계십니다.<br />
	<a href="http://browsehappy.com/">새로운 브라우저로 업그레이드</a> 하거나 <a href="http://www.google.com/chromeframe/?redirect=true">구글 크롬프레임을 설치</a>해서 더 빠르고 아름다운 웹을 경험해 보세요.
</p>
<![endif]-->

<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">Caution</h2>
			<div class="body-cont">
				<div class="box-error">에러가 발생하였습니다</div>
				<div class="info-error">
					<h3 class="tit-sub">에러정보</h3>
					<div class="txt-error">
						<h3>${message}</h3><br/><br/>
						<%
							try {
								// The Servlet spec guarantees this attribute will be available
								Throwable exceptions = (Throwable) request.getAttribute("javax.servlet.error.exception");
				
								if (exceptions != null) {
									if (exceptions instanceof ServletException) {			
										ServletException servletErr = (ServletException) exceptions;
										Throwable rootCause = servletErr.getRootCause();
										if (rootCause == null) {
											rootCause = servletErr;
										}
										out.println("Message : Root cause is - " + rootCause.getMessage());
										rootCause.printStackTrace(new java.io.PrintWriter(out));
									} else {
				
										System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
										System.out.println("? getMessage : " + exceptions.getMessage());
										System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
										System.out.println("? getStackTrace : " + exceptions.getStackTrace());				
										System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
										out.println("Message :  Root cause is - <B>" + exceptions.getCause() + "</B>");
										out.println("<BR><BR>");
										out.println("? Exception Occured!! Check the server trace log for more information.");
									}
								} else {
									out.println("No error information available");
								}
				
							} catch (Exception ex) {
								ex.printStackTrace(new java.io.PrintWriter(out));
							}
						%>
					</div>
				</div>
			</div>
		<div class="foot-cont">
			<a href="/" class="btn">홈으로</a>
			<a href="#" onclick="history.go(-1);return false;" class="btn">이전</a>
		</div>
	</div>
</section>
</body>
</html> 
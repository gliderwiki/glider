<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"
%><%@include file="/WEB-INF/views/common/include/tags.jspf"%><!DOCTYPE html>
<!--[if lt IE 8]> <html class="lt-ie8" lang="ko"> <![endif]-->
<!--[if gt IE 7]><!--> <html lang="ko"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<title>::Enterprise OpenSource Wiki – GLiDER™</title>
	<link rel="stylesheet" href="/resource/glider/front/css/common.css">
	<link rel="stylesheet" href="/resource/glider/front/css/main.css">
	<link rel="stylesheet" href="/resource/glider/front/css/modal.css">
	<link rel="stylesheet" href="/resource/glider/front/css/alimi.css">
	<link rel="stylesheet" href="/resource/glider/front/css/wiki.css" />

	<decorator:head />
	<!--[if IE]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

</head>
<body <decorator:getProperty property="body.class" writeEntireProperty="true" />>
<!--[if lt IE 8]>
<p style="padding:1em;border-bottom:10px dashed #fff;background-color:#efefef;">
	<strong>낡은 브라우저</strong>를 사용하고 계십니다.<br />
	<a href="http://browsehappy.com/">새로운 브라우저로 업그레이드</a> 하거나 <a href="http://www.google.com/chromeframe/?redirect=true">구글 크롬프레임을 설치</a>해서 더 빠르고 아름다운 웹을 경험해 보세요.
</p>
<![endif]-->
<%@include file="/WEB-INF/views/common/include/guest_header.jsp"%>

<decorator:body />

<%@include file="/WEB-INF/views/common/include/guest_footer.jsp"%>

<script type="text/javascript" src="/resource/libs/jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery-ui-1.8.17.custom.min.js"></script>
<%@ include file="/WEB-INF/views/common/include/jsconstants.jspf" %>
<script type="text/javascript" src="/resource/glider/common/wiki.js"></script>
<script type="text/javascript" src="/resource/glider/common/socket.io.js"></script>
<script type="text/javascript" src="/resource/glider/common/notification.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	<c:if test="${loginUser.guest eq false}">
		var noti = new GliderWiki.Notification.Perception($(".layer_alimi"), GliderWiki.LoginUser.alarmChannel).process();
	</c:if>
});
</script>
<js:load/>
</body>
</html>
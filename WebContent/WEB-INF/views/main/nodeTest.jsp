<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %><%@
taglib prefix="space" tagdir="/WEB-INF/tags/space" %><%@
taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<body>
<section class="contents dashboard" role="main">
<div class="wrap-cont">
	<div class="body-cont">
		현재 테스트하는 알림채널 : 1gCHjWTRC990fTFM3SPEElyHDJ4JMKIi
	</div>
</div>
</section>

<js:scripts>
<script type="text/javascript">
//<![CDATA[
$(document).ready(function() {
	var noti = new GliderWiki.Notification.Perception($(".wrap-cont"), "1gCHjWTRC990fTFM3SPEElyHDJ4JMKIi").process();
});
//]]>
</script>
</js:scripts>
</body>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %><%@
taglib prefix="space" tagdir="/WEB-INF/tags/space" %>
<html>
<head>
</head>
<body>

<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">
			<c:choose>
			<c:when test="${listType == 'all'}">
				전체공간 보기
			</c:when>
			<c:when test="${listType == 'entry'}">
				 내가 가입한 공간
			</c:when>
			<c:otherwise>
				내가 개설한 공간
			</c:otherwise>
			</c:choose>
		</h2>
		<div class="body-cont space">
		<div class="all">
			<div class="box">
				<ul class="list type1">
					<space:spacelist list="${list}" listType="${listType} "/>
				</ul>
			</div>
		</div>
	</div>
	<div class="foot-cont">
	</div>
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/glider/space/spaceService.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var list = new GliderWiki.Space.List().action();
	GliderWiki.checkAccessSpace("${loginUser.weUserIdx}");
});

</script>
</js:scripts>
</body>
</html>
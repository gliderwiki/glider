<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<html>
<head>
</head>
<body>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">공간정보 보기</h2>
		<div class="body-cont space">
			<div class="view">
				<div class="box">
					<h3>제목</h3>
					<p class="tit">
						<strong>${spaceInfo.spaceName}</strong>
					</p>
				</div>
				<div class="box">
					<h3>설명</h3>
					<p>${spaceInfo.spaceDesc}
				</div>
				<div class="box">
					<h3>Privacy 정책</h3>
					<p>
						<c:choose>
						<c:when test="${spaceInfo.exposed == true}">
							공개
						</c:when>
						<c:otherwise>비공개</c:otherwise>
						</c:choose>
					</p>
				</div>
				<div class="box">
					<h3>관리자</h3>
					<p>${adminName}</p>
				</div>
				<div class="box">
					<h3>공간의 위키를 볼 수 있는 그룹</h3>
					<div class="box-group read">
						<c:choose>
						<c:when test="${spaceInfo.viewPrivacy == 'ALLGROUP'}">
							전체
						</c:when>
						<c:otherwise>
							<c:forEach var="viewGroup" items="${spaceInfo.viewParticipant}">
							<span class="group">
								${viewGroup.name}
								<a href="#" role="button" class="btn-del viewDelete" title="삭제" data-space-idx="${spaceInfo.spaceIdx}" data-group-idx="${viewGroup.idx}" data-authority-type="view">&times;</a>
							</span>
							</c:forEach>
						</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="box">
					<h3>공간의 위키를 생성, 수정할 수 있는 그룹</h3>
					<div class="box-group edit">
						<c:choose>
						<c:when test="${spaceInfo.editPrivacy == 'ALLGROUP'}">
							전체
						</c:when>
						<c:otherwise>
						<c:forEach var="editGroup" items="${spaceInfo.editParticipant}">
						<span class="group">
							${editGroup.name}
							<a href="#" role="button" class="btn-del viewDelete1" title="삭제" data-space-idx="${spaceInfo.spaceIdx}" data-user-idx="${editGroup.idx}" data-authority-type="edit">x</a>
						</span>
						</c:forEach>
						</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="box">
					<h3>이미지</h3><!-- 이미지가 없을 경우 기본이미지로 대체 -->
					<img src="/resource/real${spaceInfo.profileImg}" onerror="this.src='/resource/glider/front/images/default_big.jpg';">
				</div>
			</div>
		</div>
		
		<div class="foot-cont">
			<a href="/space/main/${we_space_idx}" class="btn">공간메인목록</a>
		</div>


	</div>
</section>
<js:scripts>
<script type="text/javascript" src="/resource/glider/space/spaceService.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var write = new GliderWiki.Space.Show().action();
});

</script>
</js:scripts>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %><%@
taglib prefix="common" tagdir="/WEB-INF/tags/common" %>
<body>
<section class="contents " role="main">
	<div class="wrap-cont">
		<h2 class="tit-cont">검색결과 목록조회</h2>

		<div class="body-cont taglist">
			<div class="col-tag">
				<h3>검색결과 목록</h3>

				<div class="box-tag">
				<c:choose>
				<c:when test="${wikiSize == 0 }">
					<b><u>${wiki_text }</u></b>의 검색 결과가 없습니다. 
				</c:when>
				<c:otherwise>
					<b><u>${wiki_text }</u></b>의 검색 결과 ${wikiSize } 개의 위키가 검색 되었습니다. 
				</c:otherwise>
				</c:choose>
				</div>
			</div>


			<c:if test="${weSpaceList != null }">
			<c:forEach items="${weSpaceList }" var="weSpaceList" varStatus="index">
			<div class="box">

				<h3 class="tit-sec">${weSpaceList.spaceName }</h3>
				<ul class="list type1">
					<c:forEach items="${wikiList }" var="wikiList" varStatus="idx">
					<c:if test="${wikiList.weSpaceIdx eq weSpaceList.spaceIdx }">
					<li>
						<div class="title">
							<a href="/wiki/${wikiList.weWikiIdx}">${wikiList.weWikiTitle}</a>
						</div>
						<div class="user">
							<a href="#" class="name textCenter">${wikiList.weUserNick}</a>
						</div>
						<div class="time textCenter">
							${wikiList.weInsDate}
						</div>
						<div class="views textCenter">
							${wikiList.weWikiViewCnt}
						</div>
					</li>
					</c:if>
					</c:forEach>
				</ul>
			</div>

			</c:forEach>
			</c:if>
		</div>
	</div>
</section>

</body>

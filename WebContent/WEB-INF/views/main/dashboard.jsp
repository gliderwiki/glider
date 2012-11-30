<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %><%@
taglib prefix="space" tagdir="/WEB-INF/tags/space" %><%@
taglib prefix="common" tagdir="/WEB-INF/tags/common" %>

<body>
<section class="contents dashboard" role="main">
<div class="wrap-cont">
	<div class="body-cont">
	<h2 class="symbol-dashboard">대시보드</h2>
	<div class="col-2">
		<div class="col-main">
			<div class="box">
				<h3 class="tit-sec">최근 위키 목록</h3>
				<ul class="list type1" id="recentWiki">
					<common:wikilist wikiList="${recentWikiList}" />
				</ul>
				<c:if test="${loginUser.guest eq false}">
				<div class="box-expand">
					<a style="cursor:pointer" id="getRecent" rold="button" class="ico expand" title="펼치기">펼치기</a>
					<a style="cursor:pointer" id="setRecent" rold="button" class="ico unexpand" title="접기">접기</a>
				</div>
				</c:if>
				
			</div>

			<div class="box">
				<h3 class="tit-sec">최근 생성된 공간</h3>
				<ul class="list type1" id="recentSpace">
					<space:spacelist list="${recentSpaceList}" listType="all" />
				</ul>
				<c:if test="${loginUser.guest eq false}">
				<div class="box-expand">
					<a style="cursor:pointer" id="getSpace" rold="button" class="ico expand" title="펼치기">펼치기</a>
					<a style="cursor:pointer" id="setSpace" rold="button" class="ico unexpand" title="접기">접기</a>
				</div>
				</c:if>
			</div>

			<div class="box">
				<h3 class="tit-sec">전체 위키 - 총 공간 ${allSpaceCount}개, 위키 ${allWikiCount}개</h3>
				<ul class="list type1" id="allWiki">
					<common:wikilist wikiList="${allWikiList}" />
				</ul>
				<c:if test="${loginUser.guest eq false}">
				<div class="box-expand">
					<a style="cursor:pointer" id="getAll" rold="button" class="ico expand" title="펼치기">펼치기</a>
					<a style="cursor:pointer" id="setAll" rold="button" class="ico unexpand" title="접기">접기</a>
				</div>
				</c:if>
			</div>

		</div>
		<div class="col-sub">
			<div class="box">
				<h3 class="tit-sec">최근 공지사항</h3>
				<ul class="list type1" id="recentNotice">
					<c:choose>
					<c:when test="${not empty myNotiList}">
					<c:forEach var="list" items="${myNotiList}">
					<li>
						<div class="title">
							<a href="/space/${list.we_space_idx}/board/${list.we_bbs_idx}">${list.we_bbs_title}</a>
						</div>
						<div class="time textRight">
							${list.we_ins_date}
						</div>
					</li>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<div class="box-empty">최근 공지사항이 없습니다.</div>
					</c:otherwise>
					</c:choose>
				</ul>
				<c:if test="${loginUser.guest eq false}">
				<div class="box-expand">
					<a style="cursor:pointer" id="getNotice" rold="button" class="ico expand" title="펼치기">펼치기</a>
					<a style="cursor:pointer" id="setNotice" rold="button" class="ico unexpand" title="접기">접기</a>
				</div>
				</c:if>
								
			</div>
			<div class="box paddingTop-5">
				<h3 class="tit-sec">최근 업데이트</h3>
				<ul class="list type3" id="recentUpdate">
					<space:updatedlist list="${updatedList}" />
				</ul>
				
				<c:if test="${loginUser.guest eq false}">
				<div class="box-expand">
					<a style="cursor:pointer" id="getUpdate" rold="button" class="ico expand" title="펼치기">펼치기</a>
					<a style="cursor:pointer" id="setUpdate" rold="button" class="ico unexpand" title="접기">접기</a>
				</div>
				</c:if>
			</div>
			<div class="box">
				<h3 class="tit-sec">랭킹</h3>

				<h4>베스트 공감 Top 5</h4>
				<ul class="list type2 paddingTop-10" >
					<c:choose>
					<c:when test="${not empty agreeList}">
					<c:forEach var="list" items="${agreeList}">
					<li>
						<div class="title">
							<a href="/wiki/${list.we_wiki_idx}">${list.we_wiki_title}</a>
						</div>
						<div class="info">
							${list.we_wiki_agree}건
						</div>
					</li>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<div class="box-empty-rank paddingTop-10">베스트 공감 내역이 없습니다.</div>
					</c:otherwise>
					</c:choose>
				</ul>

				<h4 >베스트 즐겨찾기  Top 5</h4>
				<ul class="list type2 paddingTop-10">

					<c:choose>
					<c:when test="${not empty favorList}">
					<c:forEach var="list" items="${favorList}">
					<li>
						<div class="title">
							<a href="/wiki/${list.we_wiki_idx}">${list.we_wiki_title}</a>
						</div>
						<div class="info">
							${list.cnt}건
						</div>
					</li>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<div class="box-empty-rank paddingTop-10">베스트 즐겨찾기  내역이 없습니다.</div>
					</c:otherwise>
					</c:choose>

				</ul>
				<!-- 10포인트 이상만 출력한다.  -->
				<h4>베스트 활동포인트  Top 5</h4>
				<ul class="list type2 paddingTop-10">

					<c:choose>
					<c:when test="${not empty userPointList}">
					<c:forEach var="list" items="${userPointList}">
					<li>
							<div class="title">
								<a href="#" class="tooltip" data-user-idx="${list.we_user_idx}">${list.we_user_nick}</a>
							</div>
							<div class="info">
								${list.we_point}점
							</div>
						</li>
					</c:forEach>
					</c:when>
					<c:otherwise>
					<div class="box-empty-rank paddingTop-10">회원 포인트 내역이 없습니다.</div>
					</c:otherwise>
					</c:choose>

				</ul>
				
			</div>
		</div>
	</div>
	<div class="col-tag">
		<h3>Keyword 목록</h3>
		<div class="box-tag">
			<common:taglist taglist="${tagVoList}" />
		</div>
	</div>
</div>
<div class="foot-cont">
</div>
</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/interface/WikiFunctionService.js'></script>
<script type="text/javascript" src="/resource/glider/main/mainService.js"></script>
<script type="text/javascript" src="/resource/libs/jquery/jquery.dateFormat-1.0.js"></script>


<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		var main = new GliderWiki.Main.DashBoard($(".wrap-cont"),"${loginUser.weUserIdx}").action();
		GliderWiki.checkAccessSpace("${loginUser.weUserIdx}");
	});
//]]>
</script>
</js:scripts>
</body>
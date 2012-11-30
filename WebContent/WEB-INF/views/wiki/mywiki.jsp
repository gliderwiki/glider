<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<section class="contents " role="main">
	<div class="wrap-cont">
		
		<h2 class="tit-cont">내 위키 목록 </h2>
				
		<form action="/">
			<div class="body-cont control">
			
				<c:choose>
				<c:when test="${spaceInfoList eq null}">
				<div class="sub">
					<div class="box-empty">
					내가 작성한 위키가 없습니다.
					</div>
				</div>
				</c:when>
				<c:otherwise>
				<c:forEach items="${spaceInfoList }" var="spaceInfoList" varStatus="stat">
				<h3 class="tit-sec"><em>${spaceInfoList.weSpaceName}</em></h3>
				<table class="history">
					<colgroup>
						<col style="width:auto;" />
						<col style="width:150px;" />
						<col style="width:70px;" />
						<col style="width:70px;" />
						<col style="width:70px;" />
					</colgroup>
					<thead>
						<tr>
							<th>위키제목</th>
							<th>작성일 </th>
							<th>공감수</th>
							<th>조회수</th>
							<th>버전</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${wikiLogVoList }" var="wikiLogVoList" varStatus="i">
						<c:if test="${spaceInfoList.weSpaceIdx == wikiLogVoList.weSpaceIdx }">
						<tr>
							<td class="subject"  style="padding-left:10px"><a href="/wiki/${wikiLogVoList.weWikiIdx}">${wikiLogVoList.weWikiTitle}</a></td>
							<td>${wikiLogVoList.weInsDate }</td>
							<td>${wikiLogVoList.weWikiAgree }</td>
							<td>${wikiLogVoList.weWikiViewCnt }</td>
							<td>v.${wikiLogVoList.weWikiRevision }</td>							
						</tr>
						</c:if>
					</c:forEach>
					</tbody>
				</table>
				<div style="padding-top:30px"></div>
				</c:forEach>
				</c:otherwise>
				</c:choose>
			</div>
			
		</form>

		
	</div>
</section>

<js:scripts>
<script type="text/javascript" src="/resource/libs/jquery/jquery.form.js"></script>

<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		
	});	
//]]>
</script>
</js:scripts>

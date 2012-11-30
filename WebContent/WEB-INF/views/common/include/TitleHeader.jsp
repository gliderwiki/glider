<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<!-- 타이틀메뉴[S] -->
<div class="sub">
	<div class="wrap-cont">
		<ul class="navi">
			<c:forEach items="${titleList }" var="titleList" varStatus="idx">
			<c:choose>
				<c:when test="${spaceInfo.spaceIdx != null and spaceInfo.spaceIdx != ''}">
					<c:if test="${titleList.we_menu_group eq 'SPACE'}">
					<li>
						<a href="${titleList.we_menu_url}" name="title_menu" id="title_${titleList.we_menu_idx}" title="${titleList.we_menu_name}" url="${titleList.we_menu_url }">${titleList.we_menu_name}</a>
						<input type="hidden" id="input_title_${titleList.we_menu_idx}" value="${titleList.we_menu_idx}">
					</li>
					</c:if>
					<li class="bar"> | </li>
				</c:when>
				<c:when test="${weWiki.we_wiki_idx != null and weWiki.we_wiki_idx != ''}">
					<c:if test="${titleList.we_menu_group eq 'WIKI'}">
					<li>
						<a href="${titleList.we_menu_url}" name="title_menu" id="title_${titleList.we_menu_idx}" title="${titleList.we_menu_name}" url="${titleList.we_menu_url }">${titleList.we_menu_name}</a>
						<input type="hidden" id="input_title_${titleList.we_menu_idx}" value="${titleList.we_menu_idx}">
					</li>
					</c:if>
					<li class="bar"> | </li>
				</c:when>
			</c:choose>
			
			</c:forEach>			
			<c:choose>
				
				
				<c:when test="${loginUser.weUserIdx != null and loginUser.weUserIdx != 0}">
					<c:if test="${menuAttr eq 'default' }">
					<li>
						<a href="javascript:void(null);" name="title_menu" id="title_35" title="위키마크업활용" url="javascript:void(null);">위키마크업활용</a>
						<input type="hidden" id="input_title_35" value="35">
					</li>
					<li class="bar"> | </li>
					</c:if>
					<li>
						<a href="/logout" title="로그아웃">로그아웃</a>
					</li>
				</c:when>
				<c:otherwise>
					<c:if test="${menuAttr eq 'default' }">
					<li>
						<a href="javascript:void(null);" name="title_menu" id="title_35" title="위키마크업활용" url="javascript:void(null);">위키마크업활용</a>
						<input type="hidden" id="input_title_35" value="35">
					</li>
					<li class="bar"> | </li>
					</c:if>
					
					<li>
						<a href="/join" title="회원가입">회원가입</a>
					</li>
					<li class="bar"> | </li>
					<li>
						<a href="/index" title="로그인">로그인</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<!-- 시스템메뉴[S] -->
<c:if test="${type eq 'S'}">
<ul class="navi">
	<c:forEach items="${systemList }" var="systemList" varStatus="idx">
	<li>
		<a href="#">${systemList.we_menu_name}</a>
	</li>
	<c:choose>
		<c:when test="${idx.last}"></c:when>
		<c:otherwise><li class="bar"> | </li></c:otherwise>
	</c:choose>
	</c:forEach>
</ul>
</c:if>
<!-- 시스템메뉴[E] -->

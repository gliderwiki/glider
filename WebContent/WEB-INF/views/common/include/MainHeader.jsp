<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/include/tags.jspf" %>
<!-- 메인메뉴[S] -->
<c:if test="${type eq 'M'}">
<ul class="navi">
	<c:forEach items="${mainList }" var="mainList" varStatus="idx">
	<li>
		<a href="${mainList.we_menu_url}" class="mainMenu" name="head_menu" id="head_${mainList.we_menu_idx}" title="${mainList.we_menu_group}" data-menu-name="${mainList.we_menu_name}">${mainList.we_menu_name}</a>
		<input type="hidden" id="input_head_${mainList.we_menu_idx}" value="${mainList.we_menu_idx}">
	</li>
	<c:choose>
		<c:when test="${idx.last}"></c:when>
		<c:otherwise><li class="bar"> | </li></c:otherwise>
	</c:choose>
	</c:forEach>
</ul>
</c:if>
<!-- 메인메뉴[E] -->
<js:scripts>

<script type="text/javascript">
$(document).ready(function() {
	$(".mainMenu").on("click", function(e) {
		e.preventDefault();

		var menuName = $(this).data("menuName");
		var goUrl = $(this).attr("href");

		if(menuName =="공간생성하기") {
			if(!GliderWiki.Web.isLogined()) {
				GliderWiki.alert("확인창","로그인을 해주세요.");
				return false;
			}

			$.ajax({
				url: "/space/createSpaceCheck",
				type: "get",
				success: function(data) {
					if(data == "SUCCESS") {
						location.href = goUrl;
					}
				}
			});
		}
	});
});

</script>
</js:scripts>



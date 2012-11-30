<%@tag language="java" pageEncoding="UTF-8"
%><%@tag body-content="empty"
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"
%><%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%><%@attribute name="list" required="true" rtexprvalue="true" type="java.lang.Object" description="공간리스트객체"
%><%@attribute name="listType" required="true" rtexprvalue="true" type="java.lang.String" description="리스트타입"
%>
<c:choose>
<c:when test="${not empty list}">
<c:forEach var="list" items="${list}">
<li>
	<div class="title"> 
		<a style="cursor:pointer" class="checkAccessBtn" data-space-idx="${list.we_space_idx}" data-space-name="${list.we_space_name}" data-authority-type="${list.we_view_privacy}" data-admin-user="${list.we_ins_user}">${gf:cut(list.we_space_name,40)}</a>
	</div>

	<div class="time textCenter">
		${gf:articleDate(list.we_ins_date,'yyyy.MM.dd')}
	</div>
	<div class="user textCenter">
		<a style="cursor:pointer" class="name tooltip" data-user-idx="${list.we_ins_user}">${list.we_user_nick}</a>
	</div>
	<c:if test="${not empty list.wikiCnt }">
	<div class="active textCenter">
		글 ${list.wikiCnt}
	</div>
	</c:if>
	<!-- 멤버공개, 구성원 공개 -->
	<div class="info textCenter">
		<!-- 게스트일 경우 기본 아이콘 출력 -->
		<c:choose>
		<c:when test="${loginUser.guest eq true}">
		<a style="cursor:pointer" class="ico unstar" title="즐겨찾기추가">즐겨찾기추가</a>
		</c:when>
		<c:when test="${loginUser.guest eq false}">
			<c:choose>
			<c:when test="${list.favorited > 0}">
				<a style="cursor:pointer" role="button" class="ico star delFavorBtn" data-space-idx="${list.we_space_idx}" data-favorite-type="SPACE"></a>
			</c:when>
			<c:otherwise>
				<a style="cursor:pointer" role="button" class="ico unstar addFavorBtn" title="즐겨찾기추가" data-favorite-type="SPACE" data-space-idx="${list.we_space_idx}">즐겨찾기제거</a>
			</c:otherwise>
			</c:choose>
		</c:when>
		</c:choose>

		<c:choose>
		<c:when test="${list.we_view_privacy == 'ALLGROUP'}">
			<img src="/resource/glider/front/images/ic_all.jpg" title="전체공개">
		</c:when>
		<c:when test="${list.we_view_privacy == 'GROUP'}">
			<a style="cursor:pointer" role="button" class="ico private" title="멤버공개">멤버공개</a>
		</c:when>
		<c:otherwise>
			<a style="cursor:pointer" role="button" class="ico mem-only" title="구성원공개">구성원공개</a>
		</c:otherwise>
		</c:choose>
	</div>
</li>
</c:forEach>
</c:when>
<c:otherwise>
<div class="box-empty">
	<c:choose>
		<c:when test="${listType == 'all'}">
			개설된 공간이 없습니다.
		</c:when>
		<c:when test="${listType == 'entry'}">
			 내가 가입한 공간이 없습니다.
		</c:when>
		<c:otherwise>
			내가 개설한 공간이 없습니다.
		</c:otherwise>
	</c:choose>
</div>
</c:otherwise>
</c:choose>
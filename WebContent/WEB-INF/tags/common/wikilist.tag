<%@tag language="java" pageEncoding="UTF-8"
%><%@tag body-content="empty"
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"
%><%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%><%@attribute name="wikiList" required="true" rtexprvalue="true" type="java.lang.Object" description="위키리스트객체"
%>
<c:choose>
<c:when test="${empty wikiList}">
<div class="box-empty">
	등록된 위키 목록이 없습니다.
</div>
</c:when>
<c:otherwise>
	<c:forEach items="${wikiList}" var="list" varStatus="stat">
    <li>
	<div class="title">
		<a style="cursor:pointer" class="checkAccessBtn" data-space-idx="${list.we_space_idx}" data-wiki-idx="${list.idx}" data-authority-type="${list.we_view_privacy}" data-admin-user="${list.we_ins_user}">${list.title}</a>
	</div>

	
	<div class="time textCenter">
		${gf:articleDate(list.insDate,'yyyy.MM.dd')}
	</div>
	<div class="user textCenter">
		<span class="name">
			<a style="cursor:pointer" role="button" class="tooltip" data-user-idx="${list.we_ins_user}">${list.we_user_nick}</a>
		</span>
	</div>

	<div class="info">
		<!-- 게스트일 경우 기본 아이콘 출력 -->
		<c:choose>
		<c:when test="${loginUser.guest eq true}">
		<a style="cursor:pointer" class="ico unstar" title="즐겨찾기추가">즐겨찾기추가</a>
		</c:when>
		<c:when test="${loginUser.guest eq false}">
			<c:choose>
				
			<c:when test="${list.favorited > 0}">
				<a style="cursor:pointer" role="button" class="ico star delFavorBtn" data-space-idx="${list.we_space_idx}" data-favorite-type="WIKI" data-wiki-idx="${list.idx}"></a>
			</c:when>
			<c:otherwise>
				<a style="cursor:pointer" role="button" class="ico unstar addFavorBtn" title="즐겨찾기추가" data-favorite-type="WIKI" data-wiki-idx="${list.idx}" data-space-idx="${list.we_space_idx}">즐겨찾기추가</a>
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
</c:otherwise>
</c:choose>
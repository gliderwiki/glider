<%@tag language="java" pageEncoding="UTF-8"
%><%@tag body-content="empty"
%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@taglib prefix="gf" uri="http://www.gliderwiki.org/gftags"
%><%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%><%@attribute name="list" required="true" rtexprvalue="true" type="java.lang.Object" description="최근업데이트리스트객체"
%>
<c:choose>
<c:when test="${not empty list}">
<c:forEach var="list" items="${list}">
<li>
	<div class="thumb">
		<img src="/resource/real${list.profileImg}" alt="" width="40px" height="40px" onerror="this.src='/resource/glider/front/images/default_img.jpg';"/>
	</div>
	<div class="user">
		<span class="name">
			<a href="#" role="button" class="tooltip" data-user-idx="${list.we_user_idx}">${list.we_user_nick}</a>
		</span>
		<span class="time">${gf:articleDate(list.we_ins_date,'yyyy.MM.dd')}</span>
	</div>
	<div class="cont">
		<div style="font-weight:bold; color : #666;"><a href="/wiki/${list.we_wiki_idx}">${gf:cut(list.we_wiki_title,23)}</a></div>
		${gf:cutAndRemoveTag(list.we_wiki_text,64)}
	</div>
</li>
</c:forEach>
</c:when>
<c:otherwise>
<div class="box-empty">
	최근 업데이트 내역이 없습니다.
</div>
</c:otherwise>
</c:choose>